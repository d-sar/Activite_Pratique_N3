package ma.enset.activite_pratique_n3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotEmpty @size(min = 4,max = 20)
    private String nom;
    private LocalDate  dateNaissance;
    private boolean malade;
    //@ min(10)
    private int score;
}