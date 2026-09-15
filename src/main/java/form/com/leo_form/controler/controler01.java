package form.com.leo_form.controler;

import form.com.leo_form.repository.EnvRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controler01 {

    private final EnvRepo repository;

    public controler01(EnvRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public String employees(Model model) {

        model.addAttribute("alunos", repository.findAll());

        return "employees";
    }
}

