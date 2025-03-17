package ma.enset.activite_pratique_n3.web;

import lombok.AllArgsConstructor;
import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PactientController {
    private PatientRepository PatientRepository;
    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> listPatients = PatientRepository.findAll();
        model.addAttribute("listPatients", listPatients);
        return "patients";
    }


}
