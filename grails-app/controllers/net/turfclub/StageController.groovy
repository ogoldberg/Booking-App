package net.turfclub

class StageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static navigation = [
        order:5
    ]
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stageInstanceList: Stage.list(params), stageInstanceTotal: Stage.count()]
    }

    def create = {
        def stageInstance = new Stage()
        stageInstance.properties = params
        return [stageInstance: stageInstance]
    }

    def save = {
        def stageInstance = new Stage(params)
        if (stageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'stage.label', default: 'Stage'), stageInstance.id])}"
            redirect(action: "show", id: stageInstance.id)
        }
        else {
            render(view: "create", model: [stageInstance: stageInstance])
        }
    }

    def show = {
        def stageInstance = Stage.get(params.id)
        if (!stageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
            redirect(action: "list")
        }
        else {
            [stageInstance: stageInstance]
        }
    }

    def edit = {
        def stageInstance = Stage.get(params.id)
        if (!stageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [stageInstance: stageInstance]
        }
    }

    def update = {
        def stageInstance = Stage.get(params.id)
        if (stageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (stageInstance.version > version) {
                    
                    stageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'stage.label', default: 'Stage')] as Object[], "Another user has updated this Stage while you were editing")
                    render(view: "edit", model: [stageInstance: stageInstance])
                    return
                }
            }
            stageInstance.properties = params
            if (!stageInstance.hasErrors() && stageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'stage.label', default: 'Stage'), stageInstance.id])}"
                redirect(action: "show", id: stageInstance.id)
            }
            else {
                render(view: "edit", model: [stageInstance: stageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def stageInstance = Stage.get(params.id)
        if (stageInstance) {
            try {
                stageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stage.label', default: 'Stage'), params.id])}"
            redirect(action: "list")
        }
    }
}
