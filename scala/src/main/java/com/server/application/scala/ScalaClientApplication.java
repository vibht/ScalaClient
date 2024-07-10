package com.server.application.scala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.application.scala.sockets.XmppRequestRecievefromServer;

@SpringBootApplication
public class ScalaClientApplication {

	@Autowired
	private static XmppRequestRecievefromServer xmppService;



	public static void main(String[] args) {
		SpringApplication.run(ScalaClientApplication.class, args);
		Thread thread =new Thread(xmppService);
		thread.start();
		
	}

}
