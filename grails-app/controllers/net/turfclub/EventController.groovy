package net.turfclub
import grails.converters.JSON
import groovy.time.*

class EventController {

    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def miscService
    def eventService
    def userService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def eventInstanceList = 
            Event.findAllByEventDateGreaterThanEquals(new Date() - 1,
            [max:params.max, offset:params.offset])
        [eventInstanceList: eventInstanceList,
            eventInstanceTotal: Event.count()]
    }

    def eventfeed = {

        def events = Event.list().collect { event -> 
            def holdPriority = (event.holdPriority > 0) ? 'hold' + ' ' +event.holdPriority?.toString() + ' ' : ""
            def eventTitle = event.eventTitle ? event.eventTitle?.toString() + ' ' : ""
             [ 
                title:  event.booker.toString() + ' ' + holdPriority
                + eventTitle + ' ' + event.bookings.toString(),
                start: (event.eventDate.time / 1000).toLong(),
                url : createLink(controller:'event', action:'show', id:event.id),
                className: event.finalized ? ["finalized"] : "" || event.holdPriority > 0 ? ["hold"] : ""
            ]
        }

        render events as JSON
    }

    def calendar = {
        def eventInstanceList = Event.list()
        def eventInstance
        if (params.eventId) {
            eventInstance = Event.get(params.eventId)
        }
        [ eventInstanceList : eventInstanceList, eventInstance : eventInstance ]
    }

    def todaysEvent = {

        def todaysHtml = ''
        def todaysEventsAndBookings = eventService.todaysEventsAndBookings()

        if (todaysEventsAndBookings?.size() > 0) {

            todaysHtml = g.render(template:'/event/todaysEvent', 
                            model : [ todaysEventsAndBookings : todaysEventsAndBookings ])
        }
        else {
            todaysHtml = '<div class="tonight"><div class="content"><div class="title">TONIGHT</div><div class="headliner">Come have a drink</div></div>'
        }
        
        def data = [ 
            'result' : [ 
                'todaysEvent' : todaysHtml 
            ]
        ]

        if (params.callback) {
            // User wants JSONP
            render "${params.callback}(${data as JSON})"
        }
        else {
            render data as JSON
        }

    }

     def futureEvents = {
        def futureHtml = ''
        def futureEventsAndBookings = eventService.futureEventsAndBookings()

        if (futureEventsAndBookings?.size() > 0) {

            futureHtml = g.render(template:'/event/futureEvents', 
                            model : [ futureEventsAndBookings : futureEventsAndBookings ])
        }
        else {
            futureHtml = '<div class="future"><div class="content"><div class="title">Future Shows</div><div class="headliner">Come have a drink</div></div>'
        }
        
        def data = [ 
            'result' : [ 
                'futureEvents' : futureHtml 
            ]
        ]

        if (params.callback) {
            // User wants JSONP
            render "${params.callback}(${data as JSON})"
        }
        else {
            render data as JSON
        }

    }

     def pastEvents = {
        def pastHtml = ''
        def pastEventsAndBookings = eventService.pastEventsAndBookings()

        if (pastEventsAndBookings?.size() > 0) {

            pastHtml = g.render(template:'/event/pastEvents', 
                            model : [ pastEventsAndBookings : pastEventsAndBookings ])
        }
        else {
            pastHtml = '<div class="past"><div class="content"><div class="title">Past Shows</div><div class="headliner">Come have a drink</div></div>'
        }
        
        def data = [ 
            'result' : [ 
                'pastEvents' : pastHtml 
            ]
        ]

        if (params.callback) {
            // User wants JSONP
            render "${params.callback}(${data as JSON})"
        }
        else {
            render data as JSON
        }

    }



     def featuredEvents = {
        def featuredHtml = ''
        def featuredEventsAndBookings = eventService.featuredEventsAndBookings()
        println featuredEventsAndBookings

        if (featuredEventsAndBookings?.size() > 0) {

            featuredHtml = g.render(template:'/event/featuredEvents', 
                            model : [ featuredEventsAndBookings : featuredEventsAndBookings ])
        }
        else {
            featuredHtml = '<div class="featured"><div class="content"><div class="title">Featured Shows</div></div>'
        }
        
        def data = [ 
            'result' : [ 
                'featuredEvents' : featuredHtml 
            ]
        ]

        if (params.callback) {
            // User wants JSONP
            render "${params.callback}(${data as JSON})"
        }
        else {
            render data as JSON
        }

    }

    def createHtmlForEvent(eventData) {
        def output = '<div>' + eventData.event.title
    }

    def showTodaysEvent = {
        // get todays event, and create some awesome
        // html to send back.

    }

    def create = {
        println "In create method" + params
        def eventInstance = new Event()
        eventInstance.properties = params
        if (params.d) {
            eventInstance.eventDate=Date.parse("MM-dd-yyyy hh:mm", params.d + ' 21:00')
        }
        return [ eventInstance: eventInstance,
                 activeBookers: userService.activeBookers() ]

    }

    // params: 
    // eventDate_value:04/14/2010,
    // eventTime:9:00, 
    // eventAmPm:PM
    def save = {
        // TODO this is a kludge, and doesn't conform to
        // the grails spec.
        if (params.holdPriority == "null") {
            params.holdPriority = 0
        }
        def eventInstance = new Event(params)
        eventInstance.eventDate = 
                miscService.parseDate(params.remove('eventDate_value'),
                params.remove('eventTime'),
                params.remove('eventAmPm'))
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
        println "params inuupdate" + params
        // TODO this is a kludge, and the g:select is not working properly
        if (params.holdPriority == 'null') {
            params.holdPriority = 0
        }
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
                    
                    eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    [message(code: 'event.label', default: 'Event')] as Object[], 
                    "Another user has updated this Event while you were editing")
                    render(view: "edit", model: [eventInstance: eventInstance])
                    return
                }
            }

            eventInstance.properties = params
            // set eventdate explicitly, after setting properties = params.
            // because we know BEST!
            eventInstance.eventDate = 
                miscService.parseDate(
                params.remove('eventDate_value'),
                params.remove('eventTime'),
                params.remove('eventAmPm'))
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

      def featuredBooking = {
        println "params are:" + params
       def eventInstance = Event.get(params.id)
       eventInstance?.featured = (params.featured == 'true')

        render ''
    }

     def addNote = {
        def eventInstance = Event.get( params.id )

        if(!eventInstance) {
            flash.message = "Event not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            if (params.noteText) {
                eventInstance.addComment(userService.loggedInUser(), params.noteText)
                eventInstance.save()
                flash.message = "Note saved."
            }
            else {
                flash.message = "Blank notes are not allowed.  Meow."
            }
        
        }
        redirect(action:'show', id:eventInstance.id)
    }

    def updateNote = {
        def noteInstance = Comment.get( params.id )
        if (!noteInstance) {
            render "Note not found with id: " + params.id
        }
        else {
            noteInstance.body = params.noteText
            noteInstance.posterId = userService.loggedInUser()?.id
            noteInstance.save()
            render(template:"/common/showNote", model:[noteInstance:noteInstance]);
        }
    }

}
