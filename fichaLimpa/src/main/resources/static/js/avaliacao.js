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

stars.forEach(star => {
	star.addEventListener('click', () => {
		const value = star.getAttribute('data-value');
		setAvaliacao(value);
	});
});
o
setAvaliacao(0);

