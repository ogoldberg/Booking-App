class EventFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def loginAdmin() {
        get('/auth/login')
        form {
            username = 'admin'
            password = 'admin0'
            click "Sign in"
        }
    }
    
    void testCreateEvent() {
        loginAdmin()
        get('/event/create')
        form {
            eventDate_value = '04/20/1999'
            // you have to use a valid selection.  Since
            // selections are 4:00, 4:30, 5:00, 5:30, 6:00, 6:30 etc,
            // let's pick 4:30 (closest to 4:20 huh-huh)
            eventTime = '4:30'
            click "Create"
        }

        assertStatus 200
        assertContentContains '4:30'
        assertContentContains 'April 20, 1999'
    }

}
