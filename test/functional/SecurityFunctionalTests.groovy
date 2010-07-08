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

    // This is a sneaky test.  
    void testCannotSubmitOthersPasswords() {

        // Create a user as admin, and record their user ID
        loginAdmin()
        createUser('SallyPassword')
        def userIdElement = byId("id")
        def newUsersId = userIdElement.getAttribute("value")

        // Now, log in as bob, who's a spy.
        // He knows SallyPassword's ID
        loginBob()
        click('User')
        click('bob')
        // Bob acts like he's changing his own password,
        form {
            click('Change Password')
        }

        // Except he inserts SallyPassword's ID in the ID form
        form {
            id = newUsersId
            password = "1234evil"
            passwordConfirm = "1234evil"
            click "Update"
        }

        // Now, bob tries to log in as Sally w/new password
        loginAs("SallyPassword", "1234evil")
        assertContentContains("Invalid username and/or password")

    }
}
