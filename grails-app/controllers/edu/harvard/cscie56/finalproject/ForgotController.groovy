package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class ForgotController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

        println "index"
        flash.clear()
        render(view: "forgot")
    }

    def reset() {
        println "reset"

        if (params.username?.trim() == "" || params.password?.trim() == "") {
            println "reset has validation errors"
            flash.message = "Email and/or password cannot be blank."
            render(view: "forgot", model: [forgotInstance: new User(params)])
            return
        }

        def userInstance = User.findByUsername(params.username)

        if(!userInstance) {
            println "user does not exist in reset"
            flash.message = "No user of that email exists."
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        userInstance.properties = params

        userInstance.save(flush: true)

        if (userInstance.hasErrors()) {
            println "reset hasErrors"
            render(view: "forgot", model: [forgotInstance: userInstance])
            return
        }

        println "password reset successfully"

        if (springSecurityService.loggedIn &&
                springSecurityService.principal.username == userInstance.username) {
            springSecurityService.reauthenticate userInstance.username
        }

        println "user reauthenticated"

        flash.message = "Password was successfully reset. Please return to the login page and use the new credentials."
        render(view: "forgot", model: [forgotInstance: userInstance])
    }
}
