package edu.harvard.cscie56.finalproject

/**
 * Service class for Question
 */

class QuestionService {

    /**
     * This method saves a question
     *
     * @param questionText
     * @param type
     * @param scale
     * @param startLabel
     * @param endLabel
     * @param options
     * @param overallComment
     * @param surveyId
     * @return
     */

    Question saveQuestion(String questionText, String type, Integer scale,
                          String startLabel, String endLabel, List<String> options,
                          Boolean overallComment, Long surveyId)
    {
        def survey = Survey.get(surveyId)
        def questionInstance = new Question([
                questionText: questionText,
                type: type,
                scale: scale,
                startLabel: startLabel,
                endLabel: endLabel,
                options: options,
                overallComment: overallComment,
                surveyId: surveyId,
                survey: survey])
        questionInstance.save(flush: true)

        return questionInstance
    }

    /**
     * This method updates a question
     *
     * @param questionInstance
     * @param questionText
     * @param type
     * @param scale
     * @param startLabel
     * @param endLabel
     * @param options
     * @param overallComment
     * @return
     */

    def updateQuestion(Question questionInstance, String questionText, String type, Integer scale,
                       String startLabel, String endLabel, List<String> options,
                       Boolean overallComment)
    {
        questionInstance.questionText = questionText
        questionInstance.type = type
        questionInstance.scale = scale
        questionInstance.startLabel = startLabel
        questionInstance.endLabel = endLabel
        questionInstance.options = options
        questionInstance.overallComment = overallComment
        questionInstance.save(flush: true)
    }

    /**
     * This method deletes a question
     *
     * @param questionInstance
     */
    def deleteQuestion(Question questionInstance)
    {
        questionInstance.delete(flush: true)
    }
}
