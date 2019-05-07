import java.awt.*;

import javax.swing.JColorChooser;
/**
 * EndeavorColorBalance is an EndeavorTool for tinting and similar effects.
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public class EndeavorColorBalance implements EndeavorTool 
{
	
	public String getName()
	{
		return "Tint";
	}
	
	public static final double ratio = 0.1;
	
	int r1, g1, b1;
	
	// Allows user to pick tint color
	public void configure(Container c)
	{
		Color modifier = JColorChooser.showDialog(c, "Pick a color", Color.BLACK);
		r1 = (int) (modifier.getRed()*ratio);
		g1 = (int) (modifier.getGreen()*ratio);
		b1 = (int) (modifier.getBlue()*ratio);
	}
	
	// Adds the tint to the image, capping values at 255.
	public int[] act(int r, int g, int b)
	{
		r += r1;
		if(r>255) r=255;
		
		g += g1;
		if(g>255) g=255;
		
		b += b1;
		if(b>255) b=255;
		
		return new int[] {r, g, b};
	}
}
