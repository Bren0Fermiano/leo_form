package form.com.leo_form.controler;
import form.com.leo_form.LeoFormApplication;
import form.com.leo_form.env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class envControler {

    @PostMapping("/enviar")
    public String process(@ModelAttribute env form) {
        
        LeoFormApplication lf = new  LeoFormApplication();
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



        
        String sql = "INSERT INTO alunos (nome, sobrenome, curso, data_nasc, cpf, cep, telefone_a, telefone_b, telefone_fixo, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        	
		try (Connection con  = DriverManager.getConnection(lf.getUrl(),lf.getUser(),lf.getKey());
			  PreparedStatement stmt = con.prepareStatement(sql);){
			
            stmt.setString(1, form.getName());
            stmt.setString(2, form.getLastname());
            stmt.setString(3, form.getCourse());
            stmt.setString(4, form.getDate());
            stmt.setString(5, form.getCpf());
            stmt.setString(6, form.getCep());
            stmt.setString(7, form.getTelA());
            stmt.setString(8, form.getTelB());
            stmt.setString(9, form.getTelF());
            stmt.setString(10, form.getEmail());
			 stmt .executeUpdate();

		} catch (Exception e) {
			  System.out.println("Erro ao criar o banco: " + e.getMessage());
		}
	 
    
        // Retorno exibido na tela do navegador ao enviar
        return "Formulário enviado com sucesso!<br><br>" +
               "<b>Nome:</b> " + form.getName() + " " + form.getLastname() + "<br>" +
               "<b>Curso:</b> " + form.getCourse() + "<br>" +
               "<b>E-mail:</b> " + form.getEmail() + "<br>" +
               "<b>CPF:</b> " + form.getCpf();

             
    }


}