package image;
import javax.imageio.*;

import java.awt.image.BufferedImage;
import java.io.*;




public class image_main{


	public static void main(String[] args) throws Exception {
		int WIDTH = 300;
		int HEIGHT = 400;
		//input: Buffed Image
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
		BufferedImage.TYPE_INT_RGB);
		//input: Blacktext Image
		BufferedImage textimage = new BufferedImage(WIDTH, HEIGHT,
		BufferedImage.TYPE_INT_RGB);
		//input: filter Image
		BufferedImage filterimage;	
		//input: litter Image
		BufferedImage litterimage;
		//input: Rectangle Image
		BufferedImage Rectangle;	
		//input: block Image
		BufferedImage output;			
		
		//create TRP
		TextRecoveryProcedure TextImage = new TextRecoveryProcedure();
		
	
		//read image file
		image = ImageIO.read(new File("C:/xampp/htdocs/ImageFile/TRP/image.png"));
		//convert image to black
		textimage = TextImage.GreentoWhite(image);
				
		// 將image圖象文件輸出到磁盤文件中。
		ImageIO.write(textimage, "png", new File("C:/xampp/htdocs/ImageFile/TRP/TextImage"
		+ ".png"));			
		
		
		
		
		
		/* canny */
		//create the detector
		CannyEdgeDetector detector = new CannyEdgeDetector();
			
		//adjust its parameters as desired
		detector.setLowThreshold(0.5f);
		detector.setHighThreshold(5f);
	
		//apply it to an image/		
		detector.setSourceImage(textimage);
		detector.process();
		BufferedImage edges = detector.getEdgesImage();
			
		// 將image圖象文件輸出到磁盤文件中。
		ImageIO.write(edges, "png", new File("C:/xampp/htdocs/ImageFile/TRP/EdgesImage"
		+ ".png"));	
		int type = edges.getType();
		/*
		System.out.println(type);
		*/
		
		
		
		//create Center Weight midan Filter
		MedianFilter midanFilter =new MedianFilter();		
		filterimage = new BufferedImage(edges.getWidth(), edges.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		 filterimage = midanFilter.filter(edges);					
		ImageIO.write(filterimage, "png", new File("C:/xampp/htdocs/ImageFile/TRP/CenterWeightMedianFilterImage"
		+ ".png"));	
		
		
		//litter edge
		litterimage = TextImage.makeCoordinate(filterimage);
		ImageIO.write(litterimage, "png", new File("C:/xampp/htdocs/ImageFile/TRP/litterImage"
		+ ".png"));			
		
		//Rectangle image
		Rectangle = TextImage.drawLetterRectangle(litterimage);
		ImageIO.write(Rectangle, "png", new File("C:/xampp/htdocs/ImageFile/TRP/Rectangle"
		+ ".png"));		
		
		//TextImage.ImageRenovate(textimage);
		output = TextImage.ImageRenovate(textimage,litterimage);
		ImageIO.write(output, "png", new File("C:/xampp/htdocs/ImageFile/TRP/output"
		+ ".png"));		
		
		
		
	
	}
}
