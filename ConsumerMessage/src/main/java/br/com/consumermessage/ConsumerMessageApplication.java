package br.com.consumermessage;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ConsumerMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMessageApplication.class, args);
	}

}
