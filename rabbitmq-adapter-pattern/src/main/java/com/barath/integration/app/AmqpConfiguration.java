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
	
	@Value("${rabbitmq.request.queue}")
	private String requestQueue;
	
	@Value("${rabbitmq.response.queue}")
	private String responseQueue;
	
	@Value("${rabbitmq.request.exchange}")
	private String requestExchange;	
	
	@Value("${rabbitmq.response.exchange}")
	private String responseExchange;	
	
		
	
	@Bean
	public Queue requestQueue(){
		return new Queue(requestQueue,true);
	}
	
	@Bean
	public Queue responseQueue(){
		return new Queue(responseQueue,true);
	}
	
	@Bean
	public TopicExchange requestExchange(){
		return new TopicExchange(requestExchange, true, true);
	}
	
	@Bean
	public TopicExchange responseExchange(){
		return new TopicExchange(responseExchange, true, true);
	}
	
	@Bean
	public Binding bindReqQtoExchange(){
		
		return BindingBuilder.bind(requestQueue()).to(requestExchange()).with(requestQueue);
	}
	
	@Bean
	public Binding bindResQtoExchange(){
		
		return BindingBuilder.bind(responseQueue()).to(responseExchange()).with(responseQueue);
	}
	
	
	

}
