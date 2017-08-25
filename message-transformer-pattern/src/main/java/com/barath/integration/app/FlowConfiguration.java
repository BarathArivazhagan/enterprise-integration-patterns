package com.barath.integration.app;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.NullChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import org.springframework.integration.dsl.support.Transformers;

import org.springframework.messaging.MessageChannel;


@Configuration
public class FlowConfiguration {
	
	
	@Bean
	public MessageChannel inputChannel(){
		return new DirectChannel();
	}
	
	
	@Bean
	public MessageChannel outputChannel(){
		return new DirectChannel();
	}
	
	
	@Bean
	public MessageChannel nullChannel1(){
		return new NullChannel();
	}
		
	
	
	@Bean
	public IntegrationFlow messageToJsonFlow(){
		
		return IntegrationFlows
					.from(inputChannel())
					.transform(Transformers.toJson())
					/** using handle("beanName","methodName") **/
					.handle("inputServiceHandler","toJson")					
					.get();
	}
	
	
	@Bean
	public IntegrationFlow jsonToMessageFlow(){
		
		return IntegrationFlows
					.from(outputChannel())
					.transform(Transformers.fromJson())
					/** using handle("beanName","methodName") **/
					.handle("inputServiceHandler","fromJson")		
					.channel( nullChannel1())
					.get();
	}
	
	
	
	

}
