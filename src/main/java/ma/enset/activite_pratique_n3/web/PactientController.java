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


@Controller
@AllArgsConstructor
public class PactientController {
    private final PatientRepository patientRepository;
    private PatientRepository PatientRepository;
    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name="page" , defaultValue = "0") int p , //spring MVC
                        @RequestParam(name="size" , defaultValue = "4") int s,
                        @RequestParam(name="Keyword" ,defaultValue ="") String kw
    ) {
        Page<Patient> pagePatients = PatientRepository.findByNomContains(kw,PageRequest.of(p, s));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",pagePatients.getNumber());
        model.addAttribute("Keyword",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id , String Keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"Keyword"+Keyword; // redireeger vers /delete
    }
}
