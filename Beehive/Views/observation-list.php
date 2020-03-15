<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Beehive Prototype Home</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="../style.css"/>
    </head>
    <body>
        <div id="table">
        <table>
            <thead>
                <th>Hive Name</th>
                <th>Observation Date</th>
                <th>Duration</th>
                <th>Mite Count</th>
            </thead>
            <tbody>
        <?php 
            foreach($obsList as $row) {
                echo '<tr>';
                echo '<td>' , $row['hive_name'] , '</td>';
                echo '<td>' , $row['observation_date'] , '</td>';
                echo '<td>' , $row['duration'] , '</td>';
                echo '<td>' , $row['mite_count'] , '</td>';
                echo '</tr>';
            }
        ?>
            </tbody>
        </table>
    </div>
    </body>
</html>
