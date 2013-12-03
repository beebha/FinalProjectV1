package edu.harvard.cscie56.finalproject

class LogoutController {

    def logout()
    {
        flash.clear()
        session.invalidate()
        request.getRequestDispatcher("/j_spring_security_logout").forward(request, response)
    }
}
