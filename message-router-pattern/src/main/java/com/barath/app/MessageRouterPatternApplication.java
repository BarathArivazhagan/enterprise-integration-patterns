package com.barath.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class MessageRouterPatternApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel inputChannel;

	public static void main(String[] args) {
		SpringApplication.run(MessageRouterPatternApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {

		Message<Order> successfulOrder= MessageBuilder.withPayload(new Order(1L,"TV","INDIA",10,10000.00, Order.OrderStatus.SUCCESS)).build();
		Message<Order> pendingOrder= MessageBuilder.withPayload(new Order(1L,"TV","INDIA",10,10000.00, Order.OrderStatus.PENDING)).build();
		Message<Order> cancelledOrder= MessageBuilder.withPayload(new Order(1L,"TV","INDIA",10,10000.00, Order.OrderStatus.CANCELLED)).build();
		inputChannel.send(successfulOrder);
		inputChannel.send(pendingOrder);
		inputChannel.send(cancelledOrder);
	}
}
