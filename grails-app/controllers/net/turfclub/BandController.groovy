package net.turfclub
import grails.converters.JSON

class BandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static navigation = [
        order:2
    ]
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bandInstanceList: Band.list(params), bandInstanceTotal: Band.count()]
    }

    def bandNames = {
        println "band names params" + params
        println "I'm being called" + new Date()
        println "letter is " + params["q"]
        def bandList = Band.findByBandNameIlike(params.q +"%").collect {
            it.bandName
        }

        render bandList.join("\n")


    }
    def create = {
        def bandInstance = new Band()
        bandInstance.properties = params
        return [bandInstance: bandInstance]
    }

    def save = {
        def bandInstance = new Band(params)
        if (bandInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'band.label', default: 'Band'), bandInstance.id])}"
            redirect(action: "show", id: bandInstance.id)
        }
        else {
            render(view: "create", model: [bandInstance: bandInstance])
        }
    }

    def show = {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bandInstance: bandInstance]
        }
    }

    def edit = {
        def bandInstance = Band.get(params.id)
        if (!bandInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [bandInstance: bandInstance]
        }
    }

    def update = {
        def bandInstance = Band.get(params.id)
        if (bandInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bandInstance.version > version) {
                    
                    bandInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'band.label', default: 'Band')] as Object[], "Another user has updated this Band while you were editing")
                    render(view: "edit", model: [bandInstance: bandInstance])
                    return
                }
            }
            bandInstance.properties = params
            if (!bandInstance.hasErrors() && bandInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'band.label', default: 'Band'), bandInstance.id])}"
                redirect(action: "show", id: bandInstance.id)
            }
            else {
                render(view: "edit", model: [bandInstance: bandInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bandInstance = Band.get(params.id)
        if (bandInstance) {
            try {
                bandInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])}"
            redirect(action: "list")
        }
    }
}
