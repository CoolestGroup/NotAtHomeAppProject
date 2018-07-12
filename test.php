<?php
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_REQUEST["name"]) && isset($_REQUEST["password"])) {
    $name = $_REQUEST["name"];
	$password = $_REQUEST["password"];
 	//$name = "root";
 	//$password = "root";
    
	$result = mysql_query("SELECT * FROM user WHERE name= $name");
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $user = array();
            $user["name"] = $result["name"];
            $user["password"] = $result["password"];
			
			if($password == $result["password"])
        		$response["success"] = 1;
			else 
				$response["success"] = 0;
 
            // user node
            $response["user"] = array();
 
            array_push($response["user"], $user);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No user found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No user found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}

?>