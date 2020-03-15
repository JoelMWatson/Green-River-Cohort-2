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
        <title>Beehive Study Home</title>
    </head>
 <body>
	<h1>Observation Data</h1>
	<form action="record.php" role="form" method="post">
		<div class="form-group">
			<label>Enter Hive Name: <input type="text" class="form-control" id="hive_name" name="hive_name" placeholder="Stingy">
		</div>
		
		<div class="form-group">
			<label>Enter Observation Date:  <input type="date" class="form-control" id="obs_date" name="obs_date">							
		</div>
		
		<div class="form-group">
			<label>Enter Observation Duration(in days): <input type="number" class="form-control" id="duration" name="duration" placeholder="0">											
		</div>
		
		<div class="form-group">
			<label>Enter Number of Mites Found: <input type="number" class="form-control" id="mite_count" name="mite_count" placeholder="0">												
		</div>
		
		<button type="submit" name="submit" class="btn btn-default">Submit</button>
		
	</form>
 </body>
</html>
