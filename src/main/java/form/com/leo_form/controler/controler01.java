package form.com.leo_form.controler;

import form.com.leo_form.repository.EnvRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/alunos/{id}")
    @ResponseBody
    public Map<String, Object> buscarAlunoCompleto(@PathVariable Long id) {
        env aluno = repository.findById(id).orElse(null);

        if (aluno == null) {
            return null;
        }

        Map<String, Object> response = new HashMap<>();
        
        response.put("id", aluno.getId());
        response.put("name", aluno.getName());
        response.put("lastname", aluno.getLastname());
        response.put("cgm", aluno.getCgm());
        response.put("course", aluno.getCourse());
        response.put("turn", aluno.getTurn());
        response.put("org", aluno.getOrg() != null ? Integer.parseInt(aluno.getOrg()) : 0);
        response.put("benefic", "1".equals(aluno.getBenefic()) || "true".equalsIgnoreCase(aluno.getBenefic()) ? 1 : 0);
        response.put("docHist", toBase64(aluno.getDocHistBytes()));
        response.put("docCert", toBase64(aluno.getDocCertBytes()));
        response.put("docRG", toBase64(aluno.getDocRGBytes()));
        response.put("docLuz", toBase64(aluno.getDocLuzBytes()));
        response.put("docVac", toBase64(aluno.getDocVacBytes()));
        response.put("docNis", toBase64(aluno.getDocNisBytes()));
        response.put("docRGR", toBase64(aluno.getDocRGRBytes()));

        return response;
    }

    private String toBase64(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        return Base64.getEncoder().encodeToString(bytes);
    }
}