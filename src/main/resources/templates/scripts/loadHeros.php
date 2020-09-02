<?php 

include 'ConexionMysql.php';

echo '<!DOCTYPE html>
<html>
<head>
	<title>Cargando DATOS</title>
	<link rel="stylesheet" type="text/css" href="../views/style.css">
	<link  rel="icon"   href="https://b.thumbs.redditmedia.com/ImvLBTlS49O6ugQqGTpSXaTlcoyI9gIOrrnrTKlSf8c.png" type="image/png" />
</head>
<body>
	<div class="container">
		<div id="ok" class="ready">
			<h1>DO YOU WANT UPLOADED YOUR HERO DATABASE ???</h1>
				<button class="myButton" onclick="myFunction()">CLICK ME!!</button>
		</div>
		<div id="buttonSet" class="ready">
		</div>
	</div>

<script>
function myFunction() {
	setTimeout(function(){document.getElementById("ok").innerHTML = "Uploading..";},250)
	setTimeout(function(){document.getElementById("ok").innerHTML = "Ready, LETS TRY IT!";},2500)
	setTimeout(function(){setButton()},500)
}

function setButton() {
	setTimeout(function(){document.getElementById("buttonSet").innerHTML = "<h1>"+ "Go to page"+"</h1>";},2500)
	setTimeout(function(){document.getElementById("buttonSet").innerHTML = "<h1>"+ "Redirecting..."+"</h1>";},3500)
	setTimeout(function(){window.location.replace("listHeros.php");},5000)
}
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.0.0/p5.min.js"></script>
<script src="function.js"></script>
</body>
</html>
';


//Load info
$HeroInformation = json_decode(file_get_contents("../database/character_info.json"), true);




foreach ($HeroInformation as $hero => $value) {
// HERO VALUES
	$name = $value['name'];
	if($value['element'] =="Earth"){
		$element = "https://e7herder.com/static/game/img/cm_icon_prowind.png";
	}else{
		$element = "https://e7herder.com/static/game/img/cm_icon_pro".strtolower($value['element']).".png";
	}
	$star_nat = (int)$value['rarity'];
	$class = $value['class'];
	$imagen = $value['image'];
	$link = $value['link'];

	$array_stats = $value['stats'];
	$array_awakening = $value['awakening'];

	foreach ($array_stats as $key => $valor) {
		// STATS VALUES
		switch ($key) {
			case 'attack':
				$atk = (int)$valor;
			break;
			case 'health':
				$hp = (int)$valor;
			break;
			case 'defense':
				$def = (int)$valor;
			break;
			case 'speed':
				$spd = (int)$valor;
			break;
			case 'critical_hit_chance':
				$critRate = $valor ;
			break;
			case 'critical_hit_damage':
				$critDmg = $valor;
			break;
			case 'effectiveness':
				$eff = $valor;
			break;
			case 'effect_resistance':
				$resEff = $valor;
			break;
		}
				//added
				$dualAtk = 5;
	}

	foreach ($array_awakening as $keyA => $valorA) {
		// Specificly stats awakeinig ADDED
		switch ($keyA) {
			case 'Attack':
				$atk = $valorA + $atk;
			break;
				case 'Speed':
					$spd = $valorA + $spd;
				break;
				case 'Critical Hit Rate':
					$critRate = $valorA + $critRate;
				break;
				case 'Critical Hit Damage':
					$critDmg = $valorA+$critDmg;
				break;
				case 'Effectiveness':
					$eff = $valorA+$eff;
				break;
				case 'Effect Resistance':
					$resEff = $valorA+$resEff;
				break;
		}
				//added
				$dualAtk = 5;
	}

	$sqlHero = "insert into heros values (
	'${name}', 
	'${star_nat}',
	'${class}',
	'${element}',
	'${imagen}',
	'${link}'
	)";


	//Insert Hero ${name}
	QueryNoNResult($sqlHero);

	$sqlStats = "insert into stats values (
	'${hp}',
	'${atk}',
	'${def}',
	'${spd}',
	'${critRate}',
	'${critDmg}',
	'${eff}',
	'${resEff}',
	'${dualAtk}',
	'${name}'
	)";

	//Insert staats for ${name} 
	QueryNoNResult($sqlStats);

	

} // fin foreach



?>