package edu.harvard.cscie56.finalproject



import org.junit.*
import grails.test.mixin.*

@TestFor(OptionController)
@Mock(Option)
class OptionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/option/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.optionInstanceList.size() == 0
        assert model.optionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.optionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.optionInstance != null
        assert view == '/option/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/option/show/1'
        assert controller.flash.message != null
        assert Option.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/option/list'

        populateValidParams(params)
        def option = new Option(params)

        assert option.save() != null

        params.id = option.id

        def model = controller.show()

        assert model.optionInstance == option
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/option/list'

        populateValidParams(params)
        def option = new Option(params)

        assert option.save() != null

        params.id = option.id

        def model = controller.edit()

        assert model.optionInstance == option
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/option/list'

        response.reset()

        populateValidParams(params)
        def option = new Option(params)

        assert option.save() != null

        // test invalid parameters in update
        params.id = option.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/option/edit"
        assert model.optionInstance != null

        option.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/option/show/$option.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        option.clearErrors()

        populateValidParams(params)
        params.id = option.id
        params.version = -1
        controller.update()

        assert view == "/option/edit"
        assert model.optionInstance != null
        assert model.optionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/option/list'

        response.reset()

        populateValidParams(params)
        def option = new Option(params)

        assert option.save() != null
        assert Option.count() == 1

        params.id = option.id

        controller.delete()

        assert Option.count() == 0
        assert Option.get(option.id) == null
        assert response.redirectedUrl == '/option/list'
    }
}
