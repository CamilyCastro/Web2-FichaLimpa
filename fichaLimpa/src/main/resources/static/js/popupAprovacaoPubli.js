document.getElementById('publi-form').addEventListener('submit', function(event) {
  
    localStorage.setItem('publicacaoEnviada', 'true');
    alert("Publicação encaminhada para avaliação, aguarde até que seja aprovada.");
});