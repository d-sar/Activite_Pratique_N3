package ma.enset.activite_pratique_n3.repository;

import ma.enset.activite_pratique_n3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.security.auth.callback.LanguageCallback;

public interface PatientRepository  extends JpaRepository<Patient, Long> {
    //METHODE 1
    Page<Patient> findByNomContains(String keyword, Pageable pageable); // pageable pour faire la pageination
    //methode 2
    @Query("select p from Patient p where p.nom like : x")
    Page<Patient> cherche(@Param("x") String keyword, Pageable pageable);
}
