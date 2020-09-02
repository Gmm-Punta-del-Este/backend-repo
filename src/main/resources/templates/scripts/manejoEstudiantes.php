<?php 
include 'ConexionMysql.php';

$tipoAccion = $_POST["tipoAccion"];

session_start();
// http://php.net/manual/es/function.session-start.php
$_SESSION['datosAEnviar'] = '';

switch ($tipoAccion) {
	case 'agregar':
		$_SESSION['dataEnviada'] = array('ci' => $_POST["ci"],'nombre' => $_POST["nombre"], 'apellido' => $_POST["apellido"],'edad' => $_POST["edad"]);

		header( 'Location: agregar.php' );
		break;

	case 'modificar':
		$_SESSION['dataEnviada'] = array('ci' => $_POST["ci"]);
		
		header( 'Location: modificar.php' );
		break;

	case 'eliminar':
		$_SESSION['dataEnviada'] = array('ci' => $_POST["ci"]);
		header( 'Location: eliminar.php' );
		# code...
		break;

	case 'mostrar':
		# code...
		$_SESSION['dataEnviada'] = $_POST["ci"];
		header('Location: mostrar.php');

		break;	
		
	default:
		header( 'Location: ../index.html' );
		break;
}

?>