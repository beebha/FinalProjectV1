package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class HomeController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index()
    {
        flash.clear()
        mysurveyindex()
    }

    def mysurveyindex()
    {
        flash.clear()

        User user = User.load(springSecurityService.principal.id)

        def activeCategoriesAndCounts = SurveyUtils.getMyActiveSurveyCategoriesAndCount(user)
        def completedCategoriesAndCounts = SurveyUtils.getMyCompletedSurveyCategoriesAndCount(user)
        def incompleteCategoriesAndCounts = SurveyUtils.getMyIncompleteSurveyCategoriesAndCount(user)

        render (view: 'home', model: [
                title: 'My Surveys',
                activeCategoriesAndCounts: activeCategoriesAndCounts,
                completedCategoriesAndCounts: completedCategoriesAndCounts,
                incompleteCategoriesAndCounts: incompleteCategoriesAndCounts
        ])

    }

    def allsurveyindex()
    {
        flash.clear()

        def categoriesAndCounts = SurveyUtils.getAllSurveyCategoriesAndCount()

        render (view: 'home', model: [title: 'All Surveys', categoriesAndCounts: categoriesAndCounts])
    }
}
