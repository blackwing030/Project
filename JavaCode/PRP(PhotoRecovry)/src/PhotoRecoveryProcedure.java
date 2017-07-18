import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;


public class PhotoRecoveryProcedure {
	int size = 1000;
	private int []x ; //green cordinate x
	private int []y ;//green cordinate y
	private int [][]structor;
	private int []priority;
	private int [][]Confidence;
	private int [][]oriisgreen;
	private int [][]red;
	private int [][]green;
	private int [][]blue;
	private int [][]rgbData;
	private int []Up;
	private int []Down;
	private int []Left;
	private int []Right;	
	
	
	
	
	private boolean shutdown = true;
	int countnum = 0;
	int countnum1 =0;
	
	private int Xsizeall;
	private int Ysizeall;
	private int wei;
	private int hei;
	private BufferedImage Cannyimage;
	private BufferedImage OriImage;
	private BufferedImage copyoriimage;
	private BufferedImage whiteimage;	
	
	public void setCannyImage(BufferedImage image){
		Cannyimage = image;

	}
	public void setOriImage(BufferedImage image){
		OriImage = image;

	}	
	
	
	
	public void FindEdgeCor(){

		
		
		//index_count
		int count = 0;

	
		//read rgbData into array
		for(int i = 0;i < wei;i++){
			for(int j=0;j < hei;j++){


				//color is green(inpating color) Confidence = 0 else = 1
				if(green[i][j]>=252  && blue[i][j]<=4 && red[i][j]<=4){
					
					Confidence[i][j]= 0;
				}else
				{
					Confidence[i][j]= 1;
				}
				
			}
		}

		//find up Down edge
		for(int i = 0;i < wei;i++){
			for(int j=0;j < hei;j++){
				
				if(j+1 < hei){
					//up
					if(green[i][j] != 255  || blue[i][j] != 0 || red[i][j] != 0){
						if(green[i][j+1]>=252  && blue[i][j+1]<=4 && red[i][j+1]<=4 ){
							x[count] = i;
							y[count] = j+1;
							count++;
						}
					}
					//down
					if(green[i][j] >=252  && blue[i][j] <=4  && red[i][j] <=4 ){
						if(green[i][j+1] != 255  || blue[i][j+1] != 0 || red[i][j+1] != 0 ){
							x[count] = i;
							y[count] = j;
							count++;
						}
					}			
				}
			
			}
		}
		
	

		//find Right Left edge
		
		for(int j = 0;j < hei;j++){
			for(int i=0;i < wei;i++){
				
				if(i+1 < wei){
					//right
					if(green[i][j] != 255  || blue[i][j] != 0 || red[i][j] != 0){
						if(green[i+1][j]>=252  && blue[i+1][j]<=4 && red[i+1][j]<=4 ){
							//check repart cordinate
							for(int k=0;k<(wei*hei);k++)
							{
								if(x[k] == i+1 && y[k] == j){
									//have same cordinate
								}
								else{//recode data
									
									x[count] = i+1;
									y[count] = j;
									count++;
									break;
								}
									
							}

						}
					}
					//left
					if(green[i][j] >= 252  && blue[i][j] <=4 && red[i][j] <=4){
						if(green[i+1][j] != 255  || blue[i+1][j] != 0 || red[i+1][j] != 0 ){
							//check repart cordinate
							for(int k=0;k<(wei*hei);k++)
							{
								if(x[k] == i && y[k] == j){
									//have same cordinate
								}
								else{//record data
									x[count] = i;
									y[count] = j;
									count++;
									break;
								}
									
							}
						}
					}		
				}
			}		 
		}				
	}
	public void process() throws IOException{
		
		//inital
		rgbData= new int[OriImage.getWidth() ][OriImage.getHeight()];
		int width  = OriImage.getWidth();
		wei = width;

		
		int height = OriImage.getHeight();
		//System.out.println("圖片寬:"+width);
		//System.out.println("圖片高:"+height);
		hei = height;
		
		//定義BuffedImage
		BufferedImage outputimage = new BufferedImage(wei, hei,
		BufferedImage.TYPE_INT_RGB);
		outputimage = OriImage;
		
		BufferedImage oriiimage = new BufferedImage(wei, hei,
		BufferedImage.TYPE_INT_RGB);
		oriiimage = OriImage;

		copyoriimage =  OriImage;
		
		
		
		blue =new int[wei][hei];
		red =new int[wei][hei];
		green =new int[wei][hei];
		Confidence =new int[wei][hei];
		x = new int[(wei+hei)*2];
		y = new int[(wei+hei)*2];
		oriisgreen =new int[wei][hei];
		
		
		
		//read Data into array
		for(int i = 0;i < wei;i++){
			for(int j=0;j < hei;j++){

				rgbData[i][j]=outputimage.getRGB(i, j);
				red[i][j] = (rgbData[i][j]& 0xff0000)>> 16;
				green[i][j] = (rgbData[i][j] & 0xff00) >> 8;
				blue[i][j]  = (rgbData[i][j]) & 0xff;
				

				
			}
		}
		FindGreenEdge(oriiimage);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//TRP START
		for(int k=0 ;k<100000;k++){
			int WIDTH = 300;
			int HEIGHT = 400;
			shutdown = true;
			
			
			
	    	long time1, time2,time3;
			time1 = System.currentTimeMillis();
			
			FindEdgeCor();	
			calculate();
			ssdCalculate();
			

			
			
			/*
			BufferedImage image2222 = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
			image2222 = ImageIO.read(new File("C:/xampp/htdocs/ImageFile/PRP/EdgesImage.png"));			
			for(int i=0; i<wei; i++){
				for(int j=0;j<hei;j++){
					
					if(structor[i][j] == 1){
						Color col = new Color(255, 255, 255);
						image2222.setRGB(i,j, col.getRGB());
					}
					else{
						Color col = new Color(0, 0, 0);
						image2222.setRGB(i,j, col.getRGB());
					}
					
				}
			}
			BufferedImage image3333 = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
			image3333 = ImageIO.read(new File("C:/xampp/htdocs/ImageFile/PRP/EdgesImage.png"));			
			for(int i=0; i<wei; i++){
				for(int j=0;j<hei;j++){
					
					if(Confidence[i][j] == 1){
						Color col = new Color(255, 255, 255);
						image3333.setRGB(i,j, col.getRGB());
					}
					else{
						Color col = new Color(0, 0, 0);
						image3333.setRGB(i,j, col.getRGB());
					}
					
				}
			}
			
			ImageIO.write(image3333, "png", new File("confidenceEdge"
			+ ".png"));			
			
			*/
			
			
			
			
		

			BufferedImage image1 = new BufferedImage(WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB);
			/* canny */
			//create the detector
			//image1 = ImageIO.read(new File("C:/xampp/htdocs/ImageFile/PRP/endddddd.png"));
			CannyEdgeDetector detector = new CannyEdgeDetector();
			
			time2 = System.currentTimeMillis();		
			/*
			if(k%2==1){
				
			//adjust its parameters as desired
			detector.setLowThreshold(0.5f);
			detector.setHighThreshold(1.0f);
		
			//apply it to an image/		
			detector.setSourceImage(OriImage);
			
			
			

			detector.process();
			BufferedImage edges = detector.getEdgesImage();

			// 將image圖象文件輸出到磁盤文件中。
			//ImageIO.write(edges, "png", new File("C:/xampp/htdocs/ImageFile/PRP/EdgesImage"
			//+ ".png"));	
			Cannyimage = edges;
			}
			 */
				
			/*clear*/
			
			for(int i=0;i<((wei+hei)*2);i++){
				
				x[i] = 0;
				y[i] = 0;
			}
			
			
			
			
			
			time3 = System.currentTimeMillis();		
			//System.out.println("總共花了(1)：" + (time2-time1) + "毫秒");	
			//System.out.println("總共花了(2)：" + (time3-time2) + "毫秒");				
	
			
			
			
			if(shutdown == true){
				break;
			}
		    
		}

		// Final image		
		BufferedImage aggggggg = new BufferedImage(wei, hei,
				BufferedImage.TYPE_INT_RGB);
		
		for(int i=0;i<wei;i++){
			for(int j=0;j<hei;j++){
				if(oriisgreen[i][j] == 1){
					
					aggggggg.setRGB(i,j,OriImage.getRGB(i,j));	
				}else{
					aggggggg.setRGB(i,j,copyoriimage.getRGB(i,j));	
				}
				
			}
		}

		ImageIO.write(aggggggg, "png", new File("C:/xampp/htdocs/ImageFile/PRP/endddddd"+ ".png"));			
		
		
		
		
		
	}
	
	
	
