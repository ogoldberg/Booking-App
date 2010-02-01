package net.turfclub

import grails.test.*

class EventTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidEvent() {
        def event = new Event(date: new Date('6/2/2009'),
                              eventTitle: 'NoiseFest',
                              sponsor: 'Schlafly',
                              cover: '$5')

        assertEquals 'NoiseFest', event.toString()
            
    }
}
