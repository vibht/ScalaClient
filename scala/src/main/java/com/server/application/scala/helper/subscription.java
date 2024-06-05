package com.server.application.scala.helper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.server.application.scala.models.ResponseModel;
import com.server.application.scala.models.SubscriptionModel;
import com.server.application.scala.sockets.subscriptionSendScala;

@Service
public class subscription {

    @Autowired
    @Lazy
    private subscriptionSendScala SubscriptionSendScala;

    public Boolean validSubscription(SubscriptionModel subscripion) {
        Boolean isJoinStatus = false;

        try {
            String uniqueCode = UUID.randomUUID().toString();
            // if (subscripion.getUniqueCode().equals(uniqueCode)) {
                String events = subscripion.getEvents();
                if (events == "Join") {
                    isJoinStatus = true;
                    System.out.println("The User are Joined MBMS service in Successfully");

                } else if (events == "Leave") {
                    System.out.println("The User is Leave In MBMS Service");

                } else {
                    System.out.println("the User Is not event fire..");

                }

            // } else {
            //     System.out.println("user is already Joined In MBMS Services");
            // }
            return isJoinStatus;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error are occure" + e.getMessage());
            isJoinStatus = false;
            return isJoinStatus;
        }
    }

    public ResponseModel createdSubscription(SubscriptionModel data) {
        ResponseModel responseBody =new ResponseModel();
        try {

            SubscriptionModel model = new SubscriptionModel();
            model.setCount(0);
            model.setEvents("Join");
            model.setStatus(true);
            model.setUniqueCode("AXT00M1");
            model.setUserName("A.K Rehman");
            Boolean checkValidation = validSubscription(model);

            if (Boolean.TRUE.equals(checkValidation)) {
                // SubscriptionSendScala.sendSubscriptionToScala(model);
                responseBody.setData(model);
                responseBody.setMessage("validation is also work");
                responseBody.setMessageDetails("successfully create Subscription");
                responseBody.setStatusCode(200);
                responseBody.setStatus("OK");
                return responseBody;

            }
            responseBody.setMessage("validation");
            responseBody.setMessageDetails(null);
            responseBody.setStatus(null);

            return responseBody;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Error are found" + e.getMessage());
            responseBody.setMessage("Error are found ");
            responseBody.setMessageDetails("error are found in" + e.getMessage());
            responseBody.setStatus(null);

            return responseBody;
        }

    }
}
