<?php
if($_GET['f']!=null){
	$file=$_GET['f'];//檔案名稱


	$url="http://imageinpainting.ddns.net:8080/ImageFile/"; //路徑位置
	$num=date("Ymds");	
	header("Content-type:application");
	header("Content-Disposition: attachment; filename=".$file);	
	readfile($url.str_replace("@","",$file));	
	exit(0);
}else{
	echo "找不到相關檔案....";
}
?>