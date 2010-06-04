package net.turfclub

import org.apache.shiro.crypto.hash.Sha1Hash

class ShiroUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static navigation = [
        group:'tabs',
        title:'User',
        order:4
    ]
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [shiroUserInstanceList: ShiroUser.list(params), shiroUserInstanceTotal: ShiroUser.count()]
    }

    def create = {
        def shiroUserInstance = new ShiroUser()
        shiroUserInstance.properties = params
        return [shiroUserInstance: shiroUserInstance]
    }

     def save = {
        def shiroUserInstance = new ShiroUser(params)
        shiroUserInstance.passwordHash = new Sha1Hash(shiroUserInstance.password).toHex()
        // Generate a new password hash
        if(!shiroUserInstance.hasErrors() && shiroUserInstance.save()) {
            
            flash.message = "ShiroUser ${shiroUserInstance.id} created"
            redirect(action:show, params:[username:shiroUserInstance.username])
        }
        else {
            render(view:'create',model:[shiroUserInstance:shiroUserInstance])
        }
     }

    def show = {
        def shiroUserInstance = ShiroUser.findByUsername(params.username)
        if (!shiroUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [shiroUserInstance: shiroUserInstance]
        }
    }

    def edit = {
        def shiroUserInstance = ShiroUser.get(params.id)
        if (!shiroUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [shiroUserInstance: shiroUserInstance]
        }
    }

    def update = {
        println "Update " + params
        def shiroUserInstance = ShiroUser.get(params.id)
        if (shiroUserInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (shiroUserInstance.version > version) {
                    
                    shiroUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'shiroUser.label', default: 'ShiroUser')] as Object[], "Another user has updated this ShiroUser while you were editing")
                    render(view: "edit", model: [shiroUserInstance: shiroUserInstance])
                    return
                }
            }
            shiroUserInstance.properties = params
            println "Validating"
            if (!shiroUserInstance.hasErrors() && shiroUserInstance.validate()) {
            println "Validated!"

                if (params.password) {
                    // We know that the password and password confirm are equal
                    // due to constraints in ShiroUser
                    // Generate a new password hash, now that we're sure that
                    // the user typed something in the password box
                    shiroUserInstance.passwordHash = new Sha1Hash(shiroUserInstance.password).toHex()
                }
                shiroUserInstance.save()
                flash.message = "ShiroUser ${params.id} updated"
                redirect(action:show, params:[username:shiroUserInstance.username])
            }
            else {
                render(view: "edit", model: [shiroUserInstance: shiroUserInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def shiroUserInstance = ShiroUser.get(params.id)
        if (shiroUserInstance) {
            try {
                shiroUserInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
    }
}
