package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * Service class for Survey
 */

class SurveyService {

    /**
     * This method saves a survey
     *
     * @param surveyName
     * @param surveyCategory
     * @param complete
     * @param active
     * @param user
     * @return
     */
    Survey saveSurvey(String surveyName, String surveyCategory, Boolean complete, Boolean active, User user)
    {
        def surveyInstance = new Survey([name:surveyName, category:surveyCategory, user:user, complete:complete, active:active])
        surveyInstance.save(flush: true)
        return surveyInstance
    }

    /**
     * This method deletes a survey
     *
     * @param surveyInstance
     */

    def deleteSurvey(Survey surveyInstance)
    {
        surveyInstance.delete(flush: true)
    }
}