	public void calculate(){
		int height= Cannyimage.getHeight();
		int width = Cannyimage.getWidth();
		structor = new int[width][height];
		int red;
		int green;
		int blue;
		int orired;
		int origreen;
		int oriblue;
		//set s(p)
		for(int i = 0;i < width;i++){
			for(int j = 0;j < height;j++)
			{
				red   = (Cannyimage.getRGB(i, j) & 0xff0000)>> 16;
				green =	(Cannyimage.getRGB(i, j) & 0xff00) >> 8;
				blue  = (Cannyimage.getRGB(i, j) & 0xff) ;		
				
				
				orired   = (OriImage.getRGB(i, j) & 0xff0000)>> 16;
				origreen =	(OriImage.getRGB(i, j) & 0xff00) >> 8;
				oriblue  = (OriImage.getRGB(i, j) & 0xff) ;	
				
				
				
				//color is wdge(white) structor = 1 else = 0
				if(red == 255 && green == 255 && blue == 255){
					if(orired==0 && origreen==255 && oriblue == 0){
						structor[i][j] = 0;
					}
					else{
						structor[i][j] = 1;
					}

					
					
				}
				else{
					structor[i][j] = 0;
				}
				
			}					

		}
		
			
	
		
		//s(p)==0 in green(x,y) 3*3 block
		
		for(int i = 0;i < 2*(wei+hei);i++){
			if(x[i] == 0 && y[i] == 0){
				break;
			}
				int tmpx=x[i]-1;
				int tmpy=y[i]-1;
			
				for(int m = tmpx;m <= (tmpx+2);m++){
					for(int n = tmpy;n <= (tmpy+2);n++){
						structor[m][n] = 0;
					}
				}		
		}
		
		
		

		 
		
		
		
		//cal p(p)
		priority = new int[(wei+hei)*2];
		int count = 0 ; //array order
		for(int k=0;k < ((wei+hei)*2);k++){
			int ix = 0;
			int jy = 0;
			int xsize = 5;
			int ysize = 5;
			Xsizeall = 5;
			Ysizeall = 5;
			
			int ConfSum = 0;
			int StruSum = 0;
			
			ix = x[k]-(xsize/2);
			jy = y[k]-(ysize/2);
			
			
			
			
			
			//no data
			if(x[k] == 0 && y[k]==0)
			{
				break; //escape for loop
			}
			
			
			/*
			//X filter Lower Bound
			if(x[k]-(xsize/2) <= 0){
				ix = 0;
				for(int i = x[k];i == 0;i--){
					xsize--;
				}
			}else{
				ix = x[k]-(xsize/2);
			}
			
			//X filter Upper Bound
			if(x[k]+(xsize/2) > width){
				for(int i = x[k];i < width;i++){
					xsize--;
				}
			}	
			*/

			
			
			/*
			//Y filter Lower Bound
			if(y[k]-(ysize/2) < 0){
				jy = 0 ;
				for(int j = y[k];j == 0;j--){
					ysize--;
				}
			}else{
				jy = y[k]-(ysize/2);
			}
			
			//Y filter Upper Bound
			if(y[k]+(ysize/2) > height){
				for(int j = y[k];j < height;j++){
					ysize--;
				}
			}			
			*/
			
			
			StruSum = 0;
			ConfSum = 0;

			for(int m = ix;m <= (ix + 2*(xsize/2) ); m++){
				for(int n = jy;n <= (jy + 2*(ysize/2)); n++){
					StruSum = StruSum + structor[m][n];
					ConfSum = ConfSum + Confidence[m][n];
				}
			}
			
		

			
			
			
			priority[k] = StruSum*ConfSum;//(float)(xsize * ysize * xsize * ysize);
			/* debug
			 *System.out.println("StruSum:"+StruSum+"   ConfSum:"+ConfSum);
			 *System.out.println(priority[k]);
			 *k ++;//order +1
			*/
		}
		

		
		
		
		//sort three priority array with x,y
		merge_sort(priority,x,y);
		
		
		//reverse array
		for(int i = 0; i < priority.length / 2; i++)
		{
			int temp = priority[i];
			priority[i] = priority[priority.length - i - 1];
			priority[priority.length - i - 1] = temp;

			temp = x[i];
			x[i] = x[x.length - i - 1];
			x[x.length - i - 1] = temp;
			
			temp = y[i];
			y[i] = y[y.length - i - 1];
			y[y.length - i - 1] = temp;
			
			
		}
		/* debug
		System.out.println("2222222222");
		for(int z = 0; z< priority.length;z++){
			if(x[z]!=0 && y[z]!=0 && priority[z]!=0 ){
			System.out.printf("x:%d y: %d proirity: %d",x[z],y[z],priority[z]);
			System.out.println("");
			}
			
		}
	   */
		
				
	}
	
