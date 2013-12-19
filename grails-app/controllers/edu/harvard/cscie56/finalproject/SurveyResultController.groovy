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

    // added for export
    def exportService // Export service provided by Export plugin
    def grailsApplication  //inject GrailsApplication

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

    /**
     * This method exports the survey results in the specified format
     */
    def exportView = {
        if(!params.max) params.max = 10

        if(params?.format && params.format != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=SurveyResults.${params.extension}")

            List fields = [
                    "dateCreated",
                    "surveyTaker",
                    "surveyCreator",
                    "category",
                    "answers"
            ]

            Map labels = [
                    "dateCreated": "Date & Time",
                    "surveyTaker": "Taken By",
                    "surveyCreator": "Created By",
                    "category": "Category",
                    "answers": "Survey Results"

            ]

            def displayUsername = { domain, value ->
                return value.username
            }

            def displayDate = { domain, value ->
                return value.format('dd-MMM-yyyy HH:mm:ss')
            }

            def displayQnAnswerComments = { domain, value ->
                def allInfo = ""
                def count = 1
                value.each {
                    def qnText = it.question.questionText
                    def qnType = it.question.type
                    def ans = ""

                    if(qnType == "Comment" ||
                        qnType == "Discrete Rating Scale" ||
                        qnType == "Multiple Choice (One Answer)" ||
                        qnType == "Numerical Slider Scale") {
                        ans = it.allAnswers.get(0)
                    } else if (qnType == "Multiple Choice (Multiple Answers)") {
                        for(int i = 0; i < it.allAnswers.size(); i++) {
                            if(i > 0) {
                                ans += ", "
                            }
                            ans += it.allAnswers.get(i)
                        }
                    } else if (qnType == "Ranking") {
                        for(int i = 0; i < it.allAnswers.size(); i++) {
                            if(i > 0) {
                                ans += "\n"
                            }
                            ans += it.question.options.get(i)+ ": " + it.allAnswers.get(i)
                        }
                    }
                    def comments = it.additonalComments == null ? "N/A" : it.additonalComments
                    allInfo += "Question "+ count + ":\n" +
                            qnText + "\n" +
                            "Answer:\n" + ans + "\n" +
                            "Additional Comments:\n" + comments + "\n\n"

                    count++
                }
                return allInfo
            }

            Map formatters = [
                    "dateCreated": displayDate,
                    "surveyTaker": displayUsername,
                    "surveyCreator": displayUsername,
                    "answers" : displayQnAnswerComments
            ]

            exportService.export(params.format, response.outputStream, SurveyResult.list(params), fields, labels, formatters, [:])
        }
    }
}
