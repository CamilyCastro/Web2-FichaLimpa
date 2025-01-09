document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('mostrarSenha').addEventListener('click', function() {
      var senhaInput = document.getElementById('password');
      var senhaIcon = this.querySelector('i');
      
      if (senhaInput.type === 'password') {
        senhaInput.type = 'text'; 
        senhaIcon.classList.remove('bi-eye-slash');
        senhaIcon.classList.add('bi-eye'); 
      } else {
        senhaInput.type = 'password';
        senhaIcon.classList.remove('bi-eye'); 
        senhaIcon.classList.add('bi-eye-slash');
      }
    });
	
});