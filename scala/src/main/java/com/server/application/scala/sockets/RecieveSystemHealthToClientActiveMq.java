package com.server.application.scala.sockets;

import org.springframework.stereotype.Service;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Service
public class RecieveSystemHealthToClientActiveMq {

    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = null;
        try {
            connection = factory.createConnection("root", "root");
            connection.start();
            System.out.println("Connection started.");

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("Session created.");

            Queue queue = session.createQueue("SNMP.QUEUE");

            MessageConsumer consumer = session.createConsumer(queue);
            System.out.println("Consumer created for SNMP.QUEUE.");

            Message message = consumer.receive(20000); // Wait for 20 seconds
            System.out.println("Message received: " + (message != null));

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received message: " + text);

                // Send confirmation
                Queue confirmationQueue = session.createQueue("CONFIRMATION.SNMP.QUEUE");
                MessageProducer producer = session.createProducer(confirmationQueue);
                String confirmationText = "Confirmation for: " + text;
                TextMessage confirmationMessage = session.createTextMessage(confirmationText);
                producer.send(confirmationMessage);
                System.out.println("Sent confirmation: " + confirmationText);
            } else if (message == null) {
                System.out.println("No message received within the given timeout.");
            } else {
                System.out.println("Received non-text message: " + message);
            }

            // Clean up
            session.close();
            System.out.println("Session closed.");

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
