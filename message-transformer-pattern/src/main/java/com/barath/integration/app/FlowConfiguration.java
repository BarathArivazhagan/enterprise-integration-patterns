package com.barath.integration.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.NullChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.MessageChannel;

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
	 * Output channel.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel outputChannel() {
		return new DirectChannel();
	}

	/**
	 * Null channel 1.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel nullChannel1() {
		return new NullChannel();
	}

	/**
	 * Message to json flow.
	 *
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow messageToJsonFlow() {

		return IntegrationFlows.from(inputChannel()).transform(Transformers.toJson())
				/** using handle("beanName","methodName") **/
				.handle("inputServiceHandler", "toJson").get();
	}

	/**
	 * Json to message flow.
	 *
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow jsonToMessageFlow() {

		return IntegrationFlows.from(outputChannel()).transform(Transformers.fromJson())
				/** using handle("beanName","methodName") **/
				.handle("inputServiceHandler", "fromJson").channel(nullChannel1()).get();
	}

}
