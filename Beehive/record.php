<?php
    /*
     * IT 328 
     * Joel Watson
     */
    
    //Error reporting
    ini_set('display_errors', 1);
    error_reporting(E_ALL);
?>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">	
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="style.css"/>
		<title>Record Data</title>
		<style>
			p {
				text-align: center;
				margin: 10px;
			}
		</style>
	</head>
	<body>
	<?php
		if (isset($_POST['submit'])) {
            //validation
            $isValid = true;
			
			if (!empty($_POST['hive_name'])) {
				$hive_name = $_POST['hive_name'];
			} else {
				echo "<p>Must enter a hive name.</p>";
				$isValid = false;
			}
			if (!empty($_POST['obs_date'])) {
				$obs_date = $_POST['obs_date'];
			} else {
				echo "<p>Must enter an observation date.</p>";
				$isValid = false;
			}
			if (!empty($_POST['duration'])) {
				if ($_POST['duration'] > 0) {
					$duration = $_POST['duration'];
				}
				else {
					echo "<p>Duration must be 1 or more days.</p>";
				}
			} else {
				echo "<p>Must enter the duration.</p>";
				$isValid = false;
			}
			if (!empty($_POST['mite_count'])) {
				if ($_POST['mite_count'] < 0) {
					echo "<p>Mite count cannot be negative.</p>";
				}
				else {
					$mite_count = $_POST['mite_count'];
				}
			} else {
				echo "<p>Must enter a mite count.</p>";
				$isValid = false;
			}
			//Display summary
			if ($isValid) {
                
                include "admin.php";
			    
				$obsModel->setOneObservation($hive_name, $obs_date, $duration, $mite_count);
				
			}
		}
				
	?>
	</body>
</html>