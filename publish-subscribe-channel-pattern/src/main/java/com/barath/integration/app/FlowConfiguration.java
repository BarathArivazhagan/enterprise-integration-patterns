package com.barath.integration.app;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
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

	/**
	 * Publisher channel.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel publisherChannel() {
		PublishSubscribeChannel channel = new PublishSubscribeChannel();
		channel.subscribe(new Subscriber1());
		channel.subscribe(new Subscriber2());
		return channel;
	}

	/**
	 * Publisher flow.
	 *
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow publisherFlow() {

		return IntegrationFlows.from(publisherChannel()).log().get();
	}

	/**
	 * The Class Subscriber1.
	 */
	@Component
	protected static class Subscriber1 implements MessageHandler {

		/** The Constant logger. */
		private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

		/**
		 * Handle message.
		 *
		 * @param message the message
		 * @throws MessagingException the messaging exception
		 */
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {

			if (logger.isInfoEnabled()) {
				logger.info("Message recevied at SUBSCRIBER 1 {}", message.toString());
			}

		}

	}

	/**
	 * The Class Subscriber2.
	 */
	@Component
	protected static class Subscriber2 implements MessageHandler {

		/** The Constant logger. */
		private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

		/**
		 * Handle message.
		 *
		 * @param message the message
		 * @throws MessagingException the messaging exception
		 */
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {

			if (logger.isInfoEnabled()) {
				logger.info("Message recevied  at SUBSCRIBER 2 {}", message.toString());
			}

		}

	}

}
