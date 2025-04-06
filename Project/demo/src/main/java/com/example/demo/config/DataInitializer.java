package com.example.demo.config;

import com.example.demo.model.Court;
import com.example.demo.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public void run(String... args) {
        // Add sample courts
        Court court1 = new Court();
        court1.setName("Tennis Court Binh Thanh");
        court1.setLocation("Binh Thanh, TP.HCM");
        court1.setAvailable(true);
        courtRepository.save(court1);

        Court court2 = new Court();
        court2.setName("Tennis Court Binh Tan");
        court2.setLocation("Binh Tan, TP.HCM");
        court2.setAvailable(true);
        courtRepository.save(court2);

        Court court3 = new Court();
        court3.setName("Tennis Court Go Vap");
        court3.setLocation("Go Vap, TP.HCM");
        court3.setAvailable(true);
        courtRepository.save(court3);

        Court court4 = new Court();
        court4.setName("Tennis Court Thu Duc");
        court4.setLocation("Thu Duc, TP.HCM");
        court4.setAvailable(true);
        courtRepository.save(court4);
        
    }
}