<?php
if($_GET['f']!=null){
	$file=$_GET['f'];//�ɮצW��


	$url="http://imageinpainting.ddns.net:8080/ImageFile/"; //���|��m
	$num=date("Ymds");	
	header("Content-type:application");
	header("Content-Disposition: attachment; filename=".$file);	
	readfile($url.str_replace("@","",$file));	
	exit(0);
}else{
	echo "�䤣������ɮ�....";
}
?>