package com.server.application.scala.sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.springframework.stereotype.Service;

import com.server.application.scala.models.ResponseModel;

import jakarta.annotation.PostConstruct;

@Service
public class RecieveIpToClient {

    // @PostConstruct
    public ResponseModel receiveIpToServer() {
        boolean receiveFlag = false;

        DatagramSocket socket = null;
        ResponseModel returnValue = new ResponseModel();

        try {
            socket = new DatagramSocket(9876);
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
            receiveFlag = true;

            if (Boolean.TRUE.equals(receiveFlag)) {

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                if (clientAddress == packet.getAddress()) {
                    String responseMessage = message.concat("serverPort:" + clientPort + "");
                    byte[] responseData = responseMessage.getBytes();

                    InetAddress address = InetAddress.getByName("127.0.0.1");
                    DatagramPacket sendServerPacket = new DatagramPacket(responseData, responseData.length, address,
                            54321);
                   // socket.send(sendServerPacket);
                    String aa = new String(sendServerPacket.getData(), 0, sendServerPacket.getLength());
                    System.out.println("Packet is send after receive packet" + aa);

                }

                System.out.println("address" + clientAddress + "" + "port" + clientPort);
            }

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

    // @PostConstruct
    public ResponseModel sendAckToServer() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            int port = 54321;
            String payLoadMessage = "Successfully Receive ALL Services";
            byte[] dataBuffer = payLoadMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(dataBuffer, dataBuffer.length, address, port);
            socket.send(packet);
            System.out.println("Successfully send To Server");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error are found When client send Acknowledgement in Server" + e.getMessage());
            // logger.info("Errro are found When Service is Send To UE {}", e.getMessage());

        }
        return null;
    }
}
