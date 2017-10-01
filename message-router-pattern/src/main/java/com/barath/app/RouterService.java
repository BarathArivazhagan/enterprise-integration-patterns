package com.barath.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Created by barath on 01/10/17.
 */
@Service
public class RouterService {

    private static  final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Router(inputChannel = "inputChannel")
    public String routeToChannel(Message message){


        Order order=(Order)message.getPayload();
        if(logger.isInfoEnabled()){
            logger.info("Order {}",order);
        }
        switch (order.getStatus()){

            case PENDING: return "orderPendingChannel";
            case SUCCESS: return "orderSuccessChannel";
            case CANCELLED: return "orderCancelledChannel";
            default : return "orderPendingChannel";
        }

    }


}
