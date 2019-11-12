package edu.poli.beans;

import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import edu.poli.dto.Request;

@Component
public class ReciveData {
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	@Handler
	public void saveData(Request request) {
		this.simpleMessagingTemplate.convertAndSend("/chat",request);
	}

}
