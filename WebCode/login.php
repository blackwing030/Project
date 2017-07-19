<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">








<?php
header("Content-Type: text/html; charset=utf-8");
require_once("connMysql.php");
session_start();
//執行會員登入
if(isset($_POST["username"]) && isset($_POST["pw"])){		
	//繫結登入會員資料
	$query_Rec = "SELECT * FROM `memberdata` WHERE `m_id`='".$_POST["username"]."'";
	$RecLog = mysql_query($query_Rec);		
	//取出帳號密碼的值
	$row_RecLogin=mysql_fetch_assoc($RecLog);
	$username = $row_RecLogin["m_id"];
	$pw = $row_RecLogin["m_pw"];
	$md55 =md5($_POST["pw"]);
	
	//比對帳號 pw 比對成功 跳轉頁面
	if( ($_POST["username"] == $username) && ($md55 == $pw) && ($_POST["username"] !=NULL) && ($_POST["pw"] !=NULL)){
		session_start();
		$_SESSION["loginid"]=$username;		



	
		header("Location: upload.php");

	}
	else{?>
			
		<script>
		alert("帳號不存在或密碼錯誤");
		window.location.href='login.php';	
		</script>
	
			
<?php			
	}
	
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
				<h1><a href="#">Image</a></h1>
			</div>
			<div id="menu">
				<ul>
					<li ><a href="index.php">Home</a></li>
					<li class="current_page_item"><a href="login.php">Login</a></li>
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
						<h2 class="title"><a href="#">  登入頁面</a></h2>
				
						<div style="clear: both;">&nbsp;</div>
						<div class="entry">

					<form action="login.php" method="post">
　					帳號: <input  type="test"         name="username">
　					密碼: <input  type="password" name="pw">
					<p></p>
　					<input type="submit" value="登入">
					</form>
					
					
					
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
