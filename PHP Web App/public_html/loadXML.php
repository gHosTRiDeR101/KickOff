<?php

	$file = __DIR__ . "/soccer.xml";
	$xml = simplexml_load_file($file);
//	print_r($xml);	
//	$parser = xml_parser_create();
	$tmp = $xml->children();
	$team_names = array();
	foreach($tmp[0]->children() as $teams) {
		$team_names[]= (String) $teams->name;
		//echo $teams->name . "<br>"; 
	}
	//print_r($team_names);
	header('Content-type:application/json;charset=utf-8');
	//print_r($team_names);
	echo  json_encode($team_names); 


?>

