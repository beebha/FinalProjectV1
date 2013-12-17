package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin

@TestFor(ForgotController)
@TestMixin(DomainClassUnitTestMixin)
@Mock([User, SpringSecurityService])
class ForgotControllerTests {

    void testIndex()
    {
        controller.index()
        assert view == "/forgot/forgot"
    }

    void testUsernameBlank()
    {
        params.username = ""
        params.password = "password"

        controller.reset()
        assert flash.message == "Email and/or password cannot be blank."
        assert view == "/forgot/forgot"
    }

    void testPasswordBlank()
    {
        params.username = "test@test.com"
        params.password = ""

        controller.reset()
        assert flash.message == "Email and/or password cannot be blank."
        assert view == "/forgot/forgot"
    }

    void testUserExists()
    {
        params.username = "test@test.com"
        params.password = "password"

        controller.reset()
        assert flash.message == "No user of that email exists."
        assert view == "/forgot/forgot"
    }

    void testReset()
    {
        params.username = "test@test.com"
        params.password = "newpassword"


        User user = new User(username: 'test@test.com', password: 'oldpassword')
        user.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user.save()
        mockDomain(User, [user])

        controller.reset()
        assert user.password == "newpassword"
        assert flash.message == "Password was successfully reset. Please return to the login page and use the new credentials."
        assert view == "/forgot/forgot"
    }
}