	public void ssdCalculate() throws IOException{
		boolean flag = false;
		int realcount=0;
		for(int k=0; k<priority.length;k++){
			if(realcount == 1)
				break;
			if(x[k] != 0 && y[k] != 0)
				realcount++;
			
			
			if(flag == true)
				break;
			if(priority[k]==0 && x[k] == 0 && y[k] == 0 )//done
				break;
			if(priority[k] != priority[k])
				break;
			
			
		
			
			flag = false;
			//框框size
			int []SSD = new int[40*40];
			int []recordX = new int[40*40];
			int []recordY = new int[40*40];
			                       
			int xcor = x[k];
			int ycor = y[k];
			/*
			System.out.println("x[k]:"+x[k]+"y[k]:"+y[k]);
			*/
			
			
			
			int istart = 0;
			int jstart = 0;
			int count = 0;
			int sqrsize = 40;//30*30

			
			
			
			istart = xcor-(sqrsize/2);//center x cordi
			jstart = ycor-(sqrsize/2);//center y cordi
			/*
			 * System.out.println("istart:" + istart + "jstart" + jstart);
			 */
			
			//if ssd < 1000 than loop done
			boolean  fastflag =false;
			
			//big square 40*40
			for(int i = istart; i < istart+(sqrsize);i++){
				for(int j = jstart; j < jstart+(sqrsize);j++){
					
					//check outside point no green
					flag = false;
					for(int p = i-(Xsizeall/2);p <= i+(Xsizeall/2);p++){
						for(int q = j-(Ysizeall/2) ;q <= j+(Ysizeall/2);q++){
							
							int r,g,b;
							r = red[p][q];
							g = green[p][q];
							b = blue[p][q];
							
							//have green
							if(r <=4  && g >=252 && b <=4){
								flag = true;
								break;
							}
							
						}
						if(flag == true){
							break;
						}
					}
					
					//calculate SSD and No green
					
					if(flag == false){
						int patchX = xcor-(Xsizeall/2);
						int patchY = ycor-(Ysizeall/2);
						int windowsX = i-(Xsizeall/2);
						int windowxY = j-(Ysizeall/2);
						int sumR = 0;
						int sumG = 0;
						int sumB = 0;
						int sumOfssd = 0;
						for(int n=0; n<Xsizeall;n++){
							for(int m=0 ;m <Ysizeall ;m++){
								if(red[patchX+n][patchY+m] <=4 && green[patchX+n][patchY+m]>=252 && blue[patchX+n][patchY+m] <=4){
									//sumR = sumR + (int)Math.pow(red[windowsX+n][windowxY+m]-125,2);
									//sumG = sumG + (int)Math.pow(green[windowsX+n][windowxY+m]-125,2);
									//sumB = sumB + (int)Math.pow(blue[windowsX+n][windowxY+m]-125,2);
									
								}else{
									sumR = sumR + (int)Math.pow(red[windowsX+n][windowxY+m]-red[patchX+n][patchY+m],2);
									sumG = sumG + (int)Math.pow(green[windowsX+n][windowxY+m]-green[patchX+n][patchY+m],2);
									sumB = sumB + (int)Math.pow(blue[windowsX+n][windowxY+m]-blue[patchX+n][patchY+m],2);
									
								}
							}
						}
						sumOfssd = sumR + sumG + sumB;
						recordX[count] = i;
						recordY[count] = j;
						SSD[count] = sumOfssd;
						/*
						  if(SSD[count] <1000){
							fastflag = true;
							break;
						}
						 */
							
							
						count++;
						
						
					}
				}
				/*if(fastflag == true){
					break;
				}
				*/
			}

			
			
			
			//error have 0
			merge_sort(SSD , recordX, recordY);
			/*
			for(int r=0;r<SSD.length;r++){
				System.out.println("SSD["+r+"]="+SSD[r]);
			}
			*/
			
	
			
			
			
			for(int z = 0; z< SSD.length;z++){
				if(recordX[z]!=0 && recordY[z]!=0){
					
					//repalce
					for(int n=0;n < Xsizeall;n++){
						for(int m=0;m < Ysizeall;m++){
							red[xcor-(Xsizeall/2)+n][ycor-(Ysizeall/2)+m] = red[recordX[z]-(Xsizeall/2)+n][recordY[z]-(Ysizeall/2)+m];
							green[xcor-(Xsizeall/2)+n][ycor-(Ysizeall/2)+m] = green[recordX[z]-(Xsizeall/2)+n][recordY[z]-(Ysizeall/2)+m];
							blue[xcor-(Xsizeall/2)+n][ycor-(Ysizeall/2)+m] = blue[recordX[z]-(Xsizeall/2)+n][recordY[z]-(Ysizeall/2)+m];

							          
							Cannyimage.setRGB(xcor-(Xsizeall/2)+n, ycor-(Ysizeall/2)+m, Cannyimage.getRGB(recordX[z]-(Xsizeall/2)+n,recordY[z]-(Ysizeall/2)+m));
							
							
						}
					}
					
					break;
				}
			}
			

		}
		
		BufferedImage aggge = new BufferedImage(wei, hei,
				BufferedImage.TYPE_INT_RGB);


		//output image
		for(int i=0;i<wei;i++){
			for(int j=0;j<hei;j++){
				Color col = new Color(red[i][j], green[i][j], blue[i][j]);
				aggge.setRGB(i, j, col.getRGB());
				if(red[i][j] == 0 && green[i][j] == 255 && blue[i][j] == 0){
					shutdown = false;
					
				}
				
			}
		}

		
		OriImage = aggge;
		// 將image圖象文件輸出到磁盤文件中。
		//ImageIO.write(aggge, "png", new File("C:/xampp/htdocs/ImageFile/PRP/1233333333333"+ ".png"));	

		
	}
	
