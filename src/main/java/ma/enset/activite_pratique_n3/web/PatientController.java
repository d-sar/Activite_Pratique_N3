package ma.enset.activite_pratique_n3.web;

import lombok.AllArgsConstructor;
import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model ,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam Long id , String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;// redireeger vers /delete
    }
    @GetMapping("/admin/form")
    public String showForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "form";  // Nom du fichier du formulaire
    }
    @PostMapping("/admin/save")
    public String savePatient(@ModelAttribute  Patient patient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println("Tentative d'ajout du patient : " + patient);

        if (bindingResult.hasErrors()) {
            System.out.println("Erreurs de validation : " + bindingResult.getAllErrors());
            return "form";
        }

        patientRepository.save(patient);
        System.out.println("Patient ajouté avec succès !");
        redirectAttributes.addFlashAttribute("message", "Patient ajouté avec succès !");
        return "redirect:/user/index";
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(@RequestParam (name = "id") Long id, Model model) {
       Patient patient = patientRepository.findById(id).get();
       model.addAttribute("patient", patient);
       return "editPatient";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }
}
