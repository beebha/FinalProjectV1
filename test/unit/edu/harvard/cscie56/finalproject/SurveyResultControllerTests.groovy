package edu.harvard.cscie56.finalproject



import org.junit.*
import grails.test.mixin.*

@TestFor(SurveyResultController)
@Mock(SurveyResult)
class SurveyResultControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/surveyResult/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.surveyResultInstanceList.size() == 0
        assert model.surveyResultInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.surveyResultInstance != null
    }

    void testSave() {
        controller.save()

        assert model.surveyResultInstance != null
        assert view == '/surveyResult/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/surveyResult/show/1'
        assert controller.flash.message != null
        assert SurveyResult.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/surveyResult/list'

        populateValidParams(params)
        def surveyResult = new SurveyResult(params)

        assert surveyResult.save() != null

        params.id = surveyResult.id

        def model = controller.show()

        assert model.surveyResultInstance == surveyResult
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/surveyResult/list'

        populateValidParams(params)
        def surveyResult = new SurveyResult(params)

        assert surveyResult.save() != null

        params.id = surveyResult.id

        def model = controller.edit()

        assert model.surveyResultInstance == surveyResult
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/surveyResult/list'

        response.reset()

        populateValidParams(params)
        def surveyResult = new SurveyResult(params)

        assert surveyResult.save() != null

        // test invalid parameters in update
        params.id = surveyResult.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/surveyResult/edit"
        assert model.surveyResultInstance != null

        surveyResult.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/surveyResult/show/$surveyResult.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        surveyResult.clearErrors()

        populateValidParams(params)
        params.id = surveyResult.id
        params.version = -1
        controller.update()

        assert view == "/surveyResult/edit"
        assert model.surveyResultInstance != null
        assert model.surveyResultInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/surveyResult/list'

        response.reset()

        populateValidParams(params)
        def surveyResult = new SurveyResult(params)

        assert surveyResult.save() != null
        assert SurveyResult.count() == 1

        params.id = surveyResult.id

        controller.delete()

        assert SurveyResult.count() == 0
        assert SurveyResult.get(surveyResult.id) == null
        assert response.redirectedUrl == '/surveyResult/list'
    }
}
