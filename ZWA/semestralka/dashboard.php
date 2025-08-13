<?php
require_once 'database.php';
session_start();
if(!isset($_SESSION['email']) && !isset($_SESSION['username'])){
    header('Location: login.php?success=logout');
    session_abort();
    die();
}

if(isset($_SESSION["username"])){
    $thisUser = getUser($_SESSION["username"], "username");
}
else{
    $thisUser = getUser($_SESSION["email"], "email");
}
$offset = 0;
$usersReservation = getmyreservations($thisUser, $offset);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['new_name'])) {
        $thisUser->name = $_POST['new_name'];
        updateUser($thisUser);
        header('Location: dashboard.php?success=update');
        die();
    }
    if (isset($_POST['make_admin'])) {
        $user = getUser($_POST['make_admin'], "username");
        $user->admin = true;
        updateUser($user);
        header('Location: dashboard.php?success=update');
        die();
    }

    if (isset($_POST['update_surname'])) {
        $thisUser->surname = $_POST['new_surname'];
        updateUser($thisUser);
        header('Location: dashboard.php?success=update');
        die();
    }

    if (isset($_POST['logout'])) {
        session_destroy();
        header('Location: login.php?success=logout');
        die();
    }
    if (isset($_POST['delete_reservation'])&& ($thisUser->username === $_POST['deleteresuser']|| $thisUser->admin)) 
    {
        deleteReservation($_POST['delete_reservation']);
        header('Location: dashboard.php?success=deletereservation');
        die();
    }
    if (isset($_POST['delete_table'])&& $thisUser->admin) {
        deleteTable($_POST['delete_table']);
        header('Location: dashboard.php?success=deletetable');
        die();
    }
    if (isset($_POST['add_table']) && $thisUser->admin) {
        if ($_POST['seats'] < 1 || $_POST['seats'] > 10) {
            header('Location: dashboard.php?error=seats');
            die();
        }
        addTable($_POST['seats']);
        header('Location: dashboard.php?success=seats');
        die();
    }
    if (isset($_POST['add_menu']) && $thisUser->admin) {
        $newItem = new MenuItem($_POST['namefood'], $_POST['pricefood'], $_POST['typefood']);
        if(!addMenuItem($newItem))
        {
            header('Location: dashboard.php?error=menu');
            die();
        }
        header('Location: dashboard.php?success=add');
        die();
    }
    if (isset($_POST['delete_menu']) && $thisUser->admin) {
        deleteMenuItem($_POST['delete_menu']);
        header('Location: dashboard.php?success=deletemenu');
        die();
    }
    if (isset($_POST['update_photo'])) {
        $allowed_types = ['image/jpeg', 'image/png', 'image/gif'];

        if (file_exists($thisUser->target_file)) {
            unlink($thisUser->target_file);
        }
        $target_directory = "profilespicture/";
        $photo = $_FILES['new_photo'];
        if ($photo['error'] !== 0) {
            header('Location: dashboard.php?error=upload');

            die();
        }
        $photo_name = basename($photo['name']);
        $target_file = $target_directory . uniqid() . "_" . $photo_name. ".jpg";

        if (!is_dir($target_directory)) {
            mkdir($target_directory, 0777, true);
        }
        if ($_FILES['new_photo']['size'] > 2 * 1024 * 1024) {
            if (!in_array($photo['type'], $allowed_types)) {
                header('Location: dashboard.php?error=upload');
                die();
            }
            resizeImage($photo['tmp_name'], $target_file, 90, 90);
        }
        else
        {
            if (!in_array($photo['type'], $allowed_types)) {
                header('Location: dashboard.php?error=upload');
                die();
            }
            resizeImage($photo['tmp_name'], $target_file, 90, 90);
        }
        $thisUser->target_file = $target_file;
        updateUser($thisUser);
        header('Location: dashboard.php?success=update');
        die();
    }


}


?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="dashboard.css">
    <title>Dashboard</title>
    <script src="dash.js" defer></script>
