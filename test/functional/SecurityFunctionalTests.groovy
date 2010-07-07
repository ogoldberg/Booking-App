class SecurityFunctionalTests extends functionaltestplugin.FunctionalTestCase {

    def createUser(userName) {
        loginAdmin()
        click('User')
        click('New User')
        form {
            username = userName
            password = '1234567'
            passwordConfirm = '1234567'
            click 'Create'
        }
    }

    def loginAs(user, pass) {
        get('/auth/login')
        form {
            username = user
            password = pass
            click "Sign in"
        }
    }

    def loginAdmin() {
        loginAs("admin", "admin0")
    }
    
    def loginBob() {
        loginAs("bob", "admin0")
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
