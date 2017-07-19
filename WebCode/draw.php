<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<?php
header("Content-Type: text/html; charset=utf-8");
require_once("connMysql.php");
session_start();
//檢查是否經過登入
if(!isset($_SESSION["loginid"]) || ($_SESSION["loginid"]=="")){
	header("Location: index.php");
}
//執行登出動作
if(isset($_GET["logout"]) && ($_GET["logout"]=="true")){
	unset($_SESSION["loginMember"]);
	header("Location: index.php");
}









?>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV=”CACHE-CONTROL” CONTENT=”NO-CACHE”>


<title>Image</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
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
					<li><a href="index.php">Home</a></li>
					<li><a href="upload.php">Upload</a></li>
					<li class="current_page_item"><a href="#">Draw</a></li>
					<li><a href="index.php">Sign out</a></li>					
					

					
					
					
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

		

<?php
if(isset($_POST['srcc'])){	
		$img = $_POST['srcc'];
	    $img = str_replace('data:image/png;base64,','', $img);// 需注意 data url 格式 與來源是否相符 ex:image/jpeg	
		$data = base64_decode($img);//解base64碼
//		echo $data;
		$file = './ImageFile/'. "decode".'.png';//檔名 包含資料夾路徑 請記得此資料夾需 777 權限 方可寫入圖檔
		$success = file_put_contents($file, $data);		
	//	echo  $success;
}



if(isset($_POST['PRP'])){

	//Set 
	//$srcfile='C:\xampp\htdocs\ImageFile\output.png';
	$srcfile='C:\xampp\htdocs\ImageFile\decodePrp.png';

	$dstfile='C:\xampp\htdocs\ImageFile\PRP\image.png';
	//User UploadFile Name
/* EXAMPLE 
	$img='data:image/jpeg;base64,/9j/4AAQSkZJRgABAgEASABIAAD/4Qw8RXhpZgAATU0AK...以下省略';
    $img = str_replace('data:image/jpeg;base64,','', $img);// 需注意 data url 格式 與來源是否相符 ex:image/jpeg
    $data = base64_decode($img);//解base64碼
    $file = './img/test/'. uniqid().'.jpg';//檔名 包含資料夾路徑 請記得此資料夾需 777 權限 方可寫入圖檔
    $success = file_put_contents($file, $data);
*/

		$img = $_POST['PRP'];
	    $img = str_replace('data:image/png;base64,','', $img);// 需注意 data url 格式 與來源是否相符 ex:image/jpeg	
		$data = base64_decode($img);//解base64碼
		$file = './ImageFile/'. "decodePrp".'.png';//檔名 包含資料夾路徑 請記得此資料夾需 777 權限 方可寫入圖檔
		$success = file_put_contents($file, $data);		

	


	//move image tmpDir to imageDir
	move_uploaded_file($source_dir."output.png",$target_dir."image.png");	
	copy($srcfile,$dstfile);
	
	
	
	
	
	
	
	
	
	
	
	
	
	pclose(popen("start   /b C:\\xampp\\htdocs\\ImageFile\\PRP.jar", 'r'));


	sleep(8);
	//move image tmpDir to imageDir				
	imagepng(imagecreatefromstring(file_get_contents("ImageFile/PRP/endddddd.png")), $target_dir."pimage.png");	
	header("Location:output.php"); 
}
if(isset($_POST['TRP'])){
	//Set 上傳Image資料夾
	$target_dir = "ImageFile/";

	
	//Set 
	//$srcfile='C:\xampp\htdocs\ImageFile\output.png';
	$srcfile='C:\xampp\htdocs\ImageFile\decodeTrp.png';

	$dstfile='C:\xampp\htdocs\ImageFile\TRP\image.png';
	//User UploadFile Name

		$img = $_POST['TRP'];
	    $img = str_replace('data:image/png;base64,','', $img);// 需注意 data url 格式 與來源是否相符 ex:image/jpeg	
		$data = base64_decode($img);//解base64碼
		$file = './ImageFile/'. "decodeTrp".'.png';//檔名 包含資料夾路徑 請記得此資料夾需 777 權限 方可寫入圖檔
		$success = file_put_contents($file, $data);		
	
	
	
	
	
	//move image tmpDir to imageDir
	move_uploaded_file($source_dir."output.png",$target_dir."image.png");	
	copy($srcfile,$dstfile);

	pclose(popen("start   /b C:\\xampp\\htdocs\\ImageFile\\TRP.jar", 'r'));
	sleep(5);
	//move image tmpDir to imageDir			
	imagepng(imagecreatefromstring(file_get_contents("ImageFile/TRP/output.png")), $target_dir."timage.png");
	
	
	
	header("Location:output1.php"); 
}

