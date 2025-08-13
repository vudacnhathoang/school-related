<?php

const USER_DATABASE = "new.json";


function getUserByUsername($username)
{
    $allUsers = readUsers();
    foreach($allUsers as $user)
    {
        if($user->username === $username)
        {
            return $user;
        }
    }
    return false;
}

function createUser($newuser)
{
    $AllUsers = readUsers();
    if ($AllUsers === null)
    {
        $AllUsers = [];
    }
    foreach($AllUsers as $user)
    {
        if($user->username === $newuser->username)
        {
            return false;
        }
    }
    $AllUsers[] = $newuser;
    $encoded = json_encode($AllUsers);
    file_put_contents(USER_DATABASE, $encoded);

}

function readUser($readuser)
{
    $allUsers = readUsers();
    foreach($allUsers as $user)
    {
        if($user->username === $readuser->username)
        {
            return $user;
        }
    }
    return null;
}

function readUsers()
{
    try
    {
    $users = file_get_contents(USER_DATABASE);
    $decoded = json_decode($users);
    return $decoded;
    }
    catch(Exception $e)
    {
        return [];
    }

}

function updateUser($updateduser)
{
    $allUsers = readUsers();
    $newUsers = [];
    foreach($allUsers as $user)
    {
        if($user->username != $updateduser->username)
        {
            $newUsers[] = $user;
        }
    }
    $newUsers[] = $updateduser;
    file_put_contents(USER_DATABASE, json_encode($newUsers));


}

function deleteUser($username)
{
    $allUsers = readUsers();
    $newUsers = [];
    foreach($allUsers as $user)
    {
        if($user->username != $username)
        {
            $newUsers[] = $user;
        }
    }
    file_put_contents(USER_DATABASE, json_encode($newUsers));
}

class User
{
    public $username;
    public $email;
    public $fullname;
    public $password;


    public function __construct($username, $email, $fullname, $password)
    {
        $this->username = $username;
        $this->email = $email;
        $this->fullname = $fullname;
        $this->password = $password;
    }
}




?>