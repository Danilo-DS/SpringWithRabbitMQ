package br.com.publishmessage;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class PublishMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublishMessageApplication.class, args);
	}

}
