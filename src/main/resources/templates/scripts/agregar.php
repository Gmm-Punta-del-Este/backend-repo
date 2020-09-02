<?php
include 'ConexionMysql.php';

	session_start();
	$datos_estudiante = $_SESSION['dataEnviada'];

	$ci = $datos_estudiante['ci'];
	$edad = $datos_estudiante['edad'];
	$nombre =$datos_estudiante['nombre'];
	$apellido = $datos_estudiante['apellido'];

	$sql = "insert into estudiante values ('${ci}','${nombre}','${apellido}', '${edad}')";
	//$sql = "insert into estudiante (ci,nombre,apellido,edad) values (".$datos_estudiante['ci'].", ".$datos_estudiante['nombre'].", '".$datos_estudiante['apellido'] ."',".$datos_estudiante['edad'].") ";

	QueryNonResult($sql);

	unset($_SESSION['dataEnviada']);
	session_destroy();

	header('Location:../index.html');


?>