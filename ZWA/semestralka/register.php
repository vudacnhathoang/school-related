<?php
require_once 'database.php';



$taken ="";
if(isset($_POST['submit'])){



    if(empty($_POST['username']) || empty($_POST['name']) || empty($_POST['surname']) || 
       empty($_POST['email']) || empty($_POST['password']) || empty($_POST['password2'])) {
        $taken = "Please fill in all the fields";
    }
    else if( strlen($_POST['username']) < 3 || strlen($_POST['username'])> 30 )
    {
        $taken = "Username must be between 3 and 30 characters";
    }
    else if( strlen($_POST['name']) < 3 || strlen($_POST['name'])> 30 )
    {
        $taken = "Name must be between 3 and 30 characters";
    }
    else if( strlen($_POST['surname']) < 3 || strlen($_POST['surname'])> 30 )
    {
        $taken = "Surname must be between 3 and 30 characters";
    }
    else if( strlen($_POST['email']) < 3 || strlen($_POST['email'])> 30 )
    {
        $taken = "Email must be between 3 and 30 characters";
    }
    else if( strlen($_POST['password']) < 3 || strlen($_POST['password'])> 30 )
    {
        $taken = "Password must be between 3 and 30 characters";
    }

    elseif(empty($_FILES['photo']['name'])){
        $taken = "Please upload a profile picture";   
    }
    elseif (!preg_match('/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/', $_POST['password'])) {
        $taken = "Password must contain at least one letter and one number";
    }
    elseif($_POST['password'] !== $_POST['password2']){
        $taken = "Passwords do not match";
    }
    else if(!filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)){
        $taken = "Invalid email";
    }
    else{
        $hashed = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $allowed_types = ['image/jpeg', 'image/png', 'image/gif'];
        $target_directory = "profilespicture/";
        $photo = $_FILES['photo'];
        $photo_name = basename($photo['name']);
        $target_file = $target_directory . uniqid() . "_" . str_replace(' ', '_', $photo_name). ".jpg";


        if (!is_dir($target_directory)) {
            mkdir($target_directory, 0777, true);
        }


        if ($_FILES['photo']['size'] >  2 * 1024 * 1024) {
            resizeImage($photo['tmp_name'], $target_file, 90, 90);
            // muzem i udelat ze to nebude vubecky fungovat pokud je vetsi nez 2MB
            // $taken = "File is too big";
        }
        else
        {
            if (!in_array($photo['type'], $allowed_types)) {
                $taken = "Invalid file type";
            }
            else {
                resizeImage($photo['tmp_name'], $target_file, 90, 90);
            }
        }

        $user = new User($_POST['username'], $_POST['name'], $_POST['surname'], $_POST['email'], $hashed, $target_file);
        if(createUser($user)){
        header('Location: login.php?success=register');
    }
    else{
        $taken = "Username is already taken or email is already in use";
    }   
    }
}
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="sign.css">
    <script src="sign.js" defer></script>
    <title>KOKO signup</title>
</head>
<body>
    <a id="back" href="index.php">back</a>
    <div id="form-container">
        <form action="register.php" method="post" id= "form"  enctype="multipart/form-data">
            <h2> Register</h2>
            <div class="fullname">
                <fieldset>
                <legend>Full name</legend>
                <label for="name">Name*:</label>
                <input type="text" id="name" name="name" placeholder="Name"  value="<?php if (isset($_POST['name'])){echo htmlspecialchars($_POST['name']);}?>">
                <label for="surname">Surname*:</label>
                <input type="text" id="surname" name="surname" placeholder="Surname"  value="<?php if (isset($_POST['surname'])){echo htmlspecialchars($_POST['surname']);}?>">
                </fieldset>
            </div>
            <label for="username">Username*:</label>
            <input type="text" id="username" name="username" placeholder="Username"  value="<?php if(isset($_POST['username'])){echo htmlspecialchars($_POST['username']);}?>">
            <label for="email">Email*:</label>
            <input type="text" id ="email" name="email" placeholder="Email"  value="<?php if(isset($_POST['email'])){echo htmlspecialchars($_POST['email']);}?>">
            <label for="password">Password*:</label>
            <input type="password" id="password" name="password" placeholder="Password">
            <label for="password2">password confirm*:</label>
            <input type="password" id="password2" name="password2" placeholder="Confirm password">
            <label for="photo"></label>
            <p>Upload a profile picture*:</p>
            <input type="file" id="photo" name="photo" accept="image/*">
            <button type="submit" id="submit" name="submit" disabled>Sign up</button>
            <p>Already have an account? <a href="login.php">Log in</a></p>
            <p id ="error"></p>
          <p id="taken"><?php if($taken != ""){echo $taken;} ?></p>
        </form>
    </div>
</body>
</html>

