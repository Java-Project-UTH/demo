package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< Updated upstream

@SpringBootApplication
=======
<<<<<<< Updated upstream
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
=======
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
>>>>>>> Stashed changes
>>>>>>> Stashed changes
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
<<<<<<< Updated upstream
=======
		openHomePage();
	}

<<<<<<< Updated upstream
	private static void openHomePage() {
		String url = "http://localhost:8080";
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (Exception e) {
			e.printStackTrace();
=======
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (!GraphicsEnvironment.isHeadless()) {
			try {
				Desktop.getDesktop().browse(new URI("http://localhost:8080"));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
>>>>>>> Stashed changes
		}
>>>>>>> Stashed changes
	}

}
