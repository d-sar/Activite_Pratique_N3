package ma.enset.activite_pratique_n3.repository;

import ma.enset.activite_pratique_n3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.security.auth.callback.LanguageCallback;

public interface PatientRepository  extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContaining(String keyword, Pageable pageable);
}
