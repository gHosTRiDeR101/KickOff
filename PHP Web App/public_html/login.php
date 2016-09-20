<?php


	if (isset($_POST['username']) && (isset($_POST['password']))) {

		$user_set = (isset($_POST['username']));
		$pass_set = (isset($_POST['password']));

		if ($user_set && $pass_set) {
			$user = $_POST['username'];

			$pass = $_POST['password'];

			if ($user == "walter" && $pass == "white") {

				header("Location: Input.php");
				die();
			}
			else {

				header("Location: usernotfound.html");
				die();
			}

		}
		else {
			header("Location: usernotfound.html");
			die();


		}


	} 





?>
