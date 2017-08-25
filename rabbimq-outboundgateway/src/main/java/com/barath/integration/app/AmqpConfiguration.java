package com.barath.integration.app;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
	
	@Value("${rabbitmq.queue}")
	private String queue;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;	
	
		
	
	@Bean
	public Queue queue(){
		return new Queue(queue,true);
	}
	

	@Bean
	public TopicExchange exchange(){
		return new TopicExchange(exchange, true, true);
	}
	
	
	
	@Bean
	public Binding bindQtoExchange(){
		
		return BindingBuilder.bind(queue()).to(exchange()).with(queue);
	}
	
	
	

}
