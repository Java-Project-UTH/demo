package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
<<<<<<< Updated upstream
=======
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.awt.Desktop;
import java.net.URI;
>>>>>>> Stashed changes

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		openHomePage();
	}

	private static void openHomePage() {
		String url = "http://localhost:8080";
		try {
<<<<<<< Updated upstream
			Runtime rt = Runtime.getRuntime();
			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
=======
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI("http://localhost:8080"));
			}
>>>>>>> Stashed changes
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
