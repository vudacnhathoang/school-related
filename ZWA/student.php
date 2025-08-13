<?php


$napsal = $_GET["search"];
$students = file_get_contents('users.json', 'r');
$studentsDecodes = json_decode($students);

foreach($studentsDecodes as $student){
    if(str_contains($student, $napsal))
    {
        $seznamVyhledanych[] = $student;
    }
}

//var_dump($students);        //co je v promenne  

echo json_encode($seznamVyhledanych);
