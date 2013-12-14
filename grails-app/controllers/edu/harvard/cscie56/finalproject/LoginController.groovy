package edu.harvard.cscie56.finalproject

/**
 * <code>LoginController</code>
 * Controller class for Login feature
 */

class LoginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index()
    {
        flash.clear()
        render(view: "login")
    }

    def authenticate()
    {
        request.getRequestDispatcher("/j_spring_security_check").forward(request, response)
    }

    def loginfail()
    {
        flash.message = "Sorry, we were not able to find a user with that email and password."
        render(view: "login")
    }
}
