$('#img1b').on('click', function() {
  $('#img1').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img1').slideToggle('slow');
  });
});

$('#img2b').on('click', function() {
  $('#img2').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img2').slideToggle('slow');
  });
});

$('#img3b').on('click', function() {
  $('#img3').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img3').slideToggle('slow');
  });
});

$('#img4b').on('click', function() {
  $('#img4').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img4').slideToggle('slow');
  });
});

$('#img5b').on('click', function() {
  $('#img5').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img5').slideToggle('slow');
  });
});

$('#img6b').on('click', function() {
  $('#img6').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img6').slideToggle('slow');
  });
});

$('#img7b').on('click', function() {
  $('#img7').slideToggle('slow');
});

$('.menu-link').each(function() {
  $(this).on('click', function() {
    $('#img7').slideToggle('slow');
  });
});

fetch("/api/alunos")
    .then(response => {

        if (!response.ok) {
            throw new Error("Erro HTTP: " + response.status);
        }

        return response.json();
    })
    .then(alunos => {

        console.log("Alunos recebidos:", alunos);

        const datalist = document.getElementById("partici");

        if (!datalist) {
            console.error("Não encontrou o datalist #partici");
            return;
        }

        alunos.forEach(aluno => {

            const option = document.createElement("option");


            option.value = `${aluno.name} ${aluno.lastname}`;
            option.text = `${aluno.name} ${aluno.lastname}`;

            option.dataset.id = aluno.id;

            datalist.appendChild(option);
        });

        console.log("Opções criadas:", datalist.children.length);
    })
    .catch(error => {
        console.error("Erro ao carregar alunos:", error);
    });


