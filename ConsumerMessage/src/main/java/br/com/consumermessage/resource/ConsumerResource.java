package br.com.consumermessage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.consumermessage.service.ConsumerService;

@RestController
@RequestMapping(value = "api/consumer")
public class ConsumerResource {

	@Autowired
	private ConsumerService service;
	
	@GetMapping
	public ResponseEntity<?> consumerMessage() throws Exception {
		return ResponseEntity.ok(service.consumer());
	}
	
}
