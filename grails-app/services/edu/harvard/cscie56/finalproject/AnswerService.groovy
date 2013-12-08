package edu.harvard.cscie56.finalproject

class AnswerService {

    def saveAnswer(List<String> answers, String additionalComments, Long surveyResultId) {
        def surveyResult = SurveyResult.get(surveyResultId)
        def answerInstance = new Answer([
                answers: answers,
                additonalComments: additionalComments,
                surveyResultId:surveyResultId,
                surveyResult:surveyResult])
        answerInstance.save(flush: true)
    }
}
