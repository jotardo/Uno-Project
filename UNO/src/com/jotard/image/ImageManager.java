package com.jotard.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageManager {

	private static final String IMG_DIRECTORY = "/image/";
	private static final String IMG_FILE_EXT = ".png";
	
	private static ImageManager instance;
	
	private ArrayList<Image> cacheList;
	private ArrayList<String> cacheIDList;

	private ImageManager() {
		cacheList = new ArrayList<>();
		cacheIDList = new ArrayList<>();
	}

	//singleton??
	public static ImageManager getInstance() {
		if (instance == null)
			instance = new ImageManager();
		return instance;
	}

	public Image getImage(String name) {
		if (cacheIDList.indexOf(name) >= 0)
			return cacheList.get(cacheIDList.indexOf(name));
		return null;
	}

	public Image getScaledImage(String name, double scaleMultiplier) {
		Image original = getImage(name);
		if (original == null)
			return null;
		return original.getScaledInstance((int) (original.getWidth(null) * scaleMultiplier), (int) (original.getHeight(null) * scaleMultiplier), Image.SCALE_SMOOTH);
	}

	public void loadAndCacheImage(String url, double scaleMultiplier) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getResourceAsStream(IMG_DIRECTORY + url + IMG_FILE_EXT));
		if (img != null) {
			int w = (int) (img.getWidth() * scaleMultiplier);
			int h = (int) (img.getHeight() * scaleMultiplier);
			cacheList.add(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
			cacheIDList.add(url);
		}
	}

	public void loadAndCacheImage(String url) throws IOException {
		loadAndCacheImage(url, 1);
	}

	public void clearImageCache() {
		cacheIDList.clear();
		cacheList.clear();
	}

}