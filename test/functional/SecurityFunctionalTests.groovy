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
}
