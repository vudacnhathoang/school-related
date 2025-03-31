document.addEventListener('DOMContentLoaded', function() {

const error = document.getElementById('error-msg');
const urlParams = new URLSearchParams(window.location.search);
const success = urlParams.get('success');


if(success) {
    error.style.color = 'green';
    error.style.display = 'block';
    setTimeout(function() {
        error.style.display = 'none';
    }, 3000);
}
else 
{
        error.style.display = 'none';
    }

});