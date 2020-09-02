<?php
include '../scripts/ConexionMysql.php';
$ci = $_POST['ci'];
$sql = "delete from estudiante where ci = ${'ci'}";
QueryNonResult($sql);


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
        <div class="body-error">
            <br>
            <br>
            <h3>Borrado exitoso</h3>
            <br>
            <p>El estudiante ha sido borrado correctamente.</p>
	   </div>
    </div>
</html>';
?>