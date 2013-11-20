package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyUtils {

    public static getMyCompletedSurveyCategoriesAndCount(User user) {
        println "getMySurveyCategoriesAndCount in SurveyUtils"

        def surveys = []
        // get all survey categories and number of surveys in each list
        for(singleCategory in Survey.constraints.category.inList) {

            def surveysOfLoggedInUser = Survey.executeQuery("SELECT s FROM Survey s WHERE s.user = :user and s.category = :category and s.complete = TRUE", [user: user, category: singleCategory])
            surveys.add([
                    category: singleCategory,
                    count: surveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    public static getMyIncompleteSurveyCategoriesAndCount(User user) {
        println "getMySurveyCategoriesAndCount in SurveyUtils"

        def surveys = []
        // get all survey categories and number of surveys in each list
        for(singleCategory in Survey.constraints.category.inList) {

            def surveysOfLoggedInUser = Survey.executeQuery("SELECT s FROM Survey s WHERE s.user = :user and s.category = :category and s.complete = FALSE", [user: user, category: singleCategory])
            surveys.add([
                    category: singleCategory,
                    count: surveysOfLoggedInUser.size()
            ])
        }
        return surveys
    }

    public static getAllSurveyCategoriesAndCount() {
        println "getAllSurveyCategoriesAndCount in SurveyUtils"

        def surveys = []
        // get all survey categories and number of surveys in each list
        for(singleCategory in Survey.constraints.category.inList) {

            def completedSurveys = Survey.executeQuery("SELECT s FROM Survey s WHERE s.category = :category and s.complete = TRUE", [category: singleCategory])
            surveys.add([
                    category: singleCategory,
                    count: completedSurveys.size()
            ])
        }
        return surveys
    }

    public static getAllSurveyCategories() {
        println "getAllSurveyCategories in SurveyUtils"
        Survey.constraints.category.inList
    }

    public static getAllQuestionTypes() {
        println "getAllQuestionTypes in SurveyUtils"
        Question.constraints.type.inList
    }
}
