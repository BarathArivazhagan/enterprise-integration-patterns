package com.barath.integration.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private MessageChannel inputChannel;

	@Override
	public void run(String... args) throws Exception {
		
		Customer customer=new Customer(100L, "barath");
		inputChannel.send(MessageBuilder.withPayload(customer).build());
		
	}
}
