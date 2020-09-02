<?php 
include 'ConexionMySql.php';

$name = $_GET['hero'];

$sql = "select * from heros, stats, heros_cover where heros.name = stats.name and heros_cover.name = heros.name and heros.name = '${name}'";
$hero = QueryResult($sql);
$hero = $hero ->fetch_array(MYSQLI_ASSOC);
$cover = 'https://assets.epicsevendb.com/_source/face/'.$hero['url'].'_su.png';

$arrayLeftGear = array('70'=>'Gear 70/71','85'=> 'Gear 72/85','88'=> 'Gear 88','90'=>'Gear 90' );

$arrayNecklace = array(
	'0-0-0' =>'70/71 - Crit Dmg ',
	'0-0-1' =>'70/71 - Crit Rate ',
	'0-0-2' =>'70/71 - Atk',
	'-----',
	'1-0-0' =>'72/85 - Crit Dmg ',
	'1-0-1' =>'72/85 - Crit Rate ',
	'1-0-2' =>'72/85 - Atk ',
	'-----',
	'2-0-0' =>'88 - Crit Dmg ',
	'2-0-1' =>'88 - Crit Rate ',
	'2-0-2' =>'88 - Atk ',
	'-----',
	'3-0-0' =>'90 - Crit Dmg ',
	'3-0-1' =>'90 - Crit Rate ',
	'3-0-2' =>'90 - Atk ',

);

$arrayBoots = array(
	'0-2-0' =>'70/71 - Speed ',
	'0-2-1' =>'70/71 - Atk/Hp/Def ',
	'-----',
	'1-2-0' =>'72/85 - Speed ',
	'1-2-1' =>'72/85 - Atk/Hp/Def ',
	'-----',
	'2-2-0' =>'88 - Speed ',
	'2-2-1' =>'88 - Atk/Hp/Def ',
	'-----',
	'3-2-0' =>'90 - Speed ',
	'3-2-1' =>'90 - Atk/Hp/Def ',

);

$arrayRing = array(
	'0-1-0' => '70/71 - Hp/Atk/Def/Res/Eff',
	'-----',
	'1-1-0' => '72/85 - Hp/Atk/Def/Res/Eff',
	'-----',
	'2-1-0' => '88 - Hp/Atk/Def/Res/Eff',
	'-----',
	'3-1-0' => '90 - Hp/Atk/Def/Res/Eff',
);


// print_r($hero);
// echo "<br>";

echo '
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="../views/style.css">
	<link  rel="icon"   href="https://b.thumbs.redditmedia.com/ImvLBTlS49O6ugQqGTpSXaTlcoyI9gIOrrnrTKlSf8c.png" type="image/png" />
</head>
<body>
	<div class="header">
		
		<!--<img class="banner" src="https://b.thumbs.redditmedia.com/m149BZ3T2uq9aeRjmucMoijbOPuqDpGLk5qMCrveINk.png"> -->
		<!-- <img class="banner" src="https://b.thumbs.redditmedia.com/ciCYb6xMeAEt71-tVmgnLC1TrOL1zdrxn84WYLWQEtU.png"> -->
		
		<img class="logo" src="https://b.thumbs.redditmedia.com/ciCYb6xMeAEt71-tVmgnLC1TrOL1zdrxn84WYLWQEtU.png">

		<div class="logo-banner">
			<img src="https://b.thumbs.redditmedia.com/ST2DHRvNONRtEqTNMdneVsOc78Hc4MZV7AWmIWXC9fc.png">
		</div>

		<div class="nav-top-bar">			
			<div class="nav-top-var-tag">
				<button class="button-nav-bar" id="heroList" onclick="createBuild()">Create your build</button>
			</div>
			<div class="nav-top-var-tag">
				<button class="button-nav-bar" id="heroList" onclick="loadBuild()">Load your build</button>
			</div>
			<div class="nav-top-var-tag">
				<button class="button-nav-bar" id="heroList" onclick="listToHero()">Heros list</button>
			</div>
			<div class="nav-top-var-tag">
				<button class="button-nav-bar" id="heroList" onclick="backToHome()">Back to home</button>
			</div>
		</div>
	</div>
	<div class="body-hero">
