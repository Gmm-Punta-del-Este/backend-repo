<?php

include 'ConexionMysql.php';

$sql = "select * from heros";
$heros = QueryResult($sql);


echo '
<!DOCTYPE html>
<html>
<head>
	<title>HERO LIST</title>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<link rel="stylesheet" type="text/css" href="../views/style.css">
	<link  rel="icon"   href="https://lh3.googleusercontent.com/uqYErHlu-ZAmsUC3ThfCxNANyp5Q5jQA73AmFWl_Jt-UVo0rgU0yNd-AuyCAd3Om2g" type="image/png" />

</head>
<body>
	<div class="header">
		<img  class="banner" onclick="backToHome()" src="https://b.thumbs.redditmedia.com/hckjtE9U_hwEvLsXgqaa_BveurmUlU8B7xSMOCOvoVM.png">
		
		</div>
		<div class="logo-banner">
			<img src="https://b.thumbs.redditmedia.com/ST2DHRvNONRtEqTNMdneVsOc78Hc4MZV7AWmIWXC9fc.png">
		</div>'
		;

echo '
<div class="body-hero-list">
	<div class="nav-top-bar">
				<button class="button-nav-bar" id="heroList" onclick="createBuild()">Create your build</button>
				<button class="button-nav-bar" id="heroList" onclick="loadBuild()">Load your build</button>
				<button class="button-nav-bar" id="heroList" onclick="listToHero()">Heros list</button>
				<button class="button-nav-bar" id="heroList" onclick="backToHome()">Back to home</button>


	</div>
		<div class="list-hero">
		<table id="tableSearch" class="responsive-table centered">
			<thead>
				<tr>
					<th>Hero (6 ★)</th>
					<th>Rol</th>
					<th>Element</th>
					<th>Attack</th>
					<th>Defense</th>
					<th>Health</th>
					<th>Speed</th>
					<th>Critical Hit Rate</th>
					<th>Critical Hit Damage</th>
					<th>Effectiveness</th>
					<th>Effect Resistance</th>
					<th>Acctions</th>
				</tr>
			</thead>
			<tbody>
		';
		foreach ($heros as $hero) {
			$sqlStats = "select * from stats where name='".$hero["name"]."'";
			$stat_hero = QueryResult($sqlStats);
			$stat_hero = $stat_hero ->fetch_array(MYSQLI_ASSOC);
echo '
                         
	            <tr>
	            <script type="text/javascript" src="elementHover.js"></script>';

echo '
	            <td class="celdaHero" style="font-weight:bold;">
	            	<a href="hero.php?hero='.$hero["name"].'"><img src="'.$hero["imagen"].'"/><br>'.$hero["name"].'<a/>
	            </td>
	            <td class="celdaRol">'.$hero['class'].'</td>
	            <td class="celdaElement"><img src="'.$hero['element'].'"/></td>
	            <td class="celdaStat">'.$stat_hero['attack'].'</td>
	            <td class="celdaStat">'.$stat_hero['defense'].'</td>
	            <td class="celdaStat">'.$stat_hero['hp'].'</td>
	            <td class="celdaStat">'.$stat_hero['speed'].'</td>
	            <td class="celdaStat">'.$stat_hero['critRate'].'</td>
	            <td class="celdaStat">'.$stat_hero['critDmg'].'</td>
	            <td class="celdaStat">'.$stat_hero['effect'].'</td>
	            <td class="celdaStat">'.$stat_hero['resEffect'].'</td>
	            <td class="celdaLink"> <a href="#">Create Build</a></td>
	            </tr>                       
            </tbody>
			

';
	}
echo '	</table>
		</div>
	</div>
	<div class="footer">
		<div class="icon-upper">
			<a href="#"><img src="https://image.flaticon.com/icons/svg/57/57093.svg"> </a>
		</div>
	</div>



<script type="text/javascript">
	function listToHero() {
		setTimeout(function(){window.location.replace("listHeros.php");},200)
	}
	function backToHome(){
		setTimeout(function(){window.location.replace("../index.html");},200)
	}
	function loadBuild(){
		setTimeout(function(){window.location.replace("../index.html");},200)
	}
	function createBuild(){
		setTimeout(function(){window.location.replace("../index.html");},200)
	}
</script>


</body>
</html>
';



?>