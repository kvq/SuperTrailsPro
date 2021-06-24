package me.kvq.supertrailspro.trails;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WingsParser {
	
	public static byte[][] parseImage(File f) throws IOException{
		
		byte[][] buffer = new byte[14][14];
		BufferedImage img = buffer(f);
		int y_max = img.getWidth() < 14 ? img.getHeight() : 14;
		int x_max = img.getWidth() < 14 ? img.getWidth() : 14;
		for (int y = 0; y < 14; y++){
			for (int x = 0; x < 14; x++) {
				
				if (y < y_max && x < x_max) {
					int color = img.getRGB(x, y); byte value = colorToByte(color);
					buffer[y][x] = value;
				} else {
					buffer[y][x] = 0;
				}
				
			}
		}
		return buffer;
		
	}
	
	public static BufferedImage buffer(File f) throws IOException {
		return ImageIO.read(f);
	}
	
	private static byte colorToByte(int color) {
		return colorToType(color).getByte();
	}
	
	private static PixelType colorToType(int color) {
		int[] rgb = rgb(color);
		int red = rgb[0]; int green = rgb[1]; int blue = rgb[2];
		
		if (red == 255 && blue == 0 && green == 0) return PixelType.Red;
		if (red == 0 && blue == 255 && green == 0) return PixelType.Blue;
		if (red == 0 && blue == 0 && green == 255) return PixelType.Green;
		if (red == 255 && green == 255 && blue== 0) return PixelType.Flame;
		if (red == 255 && green == 0 && blue == 255) return PixelType.Witch;
		if (red == 255 && green == 128 && blue == 64) return PixelType.Drip;
		if (red == 0 && green == 255 && blue == 255) return PixelType.Crit;
		return PixelType.None;
	}
	
	private static int[] rgb(int color) {
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		return new int[] {red,green,blue};
	}
	
	private static BufferedImage crop(BufferedImage img) {
		if (img.getWidth() == 14 && img.getHeight() == 14) return img;
		
		BufferedImage crop = new BufferedImage(
			   14, 14, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = crop.createGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.drawImage(img, 0, 0, null);
		graphics.dispose();
		return crop;
	}
	
	
	public static BufferedImage loadPattern(File f) throws IOException {
		return crop(ImageIO.read(f));
	}


}
