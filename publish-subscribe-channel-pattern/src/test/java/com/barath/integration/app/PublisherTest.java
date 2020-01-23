package com.barath.integration.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class PublisherTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherTest {

	/** The publisher channel. */
	@Autowired
	private PublishSubscribeChannel publisherChannel;

	/**
	 * Test publisher flow.
	 */
	@Test
	public void testPublisherFlow() {

		boolean sent = publisherChannel.send(MessageBuilder.withPayload("Hello TEST Sender").build());
		assertEquals(true, sent);
	}

}
