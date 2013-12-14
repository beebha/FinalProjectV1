package edu.harvard.cscie56.finalproject

/**
 * <code>LogoutController</code>
 * Controller class for Logout feature
 */

class LogoutController {

    /**
     * This method forwards the request to the spring security logout
     */

    def logout()
    {
        flash.clear()
        session.invalidate()
        request.getRequestDispatcher("/j_spring_security_logout").forward(request, response)
    }
}
