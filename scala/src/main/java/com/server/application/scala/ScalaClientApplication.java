package com.server.application.scala;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.application.scala.service.SystemHealthPdf;
import com.server.application.scala.sockets.RecieveSystemHealthToClientActiveMq;
import com.server.application.scala.sockets.XmppRequestRecievefromServer;

@SpringBootApplication
public class ScalaClientApplication {

	@Autowired
	private static XmppRequestRecievefromServer xmppService;



	public static void main(String[] args) throws FileNotFoundException {

		SystemHealthPdf systemHealthPdf =new SystemHealthPdf();
		systemHealthPdf.createPDF();

		Thread secondAppThread = new Thread(new Runnable() {
            @Override
            public void run() {
                RecieveSystemHealthToClientActiveMq.mains();
            }
        });

        secondAppThread.start();
    

		SpringApplication.run(ScalaClientApplication.class, args);
		Thread thread = new Thread(xmppService);
		thread.start();

	}

}


