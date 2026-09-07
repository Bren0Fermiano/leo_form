
const cpfInpt = document.getElementById('cpf');

cpfInpt.addEventListener('input', (e) => {
  let value = e.target.value;
  value = value.replace(/\D/g, '');
  value = value.substring(0, 11);
  value = value.replace(/(\d{3})(\d)/, '$1.$2');
  value = value.replace(/(\d{3})\.(\d{3})(\d)/, '$1.$2.$3');
  value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4');

  e.target.value = value;
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