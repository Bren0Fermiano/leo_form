
$(document).ready(function() {
  for (let i = 1; i <= 7; i++) {
    $(`#img${i}b`).on('click', function() {
      $(`#img${i}`).slideToggle('slow');
    });
  }
});

let alunosLista = [];
let benefic = 0;
let org = 0;

fetch("/api/alunos")
  .then(response => response.json())
  .then(dados => {
    alunosLista = dados;
    const select = document.getElementById("partici");
    select.innerHTML = '<option value="">Selecione um aluno...</option>';

    alunosLista.forEach(aluno => {
      const option = document.createElement("option");
      option.value = aluno.id;
      option.textContent = `${aluno.name} ${aluno.lastname}`;
      select.appendChild(option);
    });
  })
  .catch(error => console.error("Erro ao carregar lista de alunos:", error));
document.getElementById("partici").addEventListener("change", function () {
  const id = this.value;
  if (!id) return;

  fetch(`/api/alunos/${id}`)
    .then(response => response.json())
    .then(aluno => {
      if (!aluno) return;

      document.getElementById("name").value = aluno.name || '';
      document.getElementById("lastname").value = aluno.lastname || '';
      document.getElementById("cgm").value = aluno.cgm || '';
      document.getElementById("cs").value = aluno.course || '';
      document.getElementById("tu").value = aluno.turn || '';

      benefic = aluno.benefic || 0;
      org = aluno.org || 0;

      const docs = {
        docHist_container: aluno.docHist,
        docCert_container: aluno.docCert,
        docRG_container: aluno.docRG,
        docRGR_container: aluno.docRGR,
        docVac_container: aluno.docVac,
        docLuz_container: aluno.docLuz,
        docNis_container: aluno.docNis
      };

      Object.keys(docs).forEach(containerId => {
        const container = document.getElementById(containerId);
        const base64Data = docs[containerId];

        if (container) {
          if (base64Data && base64Data.trim() !== "") {
            container.innerHTML = `<img src="data:image/jpeg;base64,${base64Data}" style="max-width: 100%; height: auto; border-radius: 4px; display: block; margin: 0 auto;">`;
          } else {
            container.innerHTML = `<p style="padding: 10px; color: #666;">Nenhum documento enviado.</p>`;
          }
        }
      });
    })
    .catch(error => console.error("Erro ao carregar dados do aluno:", error));
});
function calculate() {
  const entrv = parseFloat(document.getElementById("inpt_entrv").value) || 0;
  const ma6 = parseFloat(document.getElementById("ma6").value) || 0;
  const ma7 = parseFloat(document.getElementById("ma7").value) || 0;
  const ma8 = parseFloat(document.getElementById("ma8").value) || 0;
  const ma9 = parseFloat(document.getElementById("ma9").value) || 0;

  const pa6 = parseFloat(document.getElementById("pa6").value) || 0;
  const pa7 = parseFloat(document.getElementById("pa7").value) || 0;
  const pa8 = parseFloat(document.getElementById("pa8").value) || 0;
  const pa9 = parseFloat(document.getElementById("pa9").value) || 0;

  const mediP = (pa6 + pa7 + pa8 + pa9) / 4;
  const mediM = (ma6 + ma7 + ma8 + ma9) / 4;
  const mamp = (ma6 + ma7 + ma8 + ma9 + pa6 + pa7 + pa8 + pa9) / 8;

  let beneficP = 0;
  if (org === 1) beneficP = 85;
  else if (org === 2) beneficP = 80;
  else if (org === 3) beneficP = 75;
  else if (org === 4) beneficP = 70;

  if (benefic) {
    beneficP += 10;
  }

  const total = mamp + beneficP + entrv;

  document.getElementById("mediP").value = mediP.toFixed(2);
  document.getElementById("mediM").value = mediM.toFixed(2);
  document.getElementById("mf").value = mamp.toFixed(2);
  document.getElementById("benefic").value = beneficP.toFixed(2);
  document.getElementById("total").value = total.toFixed(2);
}

// olha eu so joguei no chat gpt e por algum motivo ele conseguiu resolver o ploblema do caminho da api
