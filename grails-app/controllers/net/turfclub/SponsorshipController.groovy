package net.turfclub

class SponsorshipController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
        def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sponsorshipInstanceList: Sponsorship.list(params), sponsorshipInstanceTotal: Sponsorship.count()]
    }

    def create = {
        println "params are" + params
        def sponsorInstance = Sponsor.get(params.id)
        def sponsorshipInstance = new Sponsorship()
        sponsorshipInstance.properties = params
        return [sponsorshipInstance: sponsorshipInstance]
    }

    def save = {
        def sponsorName = params.remove("sponsor")
        def sponsor = Sponsor.findByName(sponsorName)
        if (!sponsor) {
            sponsor = new Sponsor(name: sponsorName).save()
        }
        def sponsorshipInstance = new Sponsorship(params)
        sponsorshipInstance.sponsor = sponsor
        println params
        if (sponsorshipInstance.save(flush: true)) {
            flash.message = "${sponsorshipInstance.sponsor} sponsorship added to event"
            redirect(action: "show", controller: "event", id: sponsorshipInstance.event.id)
        }
        else {
            render(view: "create", model: [sponsorshipInstance: sponsorshipInstance])
        }
    }

    def show = {
        def sponsorshipInstance = Sponsorship.get(params.id)
        if (!sponsorshipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sponsorshipInstance: sponsorshipInstance]
        }
    }

    def edit = {
        def sponsorshipInstance = Sponsorship.get(params.id)
        if (!sponsorshipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sponsorshipInstance: sponsorshipInstance]
        }
    }

    def update = {
        def sponsorshipInstance = Sponsorship.get(params.id)
        if (sponsorshipInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sponsorshipInstance.version > version) {
                    
                    sponsorshipInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sponsorship.label', default: 'Sponsorship')] as Object[], "Another user has updated this Sponsorship while you were editing")
                    render(view: "edit", model: [sponsorshipInstance: sponsorshipInstance])
                    return
                }
            } 
            def sponsorName = params.remove("sponsor")
            def sponsor = Sponsor.findByName(sponsorName)
            if (!sponsor) {
                sponsor = new Sponsor(name: sponsorName).save()
            }
            sponsorshipInstance.sponsor = sponsor

            sponsorshipInstance.properties = params
            if (!sponsorshipInstance.hasErrors() && sponsorshipInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])}"
                redirect(action: "show", id: sponsorshipInstance.id)
            }
            else {
                render(view: "edit", model: [sponsorshipInstance: sponsorshipInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sponsorshipInstance = Sponsorship.get(params.id)
        if (sponsorshipInstance) {
            try {
                sponsorshipInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])}"
            redirect(action: "list")
        }
    }
}
