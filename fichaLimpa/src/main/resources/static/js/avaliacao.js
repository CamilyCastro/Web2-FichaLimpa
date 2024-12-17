document.addEventListener('DOMContentLoaded', function () {
    const stars = document.querySelectorAll('.star');
    const avaliacaoInput = document.getElementById('avaliacaoInput');

    function setAvaliacao(value) {
        stars.forEach(star => {
            star.classList.remove('selected');
        });

        for (let i = 0; i < value; i++) {
            stars[i].classList.add('selected');
        }

        avaliacaoInput.value = value;
    }

    const avaliacao = parseInt(avaliacaoInput.value); 
    setAvaliacao(avaliacao); 

    stars.forEach(star => {
        star.addEventListener('click', function () {
            const value = parseInt(star.getAttribute('data-value')); 
            setAvaliacao(value);
        });
    });
});
