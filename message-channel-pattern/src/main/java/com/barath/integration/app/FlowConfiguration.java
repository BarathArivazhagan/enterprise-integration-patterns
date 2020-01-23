package com.barath.integration.app;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

	/**
	 * Input channel.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	/**
	 * Message flow.
	 *
	 * @param handler the handler
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow messageFlow(InputHandler handler) {

		return IntegrationFlows.from(inputChannel()).handle(handler).get();
	}

	/**
	 * The Class InputHandler.
	 */
	@Component
	protected static class InputHandler implements MessageHandler {

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
				logger.info("Message recevied {}", message.toString());
			}

		}

	}

}
