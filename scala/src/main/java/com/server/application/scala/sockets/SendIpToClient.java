package com.server.application.scala.sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class SendIpToClient {

    @PostConstruct
    public boolean sendIpToServer() {
        boolean flag = false;
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 12345;
            String message = "hello";
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, address, port);

            socket.send(packet);
            flag = true;
            return flag;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("errore are found" + e.getMessage());
            return flag;

        }

    }

}
