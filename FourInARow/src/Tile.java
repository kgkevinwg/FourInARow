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
	
	int xc,yc,status;
	String image;
	BufferedImage bi;
	JLabel picLabel;

	public Tile(int x, int y) {
		this.xc = x;
		this.yc = y;
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
		picLabel = new JLabel(imageIcon);
		add(picLabel);
	}
	
	public void reDraw(String imgStr)
	{
		ImageIcon imageIcon = new ImageIcon(imgStr); 
		Image img = imageIcon.getImage();
		Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		picLabel.setIcon(imageIcon);
	}

	public int getXc() {
		return xc;
	}

	public void setXc(int xc) {
		this.xc = xc;
	}

	public int getYc() {
		return yc;
	}

	public void setYc(int yc) {
		this.yc = yc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public JLabel getPicLabel() {
		return picLabel;
	}

	public void setPicLabel(JLabel picLabel) {
		this.picLabel = picLabel;
	}

	

}