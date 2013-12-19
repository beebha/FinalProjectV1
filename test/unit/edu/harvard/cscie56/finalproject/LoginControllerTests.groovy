package edu.harvard.cscie56.finalproject

import grails.test.mixin.TestFor

@TestFor(LoginController)
class LoginControllerTests
{
    void testIndex()
    {
        controller.index()
        assert view == "/login/login"
    }

    void testLoginFail()
    {
        controller.loginfail()
        assert flash.message == "Sorry, we were not able to find a user with that email and password."
        assert view == "/login/login"
    }

    void testAuthenticate()
    {
        controller.authenticate()
        assert response.forwardedUrl == "/j_spring_security_check"
    }
}
