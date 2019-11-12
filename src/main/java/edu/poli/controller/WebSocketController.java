package edu.poli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
	private Logger logger = LoggerFactory.getLogger(WebSocketController.class);
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	
	
	@MessageMapping("/send/message")
	public void onReciveMessage(String message) {
		logger.info("Mensaje recivido:{}", message);
		this.simpleMessagingTemplate.convertAndSend("/chat",message);
	}
	
}
