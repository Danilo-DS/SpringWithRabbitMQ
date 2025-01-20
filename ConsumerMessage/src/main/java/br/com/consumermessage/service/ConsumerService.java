package br.com.consumermessage.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.consumermessage.domain.Client;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	private static final String QUEUE_NAME = "SpringProjectRabbit";
	
	public Client consumer() throws Exception {
		return rabbitTemplate.receiveAndConvert(QUEUE_NAME, new ParameterizedTypeReference<Client>() {});
	}
	
	@RabbitListener(queues = QUEUE_NAME)
	public void consumerByAnnotation(@Payload Client client) {
		log.info("Message Read: {}",client);
	}
	
	
	
}
