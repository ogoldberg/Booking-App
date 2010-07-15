package net.turfclub

class AgeRestrictionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static navigation = [order:5]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ageRestrictionInstanceList: AgeRestriction.list(params), ageRestrictionInstanceTotal: AgeRestriction.count()]
    }

    def create = {
        def ageRestrictionInstance = new AgeRestriction()
        ageRestrictionInstance.properties = params
        return [ageRestrictionInstance: ageRestrictionInstance]
    }

    def save = {
        def ageRestrictionInstance = new AgeRestriction(params)
        if (ageRestrictionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), ageRestrictionInstance.id])}"
            redirect(action: "show", id: ageRestrictionInstance.id)
        }
        else {
            render(view: "create", model: [ageRestrictionInstance: ageRestrictionInstance])
        }
    }

    def show = {
        def ageRestrictionInstance = AgeRestriction.get(params.id)
        if (!ageRestrictionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
            redirect(action: "list")
        }
        else {
            [ageRestrictionInstance: ageRestrictionInstance]
        }
    }

    def edit = {
        def ageRestrictionInstance = AgeRestriction.get(params.id)
        if (!ageRestrictionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [ageRestrictionInstance: ageRestrictionInstance]
        }
    }

    def update = {
        def ageRestrictionInstance = AgeRestriction.get(params.id)
        if (ageRestrictionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ageRestrictionInstance.version > version) {
                    
                    ageRestrictionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ageRestriction.label', default: 'AgeRestriction')] as Object[], "Another user has updated this AgeRestriction while you were editing")
                    render(view: "edit", model: [ageRestrictionInstance: ageRestrictionInstance])
                    return
                }
            }
            ageRestrictionInstance.properties = params
            if (!ageRestrictionInstance.hasErrors() && ageRestrictionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), ageRestrictionInstance.id])}"
                redirect(action: "show", id: ageRestrictionInstance.id)
            }
            else {
                render(view: "edit", model: [ageRestrictionInstance: ageRestrictionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def ageRestrictionInstance = AgeRestriction.get(params.id)
        if (ageRestrictionInstance) {
            try {
                ageRestrictionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ageRestriction.label', default: 'AgeRestriction'), params.id])}"
            redirect(action: "list")
        }
    }
}
