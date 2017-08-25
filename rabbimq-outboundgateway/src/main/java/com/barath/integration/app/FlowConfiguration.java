package com.barath.integration.app;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.TopicExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Configuration
public class FlowConfiguration {
	
	
	
	@Value("${rabbitmq.queue}")
	private String queue;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;	
	
	
	
	@Bean
	public MessageChannel inputChannel(){
		return new DirectChannel();
	}
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	
	
	@Bean
	public IntegrationFlow publisherFlow(AmqpTemplate amqpTemplate){
		
		return IntegrationFlows
					.from(inputChannel())
					.handle(Amqp.outboundGateway(amqpTemplate).exchangeName(exchange))					
					.get();
	}
	
	@Bean
	public IntegrationFlow subscriberFlow(){
		
		return IntegrationFlows
					.from(Amqp.inboundAdapter(connectionFactory, queue))
					.handle( r -> {
						
						System.out.println("Message received "+r);
					})					
					.get();
	}
	
	

	
	
	

}
