package seleniumTestNGinterview;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class CompareImageFiles {

	public static void main(String[] args) throws Exception {
		File fileInput = new File("D:\\Workspace\\MyStudy_Space\\FilePath\\GoogleInput.jpg");
		File fileOutPut = new File("D:\\Workspace\\MyStudy_Space\\FilePath\\GoogleOutput.jpg");
		BufferedImage inFile = ImageIO.read(fileInput);
		BufferedImage outFile = ImageIO.read(fileOutPut);

		compareImages(inFile, outFile);
	}

	public static boolean compareImages(BufferedImage img1, BufferedImage img2) {
		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}
