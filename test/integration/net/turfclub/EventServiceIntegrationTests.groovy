package net.turfclub

import grails.test.*
import java.util.Date

class EventServiceIntegrationTests extends GrailsUnitTestCase {
    def eventService
    def miscService

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTodaysEvents() {

        // Create event on May 6
        createDummyEvent('Should not see', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-06 21:00"))

        // Create event on May 7
        createDummyEvent('Todays Event', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-07 21:00"))

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
        createDummyEvent('Yesterdays Event', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-06 21:00"))

        // Create event on May 7
        createDummyEvent('Todays Event', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-07 21:00"))

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
        createDummyEvent('Yesterdays Event', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-06 21:00"))

        // Create event on May 7
        createDummyEvent('Todays Event', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-07 21:00"))

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
        def event1 = createDummyEvent('May 5 Event One Dude', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 21:00"))

        def event2 = createDummyEvent('Anohterhd May 5 Efvent Date', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 15:00"))

        // Create event on May 6
        createDummyEvent('May 6', 
            Date.parse("yyyy-MM-dd HH:mm", "2010-05-06 21:00"))

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
        createDummyEvent("Last Meow", 
                Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 22:00"))
        createDummyEvent("Second Event", 
                Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 03:00"))
        createDummyEvent("FOURTH WOOF", 
                Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 04:47"))
        createDummyEvent("REALLY THIRD", 
                Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 04:46"))
        createDummyEvent("First Event", 
                Date.parse("yyyy-MM-dd HH:mm", "2010-05-05 02:00"))

        // Throw in some other junk events, just for fun.
        createDummyEvent('May 6', Date.parse("yyyy-MM-dd HH:mm", "2010-05-06 21:00"))
        createDummyEvent('May 7', Date.parse("yyyy-MM-dd HH:mm", "2010-05-07 21:10"))
        createDummyEvent('May 4', Date.parse("yyyy-MM-dd HH:mm", "2010-05-04 21:12"))

        // Pretend like we're logging in at 2:00 a.m. on May 05
        def may7at2am = new GregorianCalendar(2010, Calendar.MAY, 5, 2, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(may7at2am.timeInMillis) }

        def todaysEvents = eventService.todaysEvents()

        assertEquals 5, todaysEvents.size()

        assertEquals 'first event is g00t', 'First Event', todaysEvents[0].eventTitle
        assertEquals 'second event is g00t', 'Second Event', todaysEvents[1].eventTitle
        assertEquals 'third event is g00t', 'REALLY THIRD', todaysEvents[2].eventTitle
        assertEquals 'fourth event is g00t', 'FOURTH WOOF', todaysEvents[3].eventTitle
        assertEquals 'fifth event is g00t', 'Last Meow', todaysEvents[4].eventTitle

    }

    // convenience method for creating test Events
    def createDummyEvent(eventTitle, eventDate) {
        return new Event(
            booker: ShiroUser.findByUsername("admin"),
            eventTitle: eventTitle,
            eventDate: eventDate,
            cover: 7
        ).save()
    }
}
