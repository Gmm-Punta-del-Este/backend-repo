<?php 
include '../scripts/ConexionMysql.php';

$sql = "select * from estudiante ORDER BY apellido, nombre ASC";
$estudiante = QueryResult($sql);

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
			<table>
                    <tr>
                    	<th>Cedula de identidad</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Edad</th>
                    </tr>
                    ';
                    foreach ($estudiante as $fila) {
					echo '<tr>';
                       	echo '<td>'.$fila['ci'].'</td>';
                       	echo '<td>'.$fila['apellido'].'</td>';
                       	echo '<td>'.$fila['nombre'].'</td>';
                       	echo '<td>'.$fila['edad'].'</td>';
                       	echo '</tr>';
					}                        
echo '
					<tr>
					<th colspan="3">Cantidad de estudiantes </th>
					<th>'.mysqli_num_rows($estudiante).'</th>
					</tr>
                    </table>	    	
        </div>
   	</div>
</html>'
?>