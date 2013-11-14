package edu.harvard.cscie56.finalproject

class LoginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def authenticate() {

        println "in authenticate"
        flash.message = ""
        request.getRequestDispatcher("/j_spring_security_check").forward(request, response)
    }

    def loginfail() {

        println "in loginfail"

        flash.message = "Sorry, we were not able to find a user with that email and password."
        render(view: "login")
    }
}
