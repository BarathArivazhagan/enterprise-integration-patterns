package com.barath.app;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * The Class Application.
 */
@SpringBootApplication
public class Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		MessageChannel inputChannel = (MessageChannel) context.getBean("inputChannel");
		FTPIntegrationFlow ftpIntegrationFlow = context.getBean(FTPIntegrationFlow.class);
		File file = ftpIntegrationFlow.getResource("classpath:/hello.txt");
		final Message<File> message = MessageBuilder.withPayload(file).build();
		inputChannel.send(message);
	}
}
