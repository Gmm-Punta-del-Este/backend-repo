<?php
include 'FileError.php';
function conect(){
	include 'valoresConexionMysql.php';
	$con = mysqli_connect($host,$user,$pass);
	mysqli_select_db($con,$bd);
	return $con;
}

function throw_ex($er){  
	throw new Exception($er);  
}  

function desconect($conexion){
	mysqli_close($conexion);
}

function QueryNonResult($sql){
	try {   
		$conexion = conect();
		$resultado = mysqli_query($conexion,$sql) or throw_ex(mysqli_error($conexion));
		if (mysqli_affected_rows($conexion)==0) { 
			die (msjerror("No se ha podido realizar la peticion"));
		}
	}  
	catch(exception $e) {

		$insert = 'Duplicate';
		if (strpos($e,$insert) !== false) {
		   $e = "The DataBASE is full of HEROS :3";
		}

  		die (msjerror($e));
	}
	desconect($conexion);
}

function QueryResult($sql){

	try {   
		$conexion = conect();
		$resultado = mysqli_query($conexion,$sql) or throw_ex(mysqli_error($conexion));

		if (mysqli_num_rows($resultado)==0) { 
			die (msjerror("Request FAIL, doesn't exist hero with this NAME"));
		}

		return $resultado;
	}  
	catch(exception $e) {
		$e = "Error: ".$e;
  		die (msjerror($e));
	}
	desconect($conexion);
}

function Login($sql){
	try {   
		$conexion = conect();
		$resultado = mysqli_query($conexion,$sql) or throw_ex(mysqli_error($conexion));

		if (mysqli_num_rows($resultado)==0) { 
			die (msjerror("No existe usuario con ese USERNAME"));
		}

		return $resultado;
	}  
	catch(exception $e) {
		$e = "Error: ".$e;
  		die (msjerror($e));
	}
	desconect($conexion);
}

?>

