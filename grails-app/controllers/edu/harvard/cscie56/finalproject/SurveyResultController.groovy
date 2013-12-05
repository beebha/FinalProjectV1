package edu.harvard.cscie56.finalproject

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured('isAuthenticated()')
class SurveyResultController {

    def surveyResultService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [surveyResultInstanceList: SurveyResult.list(params), surveyResultInstanceTotal: SurveyResult.count()]
    }

    def create() {
        [surveyResultInstance: new SurveyResult(params)]
    }

    def save() {

        def userId = params.get('user').id
        def surveyId = params.get('survey').id
        def surveyResultInstance = surveyResultService.saveSurveyResult(params.get('name'), userId.toLong(), surveyId.toLong())

        if (surveyResultInstance.hasErrors()) {
            render(view: "create", model: [surveyResultInstance: surveyResultInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), surveyResultInstance.id])
        redirect(action: "show", id: surveyResultInstance.id)
    }

    def show(Long id) {
        def surveyResultInstance = SurveyResult.get(id)
        if (!surveyResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "list")
            return
        }

        [surveyResultInstance: surveyResultInstance]
    }

    def edit(Long id) {
        def surveyResultInstance = SurveyResult.get(id)
        if (!surveyResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "list")
            return
        }

        [surveyResultInstance: surveyResultInstance]
    }

    def update(SurveyResult cmd, Long id, Long version) {

        if (!cmd.validate()) {
            render(view: "edit", model: [surveyResultInstance: cmd])
            return
        }

        def surveyResultInstance = SurveyResult.get(id)
        if (!surveyResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (surveyResultInstance.version > version) {
                surveyResultInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'surveyResult.label', default: 'SurveyResult')] as Object[],
                        "Another user has updated this SurveyResult while you were editing")
                render(view: "edit", model: [surveyResultInstance: surveyResultInstance])
                return
            }
        }

        surveyResultService.updateSurveyResult(surveyResultInstance, cmd)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), surveyResultInstance.id])
        redirect(action: "show", id: surveyResultInstance.id)
    }

    def delete(Long id) {
        def surveyResultInstance = SurveyResult.get(id)
        if (!surveyResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "list")
            return
        }

        try {
            surveyResultService.deleteSurveyResult(surveyResultInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'surveyResult.label', default: 'SurveyResult'), id])
            redirect(action: "show", id: id)
        }
    }
}
