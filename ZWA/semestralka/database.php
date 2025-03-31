<?php

const DB_FILE = 'jsons/database.json';
const RES_FILE = 'jsons/reservation.json';
const TABLE_FILE = 'jsons/tables.json';
const MENU_FILE = 'jsons/menu.json';

class User{
    public $username;
    public $name;
    public $surname;
    public $email;
    public $password;
    public $target_file;
    public $admin = false;

    public function __construct($username, $name, $surname, $email, $password, $target_file){
        $this->username = $username;
        $this->name = $name;
        $this->surname = $surname;
        $this->email = $email;
        $this->password = $password;
        $this->target_file = $target_file;
    }

}
class Reservation{
    public $id;
    public $username;
    public $date;
    public $time;
    public $table;
    public $offset;

    public function __construct($date, $time, $table, $username,$id, $offset = 0){
        $this->date = $date;
        $this->time = $time;
        $this->table = $table;
        $this->username = $username;
        $this->id = $id;
        $this->offset = $offset;

    }
}
class Table{
    public $id;
    public $seats;

    public function __construct($id, $seats){
        $this->id = $id;
        $this->seats = $seats;
    }
}

class MenuItem{
    public $name;
    public $price;
    public $type;

    public function __construct($name, $price, $type){
        $this->name = $name;
        $this->price = $price;
        $this->type = $type;
    }
}


function getUsers(){
    
    try
    {
    $users = file_get_contents(DB_FILE);
    $decoded = json_decode($users);
    
    if ($decoded === null) {
        return [];
    }
    return $decoded;
    }
    catch(Exception $e)
    {
        return [];
    }
}

function getUser($username, $switch){
    $users = getUsers();
    foreach ($users as $user){
        if($switch === "email"){
            if($user->email === $username)
                return $user;
        }
        else if ($switch === "username"){
            if($user->username === $username)
                return $user;
        }
    }
    return null;
}

function createUser($newUser){
    $users = getUsers();

    foreach ($users as $user){
        if($user->username === $newUser->username)
            return false;
        if ($user->email === $newUser->email)
            return false;
    }
    $users[] = $newUser;
    $encoded = json_encode($users);
    file_put_contents(DB_FILE, $encoded);
    return true;

} 

function verifUser($username, $password, $switch){
    $users = getUsers();
    foreach ($users as $user){
        if($switch === "email"){
            if($user->email === $username && password_verify($password, $user->password))
                return true;
        }
        else if ($switch === "username"){
            if($user->username === $username && password_verify($password, $user->password))
                return true;
        }
    }
    return false;
}

function getReservations(){
    try
    {
    $reservations = file_get_contents(RES_FILE);
    $decoded = json_decode($reservations);
    if ($decoded === null) {
        return [];
    }
    return $decoded;
    }
    catch(Exception $e)
    {
        return [];
    }
}

function createReservation($newReservation){
    $reservations = getReservations();
    $maxId = -1;
    foreach ($reservations as $reservation) {
        if ($reservation->id > $maxId) {
            $maxId = $reservation->id;
        }
    }
    $newReservation->id = $maxId + 1;

    $reservations[] = $newReservation;
    $encoded = json_encode($reservations);
    file_put_contents(RES_FILE, $encoded);
    return true;
}

function checkavailable($date, $table){
    $reservations = getReservations();
    $Times =["12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"];
    if ($reservations === [])
    {
        return $Times;
    }
    $availableTimes = [];
    $cnt = 0;
    foreach ($Times as $time){
        $available = true;
        foreach ($reservations as $reservation){
            if ($reservation->date === $date && $reservation->table === $table && $reservation->time === $time){
                $available = false;
                break;
            }
        }
        if (!$available){
           $cnt = 0;  //cnt je na to abych zarezervoval celou hodinu
        }

        if ($available && $cnt !== 1){
            $availableTimes[] = $time;
        }
        $cnt++;
    }
    return $availableTimes;
}   

function getmyreservations($user,&$off){
    $reservations = getReservations();
    $myreservations = [];
    $cnt = 0;

    foreach ($reservations as $reservation){
        if ($reservation->username === $user->username || $user->admin){
            $myreservations[] = $reservation;

            if ($cnt % 5 ===0 && $cnt !== 0){
                $off++;
            }
            $reservation->offset = $off;
            $cnt++;
        }

    }
    return $myreservations;
}

function deleteReservation($id){
    $reservations = getReservations();
    $newReservations = [];
    foreach ($reservations as $reservation){
        if ((string)$reservation->id !== (string)$id){
            $newReservations[] = $reservation;
        }
    }
    $encoded = json_encode($newReservations);
    file_put_contents(RES_FILE, $encoded);
}

