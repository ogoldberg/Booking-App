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
        def today = new GregorianCalendar(2010, Calendar.MAY, 7, 15, 0, 0)
        java.util.Date.metaClass.constructor = { -> new Date(today.timeInMillis) }

        def events = eventService.todaysEvents()

        assertEquals 1, events.size()
        assertEquals 'Todays event should be on todays date', 'Todays Event', events[0].eventTitle 

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
