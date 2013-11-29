package edu.harvard.cscie56.finalproject

import grails.converters.JSON
import edu.harvard.cscie56.finalproject.auth.User
import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class SurveyController {

    def springSecurityService
    def surveyService
    def questionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def createSurvey() {

        println "createSurvey"
        flash.clear()
        render(view: "surveyStep1", model: [surveyInstance: new Survey(), categories: SurveyUtils.getAllSurveyCategories()])
    }

    def saveSurveyStep1() {

        println "saveSurveyStep1"

        User user = User.load(springSecurityService.principal.id)
        def surveyInstance = surveyService.saveSurvey(params.get("name"), params.get("category"), false, false, user)

        if (surveyInstance.hasErrors()) {
            render(view: "surveyStep1", model: [surveyInstance: surveyInstance, categories: SurveyUtils.getAllSurveyCategories()])
            return
        }
        showSurveyStep2(surveyInstance.id)
    }

    def saveSurveyStep2() {
        println "saveSurveyStep2"

        def surveyID = Long.valueOf(params.get("surveyID").toString())

        def btnAction = params.get("submitBtnClicked")
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
        def questionInstance = questionService.saveQuestion(qnText, qnType, scale, startLabel, endLabel, options, additionalComments, surveyID)
        if (questionInstance.hasErrors()) {
            showSurveyStep2(surveyID)
            return
        }

        // redirect based on button clicked
        if(btnAction == 'savenext') {
            showSurveyStep2(surveyID)
        } else if(btnAction == 'savelater') {
            showHome()
        } else {
            saveSurveyShowHome(surveyID)

        }
    }

    def showSurveyStep2(Long surveyID) {

        println "showSurveyStep2"

        def qnTypes = SurveyUtils.getAllQuestionTypes()

        render(view: "surveyStep2", model: [surveyID: surveyID, qnTypes: qnTypes, qnTypesJSON: qnTypes as JSON])
    }

    def saveSurveyShowHome(Long surveyID) {

        println "saveSurveyShowHome"

        def surveyInstance = Survey.get(surveyID)

        surveyInstance.complete = true;
        surveyInstance.save(flush: true)

        showHome()
    }

    def showHome() {

        println "showHome"

        redirect(controller: "home", view: "home")
    }

    def viewSurvey(String surveyState, Long surveyID) {

        println "viewSurvey"
        println "Survey details of ID "+surveyID+" for state "+surveyState

        render(view: "viewSurvey", model: [surveyInstance: Survey.get(surveyID), surveyState: surveyState, categories: SurveyUtils.getAllSurveyCategories()])
    }

    def takeSurvey(Long surveyID) {

        println "takeSurvey"
        println "Taking survey of ID "+surveyID

        render(view: "takeSurvey", model: [surveyInstance: Survey.get(surveyID)])
    }

    def delete(Long id) {
        def surveyInstance = Survey.get(id)
        if (!surveyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), id])
            redirect(action: "list")
            return
        }

        if(surveyInstance.surveyResults.size() > 0) {
            surveyInstance.errors.rejectValue("version", "default.not.deleted.surveyresults.message",
                    [message(code: 'survey.label', default: 'Survey')] as Object[],
                    "This Survey has results and cannot be deleted.")
            render(view: "edit", model: [surveyInstance: surveyInstance])
            return
        }

        try {
            surveyService.deleteSurvey(surveyInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'survey.label', default: 'Survey'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'survey.label', default: 'Survey'), id])
            redirect(action: "show", id: id)
        }
    }
}
