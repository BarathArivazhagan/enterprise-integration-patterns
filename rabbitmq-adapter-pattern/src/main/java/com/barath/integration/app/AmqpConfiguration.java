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

	/** The request queue. */
	@Value("${rabbitmq.request.queue}")
	private String requestQueue;

	/** The response queue. */
	@Value("${rabbitmq.response.queue}")
	private String responseQueue;

	/** The request exchange. */
	@Value("${rabbitmq.request.exchange}")
	private String requestExchange;

	/** The response exchange. */
	@Value("${rabbitmq.response.exchange}")
	private String responseExchange;

	/**
	 * Request queue.
	 *
	 * @return the queue
	 */
	@Bean
	public Queue requestQueue() {
		return new Queue(requestQueue, true);
	}

	/**
	 * Response queue.
	 *
	 * @return the queue
	 */
	@Bean
	public Queue responseQueue() {
		return new Queue(responseQueue, true);
	}

	/**
	 * Request exchange.
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange requestExchange() {
		return new TopicExchange(requestExchange, true, true);
	}

	/**
	 * Response exchange.
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange responseExchange() {
		return new TopicExchange(responseExchange, true, true);
	}

	/**
	 * Bind req qto exchange.
	 *
	 * @return the binding
	 */
	@Bean
	public Binding bindReqQtoExchange() {

		return BindingBuilder.bind(requestQueue()).to(requestExchange()).with(requestQueue);
	}

	/**
	 * Bind res qto exchange.
	 *
	 * @return the binding
	 */
	@Bean
	public Binding bindResQtoExchange() {

		return BindingBuilder.bind(responseQueue()).to(responseExchange()).with(responseQueue);
	}

}
