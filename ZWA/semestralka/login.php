<?php  
require_once 'database.php';
$error = "";
$switch = "";


if (isset($_SESSION['email']) || isset($_SESSION['username'])) {
    header('Location: dashboard.php');
    die();
}

if(isset($_POST['submit'])){
    if (empty($_POST['email']) || empty($_POST['password'])) {
        $error = "Please fill in all fields";
        
    }
    else{
        if (filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
            $switch = "email";
        } 
        else{
            $switch = "username";
        }
        if(verifUser($_POST['email'], $_POST['password'], $switch)){
            if($switch === "email")
            {
                session_start();
                $_SESSION['email'] = $_POST['email'];
                header('Location: dashboard.php?succesful=login');
                die();
    
            }
            elseif($switch === "username")
            {
                session_start();
                $_SESSION['username'] = $_POST['email'];
                header('Location: dashboard.php?successful=login');
                die();
            }
        }   
        else{
           $error = "Invalid email or password";
        }  

    } 
}


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="sign.css">
    <script src="login.js"></script>
    <title>KOKO login</title>
</head>
<body>
    <div class= "error-msg" id="error-msg">
        <?php 
            if (isset($_GET['success'])) {
                if ($_GET['success'] === "logout") {
                    echo "You have been logged out";
                }
                if ($_GET['success'] === "register") {
                    echo "You have been registered successfully";
                }
            }
        ?>
    </div>


    <a id="back" href="index.php">back</a>
    <div id="form-container">
        <form action="login.php" method="post">
            <h2> Log in</h2>
            <label for="email">Username or Email:</label>
            <input type="text" id ="email" name="email" placeholder="Email or username">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Password">
            <button type="submit" name="submit">login</button>
            <p>Don't have an account?<a href="register.php">Register</a></p>
            <?php if($error != ""){echo $error;} ?>
        </form>
    </div>
</body>
</html>