package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

/**
 * <code>SurveyController</code>
 * Controller class for all actions associated with Survey creation/update/deletion
 */

@Secured('isAuthenticated()')
class SurveyController {

    def springSecurityService
    def surveyService
    def questionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /**
     * This method clears any flash messages and
     * redirects to step 1 in creating a survey
     */

    def createSurvey()
    {
        flash.clear()
        render(view: "surveyStep1", model: [surveyInstance: new Survey(), categories: SurveyUtils.getAllSurveyCategories()])
    }

    /**
     * This method saves the initial survey and redirects to step 2
     */

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

    /**
     * This method saves a question which is part of step 2 in a creation of a survey
     */

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

    /**
     * This method gets all question types available for a survey and redirects to step 2 of the survey
     *
     * @param surveyID - ID of survey
     */

    def showSurveyStep2(Long surveyID)
    {
        def qnTypes = SurveyUtils.getAllQuestionTypes()

        render(view: "surveyStep2", model: [surveyID: surveyID, qnTypes: qnTypes, qnTypesJSON: qnTypes as JSON])
    }

    /**
     * This method saves the survey and redirects to the home view
     *
     * @param surveyID - ID of survey
     */

    def saveSurveyShowHome(Long surveyID)
    {
        def surveyInstance = Survey.get(surveyID)

        surveyInstance.complete = true;
        surveyInstance.save(flush: true)

        showHome()
    }

    /**
     * This method shows the home view
     */

    def showHome()
    {
        redirect(controller: "home")
    }

    /**
     * This method shows the view survey page
     *
     * @param surveyState - current state of survey
     * @param surveyID - ID of survey
     */

    def viewSurvey(String surveyState, Long surveyID)
    {
        render(view: "viewSurvey", model: [surveyInstance: Survey.get(surveyID), surveyState: surveyState, categories: SurveyUtils.getAllSurveyCategories()])
    }

    /**
     * This method shows the take survey page
     *
     * @param surveyID - ID of survey
     */

    def takeSurvey(Long surveyID)
    {
        render(view: "takeSurvey", model: [surveyInstance: Survey.get(surveyID)])
    }

    /**
     * This method updates the survey and redirects based on selected button
     *
     * @param surveyID - ID of survey
     */

    def updateSurvey(Long surveyID)
    {
        def surveyInstance = Survey.get(surveyID)

        if(params.deactivate != null) {

            // check to see if survey has results
            if(SurveyResult.findAllBySurvey(surveyInstance).size() > 0) {
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

        // save survey state and redirect based on button clicked
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

    /**
     * This method deletes a survey and redirects to the home view
     */

    def deleteSurvey(Long surveyID)
    {
        def surveyInstance = Survey.get(surveyID)

        try {
            surveyService.deleteSurvey(surveyInstance)
            showHome()
        }
        catch (DataIntegrityViolationException e) {
            showHome()
        }
    }
}
