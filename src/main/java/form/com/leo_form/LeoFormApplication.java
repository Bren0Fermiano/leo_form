package form.com.leo_form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeoFormApplication {

    private static final String URL = "jdbc:sqlite:./formulario.db";

    public static void main(String[] args) {

        criarBanco();

        SpringApplication.run(LeoFormApplication.class, args);
    }

    private static void criarBanco() {

        String sql = """
            CREATE TABLE IF NOT EXISTS env (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name VARCHAR(100),
                lastname VARCHAR(100),
                course VARCHAR(100),
                turn VARCHAR(20),
                date VARCHAR(20),
                cpf VARCHAR(14),
                cep VARCHAR(10),
                tela VARCHAR(15),
                telb VARCHAR(15),
                telf VARCHAR(15),
                email VARCHAR(100),
                org VARCHAR(500),
                benefic VARCHAR(10),
                nis VARCHAR(30),
                cgm VARCHAR(20),
                doc_hist BLOB,
                doc_cert BLOB,
                doc_rg BLOB,
                doc_luz BLOB,
                doc_vac BLOB,
                doc_nis BLOB,
                doc_rgr BLOB
            )
            """;

        try (
            Connection con = DriverManager.getConnection(URL);
            Statement statement = con.createStatement()
        ) {

            statement.executeUpdate(sql);

            System.out.println("Banco criado em: " + new java.io.File("formulario.db").getAbsolutePath());
            System.out.println("Tabela env criada/verificada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return URL;
    }
}
//.\mvnw.cmd spring-boot:run