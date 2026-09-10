package form.com.leo_form.controler;

import form.com.leo_form.env;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class envControler {

    @PostMapping("/enviar")
    public String process(@ModelAttribute env form) {
        
        // Print de verificação no terminal/console do Spring
        System.out.println("==========================================");
        System.out.println("      DADOS RECEBIDOS DO FORMULÁRIO       ");
        System.out.println("==========================================");
        System.out.println("Nome Completo : " + form.getName() + " " + form.getLastname());
        System.out.println("Curso         : " + form.getCourse());
        System.out.println("Data Nasc.    : " + form.getDate());
        System.out.println("CPF           : " + form.getCpf());
        System.out.println("CEP           : " + form.getCep());
        System.out.println("Telefone A    : " + form.getTelA());
        System.out.println("Telefone B    : " + form.getTelB());
        System.out.println("Telefone Fixo : " + form.getTelF());
        System.out.println("E-mail        : " + form.getEmail());
        System.out.println("==========================================");

        // Retorno exibido na tela do navegador ao enviar
        return "Formulário enviado com sucesso!<br><br>" +
               "<b>Nome:</b> " + form.getName() + " " + form.getLastname() + "<br>" +
               "<b>Curso:</b> " + form.getCourse() + "<br>" +
               "<b>E-mail:</b> " + form.getEmail() + "<br>" +
               "<b>CPF:</b> " + form.getCpf();
    }
}