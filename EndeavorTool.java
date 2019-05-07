import java.awt.*;

/**
 * Interface EndeavorTool for image tools to inherit from
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public interface EndeavorTool
{
	public String getName();
    /**
     * Manipulates a single pixel.
     *
     * @param  r  The red value of the pixel to be modified.
     * @param  g  The green value of the pixel to be modified.
     * @param  b  The blue value of the pixel to be modified.
     * @return    An array containing the new red, green, and blue values.
     */
    public int[] act(int r, int g, int b);
    
    // Run once every time the button is clicked.
    // Useful for initialization.
    public void configure(Container c);
}
