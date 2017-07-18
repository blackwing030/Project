package image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class TextRecoveryProcedure {

	private  int [][]Left;
	private  int [][]Right;
	private  int [] Up;
	private  int [] Down;
	private  int []updowncount;
	private  int [][]GreenArray;
	private int []LetterUpEdge ;
	private int []LetterDownpEdge ;
	private int []LetterLeftEdge;
	private int []LetterRightEdge ;	
	private int []LetterWidth;
	private int []LetterHeigh; 
	private int []LetterRenovate;
	private int []BlockUedge;
	private int []BlockDedge;	
	
	
	private BufferedImage OriImage;
	private int wei;
	private int hei;
	private int lettercount=0;
	
	public void setImage(BufferedImage image) {
	}
	public void setWei(int wei) {
		this.wei=wei;
	}
	public void setHei(int hei) {
		this.hei=hei;
	}
	
	
	
	public BufferedImage covertblack(BufferedImage image){
		
		wei = image.getWidth();
		hei = image.getHeight();
		/*
		System.out.println("========================");
		System.out.println("wei:"+wei);
		System.out.println("hei:"+hei);
		*/
		int[][] rgbData= new int[image.getWidth() ][image.getHeight()];
		int width  = image.getWidth();
		int height = image.getHeight();
		int [][]red =new int[width][height ];
		int [][]green =new int[width][height ];
		int [][]blue =new int[width][height ];
		int [][]alpha =new int[width][height ];

		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();			
		Color myBlack = new Color(0, 0, 0); // Color black
		int black = myBlack.getRGB();
			
		//定義BuffedImage
		BufferedImage outputimage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0;i < width;i++){
			for(int j=0;j < height;j++){
				rgbData[i][j]=image.getRGB(i, j);
				alpha[i][j] = (rgbData[i][j] >> 24) & 0xFF;	
				red[i][j]   = (rgbData[i][j]& 0xff0000)>> 16;
				green[i][j] =	(rgbData[i][j] & 0xff00) >> 8;
				blue[i][j]  = (rgbData[i][j]) & 0xff;
				if(red[i][j]<=60 && green[i][j]<=60 && blue[i][j]<=60 ){		
					outputimage.setRGB(i, j, black);
				}
				else {
					outputimage.setRGB(i, j, white);
				}
			}
		}		

			

		return outputimage;		
	}
	public BufferedImage GreentoWhite(BufferedImage image){

		OriImage = image;
		int[][] rgbData= new int[image.getWidth() ][image.getHeight()];
		int width  = image.getWidth();
		int height = image.getHeight();
		wei = image.getWidth();
		hei = image.getHeight();
		int [][]red =new int[width][height ];
		int [][]green =new int[width][height ];
		int [][]blue =new int[width][height ];
		int [][]alpha =new int[width][height ];
		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();	
		Color myBlack = new Color(0, 0, 0); // Color white
		int black = myBlack.getRGB();
		//定義BuffedImage
		BufferedImage outputimage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);
		
		GreenArray = new int[width][height];
		
		for(int i = 0;i < width;i++){
			for(int j=0;j < height;j++){
				rgbData[i][j]=image.getRGB(i, j);
				alpha[i][j] = (rgbData[i][j] >> 24) & 0xFF;	
				red[i][j]   = (rgbData[i][j]& 0xff0000)>> 16;
				green[i][j] =	(rgbData[i][j] & 0xff00) >> 8;
				blue[i][j]  = (rgbData[i][j]) & 0xff;
				
				if(red[i][j]==0 && green[i][j]==0 && blue[i][j]==0){		
					outputimage.setRGB(i, j, black);
				}
				else{
					outputimage.setRGB(i, j, white);
				}
				if(red[i][j]==0 && green[i][j]==255 && blue[i][j]==0)
				{
					GreenArray[i][j] = 1;
				}
				else{
					GreenArray[i][j] = 0;
				}
				
			}
		}		

			

		return outputimage;
	
	
	}	
	public BufferedImage makeCoordinate(BufferedImage image){

		int[][] rgbData= new int[image.getWidth() ][image.getHeight()];
		int width  = image.getWidth();
		
		int height = image.getHeight();
		/*
		System.out.println(width);
		System.out.println(height);
		*/
		int [][]blue =new int[width][height ];
		//定義BuffedImage
		BufferedImage outputimage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);
		outputimage = image;
		
	
		boolean RowOneWhiteFlag = false;
		boolean RowTwoWhiteFlag = false;			
		
		
		int [] upedge = new int[height];
		int [] downedge =new int[height];
		int upcount = 0,downcount = 0;
		
		//find up Down edge
		for(int j=0;j < height;j++){
			for(int i = 0;i < width;i++){	
				
				//row
				rgbData[i][j]=outputimage.getRGB(i, j);
				blue[i][j]  = (rgbData[i][j]) & 0xff;
				if(blue[i][j] == 255){
					RowOneWhiteFlag = true;
				}
		
				
				if((j+1) < height){
					//next row
					rgbData[i][j+1]=outputimage.getRGB(i,j+1);
					blue[i][j+1]  = (rgbData[i][j+1]) & 0xff;
					if(blue[i][j+1] == 255){		
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
		/*
		for(int i=0;i <height;i++){
			System.out.println(upedge[i]);
		}
		 */
		
		
		
		int count=0;
		upcount = 0;
		downcount =0;		
		/*
		for(int i = 0;i < 5 ;i++){
			
			System.out.println("hight:"+(downedge[i] - upedge[i]));

		}		
		*/
		
		//mark upedge 
		for(int i = 0;i < height && upedge[upcount]!=0 ;i++){
			
			int column = upedge[count];
			markUpDownedge(width,height,column,outputimage);
			count++;
			upcount++;
		}
		
		count = 0;
		//mark downedge
		for(int i = 0;i < height && downedge[downcount]!=0 ;i++){
			
			int column = downedge[count];
			markUpDownedge(width,height,column,outputimage);
			count++;
			downcount++;
		}		
		
		
		//find & mark LeftRight
		markRangeEdge(upedge,downedge,outputimage);
		
		

		
		
		return outputimage;
		
		
		
	
	}	
	public void markLeftRightedge(int width,int height,int [][]Leftedge,int [][]Rightedge,BufferedImage image,int[] upedge,int [] downedge){
		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();
		int Leftnum =0;
		int Rightnum =0;
		int Lcount = 0;
		int Rcount = 0;
		

	
		
		
		for(int k=0;k<height  &&upedge[k]!=0 ;k++){
			Leftnum = Leftedge[k][Lcount];
			Rightnum = Rightedge[k][Rcount];		
			while(Leftedge[k][Lcount]!=0 &&  Rightedge[k][Rcount]!=0){
				for(int j=upedge[k];j<downedge[k];j++)
					image.setRGB(Leftnum, j, white);
				for(int j=upedge[k];j<downedge[k];j++)
					image.setRGB(Rightnum, j, white);				
				
				
				Lcount++;
				Rcount++;
				Leftnum = Leftedge[k][Lcount];
				Rightnum = Rightedge[k][Rcount];				
			}
			Lcount=0;
			Rcount=0;
			
		}
		/*
		System.out.println(Leftedge[1][0]);
		System.out.println(Rightedge[1][0]);
		*/
			
	}
	
	public void markUpDownedge(int width,int height,int column,BufferedImage image){

		for(int i=0;i < width;i++){//row
			Color myWhite = new Color(255, 255, 255); // Color white
			int white = myWhite.getRGB();
			image.setRGB(i, column, white);
	
		}		
			
	}
	public void markRangeEdge(int[] upedge,int [] downedge,BufferedImage outputimage){
		
		boolean ColOneWhiteFlag = false; 
		boolean ColTwoWhiteFlag = false;
		int count = 0;
		int[][] rgbData= new int[outputimage.getWidth() ][outputimage.getHeight()];
		int width  = outputimage.getWidth();
		int height = outputimage.getHeight();
		int [][]blue =new int[width][height ];
		int [][] Left  = new int [height][width];
		int [][] Right = new int [height][width];
		int LeftCount  = 0;
		int RightCount = 0;
		
		
		//k level
		for(int k = 0;k < height && downedge[count]!=0 && upedge[count]!=0;k++){
			
			
			//find letter Right Left edge
			for(int i = 0;i < width ;i++){
				for(int j = (upedge[k]+1);j < (downedge[k]-1);j++){	
					
					//find row
					rgbData[i][j]=outputimage.getRGB(i, j);
					blue[i][j]  = (rgbData[i][j]) & 0xff;
					if(blue[i][j] == 255){
						ColOneWhiteFlag = true;
					}
			
					
					if((i+1) < width){
						//next row
						rgbData[i+1][j]=outputimage.getRGB(i+1,j);
						blue[i+1][j]  = (rgbData[i+1][j]) & 0xff;
						if(blue[i+1][j] == 255){		
							ColTwoWhiteFlag  = true;
						}			

					}	
					
				
				}
				//check letter's LeftEdge
				if(ColOneWhiteFlag == false && ColTwoWhiteFlag == true)
				{
					Left[k][LeftCount]= i;
					LeftCount++;

				}
				//check letter's RightEdge
				if(ColOneWhiteFlag == true && ColTwoWhiteFlag == false)
				{
					Right[k][RightCount]= i;
					RightCount++;

				}
				ColOneWhiteFlag = false;
				ColTwoWhiteFlag = false;				
				
				
				
			}
			LeftCount=0;
			RightCount=0;
			
		}	

		
/*		
		for(int i=0;i<2;i++){
			for(int j=0;j<25;j++){
			System.out.println(Right[i][j]);
			}
			System.out.println("divide");
		}
		System.out.println("==========");
		for(int i=0;i<2;i++){
			for(int j=0;j<25;j++){
			System.out.println(Left[i][j]);
			}
			System.out.println("divide");
		}			
		System.out.println("==========");
*/		
		//draw letter's Left Right
		markLeftRightedge(width,height,Left,Right,outputimage,upedge,downedge);
		this.Left =Left;
		this.Right =Right;
		this.Up = upedge;
		this.Down =downedge;	
		
	}
	
	public BufferedImage drawLetterRectangle(BufferedImage image){
		
		BufferedImage outputimage = image;
		BlockUedge = new int[image.getWidth()];
		BlockDedge = new int[image.getWidth()];
		int []UpEdge = this.Up;
		int []DownpEdge = this.Down;
		int [][]LeftEdge = this.Left;
		int [][]RightEdge = this.Right;
		int count = 0;
		int tempcount =0;
		int width  = outputimage.getWidth();
		int height = outputimage.getHeight();
		int Ledge;
		int Redge;

		
		
		LetterUpEdge = new int[width*height];
		LetterDownpEdge = new int[width*height];
		LetterLeftEdge = new int[width*height];
		LetterRightEdge = new int[width*height];	
		LetterWidth = new int [width*height];
		LetterHeigh = new int [width*height];
		LetterRenovate = new int [width*height];
		
		int Upcount = 0 ;
		int Downcount = 0 ;
		int Lcount = 0 ;
		int Rcount = 0 ;
		int[][] rgbData= new int[outputimage.getWidth()][outputimage.getHeight()];
		int [][]blue =new int[width][height];
		boolean RowOneWhiteFlag = false;
		boolean RowTwoWhiteFlag = false;	
		Color myWhite = new Color(255, 255, 255); // Color white
		int white = myWhite.getRGB();	
		
		
		
		
		
		//k level		
		for(int k = 0;k < height && DownpEdge[k]!=0 && UpEdge[k]!=0;k++){
			
			tempcount = 0;	
			RowOneWhiteFlag = false;
			RowTwoWhiteFlag = false;
			
			//find letter Up Down edge
			do{
				RowOneWhiteFlag = false;
				RowTwoWhiteFlag = false;
				Ledge=LeftEdge[k][tempcount];
				Redge=RightEdge[k][tempcount];
				
				for(int j = (UpEdge[k]+1);j < (DownpEdge[k]);j++){
					for(int i = (Ledge+1);i < (Redge) ;i++){
						//row
						rgbData[i][j]=outputimage.getRGB(i, j);
						blue[i][j]  = (rgbData[i][j]) & 0xff;
						if(blue[i][j] == 255){
							RowOneWhiteFlag = true;
						}
				
						if((j+1) < DownpEdge[k]){
							//next row
							rgbData[i][j+1]=outputimage.getRGB(i,j+1);
							blue[i][j+1]  = (rgbData[i][j+1]) & 0xff;
							if(blue[i][j+1] == 255){		
								RowTwoWhiteFlag  = true;
							}			
	
						}
						
						
						if(j == (UpEdge[k]+1)){
							rgbData[i][j-1]=outputimage.getRGB(i, j-1);
							blue[i][j-1]  = (rgbData[i][j-1]) & 0xff;
							if(blue[i][j-1] == 255){
								RowOneWhiteFlag = false;
							}

							//next row
							rgbData[i][j]=outputimage.getRGB(i,j);
							blue[i][j]  = (rgbData[i][j]) & 0xff;
							if(blue[i][j] == 255){		
								RowTwoWhiteFlag  = true;
			
		
							}
							
						}
						if(j == (DownpEdge[k]-1)){
							rgbData[i][j]=outputimage.getRGB(i, j-1);
							blue[i][j-1]  = (rgbData[i][j]) & 0xff;
							if(blue[i][j] == 255){
								RowOneWhiteFlag = true;
							}

							//next row
							rgbData[i][j+1]=outputimage.getRGB(i,j+1);
							blue[i][j+1]  = (rgbData[i][j+1]) & 0xff;
							if(blue[i][j+1] == 255){		
								RowTwoWhiteFlag  = false;
			
		
							}
							
						}							
						
						
						
					
					}

					
					
					//check upedge
					if(RowOneWhiteFlag == false && RowTwoWhiteFlag == true)
					{
						BlockUedge[Lcount] = UpEdge[k];
						LetterUpEdge[Upcount]= j;
						LetterLeftEdge[Lcount] = Ledge+1;
						LetterRightEdge[Rcount] =Redge;
						Upcount++;
						Lcount++;
						Rcount++;
	
					}
					//check downedge
					if(RowOneWhiteFlag == true && RowTwoWhiteFlag == false)
					{
						BlockDedge[Downcount] = DownpEdge[k];
						LetterDownpEdge[Downcount]= j;
						Downcount++;
	
					}
					RowOneWhiteFlag = false;
					RowTwoWhiteFlag = false;
					
	
					
					}
				tempcount++;
				Ledge=LeftEdge[k][tempcount];
				Redge=RightEdge[k][tempcount];
				}while(Ledge!=0);	
			
		}
		/*
		for(int i=0;i<2;i++){
			for(int j =0;j<5 ;j++)
			System.out.println(LeftEdge[i][j]);	

		}	
		System.out.println("========");
		for(int i=0;i<2;i++){
			for(int j =0;j<5 ;j++)
			System.out.println(RightEdge[i][j]);
		}	
		*/
		//draw letter  rectangle
		Upcount = 0 ;
		Downcount = 0 ;
		Lcount = 0 ;
		Rcount = 0 ;
		int tempcount1 = 0;
		int flag = 0;
		//dismiss i
		for(int i=0;i < (width*height)  ;i++){

			if((LetterLeftEdge[i+1] == LetterLeftEdge[i]) && (LetterRightEdge[i+1] ==LetterRightEdge[i]) && LetterRightEdge[i]!=0){
				LetterDownpEdge[i] =LetterDownpEdge[i+1];
				
				for(int j =i+1;j<100;j++){
					LetterDownpEdge[j]=LetterDownpEdge[j+1];
					LetterUpEdge[j]=LetterUpEdge[j+1];
					LetterLeftEdge[j]=LetterLeftEdge[j+1];
					LetterRightEdge[j]=LetterRightEdge[j+1];
					
					if(LetterLeftEdge[j] == 0){
						break;
					}
				}
				
			}
			
			
			if(LetterLeftEdge[i] == 0){
				break;
			}

		}

		
		
		
		
		
		
		int [][]RTMP =new int[width][height ];
		int [][]GTMP =new int[width][height ];
		int [][]BTMP =new int[width][height ];
		int [][]RGBTEMP =new int[width][height ];
		
		
		
		
		

		//calculate letterwidth
		while(LetterUpEdge[tempcount1]!=0){
			lettercount++;
			int L = 0,R = 0,U = 0,D = 0;
			U = LetterUpEdge[tempcount1];
			D = LetterDownpEdge[tempcount1];
			L = LetterLeftEdge[tempcount1];
			R = LetterRightEdge[tempcount1];	
			LetterWidth[tempcount1] = LetterRightEdge[tempcount1] - LetterLeftEdge[tempcount1]-1;
			LetterHeigh[tempcount1] = LetterDownpEdge[tempcount1] - LetterUpEdge[tempcount1]-1;
			//System.out.println("W:"+LetterWidth[tempcount1]);
			//System.out.println("H:"+LetterHeigh[tempcount1]);
			
			for(int i = L;i < R;i++){
				for(int j=U;j <= D ;j++){
					outputimage.setRGB(i, j, white);

				
					RGBTEMP[i][j] = OriImage.getRGB(i,j);
					RTMP[i][j]   = (RGBTEMP[i][j]& 0xff0000)>> 16;
					GTMP[i][j]   =	(RGBTEMP[i][j] & 0xff00) >> 8;
					BTMP[i][j]   = (RGBTEMP[i][j]) & 0xff;				
					
					if(RTMP[i][j] == 0 && GTMP[i][j] == 255 && BTMP[i][j] == 0){
						LetterRenovate[tempcount1] = 1; 
					}
					
					
					
					
				}
			}	
			
			
			
			tempcount1++;	
			
		}

		
		return outputimage;
		
		
		
	}	
	
	
	
	
	
	
	
	
	public BufferedImage ImageRenovate(BufferedImage filterimage,BufferedImage edgeimage){
		
		
		BufferedImage outputimage = filterimage;
		
		int [][]blue =new int[wei][hei];
		int [][]red =new int[wei][hei];
		int [][]green =new int[wei][hei];
		for(int i = 0;i < wei;i++){
			for(int j=0;j < hei;j++){
				red[i][j]   = (filterimage.getRGB(i, j) & 0xff0000)>> 16;
				green[i][j] =	(filterimage.getRGB(i, j) & 0xff00) >> 8;
				blue[i][j]  = (filterimage.getRGB(i, j) & 0xff) ;		
				
			}
		}
		//System.out.println("wei:"+wei);
		//System.out.println("hei:"+hei);	
		
		
		
		
		
		//find renovate
		for(int i = 0;(i < lettercount);i++){
			//need to renovate = 1
			if(LetterRenovate[i] == 1 ){
				int targetUEdge=0;
				int targetDEdge=0;	
				int targetLEdge=0;
				int targetREdge=0;
				int []blocku =new int [lettercount];
				int []blockd =new int [lettercount];
				int []sortU = new int [lettercount];
				int []sortD = new int [lettercount];
				int []sortL = new int [lettercount];					
				int []sortR = new int [lettercount];
				int []number = new int [lettercount];
				int []blockwei = new int [lettercount];
				int []blockhei = new int [lettercount];
				//give number
				for(int z=0;z<lettercount;z++){
					sortU[z] = LetterUpEdge[z]+1;
					sortD[z] = LetterDownpEdge[z];
					sortL[z] = LetterLeftEdge[z]+1;
					sortR[z] = LetterRightEdge[z];
					blocku[z] =BlockUedge[z]+1;
					blockd[z] =BlockDedge[z];
					blockwei[z]=LetterWidth[z];
					blockhei[z]=LetterHeigh[z];
					number[z]=z;
					
					//System.out.println("blockhei:"+(BlockDedge[z]-BlockUedge[z]));
					
					/*System.out.println("ZUMBER:"+z);
					System.out.println("U:"+sortU[z]);
					System.out.println("D:"+sortD[z]);
					System.out.println("L:"+sortL[z]);
					System.out.println("R:"+sortR[z]);
					*/
				}
				
				
				int sourcewidth;
				int sourecehight;
				int RenovateHidth = LetterHeigh[i];
				int RenovateWidth = LetterWidth[i];
				int []ssdBlock = new int[lettercount];
				int []codiNumber =new int[lettercount];
				int nowblocku =BlockUedge[i]+1;
				int nowblockd =BlockDedge[i]-1;
				int UEdge=LetterUpEdge[i];
				int DEdge=LetterDownpEdge[i]-1;
				int LEdge=LetterLeftEdge[i]+1;
				int REdge=LetterRightEdge[i]-1;
				/*
				System.out.println("===============");
				System.out.println("NOWU:"+UEdge);
				System.out.println("NOWD:"+DEdge);
				System.out.println("NOWL:"+LEdge);
				System.out.println("NOWR:"+REdge);				
				*/
				int tempsumR = 0;
				int tempsumG = 0;
				int tempsumB = 0;
				int tempsumssd = 0;
				//calculate ssd
				for(int c = 0;(c < lettercount);c++){
					//default ssd = 999999
					ssdBlock[c]= 9999999;

					//source edge
					int UtempEdge = LetterUpEdge[c]+1;
					int DtempEdge = LetterDownpEdge[c]-1;
					int LtempEdge = LetterLeftEdge[c]+1;
					int RtempEdge = LetterRightEdge[c]-1;	
					/*
					System.out.println("U:"+UtempEdge);
					System.out.println("D:"+DtempEdge);
					System.out.println("L:"+LtempEdge);
					System.out.println("R:"+RtempEdge);
					 */
					
					
					sourcewidth = LetterWidth[c];
					sourecehight = LetterHeigh[c];
					/*
					System.out.println("sourcewidth:"+sourcewidth);
					System.out.println("RenovateWidth:"+RenovateWidth);
					*/
					
					//calculate ssd with not be renovated's ssd and width > sourcewidth
					if((LetterRenovate[c] == 0) && (RenovateWidth <= sourcewidth)  && (RenovateHidth <= sourecehight))
						//System.out.println("c:"+c);
						for(int n=LtempEdge;n <= LtempEdge + (sourcewidth - RenovateWidth);n++){
							for(int m=UtempEdge;m <= UtempEdge + (sourecehight - RenovateHidth);m++){
								//reset
								tempsumR = 0;
								tempsumG = 0;
								tempsumB = 0;
								tempsumssd = 0;
								//tempcount
								int tempcountWei = 0;
								int tempcountHei = 0;
								
								for(int blockI = LEdge;blockI <= REdge;blockI++){
									for(int blockY = UEdge;blockY <=  DEdge;blockY++){
										//calculate bloackssd

										tempsumR = tempsumR + (int)Math.pow(red[n + tempcountWei][m + tempcountHei]-red[blockI][blockY],2);
										tempsumG = tempsumG + (int)Math.pow(green[n + tempcountWei][m + tempcountHei]-green[blockI][blockY],2);
										tempsumB = tempsumB + (int)Math.pow(blue[n + tempcountWei][m + tempcountHei]-blue[blockI][blockY],2);
										//System.out.println("blockI:"+blockI);
										
										tempcountHei++;

									}
									tempcountHei = 0;
									tempcountWei++;

								}
								
								tempsumssd = tempsumR + tempsumG + tempsumB;
								//System.out.println("tempsumR:"+tempsumR);
								if(ssdBlock[c] > tempsumssd){
									ssdBlock[c] = tempsumssd;
								}
								
								
							}
						}

						
						
				}
				/*
				for(int c = 0;(c < lettercount);c++){
					System.out.println("ssdBlock:"+ssdBlock[c]);
					System.out.println("targetUEdge:"+sortU[c]);
					System.out.println("targetDEdge:"+sortD[c]);
					System.out.println("targetLEdge:"+sortL[c]);
					System.out.println("targetREdge:"+sortR[c]);
				}
				 */
				
				
				
				//sort ssd			
				merge_sort(ssdBlock , sortU, sortD,sortL,sortR,number,blocku,blockd);
				int tmps = 0;
				int ssssd;
				int nummm;
				do{
					int localtmp;
					/*
					System.out.println("tmps:"+tmps);
					*/
					if(blocku[tmps] == BlockUedge[i]+1){
						localtmp = tmps;
						ssssd=ssdBlock[tmps];
						targetUEdge=sortU[tmps];
						targetDEdge=sortD[tmps];
						targetLEdge=sortL[tmps];
						targetREdge=sortR[tmps];	
						nummm =number[tmps]+1;
						/*
						System.out.println("123:"+(blocku[tmps]));
						System.out.println("456:"+(BlockUedge[i]+1));
						System.out.println("********************");
						System.out.println("targetUEdge:" + targetUEdge);
						System.out.println("********************");
						*/
						
						
						
						break;
						
					}else{
						ssssd=ssdBlock[0];
						targetUEdge=sortU[0];
						targetDEdge=sortD[0];
						targetLEdge=sortL[0];
						targetREdge=sortR[0];	
						nummm =number[0]+1;
						
					}
		
					
					tmps++;

				}while(ssdBlock[tmps] == ssdBlock[tmps+1]);

				/*
				for(int c = 0;(c < 3);c++){
					System.out.println("ssdBlock:"+ssdBlock[c]);
					System.out.println("targetUEdge:"+sortU[c]);
					System.out.println("targetDEdge:"+sortD[c]);
					System.out.println("targetLEdge:"+sortL[c]);
					System.out.println("targetREdge:"+sortR[c]);
				}
				*/
				targetUEdge=sortU[0];
				targetDEdge=sortD[0];
				targetLEdge=sortL[0];
				targetREdge=sortR[0];
				
				
				
				//sort end
				/*
				int ssssd=ssdBlock[0];
				targetUEdge=sortU[0];
				targetDEdge=sortD[0];
				targetLEdge=sortL[0];
				targetREdge=sortR[0];	
				int nummm =number[0]+1;
				*/
				/*
				System.out.println("=============");
				*/
				//System.out.println("ssdBlock:"+ssssd);
				/*
				System.out.println("targetUEdge:"+targetUEdge);
				System.out.println("targetDEdge:"+targetDEdge);
				System.out.println("targetLEdge:"+targetLEdge);
				System.out.println("targetREdge:"+targetREdge);
				System.out.println("number:"+nummm);
				System.out.println("LEdge:"+LEdge);
				System.out.println("=============");			
				*/
				//renovate block
				
				int tempcountWei = 0;
				int tempcountHei = 0;
			
				if( ( ((LetterLeftEdge[i] + (targetREdge-targetLEdge)) - LetterLeftEdge[i+1])>10  ) || (BlockUedge[i] != BlockUedge[i+1]) ){
				
					for(int m=(targetLEdge);m<targetREdge;m++){
						for(int n=blocku[0];n<blockd[0];n++){
							int rgb=0;

							Color colors = new Color(red[m][n], green[m][n], blue[m][n]); // Color white//
							//Color colors = new Color(0, 0, 0);
							int color = colors.getRGB();	
							outputimage.setRGB(LEdge + tempcountWei, nowblocku + tempcountHei,color);		
							tempcountHei++;
						}
						tempcountHei = 0;
						tempcountWei++;
						
					}
				
				}else{
					
				
				
				
					for(int m=(targetREdge-1);m>=targetLEdge;m--){
						for(int n=blocku[0]+1;n<blockd[0];n++){
							int rgb=0;


							Color colors = new Color(red[m][n], green[m][n], blue[m][n]); // Color white//
							//Color colors = new Color(0, 0, 0);
							int color = colors.getRGB();	

							outputimage.setRGB(REdge + tempcountWei,nowblocku + tempcountHei,color);		
							tempcountHei++;
						}
						tempcountHei = 0;
						tempcountWei--;
						
					}
				 
				
			}
			
			
			
			
			
			
			
			
		}
		
	}		
		return outputimage;
		
	}
	
	
	
	
	static void merge_sort_recursive(int[] arr, int[] reg, int start, int end,int[] U, int[]D ,int[]L ,int[]R,int[]blocku,int[]blockd,int[] number, int[] regU ,int[] regD,int[] regL,int[] regR,int[] regNumber,int []regblocku,int []regblockd) {
		if (start >= end)
			return;
		int len = end - start, mid = (len >> 1) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge_sort_recursive(arr, reg, start1, end1, U,D,L,R,blocku,blockd,number,regU,regD,regL,regR,regNumber,regblocku,regblockd);
		merge_sort_recursive(arr, reg, start2, end2, U,D,L,R,blocku,blockd,number,regU,regD,regL,regR,regNumber,regblocku,regblockd);
		int k = start;
		
		
		while (start1 <= end1 && start2 <= end2){
			if(arr[start1] < arr[start2]){
				reg[k]  = arr[start1];
				regU[k] = U[start1];
				regD[k] = D[start1];
				regL[k] = L[start1];
				regR[k] = R[start1];
				regNumber[k]= number[start1];
				regblocku[k] =blocku[start1]; 
				regblockd[k] =blockd[start1];	
						
						
				k++;
				start1++;
			}else{
				reg[k]  = arr[start2];
				regU[k] = U[start2];
				regD[k] = D[start2];
				regL[k] = L[start2];
				regR[k] = R[start2];
				regNumber[k]= number[start2];
				regblocku[k] =blocku[start2]; 
				regblockd[k] =blockd[start2];	
				
				k++;
				start2++;
			}
		}
		while (start1 <= end1){
			reg[k]  = arr[start1];
			regU[k] = U[start1];
			regD[k] = D[start1];
			regL[k] = L[start1];
			regR[k] = R[start1];
			regNumber[k]= number[start1];
			regblocku[k] =blocku[start1]; 
			regblockd[k] =blockd[start1];	
			
			k++;
			start1++;
		}
		while (start2 <= end2){
			reg[k]  = arr[start2];
			regU[k] = U[start2];
			regD[k] = D[start2];
			regL[k] = L[start2];
			regR[k] = R[start2];
			regNumber[k]= number[start2];
			regblocku[k] =blocku[start2]; 
			regblockd[k] =blockd[start2];	
			
			k++;
			start2++;
		}
		
		//
		for (k = start; k <= end; k++)
			arr[k] = reg[k];
		for (k = start; k <= end; k++)
			U[k] = regU[k];
		for (k = start; k <= end; k++)
			D[k] = regD[k];
		for (k = start; k <= end; k++)
			L[k] = regL[k];
		for (k = start; k <= end; k++)
			R[k] = regR[k];
		for (k = start; k <= end; k++)
			number[k] = regNumber[k];		
		for (k = start; k <= end; k++)
			blocku[k] = regblocku[k];
		for (k = start; k <= end; k++)
			blockd[k] = regblockd[k];
		
		
		
	}

	public static void merge_sort(int[] arr , int[] U, int[]D,int []L,int []R,int[] number,int[]blocku,int []blockd) {
		int len = arr.length;
		int[] reg = new int[len];
		int[] regU =new int[len];
		int[] regD =new int[len];
		int[] regL =new int[len];
		int[] regR =new int[len];
		int[] regblocku =new int[len];
		int[] regblockd =new int[len];
		int[] regNumber=new int[len];
		merge_sort_recursive(arr, reg, 0, len - 1 ,U,D,L,R,blocku,blockd,number,regU,regD,regL,regR,regNumber,regblocku,regblockd);
	}	
	
	
	
	
	
	
	
	
	
	
	public int[][] getLeft(){
		
		return this.Left;
	}
	public int[][] getRight(){
		
		return this.Right;
	}


	
}
