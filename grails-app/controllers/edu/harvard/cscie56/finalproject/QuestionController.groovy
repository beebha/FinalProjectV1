package edu.harvard.cscie56.finalproject

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class QuestionController {

    def questionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def deleteQn(Long surveyID, Long questionID, String surveyState) {
        println "deleteQn"

        def surveyInstance = Survey.get(surveyID)
        if(surveyInstance.questions.size() == 1) {
            flash.message = "Question was not deleted. Survey must have at least one question."
            redirect(controller: 'survey', action: 'viewSurvey', params: [surveyState:surveyState ,surveyID:surveyID])
            return
        }

        def questionInstance = Question.get(questionID)

        try {
            questionService.deleteQuestion(questionInstance)
            flash.message = "Question was successfully deleted"
            redirect(controller: 'survey', action: 'viewSurvey', params: [surveyState:surveyState ,surveyID:surveyID])
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Question was not deleted"
            redirect(controller: 'survey', action: 'viewSurvey', params: [surveyState:surveyState ,surveyID:surveyID])
        }
    }

    def editQn(Long surveyID, Long questionID, String surveyState) {
        println "editQn"

        def qnTypes = SurveyUtils.getAllQuestionTypes()

        render(view: 'editQuestion', model: [
                surveyState:surveyState,
                surveyID:surveyID,
                qnTypes: qnTypes,
                qnTypesJSON: qnTypes as JSON,
                questionInstance: Question.get(questionID)
        ])
    }

    def saveQn() {

        println "saveQn"

        def es=params.entrySet()
        es.each{
            println "Key is " + it.key
            println "Value is " + it.value
        }

        def surveyID = Long.valueOf(params.get("surveyID").toString())
        def questionID = Long.valueOf(params.get("questionID").toString())
        def surveyState = params.get("surveyState")

        def qnText = params.get("questionText")
        def qnType = params.get("type")
        def additionalComments = false

        def options = []
        def scale = 0
        def startLabel = ""
        def endLabel = ""

        // get required question attributes
        if(qnType != "Comment")
        {
            additionalComments = params.get("comment") == "on" ? true : false

            if(qnType == "Numerical Slider Scale" || qnType == "Discrete Rating Scale") {
                scale = Integer.valueOf(params.get("scale").toString())
                startLabel = params.get("scaleStartLbl")
                endLabel = params.get("scaleEndLbl")
            } else {
                int totalOptions = 1;
                while(params.get("option"+totalOptions) != null) {
                    options.add(params.get("option"+totalOptions))
                    totalOptions++;
                }
            }
        }

        // save question
        def questionInstance = Question.get(questionID)
        questionService.updateQuestion(questionInstance, qnText, qnType, scale, startLabel, endLabel, options, additionalComments)

        redirect(controller: 'survey', action: 'viewSurvey', params: [surveyID: surveyID, surveyState:surveyState])

    }
}
