package co.com.openintl.bank.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MessageController {
	
	@Autowired
	Queue queue;
	
	@Autowired
	JmsTemplate jmsTemplate; 

	@GetMapping("send/{message}")
	public ResponseEntity<?> send(@PathVariable("message") String message) {
		try {
			jmsTemplate.convertAndSend(queue,message);
			return ResponseEntity.ok().body(message);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
