package edu.harvard.cscie56.finalproject

class AnswerService {

    def saveAnswer(List<String> answers, String additionalComments, Question qnInstance, SurveyResult surveyResultInstance) {
        def answerInstance = new Answer([
                allAnswers: answers,
                additonalComments: additionalComments,
                question: qnInstance,
                surveyResultId:surveyResultInstance.id,
                surveyResult:surveyResultInstance])
        answerInstance.save(flush: true)
    }
}
