package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

class RegisterController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index()
    {
        flash.clear()
        render(view: "register")
    }

    def save()
    {
        def userRole = Role.findOrSaveByAuthority("ROLE_USER")

        def userInstance = new User(
                username: params.username,
                password: params.password,
                enabled: true)

        if (params.username?.trim() == "" || params.password?.trim() == "") {
            flash.message = "Email and/or password cannot be blank."
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        userInstance.save(flush: true)

        if (userInstance.hasErrors()) {
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        UserRole.create userInstance, userRole

        springSecurityService.reauthenticate(params.username)

        redirect(controller: "home")
    }
}
