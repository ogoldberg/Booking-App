package net.turfclub

import grails.test.*
import java.util.Date

class EventServiceIntegrationTests extends GrailsUnitTestCase {
    def eventService
    def miscService

    def reliableBand
    def sleazeBand

    def dummyStage

    protected void setUp() {
        super.setUp()

        reliableBand = new Band(bandName:'Reliable Band').save()
        sleazeBand = new Band(bandName:'Sleaze Band').save()

        dummyStage = new Stage(stage:'This stage').save()

    }

    protected void tearDown() {
        super.tearDown()
    }

    // We should only see events that have at least one confirmed booking
    void testTodaysEventsHaveConfirmedBookings() {
        // Create event on May 6, with non-confirmed booking
        def unconfEvent = createDummyEvent('Should not see', 
            "2010-05-06 21:00")

        // Add a NON-confirmed booking to the confEvent
        createDummyBooking(unconfEvent, sleazeBand, false)

        // Create event on May 6
        def confEvent = createDummyEvent('This Event has a Confirmed Booking', 
            "2010-05-06 21:00")

        // Add a confirmed booking to the confEvent
        createDummyBooking(confEvent, reliableBand, true)

        // Spot-check our test data
        assertEquals 2, Event.count()

        // Pretend like we're logging in at 3:00 p.m. on May 6
        def may6 = new GregorianCalendar(2010, Calendar.MAY, 6, 15, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may6.timeInMillis) }

        def events = eventService.todaysEvents()

