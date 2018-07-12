<?php
 
class DB_CONNECT {
	
    function __construct() {
        $this->connect();
    }
    function __destruct() {
        $this->close();
    }
 
    function connect() {;
        // import database connection variables
        require_once __DIR__ . '/db_config.php';
 
        // Connecting to mysql database
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) ;
 		if(!$con)
			die(mysqli_connect_error());
        // returing connection cursor
        return $con;
    }

    function close() {
        mysqli_close($con);
    }
 
}
 
?>