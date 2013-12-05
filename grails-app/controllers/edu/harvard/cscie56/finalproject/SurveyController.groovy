package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured('isAuthenticated()')
class SurveyController {

    def springSecurityService
    def surveyService
    def questionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def createSurvey()
    {
        flash.clear()
        render(view: "surveyStep1", model: [surveyInstance: new Survey(), categories: SurveyUtils.getAllSurveyCategories()])
    }

    def saveSurveyStep1()
    {
        User user = User.load(springSecurityService.principal.id)
        def surveyInstance = surveyService.saveSurvey(params.name, params.category, false, false, user)

        if (surveyInstance.hasErrors()) {
            render(view: "surveyStep1", model: [surveyInstance: surveyInstance, categories: SurveyUtils.getAllSurveyCategories()])
            return
        }
        showSurveyStep2(surveyInstance.id)
    }

    def saveSurveyStep2()
    {
        def surveyID = Long.valueOf(params.surveyID.toString())

        def btnAction = params.submitBtnClicked
        def qnText = params.questionText
        def qnType = params.type
        def additionalComments = false

        def options = []
        def scale = 0
        def startLabel = ""
        def endLabel = ""

        // get required question attributes
        if(qnType != "Comment")
        {
            additionalComments = params.comment == "on" ? true : false

            if(qnType == "Numerical Slider Scale" || qnType == "Discrete Rating Scale") {
                scale = Integer.valueOf(params.scale.toString())
                startLabel = params.scaleStartLbl
                endLabel = params.scaleEndLbl
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

    def showSurveyStep2(Long surveyID)
    {
        def qnTypes = SurveyUtils.getAllQuestionTypes()

        render(view: "surveyStep2", model: [surveyID: surveyID, qnTypes: qnTypes, qnTypesJSON: qnTypes as JSON])
    }

    def saveSurveyShowHome(Long surveyID)
    {
        def surveyInstance = Survey.get(surveyID)

        surveyInstance.complete = true;
        surveyInstance.save(flush: true)

        showHome()
    }

    def showHome()
    {
        redirect(controller: "home", view: "home")
    }

    def viewSurvey(String surveyState, Long surveyID)
    {
        render(view: "viewSurvey", model: [surveyInstance: Survey.get(surveyID), surveyState: surveyState, categories: SurveyUtils.getAllSurveyCategories()])
    }

    def takeSurvey(Long surveyID)
    {
        render(view: "takeSurvey", model: [surveyInstance: Survey.get(surveyID)])
    }

    def updateSurvey(Long surveyID)
    {
        def surveyInstance = Survey.get(surveyID)

        if(params.deactivate != null) {
            // check to see if survey has results
            if(surveyInstance.surveyResults.size() > 0) {
                flash.message = "Survey has results and cannot be deactivated"
                viewSurvey('active', surveyID)
                return
            } else {
                surveyInstance.active = false
                surveyInstance.save(flush: true)
                flash.message = "Survey has been deactivated"
                viewSurvey('complete', surveyID)
                return
            }
        }

        if (params.saveadd != null ||
                params.savepublish != null ||
                params.savecontinue != null ||
                params.savecomplete != null) {

            surveyInstance.name = params.name
            surveyInstance.category = params.category

            surveyInstance.save(flush: true)

            if(params.saveadd != null) {
                showSurveyStep2(surveyID)
            } else if (params.savepublish != null) {
                surveyInstance.active = true
                surveyInstance.save(flush: true)
                viewSurvey('active', surveyID)
                return
            } else if (params.savecontinue != null) {
                showHome()
            } else if (params.savecomplete != null) {
                surveyInstance.complete = true
                surveyInstance.save(flush: true)
                viewSurvey('complete', surveyID)
                return
            }
        }
    }

    def saveSurveyResults(Long surveyID)
    {
        println "saveSurveyResults"
        def es=params.entrySet()
        es.each{
            println "Key is " + it.key
            println "Value is " + it.value
        }

        User user = User.load(springSecurityService.principal.id)


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
