<?php
class ObservationModel {
    private $db;
    
    public function ObservationModel(PDO $db) {
        $this->db = $db;
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
        
        
    public function setOneObservation($hive_name, $obs_date, $duration, $mite_count) {
        //Define the query
		$query = "INSERT INTO observation (hive_name, observation_date, duration, mite_count, submit_date)
		  		  VALUES (:hive_name, :obs_date, :duration, :mite_count, NOW())";

        //Prepare the statement
        $statement = $this->db->prepare($query);
        
        //Bind the parameters
        $statement->bindParam(':hive_name', $hive_name, PDO::PARAM_STR);
		$statement->bindParam(':obs_date', $obs_date, PDO::PARAM_STR);
		$statement->bindParam(':duration', $duration, PDO::PARAM_STR);
        $statement->bindParam(':mite_count', $mite_count, PDO::PARAM_STR);

        //Execute
        $success = $statement->execute();
        
        $count = $statement->rowCount();
        
        if ($statement->rowCount() == 1) {
					echo "<p>Data Entered Successfully!";
					echo "<p><a href='http://joel.greenrivertech.net/328/beehive/excel.php'>Download Data to Excel</a></p>";
				}
				else {
					echo "<p>Failed to enter data correctly.</p>";
                    echo "<p>Rows Affected: ".$count.".</p>";
				}
    }
    
    public function getAllObservations() {
        // PDO prepared statement code to select all rows and return them
        //define query
        $query = "SELECT hive_name, observation_date, duration, mite_count FROM observation";
        
        //prepare the statement
        $statement = $this->db->prepare($query);
        
        //execute
        $success = $statement->execute();
        
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }
        
}
?>