package com.mdw360.samle.service

import com.mdw360.domain.ReceivedMessage

class PongService {

    static rabbitQueue = 'myTestQueue'

    void handleMessage(message){
        println "PONG - received message: ${message}"
        new ReceivedMessage(message: message).save(failOnError: true)
        //this.echo();
        //Thread.sleep(4000)
    }

    def echo() {
        println "running echo ping method now... "
        rabbitSend('myTestQueue', 'some Action, Some Id: 555')
    }
}
