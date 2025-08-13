<?php
    var_dump($_POST);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <h2> REGISTRACE </h2>
    <form action="registrace.php" method=post>
        <label for="jmeno">Jméno:</label>
        <input type="text" name="jmeno" id="jmeno">
        <br>
        <label for="prijmeni">Příjmení:</label>
        <input type="text" name="prijmeni" id="prijmeni" >
        <br>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email">
        <br>
        <label for="heslo">Heslo:</label>
        <input type="password" name="heslo" id="heslo">
        <br>
        <label for="heslo_znovu">Heslo znovu:</label>
        <input type="password" name="heslo_znovu" id="heslo_znovu">
        <br>
        <button type="submit" name="register">REGISTRUJ SE</button>
    
</body>
</html>