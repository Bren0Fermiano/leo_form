
package form.com.leo_form.controler;

import form.com.leo_form.repository.EnvRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

@GetMapping("/api/alunos")
@ResponseBody
public List<Map<String, Object>> alunos() {

    List<Object[]> resultados = repository.buscarNomes();

    List<Map<String, Object>> lista = new ArrayList<>();

    for (Object[] aluno : resultados) {

        Map<String, Object> dados = new HashMap<>();

        dados.put("id", aluno[0]);
        dados.put("name", aluno[1]);
        dados.put("lastname", aluno[2]);

        lista.add(dados);
    }

    return lista;
}
}

