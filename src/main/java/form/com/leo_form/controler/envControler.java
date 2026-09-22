package form.com.leo_form.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class envControler {

    @PostMapping("/enviar")
    public String process(@ModelAttribute env form) {

        String sql = """
            INSERT INTO env (
                name, lastname, course, turn, date, cpf, cep,
                tela, telb, telf, email, org, benefic, nis, cgm,
                doc_hist, doc_cert, doc_rg, doc_luz, doc_vac, doc_nis, doc_rgr
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (
            Connection con = DriverManager.getConnection("jdbc:sqlite:./formulario.db");
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

            return """
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Inscrição realizada</title>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.3.1/css/all.min.css"
          crossorigin="anonymous"
          referrerpolicy="no-referrer">

    <style>
        * {
            box-sizing: border-box;
        }

        html,
        body {
            margin: 0;
            width: 100%;
            height: 100%;
            
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;

            font-family: Arial, sans-serif;
            background-color: #0f0f0f;
        }

        .step-details {
            width: 90%;
            max-width: 500px;
            padding: 50px 30px;

            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;

            background-color: #202020;
            border-radius: 12px;
            text-align: center;

            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.466);
        }

        .check {
            color: #88ca74;
            font-size: 60px;
            margin-bottom: 20px;
        }

        h1 {
            margin: 0 0 10px;
            color: white;
            font-size: 24px;
        }

        p {
            margin: 0;
            color: #cccccc;
            font-size: 16px;
        }
    </style>
</head>

<body>

    <div class="step-details">

        <i class="fa-regular fa-circle-check check"></i>

        <h1>Inscrição realizada!</h1>

        <p>Obrigado por se inscrever.</p>

    </div>

</body>

</html>


                    
                    """;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao processar inscrição: " + e.getMessage();
        }
    }

    private void setFile(PreparedStatement stmt, int index, org.springframework.web.multipart.MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            stmt.setBytes(index, file.getBytes());
        } else {
            stmt.setNull(index, Types.BLOB);
        }
    }
}