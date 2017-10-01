package com.barath.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Function;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Created by barath on 01/10/17.
 */
@Configuration
public class FlowConfiguration {

    @Bean
    public MessageChannel inputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel orderSuccessChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel orderCancelledChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel orderPendingChannel(){
        return new DirectChannel();
    }



    @Bean
    public IntegrationFlow successFlow(){

        return IntegrationFlows.from(orderSuccessChannel())
               .handle( (Message<?> message) ->{
                   System.out.println("MESSAGE RECEIVED "+message+"  STATUS : SUCCESS");
               }).get();
    }

    @Bean
    public IntegrationFlow cancelFlow(){

        return IntegrationFlows.from(orderCancelledChannel())
                .handle( (Message<?> message) ->{
                    System.out.println("MESSAGE RECEIVED "+message+"  STATUS : CANCELLED");
                }).get();
    }

    @Bean
    public IntegrationFlow pendingFlow(){

        return IntegrationFlows.from(orderPendingChannel())
                .handle( (Message<?> message) ->{
                    System.out.println("MESSAGE RECEIVED "+message+"  STATUS : PENDING");
                }).get();
    }
}
