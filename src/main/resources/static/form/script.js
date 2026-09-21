
const cpfInpt = document.getElementById('cpf');
if (cpfInpt) {
  cpfInpt.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '').substring(0, 11);
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})\.(\d{3})(\d)/, '$1.$2.$3');
    value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4');
    e.target.value = value;
  });
}

const cepInpt = document.getElementById('cep');
if (cepInpt) {
  cepInpt.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '').substring(0, 8);
    value = value.replace(/^(\d{5})(\d)/, '$1-$2');
    e.target.value = value;
  });

  cepInpt.addEventListener('blur', (e) => {
    const cleanCep = e.target.value.replace(/\D/g, '');
    if (cleanCep.length === 8) {
      fetch(`https://viacep.com.br/ws/${cleanCep}/json/`)
        .then(response => response.json())
        .then(data => {

        })
     
    }
  });
}

function applyPhoneMask(e) {
  let value = e.target.value.replace(/\D/g, '').substring(0, 11);
  value = value.replace(/^(\d{2})(\d)/g, '($1) $2');
  value = value.replace(/(\d{5})(\d)/, '$1-$2');
  e.target.value = value;
}

const telAInpt = document.getElementById('telA');
const telBInpt = document.getElementById('telfB');

if (telAInpt) telAInpt.addEventListener('input', applyPhoneMask);
if (telBInpt) telBInpt.addEventListener('input', applyPhoneMask);

const telFInpt = document.getElementById('telfF');
if (telFInpt) {
  telFInpt.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '').substring(0, 10);
    value = value.replace(/^(\d{2})(\d)/g, '($1) $2');
    value = value.replace(/(\d{4})(\d)/, '$1-$2');
    e.target.value = value;
  });
}

const nisInpt = document.querySelector('input[name="nis"]');
if (nisInpt) {
  nisInpt.addEventListener('input', (e) => {
    let value = e.target.value.replace(/\D/g, '').substring(0, 11);
    value = value.replace(/^(\d{3})(\d)/, '$1.$2');
    value = value.replace(/^(\d{3})\.(\d{5})(\d)/, '$1.$2.$3');
    value = value.replace(/(\d{3})\.(\d{5})\.(\d{2})(\d)/, '$1.$2.$3-$4');
    e.target.value = value;
  });
}

const cgmInpt = document.getElementById('cgm');
if (cgmInpt) {
  cgmInpt.addEventListener('input', (e) => {
    e.target.value = e.target.value.replace(/\D/g, '').substring(0, 9);
  });
}

const courseInpt = document.getElementById('selectC');
const turnInpt = document.getElementById('selectT');
    courseInpt.addEventListener('change', function(event) {
if(courseInpt.value=="tds"||courseInpt.value=="enf"){
  document.querySelector('#selectT option[value="n"]').disabled = true;
 if (turnInpt.value === "n") {
            turnInpt.value = "m";
        }
}else{
  document.querySelector('#selectT option[value="n"]').disabled = false;
}
   });


const backBtn = document.getElementById('prevbtn');
const nextBtn = document.getElementById('nextbtn');
const steps = document.querySelectorAll('.step'); 
const formSteps = document.querySelectorAll('.form'); 

let stepIndex = 0; 

function updtStepper(index) {

  steps.forEach((step, i) => {
    step.classList.remove('active', 'completed');
    
    if (i < index) {
      step.classList.add('completed');
    } else if (i === index) {
      step.classList.add('active'); 
    }
  }); 


  formSteps.forEach((form, i) => {
    if (i === index) {
      form.classList.add('active');
    } else {
      form.classList.remove('active');
    }
  });
}

steps.forEach((step, index) => {
  step.addEventListener('click', () => {
    stepIndex = index;
    updtStepper(stepIndex);
  });
});


nextBtn.addEventListener('click', () => {
  if (stepIndex < steps.length - 1) {
    stepIndex++;
    updtStepper(stepIndex);
  }
});

backBtn.addEventListener('click', () => {
  if (stepIndex > 0) {
    stepIndex--;
    updtStepper(stepIndex);
  }
});

const inptFiles = document.querySelectorAll('.input');

inptFiles.forEach((input) => {
    input.addEventListener('change', function(event) {
        const file = event.target.files[0];
        const previewImg = input.nextElementSibling.nextElementSibling;
        const label = document.querySelector(`label[for="${input.id}"]`)
          if(file){
             previewImg.src = URL.createObjectURL(file);
            previewImg.style.display = 'block';
            label.style.borderColor= '#28a745'
            label.innerText ='Documento Anexado'
          }else{
            previewImg.style.display = 'none';
           
          }
    });
});