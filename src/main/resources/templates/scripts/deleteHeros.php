<?php
include 'ConexionMysql.php';
$sqlStats = "delete from stats";
QueryNoNResult($sqlStats);

$sqlHeros = "delete from heros";
QueryNoNResult($sqlHeros);

$sqlCover = "delete from heros_cover";
QueryNoNResult($sqlCover);


echo '<!DOCTYPE html>
<html>
<head>
    <title>Cargando DATOS</title>
    <link rel="stylesheet" type="text/css" href="../views/style.css">
</head>
<body>
    <div class="container">
        <div class="ready">
        <h3><a href="loadHeros.php">Go to charge BD</a></h3>
        </div>
    </div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.0.0/p5.min.js"></script>
<script src="function.js"></script>
</body>
</html>
';
?>