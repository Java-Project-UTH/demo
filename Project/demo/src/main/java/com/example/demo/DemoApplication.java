package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8080"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
