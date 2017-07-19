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

}
else{
	header("Location: upload.php");
}







?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>ImageRecovery</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Image</a></h1>
			</div>
			<div id="menu">
				<ul>
					<li class="current_page_item"><a href="#">Home</a></li>
					<li><a href="login.php">Login</a></li>
					<li><a href="register.php">Register</a></li>					
				</ul>

				
				
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
					<div class="post">
						<h2 class="title"><a href="#">Welcome to ImageInpating  </a></h2>
						
						<div style="clear: both;">&nbsp;</div>
						<div class="entry">
							<p>歡迎使用修補系統網站版</p>
							請點選右上方REGISTER註冊會員，註冊後到電子信箱點選認證信，即可登入使用系統。
					
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
