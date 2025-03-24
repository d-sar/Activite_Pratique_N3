package ma.enset.activite_pratique_n3;

import lombok.Data;
import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.sql.DatabaseMetaData;
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
@Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
