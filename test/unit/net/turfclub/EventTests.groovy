package net.turfclub

import grails.test.*

class EventTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEventToString() {
        def event = new Event(eventDate: new Date('6/2/2009'),
                              eventTitle: 'NoiseFest',
                              cover: '5')
        assertEquals 'June 2, 2009', event.toString()
    }
}
