<?php

use LDAP\Result;

    require_once('connection.php');


    $query = $connection->prepare("SELECT * FROM product");
    $query->execute();
    $query->bind_result($productCode, $productName, $price, $currency, $discount, $dimension, $unit);

    $result = array();

    while($query->fetch()) {
        $temp = array();
        $temp['productCode'] = $productCode;
        $temp['productName'] = $productName;
        $temp['price'] = $price;
        $temp['currency'] = $currency;
        $temp['discount'] = $discount;
        $temp['dimension'] = $dimension;
        $temp['unit'] = $unit;

        array_push($result, $temp);
    }

    echo json_encode(array('data' => $result));
?>