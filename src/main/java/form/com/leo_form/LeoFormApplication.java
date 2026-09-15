package form.com.leo_form;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan

public class LeoFormApplication {
 		private static String url="jdbc:h2:./form;AUTO_SERVER=TRUE";
		private static String user ="lf";
		private static String key = "datakey";
		
	public static void main(String[] args) {
		SpringApplication.run(LeoFormApplication.class, args);

		
		
		try (Connection con  = DriverManager.getConnection(url,user,key);
			Statement statement = con.createStatement(); ){
			
		     String sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                     "nome VARCHAR(100), " +
                     "sobrenome VARCHAR(100), " +
                     "curso VARCHAR(100), " +
                     "data_nasc VARCHAR(20), " +
                     "cpf VARCHAR(14), " +
                     "cep VARCHAR(10), " +
                     "telefone_a VARCHAR(15), " +
                     "telefone_b VARCHAR(15), " +
                     "telefone_fixo VARCHAR(15), " +
                     "email VARCHAR(100))";

						 statement.executeUpdate(sql);

		} catch (Exception e) {
			  System.out.println("Erro ao criar o banco: " + e.getMessage());
		}
  

	
	}

    public String  getUrl(){
       return url;
	}

	 public String  getUser(){
       return user;
	}

	 public String  getKey(){
       return key;
	}
}
//.\mvnw.cmd spring-boot:run
//.\ngrok.exe http 8080