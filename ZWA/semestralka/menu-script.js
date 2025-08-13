document.addEventListener('DOMContentLoaded', function() {
   const menubtn = document.querySelector('.menu-btn');
   const menu = document.querySelector('.category-dropdown');
   const btns = document.querySelectorAll('.category-btn'); 
   const navtoggle = document.querySelector('.menu-toggle');
   const navlinks = document.querySelector('.navigation');
   const overlay = document.querySelector('.overlay');


   navlinks.querySelectorAll('a').forEach(link => {
    link.addEventListener('click', function() {
        if (window.innerWidth <= 730) {
            navlinks.style.display = 'none';
            overlay.style.display = 'none';
        }
        
    });
});

   navtoggle.addEventListener('click', function() {
        if (navlinks.style.display === 'none'|| navlinks.style.display === ''){
            navlinks.style.display = 'flex';
            overlay.style.display = 'block';
        }
        else{
            navlinks.style.display = 'none';
            overlay.style.display = 'none';
        };
    });

    window.addEventListener('resize', function(){
        if (window.innerWidth > 730){
            navlinks.style.display = 'flex';
            overlay.style.display = 'none';
        }
        else{
            navlinks.style.display = 'none';
            overlay.style.display = 'none';
        }


    }
);

   menubtn.addEventListener('click', function() {
        if (menu.style.display === 'none'|| menu.style.display === ''){
            menu.style.display = 'flex';  
            menubtn.textContent = 'CLOSE MENU ▲';
        }
        else{
            menu.style.display = 'none';
            menubtn.textContent = 'OPEN MENU \n \u25BC';
        };
    });

    btns.forEach(function(btn){
        btn.addEventListener('click', function() {
            const dropdown = btn.nextElementSibling;
            if (dropdown.style.display === 'none'|| dropdown.style.display === ''){
                dropdown.style.display = 'flex';  
                btn.style.textDecoration = 'underline';
                btns.forEach(function(otherBtn) {
                    const otherDropdown = otherBtn.nextElementSibling;
                    if (otherDropdown !== dropdown && otherDropdown.style.display === 'flex') {
                        otherDropdown.style.display = 'none';
                    }
                });
            }
            else{
                dropdown.style.display = 'none';
            }
        });
    });
    

});

fetch("jsons/menu.json")
.then(response => response.json())
.then(data => {
    const desserts = document.getElementById('desserts');
    data.forEach(item => {
        if (item.type === 'dessert') {
            const div = document.createElement('div');
            div.className = 'menu-item';
            div.textContent = item.name + ' - ' + item.price + 'CZK';
            desserts.appendChild(div);
        }
    });
    const starters = document.getElementById('starter');
    data.forEach(item => {
        if (item.type === 'starter') {
            const div = document.createElement('div');
            div.className = 'menu-item';
            div.textContent = item.name + ' - ' + item.price + 'CZK';
            starters.appendChild(div);
        }
    });
    const mains = document.getElementById('main_dish');
    data.forEach(item => {
        if (item.type === 'main_dish') {
            const div = document.createElement('div');
            div.className = 'menu-item';
            div.textContent = item.name + ' - ' + item.price + 'CZK';
            mains.appendChild(div);
        }
    });
    const salads = document.getElementById('salads');
    data.forEach(item => {
        if (item.type === 'salad') {
            const div = document.createElement('div');
            div.className = 'menu-item';
            div.textContent = item.name + ' - ' + item.price + 'CZK';
            salads.appendChild(div);
        }
    });

});