	public void FindGreenEdge(BufferedImage image) throws IOException{
		
		int width  = image.getWidth();
		int height = image.getHeight();
		
		
		boolean RowOneWhiteFlag = false;
		boolean RowTwoWhiteFlag = false;			
		
		
		int [] upedge = new int[height];
		int [] downedge =new int[height];

		
		whiteimage = new BufferedImage(wei, hei,
				BufferedImage.TYPE_INT_RGB);
		int upcount = 0,downcount = 0;
		
		//定義BuffedImage
		BufferedImage outputimage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);
		outputimage = image;
		
		//find up Down edge
		for(int j=0;j < height;j++){
			for(int i = 0;i < width;i++){	
				

				if(green[i][j]>=252  && blue[i][j]<=4 && red[i][j]<=4){
					RowOneWhiteFlag = true;
				}
		
				
				if((j+1) < height){
					//next row
					if(green[i][j+1]>=252  && blue[i][j+1]<=4 && red[i][j+1]<=4){		
						RowTwoWhiteFlag  = true;
					}			

				}	
				
			
			}
			//check upedge
			if(RowOneWhiteFlag == false && RowTwoWhiteFlag == true)
			{
				upedge[upcount]= j;
				upcount++;

			}
			//check downedge
			if(RowOneWhiteFlag == true && RowTwoWhiteFlag == false)
			{
				downedge[downcount]= j;
				downcount++;

			}
			RowOneWhiteFlag = false;
			RowTwoWhiteFlag = false;				
			
			
			
		}	
		Up   = new int[height];
		Down = new int[height];
		Left = new int[height];
		Right = new int[height];
		
