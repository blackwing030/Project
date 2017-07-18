import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;






public class main {
	
	private static int []x ; //green cordinate x
	private static int []y ;//green cordinate y
	private static int [][]structor;
	private static int []priority;
	private static int [][]red;
	private static int [][]green;
	private static int [][]blue;
	private static int [][]rgbData;
	private static int [][]isgreen;
	private static int wei;
	private static int hei;	
	
//塗成綠色>>找邊緣>>
	public static void main(String[] args) throws Exception{
        	long time1, time2;
        	time1 = System.currentTimeMillis();
			int WIDTH = 300;
			int HEIGHT = 400;
			//input: Buffed Image
			BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB);	
	
				
			//read image file
			image = ImageIO.read(new File("C:/xampp/htdocs/ImageFile/PRP/image.png"));
			
			PhotoRecoveryProcedure imagee = new PhotoRecoveryProcedure();		

			
		
			
			
			
			
			
			/* canny */
			//create the detector

			CannyEdgeDetector detector = new CannyEdgeDetector();

				
			//adjust its parameters as desired
			detector.setLowThreshold(0.5f);
			detector.setHighThreshold(1.0f);
		
			//apply it to an image/		
			detector.setSourceImage(image);
			int type = image.getType();
			//type  System.out.println(type);
			detector.process();
			BufferedImage edges = detector.getEdgesImage();
				
			// 將image圖象文件輸出到磁盤文件中。
			ImageIO.write(edges, "png", new File("C:/xampp/htdocs/ImageFile/PRP/EdgesImage"
			+ ".png"));	
			
		
			imagee.setCannyImage(edges);
			imagee.setOriImage(image);

			imagee.process();
			time2 = System.currentTimeMillis();
			
			System.out.println("總共花了：" + (time2-time1) + "毫秒");
			
			
			
		
			
		
	}
}

	
	
	

