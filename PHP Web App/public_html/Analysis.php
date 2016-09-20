<html>
<body background="img/background.jpg">
	<head>


	<script>
		function Strategy(obj) {
			var select = obj.selectedIndex;
			if (select == 0) {
				var options = ['Infractions', 'Goals'];
			} else {
				var options = ['Infractions', 'Points', 'Goals'];
			}
			select = document.getElementById("strategy");
			for (var i = select.options.length -1; i >= 0; i--) {
				select.remove(i);
			}
		
			for (var i = 0; i<options.length; i++) {
				var opt = options[i]; 
				var el = document.createElement("option");
				el.textContent = opt; 
				el.value = i;
				select.appendChild(el); 
			}
		}
	</script>
	<?php
			
		$jar = "/home/2010/jselwy/public_html/Server.jar";

		if ($_SERVER['REQUEST_METHOD'] === 'POST') {
						
			if (isset($_FILES['xml']) && ($_FILES['xml']['error'] == UPLOAD_ERR_OK)) {
    				$xml = simplexml_load_file($_FILES['xml']['tmp_name']);                        
			} else {
				$arg1 = $_POST["number_of_rankings"];
				$arg2 = $_POST["ranking_type"]; 
				$arg3 = $_POST["ranking_strategy"];
				$parameters = "java -jar $jar 0 $arg1 $arg2 $arg3 2>&1";

				
				exec($parameters, $output);
				$count = count($output);
	 			
				$ranked = array();
				echo "<br><br><br><br>";
				echo "<table border=\"1\" style=\"width:100%\">";
				if ($arg2 == 0 && $arg3 == 0)	{
					echo "<tr> <th>Team</th> <th>Player</th> <th>Infractions</th> <th>Yellow Cards</th> <th>Red Cards</th> <th>Penalty Kicks</th></tr>";
				}
				if ($arg2 == 0 && $arg3 == 1)	{
					echo "<tr> <th>Team</th> <th>Player</th> <th>Total Shots</th> <th>Goals Scored</th> ";
				}
				if ($arg2 == 1 && $arg3 == 0)	{
					echo "<tr> <th>Team</th> <th>Total Infractions</th> <th>Yellow Cards</th> <th>Red Cards</th> <th>Penalty Kicks</th>";
				}
				if ($arg2 == 1 && $arg3 == 1)	{
					echo "<tr> <th>Team</th> <th>Shots Taken</th> <th>Goals Scored</td>";
				}
				for($i=0; $i<$count; $i++) {
					echo "<tr>";
					$rank_entity_terms = preg_split("/[\s,]+/", $output[$i]);
					foreach ($rank_entity_terms as $term) {
						echo "<td>";
						echo $term;
						echo "</td>";
					}
					echo "</tr>";
		   			//print($output[$i]);
				}
				echo "</table>";
			}
		} 
	?>


	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KickOff - Analysis</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/landing-page.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

	</head>
	<body>

		</head>
		<body>
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
		                    <a href="login_soccer.html">Input</a>
		                </li>
		                <li>
		                    <a href="#">Analysis</a>
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
		<form action="Analysis.php" method="post" target="Analysis_frame">
			<p>    Type    </p>
			<select id="t" name="ranking_type" onChange="Strategy(this)">
				<option value="0">player</option>
				<option value="1">league</option>
			</select>
			<br>
			<p>    Strategy    </p>
			<select id="t" name="ranking_strategy" id = "strategy">
				<option value="0">Infractions</option>
				<option value="1">Goals</option>
			</select>

			<p>    Number    </p>
			<select id="t" name="number_of_rankings">
				<option value="2">2</option>
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="100">100</option>

			</select>
			<input type="submit">
						
		</form>
	</div>



	</body>
</html>
