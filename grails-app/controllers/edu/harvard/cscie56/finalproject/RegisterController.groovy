package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

/**
 * <code>RegisterController</code>
 * Controller class for Register User feature
 */

class RegisterController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method clears any flash messages and
     * redirects to the register view
     */

    def index()
    {
        flash.clear()
        render(view: "register")
    }

    /**
     * This method creates a user, logs them in and redirects to the home view of the application
     */

    def save()
    {
        // define the role
        def userRole = Role.findOrSaveByAuthority("ROLE_USER")

        // define the user instance
        def userInstance = new User(
                username: params.username,
                password: params.password,
                enabled: true)

        if (params.username?.trim() == "" || params.password?.trim() == "") {
            flash.message = "Email and/or password cannot be blank."
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        // save the user
        userInstance.save()

        if (userInstance.hasErrors()) {
            render(view: "register", model: [registerInstance: userInstance])
            return
        }

        UserRole.create userInstance, userRole

        // authenticate the user
        if(springSecurityService != null) {
            springSecurityService.reauthenticate(params.username)
        }

        // redirect to the home view
        redirect(controller: "home")
    }
}
