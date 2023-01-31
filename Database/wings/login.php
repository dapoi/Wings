<?php
    require_once('connection.php');

    $user = $_POST['user'];
    $password = $_POST['password'];

    $query = "INSERT INTO login (user, password) VALUES ('$user', '$password')";
    $sql = mysqli_query($connection, $query);

    if($sql) {
        echo json_encode(array(
            'status' => 'success',
            'code' => 200,
        ));
    } else {
        echo json_encode(array(
            'status' => 'failed',
            'code' => 400,
        ));
    }
?>