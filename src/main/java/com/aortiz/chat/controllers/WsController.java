package com.aortiz.chat.controllers;

import com.aortiz.chat.entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class WsController {

    private final String[] colores = {"red","green","purple","orange","brown"};

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message sendMessage(Message message){
        message.setDate(new Date().getTime());
        if(message.getType().equals("NEW_USER")){
            message.setColor(colores[new Random().nextInt(colores.length)]);
            message.setText(" - Nuevo usuario Conectado");
        }
        return message;
    }

    @MessageMapping("/writing")
    @SendTo("/chat/writing")
    public String checkIfUserWriting(String user){
        return user + " está escribiendo...";
    }
}
