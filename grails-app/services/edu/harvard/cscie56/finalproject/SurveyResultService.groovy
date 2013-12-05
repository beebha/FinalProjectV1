package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyResultService {

    SurveyResult saveSurveyResult(String surveyName, Long userId, Long surveyId)
    {
        def user = User.get(userId)
        def survey = Survey.get(surveyId)
        def surveyResultInstance = new SurveyResult([name:surveyName, user:user, surveyId:surveyId, survey:survey])
        surveyResultInstance.save(flush: true)
        return surveyResultInstance
    }

    def updateSurveyResult(SurveyResult surveyResultInstance, SurveyResult cmd) {
        surveyResultInstance.properties['name', 'user', 'surveyId', 'survey'] = cmd.properties
        surveyResultInstance.save(flush: true)
    }

    def deleteSurveyResult(SurveyResult surveyResultInstance) {
        surveyResultInstance.delete(flush: true)
    }
}
