<?php
// database connection details //////////////////////
// defing database connection parameters
$host = "localhost";
$dbuser = "prosyste_iuea";
$dbpassword = "Qazxsw@123";
$db_name = "prosyste_iuea";

// connecting to the database
$link = mysqli_connect($host,$dbuser,$dbpassword) or die("Cant connect to the server".mysql_error());

// select the mysql database gto use
$dbselect = mysqli_select_db($link, $db_name) or die("Failed to select database: ".mysqli_error($link));

/////////////////////////////////////////////////////////////

// calling form paraters
$name = $_POST['name'];
$course = $_POST['course'];


// inserting data in the database table

$qry = mysqli_query($link,"insert into users (name,course) 
values ('$name','$course')")
or die("Error while inserting data in the table".mysqli_error($link));

if($qry){

echo "Data inserted succesffuly ";
//header("Location: form.php");
}

?>