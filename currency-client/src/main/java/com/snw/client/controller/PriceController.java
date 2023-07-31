package com.snw.client.controller;

import com.snw.client.websocket.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PriceController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public PriceController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/currency")
    public void sendNewPrice(@Payload Message message) {
        simpMessagingTemplate.convertAndSend("/topic/dollar-price", message);
    }
}
