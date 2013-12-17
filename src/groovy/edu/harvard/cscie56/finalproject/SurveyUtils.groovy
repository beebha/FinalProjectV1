package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * Utils class for Survey application
 */

class SurveyUtils {

    /**
     * This method gets all published surveys of logged in user
     *
     * @param user - logged in user
     */

    public static getMyActiveSurveyCategoriesAndCount(User user)
    {
        def surveys = []

        for(singleCategory in Survey.constraints.category.inList) {

            def publishedSurveysOfLoggedInUser = Survey.findAllByUserAndCategoryAndCompleteAndActive(user, singleCategory, true, true)

            surveys.add([
                    category: singleCategory,
                    surveys: publishedSurveysOfLoggedInUser,
                    count: publishedSurveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    /**
     * This method gets all completed surveys of logged in user
     *
     * @param user - logged in user
     */

    public static getMyCompletedSurveyCategoriesAndCount(User user)
    {
        def surveys = []

        for(singleCategory in Survey.constraints.category.inList) {

            def completedSurveysOfLoggedInUser = Survey.findAllByUserAndCategoryAndCompleteAndActive(user, singleCategory, true, false)
            surveys.add([
                    category: singleCategory,
                    surveys: completedSurveysOfLoggedInUser,
                    count: completedSurveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    /**
     * This method gets all incomplete surveys of logged in user
     *
     * @param user - logged in user
     */

    public static getMyIncompleteSurveyCategoriesAndCount(User user)
    {
        def surveys = []

        for(singleCategory in Survey.constraints.category.inList) {

            def incompleteSurveysOfLoggedInUser = Survey.findAllByUserAndCategoryAndCompleteAndActive(user, singleCategory, false, false)

            surveys.add([
                    category: singleCategory,
                    surveys: incompleteSurveysOfLoggedInUser,
                    count: incompleteSurveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    /**
     * This method gets all published surveys of all users
     */

    public static getAllSurveyCategoriesAndCount()
    {
        def surveys = []

        for(singleCategory in Survey.constraints.category.inList) {

            def publishedSurveys = Survey.findAllByCategoryAndCompleteAndActive(singleCategory, true, true)

            surveys.add([
                    category: singleCategory,
                    surveys: publishedSurveys,
                    count: publishedSurveys.size()
            ])
        }
        return surveys
    }

    /**
     * This method gets all survey results of the published surveys of logged in user
     *
     * @param user - logged in user
     */

    public static getAllSurveyResultsCategoriesAndCountByCreator(User user)
    {
        def allSurveyResults = []

        for(singleCategory in Survey.constraints.category.inList) {

            def surveyResults = SurveyResult.findAllBySurveyCreatorAndCategory(user, singleCategory)

            allSurveyResults.add([
                    category: singleCategory,
                    surveyResults: surveyResults,
                    count: surveyResults.size()
            ])
        }
        return allSurveyResults
    }

    /**
     * This method gets all survey categories
     */

    public static getAllSurveyCategories()
    {
        Survey.constraints.category.inList
    }

    /**
     * This method gets all question types
     */

    public static getAllQuestionTypes()
    {
        Question.constraints.type.inList
    }
}
