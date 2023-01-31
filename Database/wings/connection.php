<?php
    define('HOST', 'localhost');
    define('USER', 'root');
    define('PASSWORD', '');
    define('DATABASE', 'wings');

    $connection = mysqli_connect(HOST, USER, PASSWORD, DATABASE) or die('Error connecting to database');
?>