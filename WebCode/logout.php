<?php
header("Content-Type: text/html; charset=utf-8");
require_once("connMysql.php");

session_start();

unset($_SESSION["loginid"]);

header("Location: index.php");
?>
