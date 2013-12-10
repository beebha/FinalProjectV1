package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured('isAuthenticated()')
class SurveyResultController {

    def surveyResultService
    def springSecurityService
    def answerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def saveSurveyResults()
    {
        println "saveSurveyResults"

        User surveyTaker = User.load(springSecurityService.principal.id)
        def surveyID = Long.valueOf(params.surveyID.toString())

        def surveyResultInstance = surveyResultService.saveSurveyResult(surveyTaker, surveyID)

        if (surveyResultInstance.hasErrors()) {

            println "errors!!!!!"
            println surveyResultInstance.errors
            return
        }

        println "no errors with saving survey result"

        def allParams = params.entrySet()

        allParams.each {

            def key = it.key.toString()

            if(key.indexOf("qn") != -1) {
                def qnID = Long.valueOf(key.replaceAll("qn", ""))
                def qnInstance = Question.get(qnID)
                def value = it.value
                def answers = []
                def additionalComments = ""


                if(qnInstance.type == "Comment" ||
                        qnInstance.type == "Discrete Rating Scale" ||
                        qnInstance.type == "Multiple Choice (One Answer)" ||
                        qnInstance.type == "Numerical Slider Scale") {
                    answers.add(value.toString())

                } else if (qnInstance.type == "Multiple Choice (Multiple Answers)" ||
                        qnInstance.type == "Ranking") {
                    answers = value as List
                }

                if(qnInstance.type != "Comment") {
                    additionalComments = params.get("additionalCommentsQn"+qnID)
                }

                // save Answer
                answerService.saveAnswer(answers, additionalComments, qnInstance, surveyResultInstance)
            }
        }

        println "After saving all answers : " + surveyResultInstance.answers.size()

        // send to all surveys page with thank you message
        redirect(controller: "home", action: "allsurveyindex", params: [message: "Thank you for taking the Survey!"])
    }

    def showSurveyResults(Long surveyResultID)
    {
        println "showSurveyResults"

        def surveyResultInstance = SurveyResult.get(surveyResultID)

        println surveyResultInstance.answers.size()

        render(view: "showSurveyResult", model: [surveyResultInstance: surveyResultInstance])
    }
}
