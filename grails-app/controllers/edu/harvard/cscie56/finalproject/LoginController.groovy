package edu.harvard.cscie56.finalproject

/**
 * <code>LoginController</code>
 * Controller class for Login feature
 */

class LoginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method clears any flash messages and
     * redirects to the login view
     */

    def index()
    {
        flash.clear()
        render(view: "login")
    }

    /**
     * This method forwards the request to the spring security login
     */

    def authenticate()
    {
        request.getRequestDispatcher("/j_spring_security_check").forward(request, response)
    }

    /**
     * This method renders the view for an unsuccessful login
     */

    def loginfail()
    {
        flash.message = "Sorry, we were not able to find a user with that email and password."
        render(view: "login")
    }
}
