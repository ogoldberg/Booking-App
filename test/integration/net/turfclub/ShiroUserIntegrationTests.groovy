package net.turfclub
import org.apache.shiro.crypto.hash.Sha1Hash

import grails.test.*

class ShiroUserIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTransientPassword() {

        def transientTester = new ShiroUser(username: "transientTester", 
            password : 'admin0',
            passwordConfirm : 'admin0',
            passwordHash: new Sha1Hash("admin0").toHex())
        assertTrue transientTester.validate()
        assertNotNull transientTester.save()

        def transientTesterAgain = ShiroUser.findByUsername("transientTester")
        assertNotNull transientTesterAgain
        println "password is: " + transientTesterAgain.password
        println "Confirm password is: " + transientTesterAgain.passwordConfirm
        assertNull transientTesterAgain.password
        assertNull transientTesterAgain.passwordConfirm


    }
}
