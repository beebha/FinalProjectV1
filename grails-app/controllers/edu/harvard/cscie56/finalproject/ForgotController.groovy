package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * <code>ForgotController</code>
 * Controller class for Forgot Password feature
 */

class ForgotController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method clears any flash messages and redirects to the forgot view
     */

    def index()
    {
        flash.clear()
        render(view: "forgot")
    }

    /**
     * This method validates that the user exists
     * and if so resets the password to the new specified password
     */

    def reset()
    {
        // check that required params username and password present
        if (params.username?.trim() == "" || params.password?.trim() == "") {
            flash.message = "Email and/or password cannot be blank."
            render(view: "forgot", model: [forgotInstance: new User(params)])
            return
        }

        // check that user exists
        def userInstance = User.findByUsername(params.username)

        if(!userInstance) {
            flash.message = "No user of that email exists."
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        // save the new password
        userInstance.properties = params

        userInstance.save(flush: true)

        if (userInstance.hasErrors()) {
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        // re-authenticate the user
        if (springSecurityService.loggedIn &&
                springSecurityService.principal.username == userInstance.username) {
            springSecurityService.reauthenticate userInstance.username
        }

        // set message and render ciew with message
        flash.message = "Password was successfully reset. Please return to the login page and use the new credentials."
        render(view: "forgot", model: [forgotInstance: userInstance])
    }
}
