import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
/**
 * High-level image editing abstract class for the Endeavor image editing program.
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public abstract class EndeavorImageManip 
{
	
	public static void apply_effect(Container c, BufferedImage img, EndeavorTool effect)
	{
		effect.configure(c);
		
		// Traverses every pixel in the image
		int y = 0;
		while(y < img.getHeight())
		{
			for(int x = 0; x < img.getWidth(); x++)
			{
				
				// Uses bitwise operators to deconstruct RGB from ColorInt
				
				int color = img.getRGB(x,  y);
				int red   = (color & 0x00FF0000) >> 16;
	          	int green = (color & 0x0000FF00) >> 8;
	          	int blue  =  color & 0x000000FF;
	          	
	          	int[] result = effect.act(red, green, blue);
	          	
	          	red = result[0];
	          	green = result[1];
	          	blue = result[2];
	          	
	          	// Re-applies RGB, converting to ColorInt.
	          	red = (red << 16) & 0x00FF0000;
	            green = (green << 8) & 0x0000FF00;
	            blue = blue & 0x000000FF;

	            // Applies changes to image
	            img.setRGB(x, y, 0xFF000000 | red | green | blue);
			}
			
			y++;
		}
	}
}