</head>
<body>
    <div id='alertbox'>
        <?php
        if(isset($_GET['success']))
        {
            if ($_GET['success'] === 'deletemenu'|| $_GET['success'] === 'deletetable'|| $_GET['success'] === 'deletereservation') {
                echo "Deleted successfully";
            }
            elseif ($_GET['success'] === 'update') {
                echo "Updated successfully";
            }
            elseif ($_GET['success'] === 'reservation') {
                echo "Reserved successfully";
            }
           
            else{
                echo "Added successfully";
            } 
        }
        if(isset($_GET['error']) && $_GET['error'] === 'upload') {
            echo "Error uploading photo";
        }
        if (isset($_GET['error']) && $_GET['error'] === 'seats') {
            echo "Number of seats must be between 1 and 10";
        }
        if (isset($_GET['error']) && $_GET['error'] === 'menu') {
            echo "Item already exists";
        }
        ?>
    </div>
    <a href="index.php" class="back">back</a>
    <div class="dashboard">
    <nav>
        <button id="profilebtn">My profile</button>
        <?php if($thisUser->admin): ?>
            <button id="adminmenu">MENU</button>
            <button id="tablesadmin">Tables</button>
        <?php endif; ?>

        <button id="reservationbtn"><?php if($thisUser->admin){echo "All";}else{echo "My";} ?> reservations</button>
        <a href="reserve.php">Reserve</a>
        <form action="dashboard.php" method="post">
            <input type="submit" value="Logout" name="logout" id="loggingout">
        </form>
    </nav>
    <main id="main-content">
        <h1>Welcome to your dashboard <?php echo htmlspecialchars($thisUser->name) ?></h1>
        <div id="profile">
            <h2>Profile</h2>
            <img src="<?php if ($thisUser->target_file){echo $thisUser->target_file;}?>" alt="profile picture">
            <form action="dashboard.php" method="post" enctype="multipart/form-data">
                <input type="file" name="new_photo" id="new_photo">
                <button type="submit" name="update_photo" >Change</button>
            </form>
            <p>Username: <?php echo htmlspecialchars($thisUser->username); ?> </p>
            <p>Name: <?php echo htmlspecialchars($thisUser->name); ?><button type="button" id="togglename">Edit</button></p>
            <form action="dashboard.php" method="post" id="name-form" >
                    <input type="text" name="new_name" id="new_name" value="<?php echo htmlspecialchars($thisUser->name); ?>">
                    <button type="submit" class="submit_edit">Save</button>
            </form>
            <p>Surname: <?php echo htmlspecialchars($thisUser->surname); ?><button id="togglesurname">Edit</button></p>
            <form action="dashboard.php" method="post" id="surname-form">
                    <input type="text" name="new_surname" id="new_surname" value="<?php echo htmlspecialchars($thisUser->surname); ?>">
                    <button type="submit" name="update_surname" class="submit_edit">Save</button>
                </form>
            <p>Email: <?php echo htmlspecialchars($thisUser->email); ?></p>
            <div id="erroredit">
            </div>

            <?php if($thisUser->admin): ?>
                Make user admin: <form action="dashboard.php" method="post">
                <select name="make_admin">
                    <?php
                        $users = getUsers();
                        foreach ($users as $user){
                            if ($user->admin !== true){
                                echo "<option value='" . htmlspecialchars($user->username) . "'>" . htmlspecialchars($user->username)>"</option>";
                            }
                        }
                    ?>
            </select>
                <button type="submit">Make</button>
            </form>
            <?php endif; ?>




        </div>
        <div id="reservations">
            <h2>Reservations</h2>
            <?php
             if (!isset($_GET['page'])){
                $page = 0;
            }
            else{
                $page = $_GET['page']-1;
            }

            echo "<h3>Page " . ($page+1) . "</h3>";
            ?>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Table</th>
                    <th>Username</th>
                    <th>Delete</th>
                </tr>
                <?php 
                    foreach ($usersReservation as $reservation){
                       if ($reservation->offset === $page){
                        echo "<tr>";
                        echo "<td>" . $reservation->date . "</td>";
                        echo "<td>" . $reservation->time . "</td>";
                        echo "<td>" . $reservation->table . "</td>";
                        echo "<td>" . htmlspecialchars($reservation->username) . "</td>";
                        echo "<td><form method='post'><input type='hidden' name='delete_reservation' value='" . $reservation->id . "'><input type='hidden' name='deleteresuser' value='" . htmlspecialchars($reservation->username) . "'><button type='submit'>Delete</button></form></td>";
                        echo "</tr>";
                        }
                 }
                ?>
            </table>
            <div id="page-buttons">

            <?php
          if ($page > 0) {
            echo "<a href='dashboard.php?page=" . $page  . "#reservations'>Previous </a>";
        } else {
            echo "<a href='#' class='disabled'>Previous</a>";
        }
            for($i = 1; $i <= $offset+1; $i++)
                    {
                        echo "<a href='dashboard.php?page=" . $i . "#reservations'>" . $i . "</a> ";
                    }
           if ($page < $offset) {
            echo "<a href='dashboard.php?page=" . ($page+2) . "#reservations'>Next</a>";
           }
           else
              {
                echo "<a href='#' class='disabled'>Next</a>";
                }
                ?>
            </div>
        </div>
        <div id="tables">
            <h2>Tables</h2>
            <?php
    $tables = getTables();
    foreach ($tables as $table){
        echo "table" . $table->id . " : Table with " . $table->seats . " seats <form method='post'><input type='hidden' name='delete_table' value='" . $table->id . "'><button type='submit'>Remove</button></form>";
                                }       
            ?>
            <form action="dashboard.php" method="post">
                <label>
                    Seats: <input type="number" name="seats">
                </label>
                <button type="submit" name="add_table">Add</button> 
            </form>
        </div>
        <div id="menu">
            <h2>Menu</h2>
            <div class ="menubox">
            <div class="starters">
            <h3><u>Starters</u></h3>
            <?php
                $menu = getMenuItems();
                foreach ($menu as $item){
                    if($item->type === "starter"){
                        echo "<p>" . htmlspecialchars($item->name) . " : " . $item->price . "CZK </p>";
                        echo "<form method='post'><input type='hidden' name='delete_menu' value='" . $item->name . "'><button type='submit'>Remove</button></form>";

                    }
                }
            ?>
            </div>
            <div class="salads">
            <h3><u>Salads</u></h3>
            <?php
                $menu = getMenuItems();
                foreach ($menu as $item){
                    if($item->type === "salad"){
                        echo "<p>" .htmlspecialchars($item->name). " : " . $item->price . "CZK </p>";
                        echo "<form method='post'><input type='hidden' name='delete_menu' value='" . htmlspecialchars($item->name) . "'><button type='submit'>Remove</button></form>";

                    }
                }
            ?>
            </div>
            <div class="maindishes">
            <h3><u>Main Dishes</u></h3>
            <?php
                $menu = getMenuItems();
                foreach ($menu as $item){
                    if($item->type === "main_dish"){
                        echo "<p>" . htmlspecialchars($item->name) . " : " . $item->price . "CZK </p>";
                        echo "<form method='post'><input type='hidden' name='delete_menu' value='" . htmlspecialchars($item->name) . "'><button type='submit'>Remove</button></form>";
                    }
                }
            ?>
            </div>
            <div class="desserts">
            <h3><u>Desserts</u></h3>
            <?php
                $menu = getMenuItems();
                foreach ($menu as $item){
                    if($item->type === "dessert"){
                        echo "<p>" . htmlspecialchars($item->name) . " : " . $item->price . "CZK </p>";
                        echo "<form method='post'><input type='hidden' name='delete_menu' value='" . htmlspecialchars($item->name) . "'><button type='submit'>Remove</button></form>";

                    }
                }
            ?>
            </div>
            </div>
            <form action="dashboard.php" method="post">
                <label>
                    Name: <input type="text" id="namefood" name="namefood">
                </label>
                <label>
                    Price: <input type="number" id="pricefood" name="pricefood" >
                </label>
                <label>
                    Type: 
                    <select id="type" name="typefood" >
                        <option value="starter">Starter</option>
                        <option value="salad">Salad</option>
                        <option value="main_dish">Main Dish</option>
                        <option value="dessert">Dessert</option>

    </select>
                </label>
                <button type="submit" name="add_menu" id="foodsubmit"> Add</button>
            </form>
            <div id="errorfood">
            </div>
        </div>
    </main>

    </div>
    
</body>
</html>

