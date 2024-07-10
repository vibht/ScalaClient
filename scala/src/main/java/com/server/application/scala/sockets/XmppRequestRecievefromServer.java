package com.server.application.scala.sockets;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;

@Service
public class XmppRequestRecievefromServer implements Runnable{
    public XmppRequestRecievefromServer(){
        recieveServiceFromServer();
    }

    public  String recieveServiceFromServer() {

        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("user3", "123456")
                    .setXmppDomain("gui.coraltele.com")
                    .setHost("gui.coraltele.com")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.required) // Change security mode
                    .build();

            AbstractXMPPConnection connection = new XMPPTCPConnection(config);
            connection.connect().login();

            ChatManager chatManager = ChatManager.getInstanceFor(connection);
            chatManager.addIncomingListener(new IncomingChatMessageListener() {

                @Override
                public void newIncomingMessage(EntityBareJid arg0, Message arg1, Chat arg2) {
                    System.out.println("Received message: " + arg1.getBody());
                }

            });

            Thread.sleep(Long.MAX_VALUE);
            connection.disconnect();

            return "Receive Message Service Successfully";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";

    }

    @Override
    public void run() {
        recieveServiceFromServer();
    }
}
