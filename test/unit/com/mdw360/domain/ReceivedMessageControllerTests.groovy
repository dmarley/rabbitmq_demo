package com.mdw360.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ReceivedMessageController)
@Mock(ReceivedMessage)
class ReceivedMessageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/receivedMessage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.receivedMessageInstanceList.size() == 0
        assert model.receivedMessageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.receivedMessageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.receivedMessageInstance != null
        assert view == '/receivedMessage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/receivedMessage/show/1'
        assert controller.flash.message != null
        assert ReceivedMessage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/receivedMessage/list'

        populateValidParams(params)
        def receivedMessage = new ReceivedMessage(params)

        assert receivedMessage.save() != null

        params.id = receivedMessage.id

        def model = controller.show()

        assert model.receivedMessageInstance == receivedMessage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/receivedMessage/list'

        populateValidParams(params)
        def receivedMessage = new ReceivedMessage(params)

        assert receivedMessage.save() != null

        params.id = receivedMessage.id

        def model = controller.edit()

        assert model.receivedMessageInstance == receivedMessage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/receivedMessage/list'

        response.reset()

        populateValidParams(params)
        def receivedMessage = new ReceivedMessage(params)

        assert receivedMessage.save() != null

        // test invalid parameters in update
        params.id = receivedMessage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/receivedMessage/edit"
        assert model.receivedMessageInstance != null

        receivedMessage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/receivedMessage/show/$receivedMessage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        receivedMessage.clearErrors()

        populateValidParams(params)
        params.id = receivedMessage.id
        params.version = -1
        controller.update()

        assert view == "/receivedMessage/edit"
        assert model.receivedMessageInstance != null
        assert model.receivedMessageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/receivedMessage/list'

        response.reset()

        populateValidParams(params)
        def receivedMessage = new ReceivedMessage(params)

        assert receivedMessage.save() != null
        assert ReceivedMessage.count() == 1

        params.id = receivedMessage.id

        controller.delete()

        assert ReceivedMessage.count() == 0
        assert ReceivedMessage.get(receivedMessage.id) == null
        assert response.redirectedUrl == '/receivedMessage/list'
    }
}
