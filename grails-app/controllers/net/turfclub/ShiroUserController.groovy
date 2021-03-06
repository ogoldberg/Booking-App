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
        [shiroUserInstanceList: ShiroUser.listOrderByUsername(params), shiroUserInstanceTotal: ShiroUser.count()]
    }

    def changePassword = {
        def shiroUserInstance = ShiroUser.get(params.id)
        if (!shiroUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [shiroUserInstance: shiroUserInstance]
        }
    }

    def submitPassword = {
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
            if (params.password) shiroUserInstance.password = params.password
            if (params.passwordConfirm) shiroUserInstance.passwordConfirm = params.passwordConfirm

            if (!shiroUserInstance.hasErrors() && shiroUserInstance.validate()) {
                if (params.password) {
                    // We know that the password and password confirm are equal
                    // due to constraints in ShiroUser
                    // Generate a new password hash, now that we're sure that
                    // the user typed something in the password box
                    shiroUserInstance.passwordHash = new Sha1Hash(shiroUserInstance.password).toHex()
                }
                shiroUserInstance.save()
                flash.message = "Password changed for ShiroUser ${shiroUserInstance}"
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

    // Users cannot change the password through this controller action.
    // Must use the submitPassword action.
    def update = {

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

            shiroUserInstance.properties['username', 'activeUser'] = params

            // kludge the password and password confirm to avoid validation errors.
            shiroUserInstance.password = "smurf123"
            shiroUserInstance.passwordConfirm = "smurf123"
            if (!shiroUserInstance.hasErrors() && shiroUserInstance.validate()) {
                shiroUserInstance.save(flush:true)
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
            if (shiroUserInstance.events) {
                flash.message = "You can't delete a user that has booked Events."
                redirect(action: "show", params: [username:shiroUserInstance.username])
            }
            else {
                try {
                    shiroUserInstance.delete(flush: true)
                    flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
                    redirect(action: "list")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
                    redirect(action: "show", params:[username:shiroUserInstance.username])
                }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])}"
            redirect(action: "list")
        }
    }

}
