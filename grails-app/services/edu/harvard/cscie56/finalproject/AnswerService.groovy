package edu.harvard.cscie56.finalproject

class AnswerService {

    def saveAnswer(List<String> answers, String additionalComments, Question qnInstance, SurveyResult surveyResultInstance) {


        println "saveAnswer"

        println surveyResultInstance.id
        println surveyResultInstance

        def answerInstance = new Answer([
                allAnswers: answers,
                additonalComments: additionalComments,
                questionId: qnInstance.id,
                question: qnInstance,
                surveyResultId:surveyResultInstance.id,
                surveyResult:surveyResultInstance])
        answerInstance.save(flush: true)

        println "****************"
        println answerInstance.allAnswers.size()
        println answerInstance.allAnswers
        println answerInstance.additonalComments
        println "Answers size now : "+ surveyResultInstance.answers.size()
    }
}
