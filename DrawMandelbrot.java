package eecs2030.lab3;

import princeton.introcs.Picture;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * A simple program for drawing the Mandelbrot set. When run with
 * the default settings the image is centered on the point (-0.5 + 0.0i),
 * the length of the sides of the image is 2, and the number of
 * iterations to check if a point remains bounded (i.e., is in the
 * Mandelbrot set) is 50. 
 * 
 * <p>
 * These values can be changed using command
 * line arguments: In eclipse, go to the Run menu, choose Run configurations,
 * find the Mandelbrot Java application, and in the Arguments tab enter
 * a new set of values (separated by spaces). For example, enter the following
 * four values to view Elephant Valley (view point (0.275 + 0.005i), side
 * length 0.01, 200 iterations):
 * 
 * <p>
 * 0.275 0.005 0.01 200
 * 
 * <p>
 * View points for other interesting areas of the Mandelbrot set can
 * be found <a href=http://www.nahee.com/Derbyshire/manguide.html#where>here</a>
 *
 */
public class DrawMandelbrot {

	/**
	 * Returns a colormap of 256 colors for assigning colors to points that
	 * are outside of the Mandelbrot set.
	 * 
	 * @return a 256 color colormap
	 */
	public static List<Color> makeColorMap() {
		final int NCOLORS = 256;
		List<Color> cmap = new ArrayList<Color>(NCOLORS);
		cmap.add(new Color(0, 0, 0));
		for (int i = 1; i < NCOLORS; i++) {
			final int R = 255;
			final int G = (7 * i) % NCOLORS;
			final int B = (5 * i) % NCOLORS;
			cmap.add(new Color(R, G, B));
		}
		return cmap;
	}



	public static void main(String[] args) {
		// default coordinates of the pixel at the center of the picture
		double xc = -0.5;
		double yc = 0;

		// default length of each side of the picture
		double size = 2;
		
		// default maximum number of iterations
		int max = 255;

		// use command line arguments if they are given
		if (args.length == 4) {
			xc = Double.parseDouble(args[0]);
			yc = Double.parseDouble(args[1]);
			size = Double.parseDouble(args[2]);
			max = Integer.parseInt(args[3]);
		}
		final int N = 256; // create N-by-N image

		List<Color> colorMap = makeColorMap();

		Picture pic = new Picture(N, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// compute the coordinates of pixel (i, j)
				double x0 = xc - size / 2 + size * i / N;
				double y0 = yc - size / 2 + size * j / N;
				Complex z0 = new Complex(x0, y0);
				
				// use (MAX - number of iterations) to index into a color map
				int gray = max - MandelbrotUtil.mandelbrotIterations(z0, max);
				Color color = colorMap.get(gray);
				pic.set(i, N - 1 - j, color);
			}
		}
		pic.show();
	}
}
