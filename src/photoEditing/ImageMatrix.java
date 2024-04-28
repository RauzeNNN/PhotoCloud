package photoEditing;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.UUID;

/**
 * ImageMatrix is used to represent images.
 * 
 * @author osman.yasal
 *
 */
public class ImageMatrix implements Serializable{

	private String id;
	private int[][] img;

	private int width;
	private int height;

	/**
	 * Builds up ImageMatrix from BufferedImage
	 * 
	 * @param BufferedImage image
	 * @see BufferedImage
	 */
	public ImageMatrix(BufferedImage image) {
		/*
		 * Input: BufferedImage
		 * Process: converts image to rgb pixel array.
		 */
		this.id = UUID.randomUUID().toString();
		this.img = convertImageToPixelArray(image);
		this.width = img.length;
		this.height = img[0].length;
	}

	public ImageMatrix(int width, int height) {
		/*
		 * Input: width, height
		 * Process: Creates empty array with given parameters. And assigns object values accordingly
		 */
		this.id = UUID.randomUUID().toString();
		this.img = generateEmptyImageArray(width, height);
		this.width = img.length;
		this.height = img[0].length;

	}

	private int[][] generateEmptyImageArray(int width, int height) {
		/*
		 * * Input: width, height
		 * Process: Creates empty array with given parameters.
		 */
		return new int[width][height];
	}

	private int[][] convertImageToPixelArray(BufferedImage image) {
		/*
		 * Input: image
		 * Output: 2d array with pixel values.
		 */
		int[][] im = new int[image.getWidth()][image.getHeight()];
		for (int i = 0; i < image.getWidth(); i++)
			for (int j = 0; j < image.getHeight(); j++) {
				im[i][j] = image.getRGB(i, j);
			}
		return im;
	}

	public BufferedImage getBufferedImage() {
		/*
		 * converts 2d array img to buffered image.
		 * Output: buffered image
		 */
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				bufferedImage.setRGB(i, j, img[i][j]);
			}
		}
		return bufferedImage;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRGB(int x, int y) {
		return img[x][y];
	}

	public int setRGB(int x, int y, int rgb) {
		img[x][y] = rgb;
		return rgb;
	}

	public int getRed(int x, int y) {
		return (img[x][y] >> 16) & 0xFF;
	}

	public int getGreen(int x, int y) {
		return (img[x][y] >> 8) & 0xFF;
	}

	public int getBlue(int x, int y) {
		return img[x][y] & 0xFF;
	}

	public static int convertRGB(int red, int green, int blue) {
		return (red << 16 | green << 8 | blue);
	}
}
