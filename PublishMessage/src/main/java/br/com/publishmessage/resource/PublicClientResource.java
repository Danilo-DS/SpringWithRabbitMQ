package br.com.publishmessage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.publishmessage.domain.Client;
import br.com.publishmessage.service.PublishService;

@RestController
@RequestMapping(value = "api/publish")
public class PublicClientResource {
	
	@Autowired
	private PublishService service;
	
	@PostMapping
	public ResponseEntity<?> publishClient(@RequestBody Client body) throws Exception {
		service.publish(body);
		return ResponseEntity.ok().build();
	}
}
