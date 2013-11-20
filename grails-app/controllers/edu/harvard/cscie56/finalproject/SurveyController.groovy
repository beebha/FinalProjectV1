package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class SurveyController {

    def springSecurityService
    def surveyService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def createSurvey() {

        println "createSurvey"
        flash.clear()
        render(view: "surveyStep1", model: [surveyInstance: new Survey(), categories: SurveyUtils.getAllSurveyCategories()])
    }

    def saveSurveyStep1() {

        println "saveSurveyStep1"

        render(view: "survey", model: [categories: SurveyUtils.getAllSurveyCategories()])

        User user = User.load(springSecurityService.principal.id)
        def surveyInstance = surveyService.saveSurvey(params.get("name"), params.get("category"), user)

        if (surveyInstance.hasErrors()) {
            render(view: "surveyStep1", model: [surveyInstance: surveyInstance])
            return
        }

        render(view: "surveyStep2", model: [qnTypes: SurveyUtils.getAllQuestionTypes()])
    }



//    def create() {
//        [surveyInstance: new Survey(params)]
//    }

    def save() {
        def userId = params.get('user').id
        def surveyInstance = surveyService.saveSurvey(params.get("name"), params.get("category"), userId.toLong())

//        if (surveyInstance.hasErrors()) {
//            render(view: "create", model: [surveyInstance: surveyInstance])
//            return
//        }

//        flash.message = message(code: 'default.created.message', args: [message(code: 'survey.label', default: 'Survey'), surveyInstance.id])
//        redirect(action: "show", id: surveyInstance.id)
    }

//    def show(Long id) {
//        def surveyInstance = Survey.get(id)
//        if (!surveyInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [surveyInstance: surveyInstance]
//    }

//    def edit(Long id) {
//        def surveyInstance = Survey.get(id)
//        if (!surveyInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [surveyInstance: surveyInstance]
//    }

    def update(Survey cmd, Long id, Long version) {

        def surveyInstance = Survey.get(id)
        if (!surveyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (surveyInstance.version > version) {
                surveyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'survey.label', default: 'Survey')] as Object[],
                        "Another user has updated this Survey while you were editing")
                render(view: "edit", model: [surveyInstance: surveyInstance])
                return
            }
        }
        if(surveyInstance.surveyResults.size() > 0) {
            surveyInstance.errors.rejectValue("version", "default.not.updated.surveyresults.message",
                    [message(code: 'survey.label', default: 'Survey')] as Object[],
                    "This Survey has results and cannot be updated.")
            render(view: "edit", model: [surveyInstance: surveyInstance])
            return
        }

        surveyService.updateSurvey(surveyInstance, cmd)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'survey.label', default: 'Survey'), surveyInstance.id])
        redirect(action: "show", id: surveyInstance.id)
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
