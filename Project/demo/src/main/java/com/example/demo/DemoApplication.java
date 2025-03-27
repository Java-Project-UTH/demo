package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
			Runtime rt = Runtime.getRuntime();
			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
