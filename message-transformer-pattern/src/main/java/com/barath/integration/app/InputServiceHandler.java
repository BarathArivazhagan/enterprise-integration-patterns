package com.barath.integration.app;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class InputServiceHandler{
	

	private static final Logger logger=LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	
	@ServiceActivator(outputChannel="outputChannel")
	public String toJson(String customerJson) throws MessagingException {
		
		if(customerJson != null && logger.isInfoEnabled()){
			logger.info("customer json  {}",customerJson);
		}
		
		return customerJson;
		
	}
	
	@ServiceActivator(outputChannel="nullChannel1",requiresReply="false")
	public Customer fromJson(Customer customer) throws MessagingException {
		
		if(customer != null && logger.isInfoEnabled()){
			logger.info("customer object {}",customer.toString());
		}
		
		return customer;
		
		
	}

}
