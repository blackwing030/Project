<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<?php
header("Content-Type: text/html; charset=utf-8");
require_once("connMysql.php");

if(isset($_POST["action"])&&($_POST["action"]=="join")){
	//找尋帳號是否已經註冊
	$query_RecFindUser = "SELECT `m_id` FROM `memberdata` WHERE `m_id`='".$_POST["m_username"]."'";
	$RecFindUser=mysql_query($query_RecFindUser);
	if (mysql_num_rows($RecFindUser)>0){
		header("Location: register.php?errMsg=1&username=".$_POST["m_username"]);
	}else{
		//	 user pw regnumber(random) state
	
		//copy success.php
		$copyphp=copy(success.php , s12317452.php);
	
	
	
	
	
	
		$random=6;
		$randoma="";
		//FOR回圈以$random為判斷執行次數
		for ($i=1;$i<=$random;$i=$i+1){
			//亂數$c設定三種亂數資料格式大寫、小寫、數字，隨機產生
			$c=rand(1,9);
			$randoma=$randoma.$c;
		}

	    
	
	
	    $ss=False;
	
		//資料庫寫入會員資料
		$query_insert = "INSERT INTO `memberdata` (`m_id` ,`m_pw`,`m_regnumber`,`m_state` ) VALUES (";
		$query_insert .= "'".$_POST["m_username"]."',";
		$query_insert .= "'".md5($_POST["m_passwd"])."',";
		$query_insert .= "'".$randoma."',";
		$query_insert .= "'".$ss."')";

		
		
		
		
		
		
		mysql_query($query_insert);
		//echo"<script>alert('註冊成功 請至信箱確認');</script>";
		//寄信設定
		require("PHPMailer_5.2.4/class.phpmailer.php");
		$mail = new PHPMailer(); // 建立新物件

		$mail->IsSMTP(); // 設定使用SMTP方式寄信
		$mail->SMTPAuth = true; // turn on SMTP authentication 需要SMTP驗證
		$mail->SMTPSecure = "ssl"; // Gmail的SMTP主機需要使用SSL連線
		$mail->Host = "smtp.gmail.com"; //Gamil的SMTP主機
		$mail->Port = 465;  //Gamil的SMTP主機的埠號(Gmail為465)。
		
		
		/*信件設定*/
		$mail->CharSet = "utf-8"; //郵件編碼
		$mail->Username = "imageinpaintingweb@gmail.com"; //Gamil帳號
		$mail->Password = "a0988337962"; //Gmail密碼
		$mail->From = "imageinpaintingweb@gmail.com";  //寄件者信箱
		$mail->FromName = "imageWeb"; //寄件者姓名
		$mail->Subject = '會員認證信'; // 設定郵件標題
		$mail->Body = '啟動帳號網址: <a href="http://imageinpainting.ddns.net:8080/s12317452.php">http://imageinpainting.ddns.net:8080/s12317452.php</a>      '; // 設定郵件內容
	    $mail->IsHTML(true); // 設定郵件內容為HTML

		
		
		
		//====================================
		$webmaster_email = "imageinpaintingweb@gmail.com"; 
		//回覆信件至此信箱


		$email=$_POST["m_username"];
		echo($email);
		// 收件者信箱
		$name="user";
		// 收件者的名稱or暱稱

	    /*寄信*/

		$mail->AddAddress($email,$name);
		$mail->AddReplyTo("imageinpaintingweb@gmail.com","imageweb");
		//這不用改
		//====================================		
		$mail->WordWrap = 50;
		//每50行斷一次行		
		
		
		
		//信件內容(html版，就是可以有html標籤的如粗體、斜體之類)
		//$mail->AltBody = "hello"; 



		
//======================





		if(!$mail->Send()){
			//如果有錯誤會印出原因
			echo "寄信發生錯誤：" . $mail->ErrorInfo;
		}
		else{	
			header("Location: index.php");
		}
	
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



<script language="javascript">
function checkForm(){
	if(document.formJoin.m_username.value==""){		
		alert("請填寫帳號!");
		document.formJoin.m_username.focus();
		return false;
	}
	if(!checkmail(document.formJoin.m_username)){
		document.formJoin.m_username.focus();
		alert("帳號格式不對!");
		return false;
	}
	if(!check_passwd(document.formJoin.m_passwd.value,document.formJoin.m_passwdrecheck.value)){
		document.formJoin.m_passwd.focus();
		return false;
	}	
	if(document.formJoin.m_email.value==""){
		alert("請填寫電子郵件!");
		document.formJoin.m_email.focus();
		return false;
	}
	return confirm('確定送出嗎？');

}
function check_passwd(pw1,pw2){
	if(pw1==''){
		alert("密碼不可以空白!");
		return false;
	}
	for(var idx=0;idx<pw1.length;idx++){
		if(pw1.charAt(idx) == ' ' || pw1.charAt(idx) == '\"'){
			alert("密碼不可以含有空白或雙引號 !\n");
			return false;
		}
		if(pw1.length<8 || pw1.length>16){
			alert( "密碼長度只能8到16個字母 !\n" );
			return false;
		}
		if(pw1!= pw2){
			alert("密碼二次輸入不一樣,請重新輸入 !\n");
			return false;
		}
	}
	return true;
}
function checkmail(myEmail) {
	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(filter.test(myEmail.value)){
		return true;
	}
	return false;
}
</script>










</head>
<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Image  </a></h1>
			</div>
			<div id="menu">
				<ul>
					<li ><a href="index.php">Home</a></li>
					<li ><a href="login.php">Login</a></li>
					<li class="current_page_item"><a href="">Register</a></li>					
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
						<h2 class="title">註冊帳號</a></h2>
				


						</div>
					</div>
				</div>
				
				
				
 <tr>
    <td class="tdbline"><table width="100%" border="0" cellspacing="0" cellpadding="10">
      <tr valign="top">
        <td class="tdrline"><form action="" method="POST" name="formJoin" id="formJoin" onSubmit="return checkForm();">

		  <?php if(isset($_GET["errMsg"]) && ($_GET["errMsg"]=="1")){?>
          <div class="errDiv">帳號 <?php echo $_GET["username"];?> 已經有人使用！</div>
          <?php }?>
          <div class="dataDiv">
            <hr size="1" />
            <p class="heading"></p>
            <p class="pcolorstyle"><strong>帳號</strong>：    
                <input name="m_username" type="text" class="normalinput" id="m_username">
                <br>
                <span class="smalltext">請輸入您的E-MAIL當作帳號</span></p>
            <p class="pcolorstyle"><strong>使用密碼</strong>：
                <input name="m_passwd" type="password" class="normalinput" id="m_passwd">
                <br>
                <span class="smalltext">請填入8~16個字元以內的英文字母、數字</span></p>
            <p class="pcolorstyle"><strong>確認密碼</strong>：
                <input name="m_passwdrecheck" type="password" class="normalinput" id="m_passwdrecheck">
                 <br>
                <span class="smalltext">再輸入一次密碼</span></p>
            <hr size="1" />

          </div>
          <hr size="1" />
          <p align="center">
            <input name="action" type="hidden" id="action" value="join">
            <input type="submit" name="Submit2" value="送出申請">
            <input type="button" name="Submit" value="回上一頁" onClick="window.history.back();">
          </p>
        </form></td>
        <td width="200">

        <div class="boxbl"></div><div class="boxbr"></div></td>
      </tr>
    </table></td>
  </tr>				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
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