function getTables(){
    try
    {
    $tables = file_get_contents(TABLE_FILE);
    $decoded = json_decode($tables);
    if ($decoded === null) {
        return [];
    }
    return $decoded;
    }
    catch(Exception $e)
    {
        return [];
    }
}

function addTable($seats){
    $tables = getTables();
    $maxId = -1;
    foreach ($tables as $table) {
        if ($table->id > $maxId) {
            $maxId = $table->id;
        }
    }
    $newTable = new Table($maxId + 1, $seats);
    $tables[] = $newTable;
    $encoded = json_encode($tables);
    file_put_contents(TABLE_FILE, $encoded);
    return true;
}

function deleteTable($id){
    $tables = getTables();
    $newTables = [];
    foreach ($tables as $table){
        if ((string)$table->id !== (string)$id){
            $newTables[] = $table;
        }
    }
    $encoded = json_encode($newTables);
    file_put_contents(TABLE_FILE, $encoded);
}

function getMenuItems(){
    try
    {
    $menu = file_get_contents(MENU_FILE);
    $decoded = json_decode($menu);
    if ($decoded === null) {
        return [];
    }
    return $decoded;
    }
    catch(Exception $e)
    {
        return [];
    }
}

function addMenuItem($newItem) {
    $menu = getMenuItems();
    
    foreach ($menu as $item) {
        if($item->name === $newItem->name)
            return false;
    }
    
    $menu[] = $newItem;
    
    usort($menu, function($a, $b) {
        return strcasecmp($a->name, $b->name);
    });
    
    $encoded = json_encode($menu);
    file_put_contents(MENU_FILE, $encoded);
    return true;
}

function deleteMenuItem($name){
    $menu = getMenuItems();
    $newMenu = [];
    foreach ($menu as $item){
        if ($item->name !== $name){
            $newMenu[] = $item;
        }
    }
    $encoded = json_encode($newMenu);
    file_put_contents(MENU_FILE, $encoded);
}


function updateUser($user){
    $users = getUsers();
    $newUsers = [];
    foreach ($users as $oldUser){
        if ($oldUser->username === $user->username){
            $newUsers[] = $user;
        }
        else{
            $newUsers[] = $oldUser;
        }
    }
    $encoded = json_encode($newUsers);
    file_put_contents(DB_FILE, $encoded);
}

function resizeImage($sourcePath, $targetPath, $maxWidth, $maxHeight, $quality = 80) {
    $imageInfo = getimagesize($sourcePath);
    if ($imageInfo === false) {
        throw new Exception("Invalid image file");
    }


    $originalWidth = $imageInfo[0];
    $originalHeight = $imageInfo[1];
    $mimeType = $imageInfo['mime'];

    $ratio = min($maxWidth / $originalWidth, $maxHeight / $originalHeight);
    $newWidth = round($originalWidth * $ratio);
    $newHeight = round($originalHeight * $ratio);


    switch ($mimeType) {
        case 'image/jpeg':
            $source = imagecreatefromjpeg($sourcePath);
            break;
        case 'image/png':
            $source = imagecreatefrompng($sourcePath);
            break;
        case 'image/gif':
            $source = imagecreatefromgif($sourcePath);
            break;
        default:
            throw new Exception("Unsupported image type: $mimeType");
    }

    $destination = imagecreatetruecolor($newWidth, $newHeight);

    if ($mimeType === 'image/png') {
        imagealphablending($destination, false);
        imagesavealpha($destination, true);
        $transparent = imagecolorallocatealpha($destination, 255, 255, 255, 127);
        imagefilledrectangle($destination, 0, 0, $newWidth, $newHeight, $transparent);
    }


    imagecopyresampled(
        $destination, $source,
        0, 0, 0, 0,
        $newWidth, $newHeight,
        $originalWidth, $originalHeight
    );

    switch ($mimeType) {
        case 'image/jpeg':
            imagejpeg($destination, $targetPath, $quality);
            break;
        case 'image/png':
            imagepng($destination, $targetPath, round($quality / 10));
            break;
        case 'image/gif':
            imagegif($destination, $targetPath);
            break;
    }

    imagedestroy($source);
    imagedestroy($destination);

    return true;
}

    function makeAdmin($username){
        $users = getUsers();
        $newUsers = [];
        foreach ($users as $user){
            if ($user->username === $username){
                $user->admin = true;
            }
            $newUsers[] = $user;
        }
        $encoded = json_encode($newUsers);
        file_put_contents(DB_FILE, $encoded);
    }
?>