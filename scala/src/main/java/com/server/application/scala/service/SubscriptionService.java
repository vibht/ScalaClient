package com.server.application.scala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.application.scala.models.ResponseModel;
import com.server.application.scala.models.SubscriptionModel;
import com.server.application.scala.helper.subscription;

@Service
public class SubscriptionService {
    
    @Autowired
    private subscription subscriptionHelper;

    public ResponseModel createSubscription(SubscriptionModel model) {
        try {
            ResponseModel requestBody = subscriptionHelper.createdSubscription(model);
            return requestBody;

        } catch (Exception e) {

            throw new UnsupportedOperationException("Error are found in Service 'createSubscription'" + e.getMessage());
        }

    }

}