?>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.4.js">
windows.addEventListener('load', eventWindowLoaded,false);

</script>



   <style>
        body,input { font-size: 9pt; }
        #dCanvas,#dLine { clear: both; }
        .option
        {
            float: left; width: 20px; height: 20px; border: 2px solid #cccccc;
            margin-right: 4px; margin-bottom: 4px;
        }
        .active { border: 2px solid black; }
        .lw { text-align: center; vertical-align: middle; }
        img.output { border: 1px solid green; }
        #Sketch { cursor: crosshair; }
    </style>

</head>





<body>





<!--
框框內
-->
<table width="780" border="0" align="center" cellpadding="4" cellspacing="0">


  <tr>
    <td class="tdbline">
	<table width="100%" border="0" cellspacing="0" cellpadding="10">

<!--
工具列
-->	
	<div id="dPallete"></div>
	<div id="dLine"></div>
	<div id="dundo" class='option'>	</div>
	<div id="dredo" class='option'>	</div>
	<div id="dCanvas">	
	<div id="simple" class="zoom">
<!--
繪圖
-->		
	<canvas id="Sketch" width="500" height="290" style="border: 2px solid gray">
	</canvas>

	<img id="apple11"  style="display: none;" />
	

	
	
	
	
	
	</div>

	

	
	
	<!-- bar
	<input type="range" id="slider" min="1.0" max="3.0" step="0.01" value="1.0" "display: block;margin: 20px auto;width: 800px;"/>
	<br>
-->

<!--	<input type="button" id="bGenImage" value="Save Image" />
-->
	
<!--
繪圖後圖檔
-->
	<div id="dOutput"></div>	


<!--
base64 data
-->	
<!--
	<form action="" method="post" name="basesrc">		
	<input type="hidden" name="srcc" value="123"   id="basesrc">
	<input type="submit" id="bGenImage" value="Save Image" />
	</form>
-->	
	
		
	<form action="" method="post" name="form1">		
	<input type="hidden" name="PRP" value="123"   id="baseprp">
	<input type="submit" value="Photo Recovery" id="PRP" >
	</form>

	
	<form action="" method="post" name="form2">
	<input type="hidden" name="TRP" value="123"    id="basetrp">
	<input type="submit" value="Text  Recovery"  id="TRP"   >
	</form>
	

	
	
	
	
	
	<!--
	<a id="save" href="#" download="dl.png" >Save Canvas</a>
	-->
    </table>
	</td>
  </tr>
