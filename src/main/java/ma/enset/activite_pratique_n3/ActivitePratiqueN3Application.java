package ma.enset.activite_pratique_n3;

import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.Date;

@SpringBootApplication
public class ActivitePratiqueN3Application  implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {

        SpringApplication.run(ActivitePratiqueN3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
