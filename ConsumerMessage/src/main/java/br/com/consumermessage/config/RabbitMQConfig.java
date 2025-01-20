package br.com.consumermessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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
 * Caso queira delegar a responsabilidade de binding entre a exchange e a queue
 * 
 * Necessario adicionar o @Configuration e o @Bean no method
 */

@Configuration
public class RabbitMQConfig {
	
	public static final String QUEUE = "SpringProjectRabbit";
	public static final String EXCHANGE = "SpringProjectRabbitExchanges";
	public static final String ROUNTING_KEY = "TestSpringRabbit";
	
	@Bean
    public Exchange exchange() {
        return ExchangeBuilder
        		.directExchange(EXCHANGE)
                .durable(true)
                .build();
    }
	
	@Bean
    public Queue queue() {
        return QueueBuilder
        		.durable(QUEUE)
        		.withArgument("x-message-ttl", 120000)
                .build();
    }
	
	@Bean
    public Binding binding(Exchange exchange, Queue queue) {
        return BindingBuilder
        		.bind(queue)
                .to(exchange)
                .with(ROUNTING_KEY)
                .noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplateService(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverterConfig());
        
        return rabbitTemplate;
    }

    @Bean(name = "Teste")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverterConfig());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(5);
        return factory;
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
