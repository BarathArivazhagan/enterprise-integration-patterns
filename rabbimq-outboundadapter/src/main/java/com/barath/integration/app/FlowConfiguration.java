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
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * The Class FlowConfiguration.
 */
@Configuration
public class FlowConfiguration {

	/** The response queue. */
	@Value("${rabbitmq.response.queue}")
	private String responseQueue;

	/** The response exchange. */
	@Value("${rabbitmq.response.exchange}")
	private String responseExchange;

	/**
	 * Input channel.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	/** The connection factory. */
	@Autowired
	private ConnectionFactory connectionFactory;

	/**
	 * Publisher flow.
	 *
	 * @param amqpTemplate the amqp template
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow publisherFlow(AmqpTemplate amqpTemplate) {

		return IntegrationFlows.from(inputChannel())
				.handle(Amqp.outboundAdapter(amqpTemplate).exchangeName(responseExchange)).get();
	}

	/**
	 * Subscriber flow.
	 *
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow subscriberFlow() {

		return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, responseQueue)).handle(r -> {
			System.out.println("r " + r);
		}).get();
	}

}
