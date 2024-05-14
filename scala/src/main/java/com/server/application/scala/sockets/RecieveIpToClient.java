package com.server.application.scala.sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.stereotype.Service;

import com.server.application.scala.models.ResponseModel;

import jakarta.annotation.PostConstruct;

@Service
public class RecieveIpToClient {

    @PostConstruct
    public ResponseModel receiveIpToServer() {

        DatagramSocket socket = null;
        ResponseModel returnValue = new ResponseModel(); 

        try {
            socket = new DatagramSocket(54321);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());

            // Prepare response
            returnValue.setMessage("Successfully received message");
            returnValue.setMessageDetails("Message is transferred");
            returnValue.setStatus("200 OK");
            returnValue.setStatusCode(200);
            returnValue.setData(message);
            System.out.println(returnValue);
            return returnValue;

        } catch (Exception e) {
            e.printStackTrace();
            returnValue.setMessage("ERROR");
            returnValue.setMessageDetails(e.getMessage());
            returnValue.setStatusCode(500);
            returnValue.setStatus("ERROR 500");
            returnValue.setData(null);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

        return returnValue;
    }
}
