package com.barath.integration.app;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class AmqpConfiguration.
 */
@Configuration
public class AmqpConfiguration {

	/** The queue. */
	@Value("${rabbitmq.queue}")
	private String queue;

	/** The exchange. */
	@Value("${rabbitmq.exchange}")
	private String exchange;

	/**
	 * Queue.
	 *
	 * @return the queue
	 */
	@Bean
	public Queue queue() {
		return new Queue(queue, true);
	}

	/**
	 * Exchange.
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange, true, true);
	}

	/**
	 * Bind qto exchange.
	 *
	 * @return the binding
	 */
	@Bean
	public Binding bindQtoExchange() {

		return BindingBuilder.bind(queue()).to(exchange()).with(queue);
	}

}
