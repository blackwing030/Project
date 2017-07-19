<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : Highway   
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20111016

-->
<?php
header("Content-Type: text/html; charset=utf-8");
require_once("connMysql.php");

session_start();
//檢查是否經過登入
if(!isset($_SESSION["loginid"]) || ($_SESSION["loginid"]=="")){
	header("Location: logout.php");
}
//執行登出動作
if(isset($_GET["logout"]) && ($_GET["logout"]=="true")){
	unset($_SESSION["loginMember"]);
	header("Location: index.php");
}




?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Image</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Image </a></h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="index.php">Home</a></li>
					<li class="current_page_item"><a href="#">Upload</a></li>
					<li><a href="draw.php">Draw</a></li>
					<li><a href="logout.php">Sign out</a>	</li>
			
				</ul>

			</div>
					<!--<form action="" method="post">
　					<input  type="hidden"         name="logout">
　					<input type="submit" value="登出">	
					-->	
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
					<div class="post">

					
					
					
					
					
					
  <tr>
    <td class="tdbline">
	<table width="100%" border="0" cellspacing="0" cellpadding="10">
	
		<form action="upload.php" method="post" enctype="multipart/form-data" class="file">
		<b class="aboutsy">Select image to upload:</b><input type="file" name="fileToUpload" id="fileToUpload" class="aboutsy" /><br />
		<input type="submit" name="submit" value="上傳檔案" />
		</form>
		<?php
			//Post submit
			if (isset($_POST["submit"]) && isset($_FILES["fileToUpload"])) {
				//Set 上傳Image資料夾
				$target_dir = "ImageFile/"; 
				//User UploadFile Name
				$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
				$uploadOk = 1;
				//取得副檔名名稱
				$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
				
				// Check if image file is a actual image or fake image
				if(isset($_POST["submit"])) {
					//check file type image?
					$check = @getimagesize($_FILES["fileToUpload"]["tmp_name"]);
					
					if($check !== false) {
						//					if($check !== false && $imageFileType == "png"  ) {
						//set adjust image rate
						$ratio = $check[0]/$check[1]; // width/height
						$width = $check[0];
						$height = $check[1];
						/*
						if( $ratio > 1) {
								$width = 400;
								$height = 400/$ratio;
						}
						else {
								$width = 400*$ratio;
								$height = 400;

						}
										
*/										

						if( $ratio > 1) {
							if($width < 400){
								$width = $width * 2;
								$height = $height *2;
							}
							else if($width >1000){
								$width = 600;
								$height = 600/$ratio;
							}
							else{
								$width = $check[0];
								$height = $check[1];
							}
						}
						else {
							if($height  < 500){
								$width = $width * 1.5;
								$height = $height *1.5;
							}
							else if($height  >800){
								$width = 600*$ratio;
								$height = 600;
							}
							else{
								$width = $check[0];
								$height = $check[1];
							}							

						}

						
						
						
						
						echo "File is an image - " . $check["mime"] . ".";
						$uploadOk = 1;
						
						
						//move image tmpDir to imageDir
						move_uploaded_file($_FILES["fileToUpload"]["tmp_name"],$target_dir."Image.png");				
						imagepng(imagecreatefromstring(file_get_contents($target_dir."Image.png")), $target_dir."output.png");	
						//Adjust image size
						$src = imagecreatefromstring(file_get_contents($target_dir."Image.png"));
						$dst = imagecreatetruecolor($width,$height);
						imagecopyresampled($dst,$src,0,0,0,0,$width,$height,$check[0],$check[1]);
						imagedestroy($src);
						imagepng($dst,$target_dir."output.png"); // adjust format as needed
						imagedestroy($dst);	
						//Html show image 
						
						//echo " <img src=\"ImageFile\output.png\"   ";
						//echo "  width:500px;height:500px       ";
						//echo " >   ";
						
						//draw.php
						//sleep(1);
						header("Location:draw.php"); 
						
						} else {
						//
						echo "File is not an image Try again!";
						//echo "File is not an image or  imagetype not "."PNG!   ". "Try again!";
						$uploadOk = 0;					
						}

						
				}
				// Check if file already exists
				if (file_exists($target_file)) {
					echo "Sorry, file already exists.";
					$uploadOk = 0;
				}
				
				
			}
		?>


    </table>
	</td>
  </tr>				
				
				
				
				
				
				
				請選擇電腦裡的圖片檔案，進行上傳。
				
				
						<div style="clear: both;">&nbsp;</div>
						<div class="entry">
						</div>
					</div>


					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<div id="sidebar">
					<ul>
	


					</ul>
				</div>
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
</div>
<!-- end #footer -->
</body>
</html>
