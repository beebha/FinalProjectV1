package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyResultService {

    SurveyResult saveSurveyResult(User resultsUser, Long surveyId)
    {
        def survey = Survey.get(surveyId)
        def surveyResultInstance = new SurveyResult([resultsUser: resultsUser, surveyId: surveyId, survey: survey])
        surveyResultInstance.save(flush: true)
        return surveyResultInstance
    }
}
