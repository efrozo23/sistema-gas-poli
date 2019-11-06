package edu.poli.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	@MessageMapping("/send/message")
	public void onReciveMessage(String message) {
		this.simpleMessagingTemplate.convertAndSend("/chat",  new SimpleDateFormat("HH:mm:ss").format(new Date())+"- "+message);
	}
	
}
