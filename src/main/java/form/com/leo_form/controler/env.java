package form.com.leo_form.controler;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Base64;

@Entity
public class env {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String course;
    private String turn;
    private String date;
    private String cpf;
    private String cep;

    @Column(name = "tela")
    private String telA;

    @Column(name = "telb")
    private String telB;

    @Column(name = "telf")
    private String telF;

    private String email;
    private String org;
    private String benefic;
    private String nis;
    private String cgm;

    @Transient
    private MultipartFile docHist;
    @Transient
    private MultipartFile docCert;
    @Transient
    private MultipartFile docRG;
    @Transient
    private MultipartFile docLuz;
    @Transient
    private MultipartFile docVac;
    @Transient
    private MultipartFile docNis;
    @Transient
    private MultipartFile docRGR;

    @Column(name = "doc_hist")
    private byte[] docHistBytes;

    @Column(name = "doc_cert")
    private byte[] docCertBytes;

    @Column(name = "doc_rg")
    private byte[] docRGBytes;

    @Column(name = "doc_luz")
    private byte[] docLuzBytes;

    @Column(name = "doc_vac")
    private byte[] docVacBytes;

    @Column(name = "doc_nis")
    private byte[] docNisBytes;

    @Column(name = "doc_rgr")
    private byte[] docRGRBytes;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getTurn() { return turn; }
    public void setTurn(String turn) { this.turn = turn; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getTelA() { return telA; }
    public void setTelA(String telA) { this.telA = telA; }

    public String getTelB() { return telB; }
    public void setTelB(String telB) { this.telB = telB; }

    public String getTelF() { return telF; }
    public void setTelF(String telF) { this.telF = telF; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOrg() { return org; }
    public void setOrg(String org) { this.org = org; }

    public String getBenefic() { return benefic; }
    public void setBenefic(String benefic) { this.benefic = benefic; }

    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }

    public String getCgm() { return cgm; }
    public void setCgm(String cgm) { this.cgm = cgm; }

    public MultipartFile getDocHist() { return docHist; }
    public void setDocHist(MultipartFile docHist) { this.docHist = docHist; }

    public MultipartFile getDocCert() { return docCert; }
    public void setDocCert(MultipartFile docCert) { this.docCert = docCert; }

    public MultipartFile getDocRG() { return docRG; }
    public void setDocRG(MultipartFile docRG) { this.docRG = docRG; }

    public MultipartFile getDocLuz() { return docLuz; }
    public void setDocLuz(MultipartFile docLuz) { this.docLuz = docLuz; }

    public MultipartFile getDocVac() { return docVac; }
    public void setDocVac(MultipartFile docVac) { this.docVac = docVac; }

    public MultipartFile getDocNis() { return docNis; }
    public void setDocNis(MultipartFile docNis) { this.docNis = docNis; }

    public MultipartFile getDocRGR() { return docRGR; }
    public void setDocRGR(MultipartFile docRGR) { this.docRGR = docRGR; }

    public byte[] getDocHistBytes() { return docHistBytes; }
    public void setDocHistBytes(byte[] docHistBytes) { this.docHistBytes = docHistBytes; }

    public byte[] getDocCertBytes() { return docCertBytes; }
    public void setDocCertBytes(byte[] docCertBytes) { this.docCertBytes = docCertBytes; }

    public byte[] getDocRGBytes() { return docRGBytes; }
    public void setDocRGBytes(byte[] docRGBytes) { this.docRGBytes = docRGBytes; }

    public byte[] getDocLuzBytes() { return docLuzBytes; }
    public void setDocLuzBytes(byte[] docLuzBytes) { this.docLuzBytes = docLuzBytes; }

    public byte[] getDocVacBytes() { return docVacBytes; }
    public void setDocVacBytes(byte[] docVacBytes) { this.docVacBytes = docVacBytes; }

    public byte[] getDocNisBytes() { return docNisBytes; }
    public void setDocNisBytes(byte[] docNisBytes) { this.docNisBytes = docNisBytes; }

    public byte[] getDocRGRBytes() { return docRGRBytes; }
    public void setDocRGRBytes(byte[] docRGRBytes) { this.docRGRBytes = docRGRBytes; }
}