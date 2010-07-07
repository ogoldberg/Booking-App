package net.turfclub

import grails.test.*

class ShiroUserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTrannyFields() {
        assertTrue 'Do not persist password', 
            ShiroUser.transients.contains('password')

        assertTrue 'Do not persist password confirmation', 
            ShiroUser.transients.contains('passwordConfirm')
    }
}
