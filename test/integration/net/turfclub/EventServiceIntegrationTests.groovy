package net.turfclub

import grails.test.*

class EventServiceIntegrationTests extends GrailsUnitTestCase {
    def eventService
    def miscService

    protected void setUp() {
        super.setUp()

        def e2 = new Event(
            booker: ShiroUser.findByUsername("admin"),
            eventTitle:'Todays Event',
            eventDate: new Date(),
            cover: 7
        ).save()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTodaysEvents() {
        def events = eventService.todaysEvents()
        assertNotNull events
        assertEquals 1, events.size()
        assertEquals 'Find todays event', 'Todays Event', events[0].eventTitle 

    }

    // Show events that are ongoing, but started the day before.
    void testEventYesterday() {
        def yesterday = new Date() - 1

        def e3 = new Event(
            booker: ShiroUser.findByUsername("admin"),
            eventTitle:'Yesterdays Event',
            eventDate: miscService.parseDate(
                yesterday.format('MM/dd/yyyy'),
                '9:00',
                'PM'),
            cover: 7
        ).save()

        assertNotNull e3
        assertTrue e3.eventDate.format('MM/dd/yyyy') == '04/12/2010'        

        def events = eventService.todaysEvents()
        assertNotNull events
        assertEquals 1, events.size()
        assertTrue events.find {
            it.eventTitle == 'Todays Event'
        }

        assertEquals 'Find todays event', 'Todays Event', events[0].eventTitle 

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
