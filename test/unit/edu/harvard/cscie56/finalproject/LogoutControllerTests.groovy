package edu.harvard.cscie56.finalproject

import grails.test.mixin.TestFor


@TestFor(LogoutController)
class LogoutControllerTests
{
    void testLogout()
    {
        controller.logout()
        assert response.forwardedUrl == "/j_spring_security_logout"
    }
}
