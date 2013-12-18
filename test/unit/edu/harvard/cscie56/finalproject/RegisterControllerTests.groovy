package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(RegisterController)
@Mock([User, UserRole, Role])
class RegisterControllerTests {

    void testIndex()
    {
        controller.index()
        assert view == "/register/register"
    }

    void testUsernameBlank()
    {
        params.username = ""
        params.password = "password"

        controller.save()
        assert flash.message == "Email and/or password cannot be blank."
        assert view == "/register/register"
    }

    void testPasswordBlank()
    {
        params.username = "test@test.com"
        params.password = ""

        controller.save()
        assert flash.message == "Email and/or password cannot be blank."
        assert view == "/register/register"
    }

    void testSave()
    {
        params.username = "test@test.com"
        params.password = "password"

        controller.save()

        assert response.redirectedUrl == '/home'
    }
}
