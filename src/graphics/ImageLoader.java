package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	
	public static BufferedImage loadImage(String path){
		BufferedImage img;
		path = "res/" + path;
		try {
			img = ImageIO.read(new File(path));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