';
//stats and left gear
echo '
<script type="text/javascript" src="gearSelector.js"></script>
	<div class="stats-hero">
		<table class="table-stats-hero">
			<thead>
				<tr>
					<th>Name Stat</th>
					<th>Base Stat1</th>
					<th>&nbsp + &nbsp</th>
					<th>Stats agregados</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<span>Attack</span><br>
						<span>Defense</span><br>
						<span>Health</span><br>
						<span>Speed</span><br>
						<span>Critical Hit Chance</span><br>
						<span>Critical Hit Damaage</span><br>
						<span>Effectiveness</span><br>
						<span>Effect Resistance</span><br>
					</td>
					<td>
						<div id="baseAtk">'.$hero["attack"].'</div>
						<div>'.$hero["defense"].'</div>
						<div>'.$hero["hp"].'</div>
						<div>'.$hero["speed"].'</div>
						<div id="baseCr">'.$hero["critRate"].'</div>
						<div>'.$hero["critDmg"].'</div>
						<div>'.$hero["effect"].'</div>
						<div id="baseRes">'.$hero["resEffect"].'</div>
					</td>
					<td>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
						<div>&nbsp+&nbsp</div>
					</td>
					<td>
						<div id="atk">'.$hero["attack"].'</div>
						<div id="def">'.$hero["defense"].'</div>
						<div id="hp">'.$hero["hp"].'</div>
						<div id="spd">'.$hero["speed"].'</div>
						<div id="cr">'.$hero["critRate"].'</div>
						<div id="cd">'.$hero["critDmg"].'</div>
						<div id="eff">'.$hero["effect"].'</div>
						<div id="res">'.$hero["resEffect"].'</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="gear-and-image">
		<div id="left-gear-hero" class="left-gear-hero">
			<div class="gear-selector">
				<select id="weaponLv" onChange="displayLeftGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayLeftGear as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="weapon" src="../utils/testGear70.png"/></div>
			</div>


			<div class="gear-selector">
				<select id="helmetLv" onChange="displayLeftGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayLeftGear as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="helmet" src="../utils/testGear70.png"/></div>
			</div>
			<div class="gear-selector">
				<select id="chestLv" onChange="displayLeftGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayLeftGear as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="chest" src="../utils/testGear70.png"/></div>
			</div>
		</div>

';

echo '	<div class="name-and-image">
			<div class="image-name-hero">
				<div class="name-hero">'.
					$hero['name'].
				'</div>
			</div>
			<div class="imagen-hero"><img  src="'.$cover.'"/>
			</div>
		</div>

';

//right gear
echo '
		<div class="right-gear-hero">
			<div class="gear-selector">
				<select id="necklaceLv" onChange="displayRightGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayNecklace as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="necklace" src="../utils/testGear70.png"/></div>
			</div>
			<div class="gear-selector">
				<select id="ringLv" onChange="displayRightGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayRing as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="ring" src="../utils/testGear70.png"/></div>
			</div>
			<div class="gear-selector">
				<select id="bootsLv" onChange="displayRightGear(this.id,this.value);" class="select-lv-gear">';
					foreach ($arrayBoots as $key => $value) {
						echo '<option value="'.$key.'">'.$value.'</option>';}
echo '			</select>
				<div><img id="boots" src="../utils/testGear70.png"/></div>
			</div>
		</div>

	</div>
';

echo '
	</div>
	<div class="footer">
		<div class="icon-upper">
			<img src="https://image.flaticon.com/icons/svg/57/57093.svg"> 
		</div>
	</div>


<script type="text/javascript">
	function listToHero() {
		setTimeout(function(){window.location.replace("listHeros.php");},100)
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