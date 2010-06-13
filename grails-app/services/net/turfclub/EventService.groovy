package net.turfclub

class EventService {

    boolean transactional = true

    /* Return todaysEvent and Bookings, in this format
        [
          event : foo event,
          headliner : foo booking,
          bookings : [ bar booking, baz booking ]
        ],

        [  
          event : nate event,
          headliner : rusty booking,
          bookings : [ the plugs booking, baz booking ]
        ]
    */

    def todaysEventsAndBookings() {
        // get our list of todaysEvents
        def confEvents = todaysEvents()

        def todaysEventsAndBookings = []
        confEvents.each { confEvent ->
            def individualEventMap = [ event : confEvent, headliners : [], bookings : [] ]

            def confBookings = confEvent.bookings.findAll {
                it.confirmed == true
            }

            // loop through conf bookings
            // if booking.headliner == true, then set headliner
            // else add booking to the bookings list
            confBookings.sort{it.appearanceTime}.reverse().each { confBooking ->
                if (confBooking.headliner) {
                     individualEventMap.headliners.add confBooking
                }
                else {
                    individualEventMap.bookings.add confBooking
                }
            }

            todaysEventsAndBookings.add(individualEventMap)
        }

        return todaysEventsAndBookings

    }

    def todaysEvents() {
        def today = new Date()
        // Unfortunately, we have to use the lame Calendar to figure
        // out what the hour of the day is :-(
        def cal = Calendar.instance
        cal.setTime(today)
        
        // If it's earlier than 2 a.m. right now, then back up one day
        if (cal.get(Calendar.HOUR_OF_DAY) < 2) {
            today = today - 1
        }

        // remove hour/min from today, so if we search for
        // todays events, we're not limited to events that are after today.hour
        def todayAt12am = Date.parse('yyyy-MM-dd', today.format('yyyy-MM-dd'))
        def todaysEvents = Event.findAllByEventDateBetween(todayAt12am, (todayAt12am + 1)).sort {
            it.eventDate
        }

        def confEvents = []
        todaysEvents.each { 
            if(it.bookings.find { booking -> booking.confirmed == true }) {
                confEvents.add(it)
            }
        }

        return confEvents

    }

     def futureEventsAndBookings() {
        // get our list of futureEvents
        def confEvents = futureEvents()

        def futureEventsAndBookings = []
        confEvents.each { confEvent ->
            def individualEventMap = [ event : confEvent, headliners : [], bookings : [] ]

            def confBookings = confEvent.bookings.findAll {
                it.confirmed == true
            }

            // loop through conf bookings
            // if booking.headliner == true, then set headliner
            // else add booking to the bookings list
            confBookings.sort{it.appearanceTime}.each { confBooking ->
                if (confBooking.headliner) {
                     individualEventMap.headliners.add confBooking
                }
                else {
                    individualEventMap.bookings.add confBooking
                }
            }

            futureEventsAndBookings.add(individualEventMap)
        }

        return futureEventsAndBookings

    }
    
     def futureEvents() {
        def today = new Date()
        // Unfortunately, we have to use the lame Calendar to figure
        // out what the hour of the day is :-(
        def cal = Calendar.instance
        cal.setTime(today)
        
        // If it's earlier than 2 a.m. right now, then back up one day
        if (cal.get(Calendar.HOUR_OF_DAY) < 2) {
            today = today - 1
        }

        // remove hour/min from today, so if we search for
        // todays events, we're not limited to events that are after today.hour
        def todayAt12am = Date.parse('yyyy-MM-dd', today.format('yyyy-MM-dd'))
        def futureEvents = Event.findAllByEventDateGreaterThan(todayAt12am).sort {
            it.eventDate
        }

        def confEvents = []
        futureEvents.each { 
            if(it.bookings.find { booking -> booking.confirmed == true }) {
                confEvents.add(it)
            }
        }

        return confEvents

    }

 def featuredEvents() {
    def futureEvents = futureEvents() 
    def featuredEvents = futureEvents.findAll {
        it.featured == true
    }

    return featuredEvents
 }

  def eventDataFeed(events) {

        def eventDataFeed = []
        events.each { confEvent ->
            println "ConfEvent" + confEvent
            def individualEventMap = [ event : confEvent, headliners : [], bookings : [] ]

            def confBookings = confEvent.bookings.findAll {
                it.confirmed == true
            }

            // loop through conf bookings
            // if booking.headliner == true, then set headliner
            // else add booking to the bookings list
            confBookings.sort{it.appearanceTime}.each { confBooking ->
                if (confBooking.headliner) {
                     individualEventMap.headliners.add confBooking
                }
                else {
                    individualEventMap.bookings.add confBooking
                }
            }
            eventDataFeed.add(individualEventMap)
        }
        return eventDataFeed

    }

  def featuredEventsAndBookings() {
      def featuredEvents = featuredEvents()
      return eventDataFeed(featuredEvents)
  }
}
