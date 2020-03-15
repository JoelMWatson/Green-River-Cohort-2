<?php 
    $db;

    $username = "joel_db";
    $password = "joel123";
    $hostname = "localhost";
    $database = "joel_bee_database";
    try {
        //Instantiate a database object
        $db = new PDO("mysql:host=$hostname; dbname=$database", $username, $password);
        echo '<p>Connected to database</p>';
    } catch(PDOException $e) {
        echo $e->getMessage();
    }
        
    include "Models/observationModel.php";

    $obsModel = new ObservationModel($db);
    $obsList = $obsModel->getAllObservations();

    include "Views/observation-list.php";

    $db = null;
?>