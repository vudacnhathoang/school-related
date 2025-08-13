<?php

require_once "try.php";

if(isset($_POST["register"])){

    $passwordOtisk= password_hash($_POST["password"], PASSWORD_DEFAULT);
    $user = new User($_POST["username"], $_POST["email"], $_POST["fullname"], $passwordOtisk);
    createUser($user);
    header("Location: login.php");
}

?>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>Registrace</h2>
    <form action="registrace.php" method="post">
        <label for="username">Uživatelské jméno:</label>
        <input type="text" name="username">
        <br>
        <label for="email">Email:</label>
        <input type="email" name="email">
        <br>
        <label for="fullname">Jméno:</label>
        <input type="text" name="fullname">
        <br>
        <label for="password">Heslo:</label>
        <input type="password" name="password">
        <br>
        <label for="password2">Heslo znovu:</label>
        <input type="password" name="password2">
        <br>
        <input type="submit" value="Registrovat">    
    </form>

</body>
</html>