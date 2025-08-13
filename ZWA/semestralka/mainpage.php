<?php

session_start();

require_once "database.php";

$dashboard = "dashboard.php";

if (!isset($_SESSION['email']) && !isset($_SESSION['username'])) {
    $dashboard = "login.php";
}

$name = strtoupper(pathinfo($dashboard, PATHINFO_FILENAME));
?>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KOKO organic</title>
    <link rel="stylesheet" href="mainpage.css">
    <script src="menu-script.js" defer></script>
</head>

<body>
    <header>
        <div class ="bar">
            <img src="images/kokologo.png" alt="logo">
            <button class="menu-toggle" id="menu-toggle">&#9776;</button>
            <nav class = "navigation">
                <a href="#home">HOME</a>
                <a href="#about_us">ABOUT US</a>
                <a href="#contact">CONTACT</a>
                <a href="<?php echo $dashboard?>" id="dash"><?php echo $name?></a>
            </nav>
            <div class="overlay"></div>
        </div>
    </header>    
    <main id="home">
        <div class = "hero">
            <div class="underline">
                <div>
                    <p class="slogan">Powered by plants and LOVE</p>
                </div>
                <div>
                    Experience the true nature of vegan vietnamese food in heart of Europe. We invite you to taste the best of our dishes, made with love and care for the environment.
                        <br>
                        <br>
                        <div class="buttons-underline">
                            <a href="<?php if($dashboard==="login.php"){echo "login.php";}else{echo "reserve.php";}?>">RESERVE</a>
                            <a href="#menu">MENU</a>
                        </div>
                </div>
            </div>
        </div>
        <section id="about_us">
            <h2>About us</h2>
            <div class="container_about">
                <p>Our restaurant is a family business, founded by a group of young vietnamese people who are passionate about vegan food and care for the environment. We believe that vegan food is not only healthy, but also delicious and can be enjoyed by everyone. Our mission is to bring the best of vegan vietnamese cuisine to Europe and to promote a healthy lifestyle. We are proud to offer a wide range of dishes, made with fresh and organic ingredients, that will satisfy even the most demanding customers. Come and visit us to experience the true taste of vegan vietnamese food!</p>
                <img src="https://www.vegansociety.com/sites" alt="vzhled restaurace">
            </div>
        </section>
        <section id="menu"> 
        <h2>Menu</h2>
            <div class="menu_container">
            <h3>Come and enjoy the unique taste of vegan food mixed with vietnamese cousine.</h3>
            <div class="image_container">
                <img src="images/nem.jpeg" alt="nem">
                <img src="images/veganpho.jpeg" alt="veganpho">
                <img src="images/mangost.jpeg" alt="mangostickyrice">
            </div>
            <button class="menu-btn">OPEN MENU ▼</button>

    </div>
        </section>
        <div class="dropdown-container">
                <div class="category-dropdown">
                    <p>--------------------</p>
                    <button class="category-btn">Starters</button>
                    <div class="food-dropdown" id="starter">
                    </div>
    
                    <button class="category-btn">Salads</button>
                    <div class="food-dropdown" id="salads">
                    </div>
    
                    <button class="category-btn">Main Dishes</button>
                    <div class="food-dropdown" id="main_dish">
                    </div>
    
                    <button class="category-btn">Desserts</button>
                    <div class="food-dropdown" id ="desserts">
                        
                    </div>
                    <p>--------------------</p>
                </div>
        </div>
        <section id="map">
            <h2>Find us</h2>
            <div class= "mapping">
                <p>
                    U will find us in the heart of Prague. Near tram station <b>Bílá labuť</b>. The address is Biskupská 1753/5, right next to our parent restaurant K-Remember</p>
                    <iframe 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2564.123456789!2d14.42076!3d50.08804!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470b94f3b6b6b6b6%3A0x6b6b6b6b6b6b6b6b!2sBiskupska%201753%2F5%2C%20110%2000%20Praha%201-Nove%20Mesto%2C%20Czechia!5e0!3m2!1sen!2sus!4v1600000000000!5m2!1sen!2sus">
                    </iframe>
            </div>
        </section>
    </main>
    <footer id ="contact">
            <div class="contact">
                <h3>Contact</h3>
                <p>Address: Biskupska 1753/5</p>
                <p>Phone: 123 4534 432</p>
                <p>Email:</p>
            </div>
    </footer>
</body>
</html>