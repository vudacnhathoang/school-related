
document.addEventListener('DOMContentLoaded', function() {


const profilebtn = document.getElementById('profilebtn');
const profile = document.getElementById('profile');
const reservationbtn = document.getElementById('reservationbtn');
const reservation = document.getElementById('reservations');
const menubtn = document.getElementById('adminmenu');
const tablebtn = document.getElementById('tablesadmin');
const menu = document.getElementById('menu');
const table = document.getElementById('tables');
const alertbox = document.getElementById('alertbox');
const urlParams = new URLSearchParams(window.location.search);
const success = urlParams.get('success');
const error = urlParams.get('error');
const togglename = document.getElementById('togglename');
const togglesurname = document.getElementById('togglesurname');
const newname = document.getElementById('new_name');
const newsurname = document.getElementById('new_surname');
const namefood = document.getElementById('namefood');
const pricefood = document.getElementById('pricefood');
const editsubmit = document.querySelectorAll('.submit_edit');
const foodsubmit = document.getElementById('foodsubmit');
const page = urlParams.get('page');

foodsubmit.addEventListener('click', function(event) {
    const error = document.getElementById('errorfood');
    if (error && error.textContent.trim() !== '') {
        event.preventDefault();
    }
    if (namefood.value === ''|| pricefood.value === '') {
       event.preventDefault();
    }
});

namefood.addEventListener('input', function() {
    const error = document.getElementById('errorfood');
    if (namefood.value.length < 3 || namefood.value.length > 30) {
        error.style.color = 'red';
        error.style.display = 'block';
        error.textContent = 'Food name must be between 3 and 30 characters long';
    }
    else {
        error.style.display = 'none';
        error.textContent = '';
    }
});

pricefood.addEventListener('input', function() {
    const error = document.getElementById('errorfood');
    if (isNaN(pricefood.value) || pricefood.value < 0 || pricefood.value > 1000) {
        error.style.color = 'red';
        error.style.display = 'block';
        error.textContent = 'Price must be a number between 0 and 1000';
    }
    else {
        error.style.display = 'none';
        error.textContent = '';
    }
});

editsubmit.forEach((button) => {
  button.addEventListener('click', (event) => {
    const error = document.getElementById('erroredit'); 
    if (error && error.textContent.trim() !== '') {
      event.preventDefault();
    }
  });
});

newname.addEventListener('input', function() {
    const error = document.getElementById('erroredit');
    if (!/^[a-zA-Z]+$/.test(newname.value)|| newname.value.length < 3 || newname.value.length > 30) {
        error.style.color = 'red';
        error.style.display = 'block';
        error.textContent = 'Name must contain only alphabetic characters and be between 3 and 30 characters long';
    }
    else {
        error.style.display = 'none';
        error.textContent = '';
    }
});

newsurname.addEventListener('input', function() {
    const error = document.getElementById('erroredit');
    if (!/^[a-zA-Z]+$/.test(newsurname.value) || newsurname.value.length < 3 || newsurname.value.length > 30) {
        error.style.color = 'red';
        error.style.display = 'block';
        error.textContent = 'Surname must contain only alphabetic characters and be between 3 and 30 characters long';
    }
    else {
        error.style.display = 'none';
        error.textContent = '';
    }
});

togglename.addEventListener('click', function() {
    const formname = document.getElementById('name-form');
    if (formname.style.display === 'block') {
        formname.style.display = 'none';
    }
    else {
        formname.style.display = 'block';
    }
});

togglesurname.addEventListener('click', function() {
    const formsurname = document.getElementById('surname-form');
    if (formsurname.style.display === 'block') {
        formsurname.style.display = 'none';
    }
    else {
        formsurname.style.display = 'block';
    }
});

profilebtn.addEventListener('click', function() {
    profile.style.display = 'flex';
    reservation.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'none';
    
});
reservationbtn.addEventListener('click', function() {
    profile.style.display = 'none';
    reservation.style.display = 'flex';
    table.style.display = 'none';
    menu.style.display = 'none';
});
if (menubtn !== null){
menubtn.addEventListener('click', function() {
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'flex';
});
}
if (tablebtn !== null){
tablebtn.addEventListener('click', function() {
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'flex';
    menu.style.display = 'none';
});
}
if (success || error){
    if (error){
        alertbox.style.color = 'red';
    }
    else {
        alertbox.style.color = 'green';
    }
    alertbox.style.display = 'block';
setTimeout(function() {
    alertbox.style.display = 'none';
}, 3000);
}

if (page)
    {
        reservation.style.display = 'flex';
        profile.style.display = 'none';
        table.style.display = 'none';
        menu.style.display = 'none';
    }
 if (success === 'reservation'){
    reservation.style.display = 'flex';
    profile.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'none';
    }
    if (error === 'menu'){ 
    menu.style.display = 'flex';
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'none';
    }
   if (error === 'seats'){
    table.style.display = 'flex';
    profile.style.display = 'none';
    reservation.style.display = 'none';
    menu.style.display = 'none';
   }
   if (success === 'seats'){
    table.style.display = 'flex';
    profile.style.display = 'none';
    reservation.style.display = 'none';
    menu.style.display = 'none';
   }
   if (success === 'update')
   {
    profile.style.display = 'flex';
    reservation.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'none';
   }
   if (success === 'add'){
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'flex';

   }
   if (success === 'deletemenu'){
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'none';
    menu.style.display = 'flex';
   }
   if (success === 'deletetable'){
    profile.style.display = 'none';
    reservation.style.display = 'none';
    table.style.display = 'flex';
    menu.style.display = 'none';
   }
   if (success === 'deletereservation'){
    profile.style.display = 'none';
    reservation.style.display = 'flex';
    table.style.display = 'none';
    menu.style.display = 'none';
   }
});