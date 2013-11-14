package edu.harvard.cscie56.finalproject

class LoginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def authenticate() {

        println "in authenticate"

        request.getRequestDispatcher("/j_spring_security_check").forward(request, response)
    }

    def loginfail() {

        println "in loginfail"

        flash.message = "Invalid username and/or password."
        render(view: "login")
    }
}
