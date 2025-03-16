package com.example.demo.config;

import com.example.demo.model.Court;
import com.example.demo.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public void run(String... args) {
        // Add sample courts
        Court court1 = new Court();
        court1.setName("Tennis Court A");
        court1.setLocation("Main Building");
        court1.setAvailable(true);
        courtRepository.save(court1);

        Court court2 = new Court();
        court2.setName("Tennis Court B");
        court2.setLocation("East Wing");
        court2.setAvailable(true);
        courtRepository.save(court2);

        Court court3 = new Court();
        court3.setName("Tennis Court C");
        court3.setLocation("West Wing");
        court3.setAvailable(true);
        courtRepository.save(court3);
    }
}