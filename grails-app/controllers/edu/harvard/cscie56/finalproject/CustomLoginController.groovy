package edu.harvard.cscie56.finalproject

class CustomLoginController {

    def verifyUser() {

        println "in verifyUser"

        request.getRequestDispatcher("/j_spring_security_check").forward(request, response)
    }
}
