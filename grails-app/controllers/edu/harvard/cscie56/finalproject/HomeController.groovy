package edu.harvard.cscie56.finalproject

class HomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

        println "index"
        flash.clear()
        render(view: "home")
    }
}
