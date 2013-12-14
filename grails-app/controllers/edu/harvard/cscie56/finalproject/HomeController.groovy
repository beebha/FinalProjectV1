package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.annotation.Secured

/**
 * <code>HomeController</code>
 * Controller class for home page of application
 */

@Secured('isAuthenticated()')
class HomeController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method clears any flash messages and
     * redirects to the surveys created by logged in user
     */

    def index()
    {
        flash.clear()
        mysurveyindex()
    }

    /**
     * This method gets all complete, incomplete and published surveys of the logged in user
     * and then redirects to the my surveys home view
     */

    def mysurveyindex()
    {
        flash.clear()

        User user = User.load(springSecurityService.principal.id)

        // get published surveys of logged in user
        def activeCategoriesAndCounts = SurveyUtils.getMyActiveSurveyCategoriesAndCount(user)

        // get completed surveys of logged in user
        def completedCategoriesAndCounts = SurveyUtils.getMyCompletedSurveyCategoriesAndCount(user)

        // get incomplete surveys of logged in user
        def incompleteCategoriesAndCounts = SurveyUtils.getMyIncompleteSurveyCategoriesAndCount(user)

        // redirect to home view
        render (view: 'home', model: [
                title: 'My Surveys',
                activeCategoriesAndCounts: activeCategoriesAndCounts,
                completedCategoriesAndCounts: completedCategoriesAndCounts,
                incompleteCategoriesAndCounts: incompleteCategoriesAndCounts
        ])

    }

    /**
     * This method gets all published surveys of all users
     * and then redirects to the all surveys home view
     */

    def allsurveyindex()
    {
        if (params.message != null) {
            flash.message = params.message
        } else {
            flash.clear()
        }

        // get all published surveys of all users
        def categoriesAndCounts = SurveyUtils.getAllSurveyCategoriesAndCount()

        // redirect to home view
        render (view: 'home', model: [title: 'All Surveys', categoriesAndCounts: categoriesAndCounts])
    }

    /**
     * This method gets all survey results of published surveys of the logged in user
     * and then redirects to the all results home view
     */

    def allresults()
    {
        flash.clear()

        User user = User.load(springSecurityService.principal.id)

        // get all survey results of published surveys of logged in user
        def allResultsCategoriesAndCounts = SurveyUtils.getAllSurveyResultsCategoriesAndCountByCreator(user)

        // redirect to home view
        render (view: 'home', model: [title: 'All Results', allResultsCategoriesAndCounts: allResultsCategoriesAndCounts])
    }
}
