import java.io.*;
/**
 * Endeavor main class. Runs the entire program.
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public class Endeavor {

	public static void main() throws IOException
	{
		EndeavorUI editor = new EndeavorUI();
		editor.askUserToOpenImage();
	}
	
}
