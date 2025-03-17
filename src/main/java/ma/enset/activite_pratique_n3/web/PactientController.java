package ma.enset.activite_pratique_n3.web;

import lombok.AllArgsConstructor;
import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PactientController {
    private PatientRepository PatientRepository;
    @GetMapping("/index")
    public String index(Model model , @RequestParam(name="page" , defaultValue = "0") int p , //spring MVC
                                      @RequestParam(name="size" , defaultValue = "1") int s) {
        Page<Patient> pagePatients = PatientRepository.findAll(PageRequest.of(p, s));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",pagePatients.getNumber());
        return "patients";
    }


}
