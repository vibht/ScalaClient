package com.server.application.scala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.application.scala.models.SubscriptionModel;
import com.server.application.scala.sockets.subscriptionSendScala;

@Service
public class SubscriptionService {
    
    @Autowired
    private subscriptionSendScala subscriptionSend;

    public Boolean createSubscriptionService(SubscriptionModel model) {
        try {
            Boolean requestBody = subscriptionSend.sendSubscriptionToScala(model);
            return requestBody;

        } catch (Exception e) {

            throw new UnsupportedOperationException("Error are found in Service 'createSubscription'" + e.getMessage());
        }

    }

}
