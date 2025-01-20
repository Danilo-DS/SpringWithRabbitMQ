package br.com.publishmessage.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/*
 * Caso queira delegar a responsabilidade de criação da exchange para o spring
 * 
 * Necessario adicionar o @Configuration e o @Bean no method
 */

@Configuration
public class RabbitMQConfig {
	
//	public static final String EXCHANGE_NAME = "SpringProjectRabbitExchange";
//	
//	public Exchange createExchange() {
//		return ExchangeBuilder
//				.directExchange(EXCHANGE_NAME)
//				.durable(true)
//				.build();
//	}
	
	@Bean
	public RabbitTemplate rabbitTemplateConfig(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverterConfig());
		return rabbitTemplate;
		
	}
	
	@Bean
    public Jackson2JsonMessageConverter jsonMessageConverterConfig() {
        return new Jackson2JsonMessageConverter(objectMapperConfig());
    }
    
    private ObjectMapper objectMapperConfig() {
		return Jackson2ObjectMapperBuilder.json()
				.modules(new JavaTimeModule()) //Recebe uma lista de modulos de deserialização e serialização //JavaTimeModule contem um Map de SimpleModule para deserialização
				.dateFormat(new StdDateFormat()) //Recebe um pattern para serialização e deserialização de datas
				.build();
    	
    }
	
}
