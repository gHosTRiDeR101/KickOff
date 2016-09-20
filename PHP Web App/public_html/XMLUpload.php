<html>
	<body>

		<?php

			if ($_FILES["upload"]["error"] == 0) {
				$file_tmp = $_FILES['upload']['tmp_name'];
				

					    
				$user = "jselwy";
				$xmlFile_t = __DIR__  . "/soccert.xml";
				if(file_exists($xmlFile_t)) {
					unlink($xmlFile_t);
				}
				$uploaded = move_uploaded_file($file_tmp, $xmlFile_t);
/*
				$file_lines = file($xmlFile_tmp, 'r');
				
				$param = "echo '' > $xmlFile";
				exec($param);				

				$file2 = fopen($xmlFile, 'w');
				
				foreach($file_lines as $line) {
					fwrite($file2, $line . "\n");
				}
				fclose($file2);
				chmod($file2, 777);

*/				/*
				$command = "cp $xmlFile_tmp $xmlFile";
				exec($command);
				$command = "chown $xmlFile jselwy";
				exec($command);
				chmod($xmlFile,777);
				*/
				//chown($xmlFile, $user);
				//chown($xmlFile2, $user);
				if($uploaded) {
					echo "uploaded to " . $xmlFile;
				}
			}


		?>



</html>
