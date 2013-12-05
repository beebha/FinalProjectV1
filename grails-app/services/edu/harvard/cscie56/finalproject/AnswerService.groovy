package edu.harvard.cscie56.finalproject

class AnswerService {

    Answer saveAnswer(Long surveyResultId) {
        def surveyResult = SurveyResult.get(surveyResultId)
        def answerInstance = new Answer([surveyResultId:surveyResultId, surveyResult:surveyResult])
        answerInstance.save(flush: true)
        return answerInstance
    }

    def updateAnswer(Answer answerInstance, Answer cmd) {
        answerInstance.properties['surveyResultId', 'surveyResult'] = cmd.properties
        answerInstance.save(flush: true)
    }

    def deleteAnswer(Answer answerInstance) {
        answerInstance.delete(flush: true)
    }
}
