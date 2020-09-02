<?php 
session_start();
$datos_estudiante = isset($_SESSION['dataEnviada']) ? $_SESSION['dataEnviada'] : 'null';

echo '<!DOCTYPE html>
<html>
<head>
	<title>Practico PHP | Ejercicio # </title>
	<link rel="stylesheet" type="text/css" href="../web/web.css">
</head>
<body>
	<div class="contenedor">
	<div class="header">
    	    <h2>Confirma eliminar el estudiante con la Cedula : '.$datos_estudiante['ci'].' ??? </h2>
        </div>
        <div class="body">
	    	<div class="formulario">
	           	<form action="eliminar2.php" method="POST">
			    	<input type="hidden" name="ci" value="'.$datos_estudiante['ci'].'" readonly="readonly">
			    	<button class="action-buttons" type="submit">SI</button>
	           	</form>
			    	<button class="action-buttons" onclick="window.history.go(-1)">Al formulario...</button>
			    	<a class="action-buttons" href="../index.html">Regresa al Menu</a>
	        </div>
        </div>
   	</div>
</html>';

	unset($_SESSION['dataEnviada']);
	session_destroy();
?>