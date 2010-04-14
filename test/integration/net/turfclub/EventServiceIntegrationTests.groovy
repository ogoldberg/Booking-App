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
