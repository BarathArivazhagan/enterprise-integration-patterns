package com.barath.integration.app;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;

import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class MessageChannelTest {
	
	@Autowired
	private DirectChannel inputChannel;
	
	
	

	@Test
	public void testMessageFlow() {
		
		boolean sent=inputChannel.send(MessageBuilder.withPayload("Hello TEST Sender").build());
		assertEquals(true, sent);
	}

}
