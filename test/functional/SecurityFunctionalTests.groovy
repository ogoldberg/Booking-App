class SecurityFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def loginAdmin() {
        get('/auth/login')
        form {
            username = 'admin'
            password = 'admin0'
            click "Sign in"
        }
    }
    
    def loginBob() {
        get('/auth/login')
        form {
            username = 'bob'
            password = 'admin0'
            click "Sign in"
        }
        assertStatus 200
    }

    // Only admins are allowed to create users
    void testCreateUsers() {
        loginBob()
        redirectEnabled = false
        get('/shiroUser/create')
        assertRedirectUrlContains('/unauthorized')
        followRedirect()
        assertContentContains("You do not have")
    }

    // This test doesn't work.
    void testSuccessfulLogin() {
        loginBob()
        assertContentContains("Calendar")
        assertContentContains("Band")

    }
    // This test doesn't work either
    void testUserCanCreateEvent() {
        loginBob()
        get('/event/create')
        assertContentContains("Create Event")
        assertContentContains("Booker")
        assertContentContains("Cover")

    }
    
    void testAdminCanCreateEvent() {
        loginAdmin()
        get('/event/create')
        assertContentContains("Create Event")
        assertContentContains("Booker")
        assertContentContains("Cover")

    }
}
