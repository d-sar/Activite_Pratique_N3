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
        Patient patient = new Patient();
        patient.setId(null);
        patient.setNom("bbb");
        patient.setDateNaissance(new Date(System.currentTimeMillis()));
        patient.setMalade(false);
        patient.setScore(43);
        patientRepository.save(patient);
        Patient patient1 = Patient.builder()
                .nom("Ali")
                .dateNaissance(new Date(System.currentTimeMillis()))
                .score(20)
                .malade(true)
                .build();
        patientRepository.save(patient1);
        Patient patient2 = Patient.builder()
                .nom("CCC")
                .dateNaissance(new Date(System.currentTimeMillis()))
                .score(70)
                .malade(true)
                .build();
        patientRepository.save(patient2);


        Patient patient5 = Patient.builder()
                .nom("AAA")
                .dateNaissance(new Date(System.currentTimeMillis()))
                .score(52)
                .malade(true)
                .build();
        Patient patient4 = Patient.builder()
                .nom("SARA")
                .dateNaissance(new Date(System.currentTimeMillis()))
                .score(20)
                .malade(true)
                .build();
        patientRepository.save(patient5);
        patientRepository.save(patient4);
    }
}
