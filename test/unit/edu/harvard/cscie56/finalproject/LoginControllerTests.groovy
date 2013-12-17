package edu.harvard.cscie56.finalproject

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

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
}
