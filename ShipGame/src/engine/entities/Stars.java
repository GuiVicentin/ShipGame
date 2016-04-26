package engine.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stars {
	
	private BufferedImage img;
	
	public Stars() {
		try {
			img = ImageIO.read(new File("res/stars.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, 0, 0, null);
	}
}
