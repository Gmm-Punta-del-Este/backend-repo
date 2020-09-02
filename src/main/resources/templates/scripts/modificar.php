<?php 
include '../scripts/ConexionMysql.php';
session_start();
$datos_estudiante = isset($_SESSION['dataEnviada']) ? $_SESSION['dataEnviada'] : 'null';

$ci = $datos_estudiante['ci'];
$sql = "select * from estudiante where ci='$ci'";
$estudiante = QueryResult($sql);

$estudiante = $estudiante->fetch_array(MYSQLI_ASSOC);

echo '<!DOCTYPE html>
<html>
<head>
	<title>Practico PHP | Ejercicio # </title>
	<link rel="stylesheet" type="text/css" href="../web/web.css">
</head>
<body>
	<div class="contenedor">
        <div class="header">
    	    <a class="action-buttons" href="../index.html">Regresa al Menu</a>
        </div>
        <div class="body">
	    	<div class="formulario">
	           	<form action="../scripts/modificar2.php" method="POST">
	           		<input type="hidden" name="tipoAccion" value="modificar">
			    	<label>Cedula Identidad </label>
			    	<input type="number" name="ci" value="'.$estudiante['ci'].'" readonly="readonly" >
			    	<label>Nombre </label>
			    	<input type="text" name="nombre" value="'.$estudiante['nombre'].'" readonly="readonly">
			    	<label>Apellido </label>
			    	<input type="text" name="apellido" value="'.$estudiante['apellido'].'" readonly="readonly">
			    	<label>Edad </label>
			    	<input type="number" name="edad" placeholder="Edad previa '.$estudiante['edad'].'">
			    	<button class="action-buttons" type="submit">Modificar</button>
	           	</form>
	        </div>
        </div>
   	</div>
</html>';
	unset($_SESSION['dataEnviada']);
	session_destroy();
?>