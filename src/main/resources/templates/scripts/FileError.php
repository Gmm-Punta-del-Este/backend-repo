<?php

function msjerror($msj){
echo '<!DOCTYPE html>
<html>
<head>
    <title>Cargando DATOS</title>
    <link rel="stylesheet" type="text/css" href="../views/style.css">
    <link  rel="icon"   href="https://b.thumbs.redditmedia.com/ImvLBTlS49O6ugQqGTpSXaTlcoyI9gIOrrnrTKlSf8c.png" type="image/png" />
</head>
<body>
    <div class="container">
        <div style="font-size:50px;" id="ok" class="ready">
        '.$msj.'
        <br>
        <a style="font-size:30px"; href="login.php"> VOLVER AL LOGIN PERRI</a>
        </div>

    </div>

<script>
function myFunction() {
    setTimeout(function(){document.getElementById("ok").innerHTML = "ERROR";},230)
    setTimeout(function(){document.getElementById("buttonSet").innerHTML = "<h1>"+ "Deleting Heros..."+"</h1>";},1500)
    setTimeout(function(){window.location.replace("deleteHeros.php");},2500)
}

</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.0.0/p5.min.js"></script>

</body>
</html>
';
}
//setTimeout(function(){setButton()},500)<script src="function.js"></script>
?>