</table>


				
				
				
				
				
				
				
				
				
				
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

	<script>	

	
		// Shorthand for $( document ).ready()
        $(function () {
			//存储当前表面状态数组-上一步
            var preDrawAry = [];
            //存储当前表面状态数组-下一步
            var nextDrawAry = [];
            //中间数组
            var middleAry= [];
			
			//main start
            //產生不同顏色的div方格當作調色盤選項
            var colors =
            "#00FF00".split(';');
			//"#00FF00;white".split(';');
            var sb = [];
            $.each(colors, function (i, v) {
                sb.push("<div class='option' style='background-color:" + v + "'></div>");
            });
            $("#dPallete").html(sb.join("\n"));
			
			
            //產生不同尺寸的方格當作線條粗細選項
            sb = [];
            for (var i = 4; i <= 9; i++)
                sb.push("<div class='option lw'>" +
			"<div style='margin-top:#px;margin-left:#px;width:%px;height:%px'></div></div>"
                .replace(/%/g, i).replace(/#/g, 10 - i / 2));
            $("#dLine").html(sb.join('\n'));
            var $clrs = $("#dPallete .option");
            var $lws = $("#dLine .option");
			var $undos = $("#dundo .option");
			var $redos = $("#dundo .option");			
			
			
			
			
            //點選調色盤時切換焦點並取得顏色存入p_color，
            //同時變更線條粗細選項的方格的顏色
            $clrs.click(function () {
                $clrs.removeClass("active");
                $(this).addClass("active");
                p_color = this.style.backgroundColor;
                $lws.children("div").css("background-color", p_color);
            }).first().click();
			
			
            //點選線條粗細選項時切換焦點並取得寬度存入p_width
            $lws.click(function () {
                $lws.removeClass("active");
                $(this).addClass("active");
                p_width =
                    $(this).children("div").css("width").replace("px", "");
 
            }).eq(3).click();
			

			//canvas===================================
            //取得canvas context id biding
            var $canvas = $("#Sketch");
			//定義畫布框框
            var ctx = $canvas[0].getContext("2d");
			var slider = document.getElementById("slider");
			
			
			var imageObj = new Image();
			
			
			var ddx;
			var ddy;

			imageObj.src ='http://imageinpainting.ddns.net:8080/ImageFile/output.png';
			imageObj.onload = function() {
				//定義畫布框框大小為圖片的大小
				$canvas[0].height = imageObj.height;
				$canvas[0].width  = imageObj.width;
				ctx.drawImage(imageObj, 0, 0, imageObj.width, imageObj.height);
				slider.onmousemove = function(){
					drawImageByScale(slider.value,ctx);
		};
			//imageObj.src ='http://imageinpainting.ddns.net:8080/ImageFile/output.png';
				
				
				
				
				
			};
			


			
			
			
            ctx.lineCap = "round";
            ctx.fillStyle = "white"; //整個canvas塗上白色背景避免PNG的透明底色效果
            ctx.fillRect(0, 0, $canvas.width(), $canvas.height());
            var drawMode = false;
           //canvas點選、移動、放開按鍵事件時進行繪圖動作
            $canvas.mousedown(function (e) {

				
                ctx.beginPath();

                ctx.strokeStyle = p_color;
                ctx.lineWidth = p_width;
                ctx.moveTo(e.pageX - $canvas.position().left, e.pageY - $canvas.position().top);
                drawMode = true;
				//存到先前狀態
				var Datanow = $canvas.getImageData(0, 0, 600, 400);
				preDrawAry.push(Datanow);			
				ddx = e.pageX - $canvas.position().left;
				ddy = e.pageY - $canvas.position().top;

            })
            .mousemove(function (e) {
                if (drawMode) {
					//ctx.fillRect(ddx,ddy,(e.pageX - $canvas.position().left,e.pageY - $canvas.position().top);
                    ctx.lineTo(e.pageX - $canvas.position().left, e.pageY - $canvas.position().top);
					//繪製 绘制出通过 moveTo() 和 lineTo() 方法定义的路径
                    ctx.stroke();
                }
            })
            .mouseup(function (e) {
                drawMode = false;
			    //存到現在狀態
				//ctx.fillRect(ddx,ddy,(e.pageX - $canvas.position().left,e.pageY - $canvas.position().top);
                var Data = $canvas.getImageData(0, 0, 600, 400);
				middleAry.push(Data);
				// add state to history stack
			
            });
			
			
			
			

			//點選上一步
			$undos.click(function () {
	 
			});		
			//點選下一步
			$redos.click(function () {
	 
			});				
			
/*
			 $("#bGenImage").click(function () {
                $("#dOutput").html(
                $("<img />", { src: $canvas[0].toDataURL(),
                    "class": "output",
					"id": "output123",
			//		"style": "display: none;"
                }
				)
				);
				
				$("#basesrc").val($canvas[0].toDataURL()			
				);
				//alert($canvas[0].toDataURL()	);
				
				
            });
*/
				$("#PRP").click(function () {
				
				$("#baseprp").val($canvas[0].toDataURL()			
				);
				//alert($canvas[0].toDataURL()	);
				
            });		
				$("#TRP").click(function () {
				
				$("#basetrp").val($canvas[0].toDataURL()			
				);
				//alert($canvas[0].toDataURL()	);			
            });			
			
			
			
        });
			self.opener.location.reload();
</script>	











</body>
</html>