        // We should only see one event, and that event should be 
        // 'This Event has a Confirmed Booking'
        assertEquals 1, events.size()
        assertEquals 'We only want events w/confirmed bookings', 
                     'This Event has a Confirmed Booking', 
                     events[0].eventTitle 
        
    }

    // Todays events should be on the correct day
    void testTodaysEvents() {

        // Create event on May 6
        createDummyEventAndBooking('Should not see', "2010-05-06 21:00",
            reliableBand, true)

        // Create event on May 7
        createDummyEventAndBooking('Todays Event', 
            "2010-05-07 21:00",
            reliableBand, true)

        // Pretend like we're logging in at 3:00 p.m. on May 07
        def may7 = new GregorianCalendar(2010, Calendar.MAY, 7, 15, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may7.timeInMillis) }

        def events = eventService.todaysEvents()

        assertEquals 1, events.size()
        assertEquals 'Todays event should be on todays date', 'Todays Event', events[0].eventTitle 

    }

    // We should be able to see yesterday's event, IF
    // it's earlier than 2:00 a.m.
    void testYesterdaysEvent() {

        // Create event on May 6
        createDummyEventAndBooking('Yesterdays Event', "2010-05-06 21:00")

        // Create event on May 7
        createDummyEventAndBooking('Todays Event', "2010-05-07 21:00")

        // Pretend like we're logging in at 1:59 a.m. on May 07
        // We should still see May 6's event.
        def may7at159 = new GregorianCalendar(2010, Calendar.MAY, 7, 1, 59, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may7at159.timeInMillis) }

        def events = eventService.todaysEvents()

        assertEquals 1, events.size()
        assertEquals '1:59 you should still see yesterdays event', 'Yesterdays Event', events[0].eventTitle 

    }

    // We should be see today's event, if it's later than or equal to 2:00 a.m.
    void test2am() {

        // Create event on May 6
        createDummyEventAndBooking('Yesterdays Event', 
            "2010-05-06 21:00")

        // Create event on May 7
        createDummyEventAndBooking('Todays Event', 
            "2010-05-07 21:00")

        // Pretend like we're logging in at 2:00 a.m. on May 07
        def may7at2am = new GregorianCalendar(2010, Calendar.MAY, 7, 2, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may7at2am.timeInMillis) }

        def events = eventService.todaysEvents()

        assertEquals 1, events.size()
        // We should see May 7's event
        assertEquals '2:00 am should see Todays Event', 'Todays Event', events[0].eventTitle 

    }

    void testMultipleTodaysDates() {
        // Create two events on May 5
        def event1 = createDummyEventAndBooking('May 5 Event One Dude', 
            "2010-05-05 21:00")

        def event2 = createDummyEventAndBooking('Anohterhd May 5 Efvent Date', 
            "2010-05-05 15:00")

        // Create event on May 6
        createDummyEventAndBooking('May 6', "2010-05-06 21:00")

        // Pretend like we're logging in at 11 a.m. on May 5
        def may5at11am = new GregorianCalendar(2010, Calendar.MAY, 5, 11, 0, 0)
        java.util.Date.metaClass.constructor = { -> 
            new Date(may5at11am.timeInMillis) 
        }

        def todaysEvents = eventService.todaysEvents()

        assertEquals 2, todaysEvents.size()

        def firstEvent = todaysEvents.find {
            it.id == event1.id
        }

        assertNotNull firstEvent

        def secondEvent = todaysEvents.find {
            it.id == event2.id
        }

        assertNotNull secondEvent
        assertEquals secondEvent.id, event2.id
        assertEquals "Second event title is correct", 'Anohterhd May 5 Efvent Date',
            secondEvent.eventTitle
        assertEquals 'Second event time is correct', '2010-05-05 15:00',
            secondEvent.eventDate.format('yyyy-MM-dd HH:mm')
    }

    void testSortOrderOfMultipleDates() {
        // Create 5 events each with different event times
        createDummyEventAndBooking("Last Meow", "2010-05-05 22:00")
        createDummyEventAndBooking("Second Event", "2010-05-05 03:00")
        createDummyEventAndBooking("FOURTH WOOF", "2010-05-05 04:47")
        createDummyEventAndBooking("REALLY THIRD", "2010-05-05 04:46")
        createDummyEventAndBooking("First Event", "2010-05-05 02:00")

        // Throw in some other junk events, just for fun.
        createDummyEventAndBooking('May 6', "2010-05-06 21:00")
        createDummyEventAndBooking('May 7', "2010-05-07 21:10")
        createDummyEventAndBooking('May 4', "2010-05-04 21:12")

        // Pretend like we're logging in at 2:00 a.m. on May 05
        def may5at2am = new GregorianCalendar(2010, Calendar.MAY, 5, 2, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may5at2am.timeInMillis) }

        def todaysEvents = eventService.todaysEvents()

        assertEquals 5, todaysEvents.size()

        assertEquals 'first event is g00t', 'First Event', todaysEvents[0].eventTitle
        assertEquals 'second event is g00t', 'Second Event', todaysEvents[1].eventTitle
        assertEquals 'third event is g00t', 'REALLY THIRD', todaysEvents[2].eventTitle
        assertEquals 'fourth event is g00t', 'FOURTH WOOF', todaysEvents[3].eventTitle
        assertEquals 'fifth event is g00t', 'Last Meow', todaysEvents[4].eventTitle

    }

    // When searching for dates, you need to make sure that 
    // hour / minute are considered.  If you log in at 10 p.m. on May 5
    // then the service should find all dates that are on May 5, even
    // if they started at 9:00 p.m.
    void testEventInPastButOnSameDate() {

        createDummyEventAndBooking("Event thats in 6 hours", 
                "2010-05-05 21:00")
        createDummyEventAndBooking("Event thats in 30 hours", 
                "2010-05-06 21:00")

        // Pretend like we're logging in at 10 p.m. on May 05
        // We want to see Event thats in 6 hours
        def may5at10pm = new GregorianCalendar(2010, Calendar.MAY, 5, 22, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may5at10pm.timeInMillis) }

        def todaysEvents = eventService.todaysEvents()

        assertEquals 1, todaysEvents.size()

        assertEquals 'first event is g00t', 'Event thats in 6 hours', todaysEvents[0].eventTitle

    }

    void testBookings() {
        createDummyEventAndBooking("Event thats in 6 hours", "2010-05-05 21:00")
        // Pretend like we're logging in at 10 p.m. on May 05
        def may5at10pm = new GregorianCalendar(2010, Calendar.MAY, 5, 22, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may5at10pm.timeInMillis) }

        def todaysEventsAndBookings = eventService.todaysEventsAndBookings()
        assertEquals todaysEventsAndBookings.size(), 1
        assertEquals "Event thats in 6 hours", todaysEventsAndBookings[0].event.eventTitle

        assertEquals 1, todaysEventsAndBookings[0].bookings.size()

    }

    void testHeadliner() {

        // reliable band is just opening band.
        def event = createDummyEventAndBooking("Event with headliner", 
           "2010-05-05 21:00", 
           reliableBand)

        // sleazeband is headliner
        createDummyBooking(event, sleazeBand, true, true)

        // Login on appropriate day
        def may5at10pm = new GregorianCalendar(2010, Calendar.MAY, 5, 22, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may5at10pm.timeInMillis) }

        def todaysEventsAndBookings = eventService.todaysEventsAndBookings()
        assertEquals "Event with headliner", todaysEventsAndBookings[0].event.eventTitle

        assertEquals 1, todaysEventsAndBookings[0].bookings.size()
        assertNotNull todaysEventsAndBookings[0].headliners
        assertEquals 'Sleaze Band', todaysEventsAndBookings[0].headliners[0].band.bandName

    }


    // convenience method for creating test Events
    def createDummyEvent(eventTitle, dateString) {
        return new Event(
            booker: ShiroUser.findByUsername("admin"),
            eventTitle: eventTitle,
            eventDate: Date.parse("yyyy-MM-dd HH:mm", dateString),
            cover: 7
        ).save()
    }

    def createDummyBooking(event, band, confirmed, headliner = false) {
        def b = new Booking(appearanceTime:event.eventDate, 
                           event:event, 
                           band:band, 
                           headliner:headliner,
                           confirmed:confirmed, 
                           stage:dummyStage)
        event.addToBookings(b)
    }

    // shortcut method that creates a booking to go with a new Dummy Event
    def createDummyEventAndBooking(eventTitle, eventDate, 
        bookingBand = reliableBand, 
        bookingConfirmed = true) {
        def dummyEvent = createDummyEvent(eventTitle, eventDate)
        createDummyBooking(dummyEvent, bookingBand, bookingConfirmed)

        return dummyEvent
    }

 void testFutureEventsHaveConfirmedBookings() {
        // Create event on May 6, with non-confirmed booking
        def unconfEvent = createDummyEvent('Should not see', 
            "2010-05-06 21:00")

        // Add a NON-confirmed booking to the confEvent
        createDummyBooking(unconfEvent, sleazeBand, false)

        // Create event on May 6
        def confEvent = createDummyEvent('This Event has a Confirmed Booking', 
            "2010-06-06 21:00")

         // Add a confirmed booking to the confEvent
        createDummyBooking(confEvent, reliableBand, true)

         // Create event on May 6
        def confEvent2 = createDummyEvent('Conf Featured Event', 
            "2011-06-06 21:00")
        confEvent2.featured = true
        confEvent2.save()
        // Add a confirmed booking to the confEvent
        createDummyBooking(confEvent2, reliableBand, true)

        // Spot-check our test data
        assertEquals 3, Event.count()
        // Pretend like we're logging in at 3:00 p.m. on May 6
        def may6 = new GregorianCalendar(2010, Calendar.MAY, 6, 15, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may6.timeInMillis) }

        def events = eventService.futureEvents()

        // We should only see one event, and that event should be 
        // 'This Event has a Confirmed Booking'
        assertEquals 2, events.size()
        assertEquals 'We only want events w/confirmed bookings', 
                     'This Event has a Confirmed Booking', 
                     events[0].eventTitle 

        assertEquals 'We only want events w/confirmed bookings', 
                     'Conf Featured Event', 
                     events[1].eventTitle 
        
        def featuredEvents = eventService.featuredEvents()
    
        assertEquals 'We only want events w/featured bookings', 
                     'Conf Featured Event', 
                     featuredEvents[0].eventTitle 
    
        
    }

}
