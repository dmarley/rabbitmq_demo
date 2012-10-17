package com.mdw360.sample

class PingPongController {

    def index() { redirect(action: "edit") }

    def edit() {
        [numMessages:params?.numMessage ?: 10]
    }

    def ping() {
        Integer numMessages = params?.numMessages as Integer

        (1..numMessages).each{
            rabbitSend('myTestQueue', "Message Content: ${it}-${new Date()}")
        }
        flash.message = "${numMessages} successfully sent to RabbitMQ Broker"
        redirect(action: "edit", params: [numMessage:numMessages])
    }
}
