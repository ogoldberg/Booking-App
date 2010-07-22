import grails.test.*

class TurfTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    // <turfclub:stages = eventData="${thisEvent}" / >
    void testEventStages() {

        def tl = new TurfTagLib()
        def testData = [
        ]

        tl.eventStages(eventData:testData)
        assertEquals 'Main Stage, Clown Lounge', tagLib.out.toString()

    }
}
