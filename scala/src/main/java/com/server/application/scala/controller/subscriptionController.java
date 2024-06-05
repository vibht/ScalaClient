package com.server.application.scala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.application.scala.models.ResponseModel;
import com.server.application.scala.models.SubscriptionModel;

import com.server.application.scala.helper.subscription;
import com.server.application.scala.service.SubscriptionService;

@RestController
public class subscriptionController {

    @Autowired
    private SubscriptionService subscription;

    @PostMapping("/subscription")
    public ResponseEntity<Boolean> submitSubscription(SubscriptionModel model) {
        try {
            Boolean returnValue = subscription.createSubscriptionService(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
                    //.body(new ResponseModel("error are found", 500, "Error", null, e.getMessage()));

        }
    }

}
