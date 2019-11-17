package edu.poli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.poli.dto.Request;

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

@RestController
class SensorController{
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	@PostMapping("/persis")
	public ResponseEntity<?> getTest(@RequestBody Request r) {
		simpleMessagingTemplate.convertAndSend("/chat", r);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
}
