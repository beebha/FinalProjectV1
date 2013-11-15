package edu.harvard.cscie56.finalproject
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class HomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        println "index"
        flash.clear()

        def categories = getAllSurveyCategories()

        render (view: 'home', model: [categories: categories])

    }

    private getAllSurveyCategories() {
        println "allSurveyData"
        // get all survey categories
        Survey.constraints.category.inList
    }
}
