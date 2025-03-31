<?php
require_once 'database.php';

session_start();
if(!isset($_SESSION['email'])&&!isset($_SESSION['username'])){
    header('Location: login.php');
    session_abort();
    die();
}

if(isset($_SESSION['username'])){
    $thisUser = getUser($_SESSION['username'], 'username');
}
else{
    $thisUser = getUser($_SESSION['email'], 'email');
}



if(isset($_POST['available']) && !empty($_POST['date']) && !empty($_POST['table'])){
    $date = $_POST['date'];
    $table = $_POST['table'];
    $today = date("Y-m-d");
    if($date < $today){
        $availableTimes = "huh";
    }
    else{
        $availableTimes = checkavailable($date, $table);
    }
}
if(isset($_POST['submit'])){
    $newReservation = new Reservation($_POST['date'], $_POST['hour'], $_POST['table'],$thisUser->username ,0);
    foreach(getReservations() as $reservation){
        if($reservation->date === $newReservation->date && $reservation->time === $newReservation->time && $reservation->table === $newReservation->table){
            $availableTimes = "hej";
        }
    }  
    createReservation($newReservation);
    header('Location: dashboard.php?success=reservation');
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="reserve.css">
    <title>reservation KOKO</title>
</head>
<body>
    <a id="back" href="index.php">back</a>

    <div class= "container">
        <h2>Reservation</h2>
        <form action="reserve.php" method="post">
            <label>
                Date: <input type="date" name="date" id="date">
            </label>
            <label>Table option: 
            <select name="table" id="table">
                <option value="">Choose your table</option>
                <?php
                $tables = getTables();
                foreach ($tables as $table){
                    echo "<option value='table$table->id'>table for $table->seats</option>";
                }
                ?>
            </select>
            </label>
            <label>
                <button type="submit" name= "available">Check Available Times</button>
            </label>
        </form>
        <?php 
       
        if(isset($_POST['available']) && isset($availableTimes)){
            
            if ($availableTimes === "hej"){
                echo "<p>Already reserved for this time</p>";
            }
            else if( $availableTimes === "huh"){
                echo "<p>Cannot reserve for past dates</p>";
            }
            else if ($availableTimes === []){
               echo "<p>No available times for this date and table</p>";
           }
           else{
            echo "<form action='reserve.php' method='post'>";
            echo "<label>Available Times: ";
            echo "<select name='hour'>";
            foreach ($availableTimes as $time){
                echo "<option value='$time'>$time</option>";
            }
            echo "<input type='hidden' name='date' value='" . htmlspecialchars($_POST['date']) . "'>";
            echo "<input type='hidden' name='table' value='" . htmlspecialchars($_POST['table']) . "'>";

            echo "</select></label>";
            echo "<button type='submit' name='submit'>Reserve</button>";
           }
        }
        else{
            echo "<p>Choose a date and table to see available times</p>";
        }
        ?>
    </div>
</body>
</html>