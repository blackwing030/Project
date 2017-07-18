package image;
import java.util.Arrays;
import java.awt.Color;
import java.awt.image.BufferedImage;







public class MedianFilter{

	public BufferedImage filter(BufferedImage srcImage) {

		//©w¸qBuffedImage
		BufferedImage outputimage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(),
		BufferedImage.TYPE_INT_RGB);
		
		
        Color[] pixel=new Color[13];
        int[] R=new int[13];
        int[] B=new int[13];
        int[] G=new int[13];

        BufferedImage img=srcImage;
        
        for(int i=1;i<img.getWidth()-1;i++){
            for(int j=1;j<img.getHeight()-1;j++){
               pixel[0]=new Color(img.getRGB(i-1,j-1));
               pixel[1]=new Color(img.getRGB(i-1,j));
               pixel[2]=new Color(img.getRGB(i-1,j+1));
               pixel[3]=new Color(img.getRGB(i,j+1));
               pixel[4]=new Color(img.getRGB(i+1,j+1));
               pixel[5]=new Color(img.getRGB(i+1,j));
               pixel[6]=new Color(img.getRGB(i+1,j-1));
               pixel[7]=new Color(img.getRGB(i,j-1));
               pixel[8]=new Color(img.getRGB(i,j));
               pixel[9]=new Color(img.getRGB(i,j));
               pixel[10]=new Color(img.getRGB(i,j));
               pixel[11]=new Color(img.getRGB(i,j));
               pixel[12]=new Color(img.getRGB(i,j));
               
               
               
               for(int k=0;k<13;k++){
                   R[k]=pixel[k].getRed();
                   B[k]=pixel[k].getBlue();
                   G[k]=pixel[k].getGreen();
               }
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
               outputimage.setRGB(i,j,new Color(R[6],B[6],G[6]).getRGB());
            }
        }
        return outputimage;    
    
 
    
	}
}