package net.turfclub

class BookingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bookingInstanceList: Booking.list(params), bookingInstanceTotal: Booking.count()]
    }

    def create = {
        def bookingInstance = new Booking()
        bookingInstance.properties = params
        return [bookingInstance: bookingInstance]
    }

    def save = {
        def bookingInstance = new Booking(params)
        if (bookingInstance.save(flush: true)) {
            flash.message = "${bookingInstance.band} added to event"
            redirect(action: "show", controller: "event", id: bookingInstance.eventDate.id)
        }
        else {
            render(view: "create", model: [bookingInstance: bookingInstance])
        }
    }

    def show = {
        def bookingInstance = Booking.get(params.id)
        if (!bookingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
            redirect(action: "list")
        }
        else {
            [bookingInstance: bookingInstance]
        }
    }

    def edit = {
        def bookingInstance = Booking.get(params.id)
        if (!bookingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [bookingInstance: bookingInstance]
        }
    }

    def update = {
        def bookingInstance = Booking.get(params.id)
        if (bookingInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bookingInstance.version > version) {
                    
                    bookingInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'booking.label', default: 'Booking')] as Object[], "Another user has updated this Booking while you were editing")
                    render(view: "edit", model: [bookingInstance: bookingInstance])
                    return
                }
            }
            bookingInstance.properties = params
            if (!bookingInstance.hasErrors() && bookingInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'booking.label', default: 'Booking'), bookingInstance.id])}"
                redirect(action: "show", id: bookingInstance.id)
            }
            else {
                render(view: "edit", model: [bookingInstance: bookingInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def bookingInstance = Booking.get(params.id)
        if (bookingInstance) {
            try {
                bookingInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), params.id])}"
            redirect(action: "list")
        }
    }
}
