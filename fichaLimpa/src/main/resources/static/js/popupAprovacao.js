document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    
    if (urlParams.get('success') === 'true') {
        const popup = document.getElementById('popup');
        const popupOverlay = document.getElementById('popup-overlay');

        popup.style.display = 'block';
        popupOverlay.style.display = 'block';

        setTimeout(() => {
            popup.style.display = 'none';
            popupOverlay.style.display = 'none';
        }, 3000);
    }
});