		int[] uedge = new int[height];
		int[] dedge = new int[height];
		uedge = upedge;
		dedge = downedge;

		int count =0;
		
		boolean ColOneWhiteFlag = false;
		boolean ColTwoWhiteFlag = false;		
		
		//k level		
		for(int k = 0;k < height && dedge[k]!=0 && uedge[k]!=0;k++){
			
			int rowcount = 0;
			int rowin_l_count = 0;
			int rowin_r_count = 0;
			//find letter Right Left edge
			for(int i = 0;i < width ;i++){
				for(int j = (upedge[k]+1);j < (downedge[k]-1);j++){	
			

					if(green[i][j]>=252  && blue[i][j]<=4 && red[i][j]<=4){
						ColOneWhiteFlag = true;
					}
			
					
					if((i+1) < width){

						if(green[i+1][j]>=252  && blue[i+1][j]<=4 && red[i+1][j]<=4){		
							ColTwoWhiteFlag  = true;
						}			
		
					}	
					
							
				
				}
				//check letter's LeftEdge
				if(ColOneWhiteFlag == false && ColTwoWhiteFlag == true)
				{
					Left[count]= i;
		
				}
				//check letter's RightEdge
				if(ColOneWhiteFlag == true && ColTwoWhiteFlag == false)
				{
					Right[count]= i;
					Up[count]   = uedge[k];
					Down[count] = dedge[k];
					count++;

				}
				ColOneWhiteFlag = false;
				ColTwoWhiteFlag = false;	

				
			}

		}		
		////mark 
		for(int k = 0;k < height && Up[k]!=0 && Down[k]!=0;k++){	

			
			
			Up[k] = Up[k] -25;
			Down[k] = Down[k]+25;
			Left[k] = Left[k] -25;
			Right[k] = Right[k] +25;
			if((Up[k]-25) <0 ){
				Up[k]=0;
			}
			if((Down[k]+25) >hei ){
				Down[k]=hei;
			}
			if((Left[k]-25) <0 ){
				Left[k]=0;
			}			
			if((Right[k]+25) >wei ){
				Right[k]=wei;
			}			
			
			int tmpU = Up[k];
			int tmpD = Down[k];
			int tmpL = Left[k];
			int tmpR = Right[k];			
			 //System.out.println("u"+Up[k]+"  d"+Down[k]+"  l"+Left[k]+"  rr"+Right[k]);
			 //System.out.println("w"+wei+"  h"+hei);
			//read Data into array
			for(int i = tmpL;i < tmpR;i++){

				for(int j = tmpU;j < tmpD;j++){

					oriisgreen[i][j] = 1;
				}
			}
		}
		
