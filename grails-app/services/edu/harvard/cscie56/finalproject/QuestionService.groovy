package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.Question
import edu.harvard.cscie56.finalproject.Survey
import org.springframework.transaction.annotation.Transactional

@Transactional
class QuestionService {

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

    def deleteQuestion(Question questionInstance)
    {
        questionInstance.delete(flush: true)
    }
}
