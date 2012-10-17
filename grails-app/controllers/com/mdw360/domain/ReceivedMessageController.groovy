package com.mdw360.domain

import org.springframework.dao.DataIntegrityViolationException

class ReceivedMessageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [receivedMessageInstanceList: ReceivedMessage.list(params), receivedMessageInstanceTotal: ReceivedMessage.count()]
    }

    def create() {
        [receivedMessageInstance: new ReceivedMessage(params)]
    }

    def save() {
        def receivedMessageInstance = new ReceivedMessage(params)
        if (!receivedMessageInstance.save(flush: true)) {
            render(view: "create", model: [receivedMessageInstance: receivedMessageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), receivedMessageInstance.id])
        redirect(action: "show", id: receivedMessageInstance.id)
    }

    def show(Long id) {
        def receivedMessageInstance = ReceivedMessage.get(id)
        if (!receivedMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "list")
            return
        }

        [receivedMessageInstance: receivedMessageInstance]
    }

    def edit(Long id) {
        def receivedMessageInstance = ReceivedMessage.get(id)
        if (!receivedMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "list")
            return
        }

        [receivedMessageInstance: receivedMessageInstance]
    }

    def deleteAll(){
        ReceivedMessage.list().each{
            it.delete()
        }
        redirect(action: "list")
    }

    def update(Long id, Long version) {
        def receivedMessageInstance = ReceivedMessage.get(id)
        if (!receivedMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (receivedMessageInstance.version > version) {
                receivedMessageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'receivedMessage.label', default: 'ReceivedMessage')] as Object[],
                          "Another user has updated this ReceivedMessage while you were editing")
                render(view: "edit", model: [receivedMessageInstance: receivedMessageInstance])
                return
            }
        }

        receivedMessageInstance.properties = params

        if (!receivedMessageInstance.save(flush: true)) {
            render(view: "edit", model: [receivedMessageInstance: receivedMessageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), receivedMessageInstance.id])
        redirect(action: "show", id: receivedMessageInstance.id)
    }

    def delete(Long id) {
        def receivedMessageInstance = ReceivedMessage.get(id)
        if (!receivedMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "list")
            return
        }

        try {
            receivedMessageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'receivedMessage.label', default: 'ReceivedMessage'), id])
            redirect(action: "show", id: id)
        }
    }
}
