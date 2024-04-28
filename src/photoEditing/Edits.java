package photoEditing;

public class Edits {
	/*
	 * Provides static edit methods.
	 */
	
	
	/*
	 * takes image's ImageMatrix and an int which is used for blur amount
	 * Uses aroundAverage to find average value of pixels around spesific pixel.
	 * returns blurred image ImageMatrix 
	 */
	public static ImageMatrix blur(ImageMatrix img, int x) {
		int width = img.getWidth();
		int height = img.getHeight();
		int bound = (int)(x/2);
		
		int[][] rendered = new int[width][height];
		for (int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				if (i < bound || i > rendered.length - bound -1 || j < bound || j > rendered[0].length - bound -1) {
					rendered[i][j] = img.getRGB(i, j);
					continue;
				}
				rendered[i][j] = aroundAverage(img, i, j, x);
			}
		}
		ImageMatrix ret = new ImageMatrix(width, height);
		
		for (int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				ret.setRGB(i, j, rendered[i][j]);
			}
		}
		
		return ret;
	}
	
	
	/*
	 * takes image's ImageMatrix
	 * Uses blur to find averege and then details.
	 * returns sharpened image ImageMatrix 
	 */
	public static ImageMatrix sharpen(ImageMatrix img) {
		int width = img.getWidth();
		int height = img.getHeight();
		ImageMatrix sharpened = new ImageMatrix(width, height);  //return Matrix

		ImageMatrix averaged = Edits.blur(img, 5);
		
		//img - average = details
		//img + details = sharpened
		//img + img - average = sharpened
		//so sharpened = 2*img - average
		
		for(int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				int red = 2*img.getRed(i, j) - averaged.getRed(i, j);
				if (red > 255) red = 255;
				else if (red < 0) red = 0;
				
				int green = 2*img.getGreen(i, j) - averaged.getGreen(i, j);
				if (green > 255) green = 255;
				else if (green < 0) green = 0;
				
				int blue = 2*img.getBlue(i, j) - averaged.getBlue(i, j);
				if (blue > 255) blue = 255;
				else if (blue < 0) blue = 0;
				
				sharpened.setRGB(i, j, ImageMatrix.convertRGB(red, green, blue));
			}
		}
		
		return sharpened;
	}
	
	/*
	 * takes image's ImageMatrix, i, j coordinate values and an int for understanding the size of the average square
	 * returns average value for pixel at location at i,j 
	 */
	private static int aroundAverage(ImageMatrix img, int i, int j, int x) {
		int bound = (int)(x/2);
		
		int redRet = 0;
		int greenRet = 0;
		int blueRet = 0;
		for (int a = -bound; a <= bound; a++) {
			for(int b = -bound; b <= bound; b++) {
				redRet += img.getRed(i+a, j+b);
				greenRet += img.getGreen(i+a, j+b);
				blueRet += img.getBlue(i+a, j+b);
			}
		}
		redRet = Math.round(redRet/(x*x));
		greenRet = Math.round(greenRet/(x*x));
		blueRet = Math.round(blueRet/(x*x));
		
//		
//		int redRet = 0;
//		redRet += img.getRed(i,j) + img.getRed(i+1, j) + img.getRed(i-1, j) + img.getRed(i, j+1) + img.getRed(i, j-1);
//		redRet += img.getRed(i+1, j+1) + img.getRed(i+1, j-1) + img.getRed(i-1, j+1) + img.getRed(i-1, j-1);
//		redRet = Math.round(redRet/9);
//		
//		int greenRet = 0;
//		greenRet += img.getGreen(i,j) + img.getGreen(i+1, j) + img.getGreen(i-1, j) + img.getGreen(i, j+1) + img.getGreen(i, j-1);
//		greenRet += img.getGreen(i+1, j+1) + img.getGreen(i+1, j-1) + img.getGreen(i-1, j+1) + img.getGreen(i-1, j-1);
//		greenRet = Math.round(greenRet/9);
//		
//		int blueRet = 0;
//		blueRet += img.getBlue(i,j) + img.getBlue(i+1, j) + img.getBlue(i-1, j) + img.getBlue(i, j+1) + img.getBlue(i, j-1);
//		blueRet += img.getBlue(i+1, j+1) + img.getBlue(i+1, j-1) + img.getBlue(i-1, j+1) + img.getBlue(i-1, j-1);
//		blueRet = Math.round(blueRet/9);
		
		int ret = ImageMatrix.convertRGB(redRet, greenRet, blueRet);
		
		return ret;
	}
	
	
	/*
	 * takes image's ImageMatrix and a scale.
	 * returns grayScaled image ImageMatrix 
	 */
	public static ImageMatrix grayScale(ImageMatrix img, double scale) {
		/*
		 * scale should be between 0 and 1
		 */
		int width = img.getWidth();
		int height = img.getHeight();
		ImageMatrix scaled = new ImageMatrix(width, height);  //return Matrix
		
		double gray = 0;
		int num = 0;
		for(int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				gray = (img.getRed(i, j) + img.getGreen(i, j) + img.getBlue(i, j))/3;
				gray = gray * scale;
				
				num = (int) Math.round(gray);
				scaled.setRGB(i, j, ImageMatrix.convertRGB(num, num, num));
			}
		}
		
		return scaled;
	}
	
	
	/*
	 * takes image's ImageMatrix and a scale.
	 * returns Bright image ImageMatrix 
	 */
	public static ImageMatrix brightness(ImageMatrix img, int val) {
		int width = img.getWidth();
		int height = img.getHeight();
		ImageMatrix ret = new ImageMatrix(width, height);  //return Matrix
		
		for(int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				
				int red = img.getRed(i, j) + val;
				if (red > 255) red = 255;
				else if (red < 0) red = 0;
				
				int green = img.getGreen(i, j) + val;
				if (green > 255) green = 255;
				else if (green < 0) green = 0;
				
				int blue = img.getBlue(i, j) + val;
				if (blue > 255) blue = 255;
				else if (blue < 0) blue = 0;
				
				ret.setRGB(i, j, ImageMatrix.convertRGB(red, green, blue));
			}
		}
		return ret;
	}
	
	/*
	 * takes image's ImageMatrix and a value
	 * returns contrast image ImageMatrix 
	 * 
	 * note: I find that contrast formula from web and then coded. I won't expect it to be worked :D
	 */
	public static ImageMatrix contrast(ImageMatrix img, double val) {
		int width = img.getWidth();
		int height = img.getHeight();
		ImageMatrix ret = new ImageMatrix(width, height);  //return Matrix
		
		for(int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				
				int red = (int) Math.round((img.getRed(i, j) - 128)*val + 128);
				if (red > 255) red = 255;
				else if (red < 0) red = 0;
				
				int green = (int) Math.round((img.getGreen(i, j) - 128)*val + 128);
				if (green > 255) green = 255;
				else if (green < 0) green = 0;
				
				int blue = (int) Math.round((img.getBlue(i, j) - 128)*val + 128);
				if (blue > 255) blue = 255;
				else if (blue < 0) blue = 0;
				
				ret.setRGB(i, j, ImageMatrix.convertRGB(red, green, blue));
			}
		}
		return ret;
	}
	
	
	/*
	 * takes image's ImageMatrix and a treshold
	 * returns edge detected image ImageMatrix 
	 * 
	 * explanation: For pixels whis are not at the borders I calculate their x and y directional derivative and then I calculate the magnitude of the gradient while using them.
	 * at last assigned pixels which has a gradient magnitude bigger than or equal to treshold, white others black.
	 */
	public static ImageMatrix edgeDetection(ImageMatrix img, int treshold) {
		/*
		 * treshold around 50
		 */
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		ImageMatrix blur3 = blur(img, 3);
		ImageMatrix grayScaled = grayScale(blur3, 1);
		ImageMatrix ret = new ImageMatrix(width, height); 
		
		for (int i = 0; i < width-1; i++) {
			for (int j = 0; j < height-1; j++) {
				if (i < 1 || i > ret.getWidth() - 2 || j < 1 || j > ret.getHeight() - 2) {
					ret.setRGB(i, j, grayScaled.getRGB(i, j));
					continue;
				}
				// since all rgb values are same by grayscale operation
				double gx = grayScaled.getRed(i+1, j)*2 + grayScaled.getRed(i-1, j)*-2 + grayScaled.getRed(i+1, j+1) + grayScaled.getRed(i+1, j-1) + grayScaled.getRed(i-1, j+1)*-1 + grayScaled.getRed(i-1, j-1)*-1;
				double gy = grayScaled.getRed(i, j+1)*-2 + grayScaled.getRed(i, j-1)*2 + grayScaled.getRed(i+1, j+1)*-1 + grayScaled.getRed(i-1, j+1)*-1 + grayScaled.getRed(i+1, j-1) + grayScaled.getRed(i-1, j-1);
				
				int magn = (int) Math.round(Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2)));
				
				if (magn >= treshold) {
					ret.setRGB(i, j, ImageMatrix.convertRGB(230, 230, 230));
				}
				else {
					ret.setRGB(i, j, ImageMatrix.convertRGB(0, 0, 0));
				}
				
			}
		}
		return ret;
		
	}
	
	
}
