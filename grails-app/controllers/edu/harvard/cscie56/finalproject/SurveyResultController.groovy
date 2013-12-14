package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.annotation.Secured

/**
 * <code>SurveyResultController</code>
 * Controller class for for all actions associated with Survey Results creation/update
 */

@Secured('isAuthenticated()')
class SurveyResultController {

    def surveyResultService
    def springSecurityService
    def answerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method saves a survey results
     */

    def saveSurveyResults()
    {

        // gets the instance of the logged in user
        User surveyTaker = User.load(springSecurityService.principal.id)
        def surveyID = Long.valueOf(params.surveyID.toString())

        def surveyResultInstance = surveyResultService.saveSurveyResult(surveyTaker, surveyID)

        def allParams = params.entrySet()

        // saves the results for each question in the survey
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

                // save each answer
                answerService.saveAnswer(answers, additionalComments, qnInstance, surveyResultInstance)
            }
        }

        // send to all surveys page with thank you message
        redirect(controller: "home", action: "allsurveyindex", params: [message: "Thank you for taking the Survey!"])
    }

    /**
     * This method shows the survey results
     *
     * @param surveyResultID - ID of survey results
     */

    def showSurveyResults(Long surveyResultID)
    {
        def surveyResultInstance = SurveyResult.get(surveyResultID)

        render(view: "showSurveyResult", model: [surveyResultInstance: surveyResultInstance])
    }
}
