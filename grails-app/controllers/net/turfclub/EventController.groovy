package net.turfclub
import grails.converters.JSON


class EventController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.findAllByEventDateGreaterThanEquals(new Date()),
            eventInstanceTotal: Event.count()]
    }

    def eventfeed = {

        def events = Event.list().collect { event ->
             [
                title: event.booker.toString() + ' ' + event.bookings.toString(),
                start: (event.eventDate.time / 1000).toLong(),
                url : createLink(controller:'event', action:'show', id:event.id),
                className: event.finalized ? ["finalized"] : ""
            ]
        }

        render events as JSON
    }

    def create = {
        println "In create method" + params
        def eventInstance = new Event()
        eventInstance.properties = params
        if (params.d) {
            eventInstance.eventDate=Date.parse("MM-dd-yyyy hh:mm", params.d + ' 21:00')
        }
        return [eventInstance: eventInstance]

    }

    def save = {
        def eventInstance = new Event(params)
        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "show", id: eventInstance.id)
        }
        else {
            render(view: "create", model: [eventInstance: eventInstance])
        }
    }

    def show = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
            [eventInstance: eventInstance]
        }
    }

    def edit = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [eventInstance: eventInstance]
        }
    }

    def update = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
                    
                    eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
                    render(view: "edit", model: [eventInstance: eventInstance])
                    return
                }
            }
            eventInstance.properties = params
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
                redirect(action: "show", id: eventInstance.id)
            }
            else {
                render(view: "edit", model: [eventInstance: eventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            try {
                eventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def confirmBooking = {
        println "params are:" + params
       def bookingInstance = Booking.get(params.id)
       bookingInstance?.confirmed = (params.confirmed == 'true')
        
        render ''
    }

    def finalizeBooking = {
        println "params are:" + params
       def eventInstance = Event.get(params.id)
       eventInstance?.finalized = (params.finalized == 'true')

        render ''
    }

    
}
