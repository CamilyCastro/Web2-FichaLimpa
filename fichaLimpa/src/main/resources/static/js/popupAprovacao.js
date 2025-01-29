/*document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    
    if (urlParams.get('success') === 'true') {
        const popup = document.getElementById('popup');
        const popupOverlay = document.getElementById('popup-overlay');

        popup.style.display = 'block';
        popupOverlay.style.display = 'block';

        setTimeout(() => {
            popup.style.display = 'none';
            popupOverlay.style.display = 'none';
        }, 1500);
    }
});*/
document.getElementById('form-publicacao').addEventListener('submit', function(event) {
    // Lógica para enviar a publicação
    // Exemplo: enviar a publicação via AJAX ou outro método
    
    // Depois que a publicação for enviada, defina o flag no localStorage
    localStorage.setItem('publicacaoEnviada', 'true');
    
    // Redireciona para o perfil (você pode ajustar a URL conforme necessário)
    //window.location.href = '/perfil';  // Ou a URL que você usa para o perfil
});

document.addEventListener('DOMContentLoaded', function() {
    // Verifica se a publicação foi enviada
    if (localStorage.getItem('publicacaoEnviada') === 'true') {
        const popupModal = new bootstrap.Modal(document.getElementById('popupModal'));
        popupModal.show();

        // Remove a flag do localStorage para não exibir o pop-up novamente
        localStorage.removeItem('publicacaoEnviada');
    }
});