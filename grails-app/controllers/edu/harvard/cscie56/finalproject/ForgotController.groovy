package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class ForgotController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index()
    {
        flash.clear()
        render(view: "forgot")
    }

    def reset()
    {
        if (params.username?.trim() == "" || params.password?.trim() == "") {
            flash.message = "Email and/or password cannot be blank."
            render(view: "forgot", model: [forgotInstance: new User(params)])
            return
        }

        def userInstance = User.findByUsername(params.username)

        if(!userInstance) {
            flash.message = "No user of that email exists."
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        userInstance.properties = params

        userInstance.save(flush: true)

        if (userInstance.hasErrors()) {
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        if (springSecurityService.loggedIn &&
                springSecurityService.principal.username == userInstance.username) {
            springSecurityService.reauthenticate userInstance.username
        }

        flash.message = "Password was successfully reset. Please return to the login page and use the new credentials."
        render(view: "forgot", model: [forgotInstance: userInstance])
    }
}
