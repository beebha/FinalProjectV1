package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyService {

    Survey saveSurvey(String surveyName, String surveyCategory, Boolean complete, Boolean active, User user)
    {
        def surveyInstance = new Survey([name:surveyName, category:surveyCategory, user:user, complete:complete, active:active])
        surveyInstance.save(flush: true)
        return surveyInstance
    }

    def deleteSurvey(Survey surveyInstance)
    {
        surveyInstance.delete(flush: true)
    }
}
