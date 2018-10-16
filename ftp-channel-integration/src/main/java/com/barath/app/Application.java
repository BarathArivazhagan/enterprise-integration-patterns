package com.barath.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.integration.file.support.FileUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;

@SpringBootApplication
public class Application {



	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		MessageChannel inputChannel = (MessageChannel)context.getBean("inputChannel");
		FTPIntegrationFlow ftpIntegrationFlow = context.getBean(FTPIntegrationFlow.class);
		File file = ftpIntegrationFlow.getResource("classpath:/hello.txt");
		final Message<File> message = MessageBuilder.withPayload(file).build();
		inputChannel.send(message);
	}
}
