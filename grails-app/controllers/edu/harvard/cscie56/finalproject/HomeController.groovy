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

        def categoriesAndCounts = getMySurveyCategoriesAndCount()

        render (view: 'home', model: [title: 'My Surveys', categoriesAndCounts: categoriesAndCounts])

    }

    def allsurveyindex() {
        println "allsurveyindex"
        flash.clear()

        def categoriesAndCounts = getAllSurveyCategoriesAndCount()

        render (view: 'home', model: [title: 'All Surveys', categoriesAndCounts: categoriesAndCounts])

    }

    private getMySurveyCategoriesAndCount() {
        println "getMySurveyCategoriesAndCount"

        User user = User.load(springSecurityService.principal.id)

        def surveys = []
        // get all survey categories and number of surveys in each list
        for(singleCategory in Survey.constraints.category.inList) {

            def surveysOfLoggedInUser = Survey.executeQuery("SELECT s FROM Survey s WHERE s.user = :user and s.category = :category", [user: user, category: singleCategory])
            surveys.add([
                category: singleCategory,
                count: surveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    private getAllSurveyCategoriesAndCount() {
        println "getAllSurveyCategoriesAndCount"

        def surveys = []
        // get all survey categories and number of surveys in each list
        for(singleCategory in Survey.constraints.category.inList) {
            surveys.add([
                    category: singleCategory,
                    count: Survey.findAllByCategory(singleCategory).size()
            ])
        }
        return surveys
    }
}
