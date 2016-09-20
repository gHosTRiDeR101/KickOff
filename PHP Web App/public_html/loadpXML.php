<?php

	$team = $_GET['team'];
	$file = __DIR__ . "/soccer.xml";
	$xml = simplexml_load_file($file);
	$tmp = $xml->children();
	$player_names = array();
	foreach($tmp[0]->children() as $teams) {
		if ($team == (String) $teams->name) {
			$team_e = $teams->players;
			foreach($team_e->children() as $players) {
				array_push($player_names, (String) $players->name);
			}

		}
	}
	header('Content-type:application/json;charset=utf-8');
	echo  json_encode($player_names); 


?>
