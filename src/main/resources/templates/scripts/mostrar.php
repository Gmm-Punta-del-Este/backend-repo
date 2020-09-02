<?php 
include '../scripts/ConexionMysql.php';

session_start();
$datos_estudiante = isset($_SESSION['dataEnviada']) ? $_SESSION['dataEnviada'] : 'null';

$sql = "select * from estudiante where ci='".$datos_estudiante."'";

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
        <a class="action-buttons" href="../web/mostrar.html">Ver otro estudiante</a>
        </div>
        <div class="body">
			<table>
                <thead>
                    <tr>
                    	<th>Cedula de identidad</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Edad</th>
                    </tr>
                    </thead>
                    <tbody align="center">';
                      	echo '<tr>';
                       	echo '<td>'.$estudiante['ci'].'</td>';
                       	echo '<td>'.$estudiante['nombre'].'</td>';
                       	echo '<td>'.$estudiante['apellido'].'</td>';
                       	echo '<td>'.$estudiante['edad'].'</td>';

                       	echo '</tr>';
echo '
                        </tbody>
                    </table>	    	
        </div>
   	</div>
</html>';
  unset($_SESSION['dataEnviada']);
  session_destroy();

?>