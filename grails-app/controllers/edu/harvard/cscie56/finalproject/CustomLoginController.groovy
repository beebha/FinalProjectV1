package edu.harvard.cscie56.finalproject

class CustomLoginController {

    def verifyUser(String j_username, String j_password) {
        println "in verifyUser....."
        println "username: "+ j_username
        println "password: "+ j_password

        request.getRequestDispatcher("/j_spring_security_check").forward (request, response);
    }
}
