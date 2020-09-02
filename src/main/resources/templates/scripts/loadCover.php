<?php 
include 'ConexionMysql.php';

$sqlCoverDelete = "delete from heros_cover";
QueryNoNResult($sqlCoverDelete);

$HeroCover = json_decode(file_get_contents("../database/character_info_cover.json"), true);

foreach ($HeroCover as $heroC) {
	# code...

	$url = $heroC['id'];
	$name = $heroC['name'];

	$sqlCover = "insert into heros_cover values (
	'${name}',
	'${url}'
	)";

	QueryNoNResult($sqlCover);
}

?>