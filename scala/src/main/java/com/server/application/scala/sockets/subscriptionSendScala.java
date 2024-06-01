package com.server.application.scala.sockets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.application.scala.helper.subscription;
import com.server.application.scala.models.ResponseModel;
import com.server.application.scala.models.SubscriptionModel;

import jakarta.annotation.PostConstruct;

@Service
public class subscriptionSendScala {

    @Autowired
    private subscription subscriptionClass;

    // @PostConstruct
    public Boolean sendSubscriptionToScala(SubscriptionModel subscription) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        DatagramSocket socket = new DatagramSocket(8089);

        try {

            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 89899;
            ResponseModel requestData = subscriptionClass.createdSubscription(subscription);

            oos.writeObject(requestData);
            byte[] byteArrayData = bos.toByteArray();

            DatagramPacket packet = new DatagramPacket(byteArrayData, 0, address, port);

            socket.send(packet);

            String msg = new String(packet.toString());

            System.out.println(msg);
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error are found" + e.getMessage());
            return false;

        } finally {
            oos.close();
            oos.flush();
            bos.close();
            socket.close();
            System.out.println("Finally socket is closed when all task execute");

        }

    }

}
