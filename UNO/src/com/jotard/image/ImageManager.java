package com.jotard.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageManager {
	
	private static ImageManager instance;
	
	private ArrayList<Image> cacheImageList;
	private ArrayList<String> cacheURLList;

	private ImageManager() {
		cacheImageList = new ArrayList<>();
		cacheURLList = new ArrayList<>();
	}

	//singleton??
	public static ImageManager getInstance() {
		if (instance == null)
			instance = new ImageManager();
		return instance;
	}

	public Image getImage(String name) {
		if (cacheURLList.indexOf(name) >= 0)
			return cacheImageList.get(cacheURLList.indexOf(name));
		return null;
	}

	public Image getScaledImage(String name, double scaleMultiplier) {
		Image original = getImage(name);
		if (original == null)
			return null;
		return original.getScaledInstance((int) (original.getWidth(null) * scaleMultiplier), (int) (original.getHeight(null) * scaleMultiplier), Image.SCALE_AREA_AVERAGING);
	}

	public void loadAndCacheImage(String url) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getResourceAsStream(url));
		if (img != null) {
			cacheImageList.add(img);
			cacheURLList.add(url);
		}
	}

	public void clearImageCache() {
		cacheURLList.clear();
		cacheImageList.clear();
	}

}