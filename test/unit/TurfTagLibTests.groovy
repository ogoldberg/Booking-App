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

        def bookings = [
            [ stage : 'Main Stage' ], 
            [ stage : 'Main Stage' ], 
            [ stage : 'Clown Lounge' ]
        ]

        def headliners = [
            [ stage : 'Foo Stage' ], 
            [ stage : 'Clown Lounge' ]
        ]

        def testData = [
            bookings : bookings,
            headliners : headliners
        ]

        assertEquals 2, testData.headliners.size() 

        tl.eventStages(eventData:testData)
        assertEquals 'Main Stage, Clown Lounge, Foo Stage',
                     tagLib.out.toString()

    }
}
