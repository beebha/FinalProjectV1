package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class HomeController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        flash.clear()
        mysurveyindex()
    }

    def mysurveyindex() {
        println "mysurveyindex"
        flash.clear()

        User user = User.load(springSecurityService.principal.id)

        def categoriesAndCounts = SurveyUtils.getMySurveyCategoriesAndCount(user)

        render (view: 'home', model: [title: 'My Surveys', categoriesAndCounts: categoriesAndCounts])

    }

    def allsurveyindex() {
        println "allsurveyindex"
        flash.clear()

        def categoriesAndCounts = SurveyUtils.getAllSurveyCategoriesAndCount()

        render (view: 'home', model: [title: 'All Surveys', categoriesAndCounts: categoriesAndCounts])

    }
}