		//read Data into array
		for(int i = 0;i < wei;i++){
			for(int j=0;j < hei;j++){


				if(oriisgreen[i][j] == 1){
					
					whiteimage.setRGB(i,j,OriImage.getRGB(i,j));	
				}else{
					Color myWhite = new Color(255, 255, 255); // Color white
					int white = myWhite.getRGB();	
					whiteimage.setRGB(i,j,white);		

				}
				
				
				
				rgbData[i][j]=whiteimage.getRGB(i, j);
				red[i][j] = (rgbData[i][j]& 0xff0000)>> 16;
				green[i][j] = (rgbData[i][j] & 0xff00) >> 8;
				blue[i][j]  = (rgbData[i][j]) & 0xff;
			}
		}
		/*		
		ImageIO.write(whiteimage, "png", new File("C:/xampp/htdocs/ImageFile/PRP/whiteimageImage"
		+ ".png"));	

		OriImage = whiteimage;
		ImageIO.write(OriImage, "png", new File("C:/xampp/htdocs/ImageFile/PRP/OriImageImage"
		+ ".png"));			
		*/
		
	
		
		
	}
	
	
	static void merge_sort_recursive(int[] arr, int[] reg, int start, int end,int[] x, int[]y ,int[] regx ,int[] regy) {
		if (start >= end)
			return;
		int len = end - start, mid = (len >> 1) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge_sort_recursive(arr, reg, start1, end1, x,y,regx,regy);
		merge_sort_recursive(arr, reg, start2, end2, x,y,regx,regy);
		int k = start;

		
		while (start1 <= end1 && start2 <= end2){
			if(arr[start1] < arr[start2]){
				reg[k] = arr[start1];
				regx[k] = x[start1];
				regy[k] = y[start1];
				k++;
				start1++;
			}else{
				reg[k] = arr[start2];
				regx[k] = x[start2];
				regy[k] = y[start2];
				k++;
				start2++;
			}
		}
		while (start1 <= end1){
			reg[k] = arr[start1];
			regx[k] = x[start1];
			regy[k] = y[start1];
			k++;
			start1++;
		}
		while (start2 <= end2){
			reg[k] = arr[start2];
			regx[k] = x[start2];
			regy[k] = y[start2];
			k++;
			start2++;
		}
		
		//
		for (k = start; k <= end; k++)
			arr[k] = reg[k];
		for (k = start; k <= end; k++)
			x[k] = regx[k];
		for (k = start; k <= end; k++)
			y[k] = regy[k];
	}
		
	public static void merge_sort(int[] arr , int[] x, int[]y) {
		int len = arr.length;
		int[] reg = new int[len];
		int[] regx =new int[len];
		int[] regy =new int[len];
		merge_sort_recursive(arr, reg, 0, len - 1 ,x,y,regx,regy);
	}	
		
	public static void merge_sort(int[] arr) {
		int len = arr.length;
		int[] reg = new int[len];
		merge_sort_recursive(arr, reg, 0, len - 1);
	}	
	
	
	static void merge_sort_recursive(int[] arr, int[] reg, int start, int end) {
		if (start >= end)
			return;
		int len = end - start, mid = (len >> 1) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge_sort_recursive(arr, reg, start1, end1);
		merge_sort_recursive(arr, reg, start2, end2);
		int k = start;
		//
		
		while (start1 <= end1 && start2 <= end2){
			if(arr[start1] < arr[start2]){
				reg[k] = arr[start1];
				k++;
				start1++;
			}else{
				reg[k] = arr[start2];
				k++;
				start2++;
			}
		}
		while (start1 <= end1){
			reg[k] = arr[start1];
			k++;
			start1++;
		}
		while (start2 <= end2){
			reg[k] = arr[start2];
			k++;
			start2++;
		}
		

		for (k = start; k <= end; k++)
			arr[k] = reg[k];
	}		
		

	
	
}

