import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import java.awt.*;
/**
 * EndeavorChromaKey is an EndeavorTool for green screen/chroma key effects.
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public class EndeavorChromaKey implements EndeavorTool
{
	
	private double allowance = 20;
	
	public String getName()
	{
		return "Chroma Key";
	}
    
    // The chroma key color, which is to be removed.
    private int[] targetColor = new int[]{0,0,0};

    /**
     * Constructor for objects of class EndeavorChromaKey
     */
    public EndeavorChromaKey(int[] keyTargetColor)
    {
    	targetColor = keyTargetColor;
    }
    
    public EndeavorChromaKey()
    {
    	targetColor = new int[] {0, 255, 0};
    }

    /**
     * Runs a chroma key effect on the image using the target color.
     * 
     * @param  r  The red value of the pixel to be modified.
     * @param  g  The green value of the pixel to be modified.
     * @param  b  The blue value of the pixel to be modified.
     * @return    An array containing the new red, green, and blue values.
     */
    @Override
    public int[] act(int r, int g, int b)
    {
        if(Math.abs(r - targetColor[0]) <= allowance && Math.abs(g - targetColor[1]) <= allowance && Math.abs(b - targetColor[2]) <= allowance)
        {
        	return new int[] {0, 0, 0};
        }
        else
        {
        	return new int[] {r, g, b};
        }
    }
    
    public void configure(Container c)
    {
    	Color key = JColorChooser.showDialog(c, "Pick a key color", Color.GREEN);
    	int r = key.getRed();
    	int g = key.getBlue();
    	int b = key.getGreen();
    	targetColor = new int[] {r, g, b};
    	
    	String strAllowance = JOptionPane.showInputDialog("Chroma key allowance - larger values mean more is keyed out.");
    	
    	try {
    		allowance = Double.valueOf(strAllowance);
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Could not parse allowance input. Defaulting to "+String.valueOf(allowance));
    	}
    }
    
    public void setTargetColor(int r, int g, int b)
    {
    	targetColor[0] = r;
    	targetColor[1] = g;
    	targetColor[2] = b;
    }
}
