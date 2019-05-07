import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * Main UI class for the Endeavor image editing program.
 *
 * @author Joseph Daly
 * @version 0.0.1
 */
public class EndeavorUI extends JFrame
{
    // Current image
    private BufferedImage image;
    private JLabel displayedImage;
    private JFileChooser fileChoose = new JFileChooser();
    private JMenuBar toolbar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu toolMenu = new JMenu("Tools");
    private EndeavorUI ref = this;
    ArrayList<EndeavorTool> tools = new ArrayList<EndeavorTool>() {{add(new EndeavorColorBalance()); add(new EndeavorChromaKey());}};

    /**
     * Constructor for objects of class EndeavorUI
     */
    public EndeavorUI()
    {
        super("Endeavor");
        setSize(1000, 700);
        setLayout(new FlowLayout());
        setVisible(true);
        displayedImage = new JLabel();
        add(displayedImage);
        
        JMenuItem openImageMenu = new JMenuItem("Open");
        openImageMenu.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
					ref.askUserToOpenImage();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(ref, "An I/O error occured. Please check access permissions and file type.");
				}
        	}
        });
        
        JMenuItem saveImageMenu = new JMenuItem("Save");
        saveImageMenu.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ref.askUserToSaveImage();
        	}
        });
        
        fileMenu.add(openImageMenu);
        fileMenu.add(saveImageMenu);
        
        for(EndeavorTool tool : tools)
        {
        	addTool(tool, tool.getName());
        }
        toolbar.add(fileMenu);
        toolbar.add(toolMenu);
        
        setJMenuBar(toolbar);
    }
    
    public void openImage(File imageFile) throws IOException
    {
    	try {
    		image = ImageIO.read(imageFile);
    		displayedImage.setIcon(new ImageIcon(image));
    	} catch (IOException e1) {
    		JOptionPane.showMessageDialog(ref, "An I/O error occured. Please check access permissions and file type.");
    	}
    }
    
    public void askUserToOpenImage() throws IOException
    {
    	int returnVal = fileChoose.showOpenDialog(this);
    	
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
    		File file = fileChoose.getSelectedFile();
    		openImage(file);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(this, "Please select an image!");
    	}
    }
    
    public void askUserToSaveImage()
    {
    	int returnVal = fileChoose.showOpenDialog(this);
    	
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
    		try {
    			File file = fileChoose.getSelectedFile();
    			ImageIO.write(image, "png", file);
    		} catch (IOException e1) {
    			JOptionPane.showMessageDialog(this, "An error occured while saving the image.");
    		}
    	}
    }
    
    public void addTool(EndeavorTool tool, String name)
    {
    	EndeavorUI ref = this;
    	JMenuItem toolButton = new JMenuItem(name);
    	toolButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e)
    		{
    			EndeavorImageManip.apply_effect(ref, image, tool);
    			displayedImage.setIcon(new ImageIcon(image));
    		}
    	});
    	
    	toolMenu.add(toolButton);
    }
}
