package br.com.publishmessage.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.publishmessage.domain.Client;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PublishService {

	private static final String EXCHANGE = "SpringProjectRabbitExchanges";
	private static final String ROUNTING_KEY = "TestSpringRabbit";
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void publish(Client client) {
		
		log.info("Message published: {}", client);
		
		rabbitTemplate.convertAndSend(EXCHANGE, ROUNTING_KEY, client);
	}
	
}
