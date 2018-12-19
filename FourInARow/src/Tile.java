import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tile extends JPanel{
	
	int x,y,status;
	String image;
	BufferedImage bi;
	

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = "grb.png";
		this.status = 0;
		this.setLayout(new FlowLayout());
		try {
			setPanelImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	private void setPanelImage() throws IOException
	{
//		JLabel picLabel = new JLabel("WOI");
//		add(picLabel);
//		System.out.println("set");
		ImageIcon imageIcon = new ImageIcon(image); 
		Image img = imageIcon.getImage();
		Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JLabel picLabel = new JLabel(imageIcon);
		add(picLabel);
	}
	
	

}
