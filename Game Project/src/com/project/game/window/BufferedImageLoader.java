package com.project.game.window;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
//functia pentru import din folderul src
public class BufferedImageLoader {
	public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
