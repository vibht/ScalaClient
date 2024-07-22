package com.server.application.scala.sockets;

import org.springframework.stereotype.Service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class RecieveSystemHealthToClientActiveMq {

    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = null;
        try {

            connection = factory.createConnection("root", "root");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue("TEST.QUEUE");
            MessageConsumer consumer = session.createConsumer(queue);

            TextMessage message = (TextMessage) consumer.receive();

            System.out.println("Message received: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
