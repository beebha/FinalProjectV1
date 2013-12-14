package edu.harvard.cscie56.finalproject

/**
 * Service class for Answer
 */

class AnswerService {

    /**
     * This method saves an answer
     *
     * @param answers
     * @param additionalComments
     * @param qnInstance
     * @param surveyResultInstance
     * @return
     */
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
