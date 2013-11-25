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

        render(view: 'viewQuestion', model: [
                surveyState:surveyState,
                surveyID:surveyID,
                qnTypes: qnTypes,
                qnTypesJSON: qnTypes as JSON,
                questionInstance: Question.get(questionID)
        ])
    }

    def saveQn() {

        println "saveQn"

    }










    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }

    def create() {
        [questionInstance: new Question(params)]
    }

    def save() {
        def surveyId = params.get('survey').id
        def questionInstance = questionService.saveQuestion(params.get("questionText"), params.get("type"), surveyId.toLong())

        if (questionInstance.hasErrors()) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def show(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }

    def edit(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }

    def update(Question cmd, Long id, Long version) {
        if (!cmd.validate()) {
            render(view: "edit", model: [questionInstance: cmd])
            return
        }

        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (questionInstance.version > version) {
                questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'question.label', default: 'Question')] as Object[],
                        "Another user has updated this Question while you were editing")
                render(view: "edit", model: [questionInstance: questionInstance])
                return
            }
        }

        questionService.updateQuestion(questionInstance, cmd)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def delete(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        try {
            questionService.deleteQuestion(questionInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "show", id: id)
        }
    }
}
