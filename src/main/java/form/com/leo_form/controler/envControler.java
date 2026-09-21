package form.com.leo_form.controler;

import form.com.leo_form.LeoFormApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class envControler {

    @PostMapping("/enviar")
    public String process(@ModelAttribute env form) {

        LeoFormApplication lf = new LeoFormApplication();

        String sql = """
            INSERT INTO env (
                name,
                lastname,
                course,
                turn,
                date,
                cpf,
                cep,
                tela,
                telb,
                telf,
                email,
                org,
                benefic,
                nis,
                cgm,
                doc_hist,
                doc_cert,
                doc_rg,
                doc_luz,
                doc_vac,
                doc_nis,
                doc_rgr
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (
            Connection con = DriverManager.getConnection(
                "jdbc:sqlite:./formulario.db"
            );

            PreparedStatement stmt = con.prepareStatement(sql)
        ) {

            stmt.setString(1, form.getName());
            stmt.setString(2, form.getLastname());
            stmt.setString(3, form.getCourse());
            stmt.setString(4, form.getTurn());
            stmt.setString(5, form.getDate());
            stmt.setString(6, form.getCpf());
            stmt.setString(7, form.getCep());
            stmt.setString(8, form.getTelA());
            stmt.setString(9, form.getTelB());
            stmt.setString(10, form.getTelF());
            stmt.setString(11, form.getEmail());
            stmt.setString(12, form.getOrg());
            stmt.setString(13, form.getBenefic());
            stmt.setString(14, form.getNis());
            stmt.setString(15, form.getCgm());

            setFile(stmt, 16, form.getDocHist());
            setFile(stmt, 17, form.getDocCert());
            setFile(stmt, 18, form.getDocRG());
            setFile(stmt, 19, form.getDocLuz());
            setFile(stmt, 20, form.getDocVac());
            setFile(stmt, 21, form.getDocNis());
            setFile(stmt, 22, form.getDocRGR());

            stmt.executeUpdate();

            System.out.println("Inscrição salva guri!");

            return """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <title>Documentos recebidos</title>
                </head>
                <body>
                    <h1>Documentos recebidos</h1>

                    <h2>Histórico escolar</h2>
                    <img src="data:image/jpeg;base64,%s"
                         style="max-width: 300px;">

                    <h2>Certidão de Nascimento</h2>
                    <img src="data:image/jpeg;base64,%s"
                         style="max-width: 300px;">

                    <h2>RG e CPF</h2>
                    <img src="data:image/jpeg;base64,%s"
                         style="max-width: 300px;">
                </body>
                </html>
                """.formatted(
                    toBase64(form.getDocHist()),
                    toBase64(form.getDocCert()),
                    toBase64(form.getDocRG())
                );

        } catch (Exception e) {
            e.printStackTrace();
            return "inscrição falha com exito " + e.getMessage();
        }
    }

    private void setFile(
            PreparedStatement stmt,
            int index,
            org.springframework.web.multipart.MultipartFile file
    ) throws Exception {

        if (file != null && !file.isEmpty()) {
            stmt.setBytes(index, file.getBytes());
        } else {
            stmt.setNull(index, Types.BLOB);
        }
    }

    private String toBase64(
            org.springframework.web.multipart.MultipartFile file
    ) {

        try {
            if (file == null || file.isEmpty()) {
                return "";
            }

            return java.util.Base64.getEncoder()
                    .encodeToString(file.getBytes());

        } catch (Exception e) {
            return "";
        }
    }


    @GetMapping("/api/alunos/{id}")
public ResponseEntity<Map<String, Object>> getAlunoById(@PathVariable Long id) {
    String sql = "SELECT * FROM env WHERE id = ?";
    
    try (Connection con = DriverManager.getConnection("jdbc:sqlite:./formulario.db");
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Map<String, Object> data = new HashMap<>();
            
            data.put("docHist", toBase64FromBytes(rs.getBytes("doc_hist")));
            data.put("docCert", toBase64FromBytes(rs.getBytes("doc_cert")));
            data.put("docRG", toBase64FromBytes(rs.getBytes("doc_rg")));
            data.put("docLuz", toBase64FromBytes(rs.getBytes("doc_luz")));
            data.put("docVac", toBase64FromBytes(rs.getBytes("doc_vac")));
            data.put("docNis", toBase64FromBytes(rs.getBytes("doc_nis")));
            data.put("docRGR", toBase64FromBytes(rs.getBytes("doc_rgr")));

            return ResponseEntity.ok(data);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ResponseEntity.notFound().build();
}

private String toBase64FromBytes(byte[] bytes) {
    if (bytes == null || bytes.length == 0) return "";
    return java.util.Base64.getEncoder().encodeToString(bytes);
}
}