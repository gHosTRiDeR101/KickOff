<html>
<head>
	
	<?php
		$jar = "/home/2010/jselwy/public_html/Server.jar";
		$team1_set = (isset($_POST['team_1']));  
		$team2_set = (isset($_POST['team_2']));
		$end_set = (isset($_POST['end']));

		$player_set = (isset($_POST['player']));
		$pEvent_set = (isset($_POST['player_event']));
		$select_set = (isset($_POST['selection_list']));
		$match_set = (isset($_POST['matchName']));
		$penalty_set = (isset($_POST['penalty']));

		$player = $player_set? $_POST['player'] :  null;
		$pEvent = $pEvent_set? $_POST['player_event'] : 0;
		$select_list = $select_set? $_POST['selection_list'] : 0;

		$team1 = $team1_set? $_POST['team_1'] : null;
		$team2 = $team2_set? $_POST['team_2'] : null;
		$match = $match_set? $_POST['matchName'] : null; 
		$end = $end_set? $_POST['end'] : 0;
		$penalty = $penalty_set? $_POST['penalty'] : 0;

		//$parameters = "java -jar";
		if ($player_set && $team1_set) {

			//$parameters += " 1";
			//$parameters += " " . $end;
			//$parameters += " " . $team1;
			//$parameters += " " . $player;
			//$parameters += " " . $match;
			//$parameters += " " . $pEvent;
			//$parameters += " " . $select_list;
			//$parameters += " " . $penalty;
			if ($penalty_set) {
				$parameters = "java -jar $jar 1 $end $team1 $player $match $pEvent $select_list $penalty 2>&1";
			} else {
				$parameters = "java -jar $jar 1 $end $team1 $player $match $pEvent $select_list 2>&1";
			}
			// run Java Applet
			$error = false; 
		} else if ($team1_set && $team2_set && $match_set) {
			$parameters = "java -jar $jar 1 2 $team1 $team2 $match 2>&1";
			//$parameters = " 1";
			//$parameters += " 2";
			//$parameters += " " . $team1;
			//$parameters += " " . $team2;
			//$parameters += " " . $match;
					
			$error = false; 
		} else if ($match_set) {
			$error = true;
		}
		//$parameters += " 2>&1";	
		if ($error == false) {
//    			echo '<script type="text/javascript">alert("'. $parameters . '")</script>';

			exec($parameters, $output);

			$count = count($output);
			for($i=0; $i<$count; $i++) {
				$out += $output[$i];
			}
//			echo '<script type="text/javascript">alert("' . $out . '");</script>';
		}
	?>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>

		$(document).ready( function () {
			Update_Page();

		});
		function Update_Page(obj) {

			var post = "<?php echo $team1_set ?>";
			var type_set = "<?php echo $type_set ?>";
			var type = "<?php echo $type ?>";
			
			var error = "<?php echo $error ?>";
			if (error) {
				loadError();
			}
			else if (post) {
				/*if (type_set) {
					if (type == "batch") {
						loadBatch();
					} else if (type == "live") {
						loadLive();
					}
				} else {
				*/
				//}
				var end = "<?php echo $end ?>";
				if (end == 1) {
					loadSelectionData();
				} else {
					loadInputData();
				}
			} else {
				loadSelectionData();
			}
			var html = '<input type="submit">';
			$('#Submit').html(html);
			

		}

		function loadError(obj) {

			var html = "ERROR OCCURED!";
			$('#content').html(html);
			loadSelectionData();

		}

		function loadInputData(obj) {
			var team1 = "<?php echo $team1 ?>";
			var team2 = "<?php echo $team2 ?>";
			var type = "<?php echo $type ?>";
		
		        var htmlTeam = 'Select Team <br>';		
			htmlTeam += '<select id="t" name="team_1" onChange="loadPlayers(this)">';
			htmlTeam += '<option value="none">Please select a team</option>';
			htmlTeam += '<option value="' + team1 +'">'+ team1 + '</option>';
			htmlTeam += '<option value="' + team2 +'">'+ team2 + '</option>';
			htmlTeam += "</select>";
			$("#List1").html(htmlTeam);
			var html_End = '<input type="radio" name="end" value="0" id="ra" checked="checked">Continue Match<br><input type="radio" name="end" value="1" id="ra">End Match';
			$('#Radio').html(html_End);

					
	
		}

	/*	function loadBatch(obj) {

			var html = "Start Match";
			$('#content').html(html);

		}

		function loadLive(obj) {

			$.getJSON('loadmXML.php' ,function(json){
				
				var html = '<select id="match_list" name="match">';
				html += '<option value="none">Please select a player</option>';
				var $i = 0;
				$.each(json, function () {
					html += '<option value="' + this  +'">'+ this + '</option>';
					$i++;
				});   
				html += "</select>";
				$("#List1").html(html);
			});

		} 
	*/
		function loadPlayers(obj) {
		
			var team2 = "<?php echo $team2 ?>";
			var team1 = "<?php echo $team1 ?>";
			var team = team2; 
			if (obj.value == team2) {
				team = team1;
			}
			var match = "<?php echo $match ?>";	
			$.getJSON('loadpXML.php?team=' + obj.value ,function(json){
				
				var html = 'select player <br>';
				html += '<select id="t" name="player">';
				html += '<option value="none">Please select a player</option>';
				var $i = 0;
				$.each(json, function () {
					html += '<option value="' + this  +'">'+ this + '</option>';
					$i++;
				});   
				html += "</select>";
				$("#List2").html(html);
			});
			var htmlType = 'Select Match Event<br>';
			htmlType += '<select id="t" name="player_event" onChange="loadSelector(this)">';
			htmlType += '<option value="none">Please select an option</option>';
			htmlType += '<option value="' + 0 +'">'+ 'Infraction' + '</option>';
			htmlType += '<option value="' + 1 +'">'+ 'Shot' + '</option>';
			htmlType += "</select>";
			$("#Type").html(htmlType); 
			
			var htmlHidden = '<input type="hidden" name="team_2" value="' + team + '">';
			$("#Hidden1").html(htmlHidden);
			var htmlHidden2 = '<input type="hidden" name="matchName" value="' + match + '">';
			$("#Hidden2").html(htmlHidden2);
		}
		
		function loadSelector(obj) {
			var type = obj.value;
			if(type == 0) {
				var html = 'Infraction Type<br>';
				html += '<select id="t" name="selection_list" onChange="loadPenalty(this)">';
				html += '<option value="none">Please select an option</option>';
				html += '<option value="' + 0 +'">'+ 'Yellow' + '</option>';
				html += '<option value="' + 1 +'">'+ 'Red' + '</option>';
			} else {
				var html = 'Shot Type <br>';
				html += '<select id="t" name="selection_list">';
				html += '<option value="none">Please select an option</option>';

				html += '<option value="' + 0 +'">'+ 'Goal' + '</option>';
				html += '<option value="' + 1 +'">'+ 'Shot on Net' + '</option>';
			}
			html += "</select>";
			$("#Selector").html(html);
		}

		function loadPenalty(obj) {
				
			var html = 'Did a penalty occur?<br>';
			html += '<select id="t" name="penalty">';
			html += '<option value="none">Please select an option</option>';
			html += '<option value="' + 0 +'">'+ 'No Penalty Kick' + '</option>';
			html += '<option value="' + 1 +'">'+ 'Penalty Kick' + '</option>';
				
			html += "</select>";
			$("#Penalty").html(html);

		}  


		function loadSelectionData(obj) {

			//var html_radio = '<input type="radio" name="input" value="batch" id="radioc">Batch<br><input type="radio" name="input" value="live" id="radioc" checked="checked">Live<br>';
			//$('#Radio').html(html_radio);

			$.getJSON("loadXML.php",function(json){
				 
				var html_label1 = 'Team 1 : ';
				var html_label2 = 'Team 2 : ';
				var html_a = 'Team 1 : <br>';
				var html_b = 'Team 2 : <br>';
				html_a += '<select id="t" name="team_1">';
				html_b += '<select id="t" name="team_2">';
				var html = '<option value="none">Please select a team</option>';
				var $i = 0;
				$.each(json, function () {
					html += '<option value="' + this +'">'+ this + '</option>';
					$i++;
				});   
				html += "</select>";
				html_a += html; 
				html_b += html;
				$("#List1").html(html_a);
				$("#List2").html(html_b);

			}).fail(function( jqxhr, textStatus, error ) {
                    		var err = textStatus + ', ' + error;
                    		console.log( "Request Failed: " + err);
              		});
			
			var html_match = 'Match name<br>';
			html_match += '<input type="text" name="matchName">';
			$("#Selector").html(html_match);

		}
	</script>



	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KickOff - Input</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/landing-page.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

</head>
<body background="img/background.jpg">
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="Soccer.html">KickOff</a>
            </div>            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#">Input</a>
                    </li>
                    <li>
                        <a href="Analysis.php">Analysis</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
    <br>
    <br>
    <br>


    
    <div class="col-md-4">
	<form action="Input.php" method="POST">
		<div id = "Radio">
		</div>
		<div id = "List1">
		</div>
		<div id = "List2">
		</div>
		<div id = "Type">
		</div>
		<div id = "Selector">
		</div>
		<div id = "Penalty">
		</div>
		<div id = "Hidden1">
		</div>
		<div id = "Hidden2">
		</div>
		<div id = "Submit">
		</div> 
	</form>
	</div>

</body>
</html>
