package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * Service class for SurveyResult
 */

class SurveyResultService {

    /**
     * This method saves a survey result
     *
     * @param surveyTaker
     * @param surveyId
     * @return
     */
    SurveyResult saveSurveyResult(User surveyTaker, Long surveyId)
    {
        def survey = Survey.get(surveyId)
        def surveyResultInstance = new SurveyResult([
                surveyTaker: surveyTaker,
                surveyCreator: survey.user,
                category: survey.category,
                survey: survey])
        surveyResultInstance.save(flush: true)
        return surveyResultInstance
    }
}
