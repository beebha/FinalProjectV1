package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

class RegisterController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

        println "index"
        flash.clear()
        render(view: "register")
    }

    def save() {

        println "save"

        def userRole = Role.findByAuthority("ROLE_USER")?: new Role(authority:"ROLE_USER").save(failOnError:true)

        def userInstance = new User(
                username: params.username,
                password: params.password,
                enabled: true)

        if (params.username?.trim() == "" || params.password?.trim() == "") {
            println "register has validation errors"
            flash.message = "Email and/or password cannot be blank."
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        userInstance.save(flush: true)

        if (userInstance.hasErrors()) {
            println "save hasErrors"
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        println "user created successfully"

        UserRole.create userInstance, userRole

        println "user role created successfully"

        springSecurityService.reauthenticate(params.username)

        println "user reauthenticated"

        redirect(controller: "home")
    }
}
