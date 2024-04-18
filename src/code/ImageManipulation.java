package code;

import image.Pixel;
import image.APImage;
public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    private static APImage Keanu;
    public static void main(String[] args) {
        Keanu = new APImage("cyberpunk.jpg");
        Keanu.draw();
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        Keanu = new APImage(pathOfFile);
        int width = Keanu.getWidth();
        int height = Keanu.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int avg = getAverageColour(Keanu.getPixel(i, j));
                Pixel p = new Pixel(avg,avg,avg);
                Keanu.setPixel(i,j,p);
            }
        }
        Keanu.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int avg = (pixel.getRed() + pixel.getBlue() + pixel.getGreen())/3;
        return avg;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        Keanu = new APImage(pathOfFile);
        int width = Keanu.getWidth();
        int height = Keanu.getHeight();
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                int bw = bw(Keanu.getPixel(i,j));
                Pixel p = new Pixel(bw,bw,bw);
                Keanu.setPixel(i,j,p);
            }
        }
        Keanu.draw();
    }
    private static int bw(Pixel pixel){
        int avg = (pixel.getRed() + pixel.getBlue() + pixel.getGreen())/3;
        if(avg >= 128){
            return 255;
        }
        return 0;
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        Keanu = new APImage(pathToFile);
        int w = Keanu.getWidth();
        int h = Keanu.getHeight();
        boolean[][] border = new boolean[w][h]
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                int avg = getAverageColour(Keanu.getPixel(i,j));
                Pixel p;
                if(i>0){
                    int avg2 = getAverageColour(Keanu.getPixel(i-1,j));
                    if(Math.abs(avg-avg2) > threshold){
                        border[i][j] = true;
                        continue;
                    }
                }
                if(j<h-1){
                    int avg3 = getAverageColour(Keanu.getPixel(i,j+1));
                    if(Math.abs(avg - avg3) > threshold){
                        border[i][j] = true;
                        continue;
                    }
                }
                border[i][j] = false;
            }
        }
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                Pixel p;
                if(border[i][j]){
                    p = new Pixel(0,0,0);
                }else{
                    p = new Pixel(255,255,255);
                }
                Keanu.setPixel(i,j,p);
            }
        }
        Keanu.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        Keanu = new APImage(pathToFile);
        APImage temp = Keanu.clone();

        int w = Keanu.getWidth();
        int h = Keanu.getHeight();
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                Pixel p = temp.getPixel(i,j);
                Keanu.setPixel(w-i-1,j,p);
            }
        }
        Keanu.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        Keanu = new APImage(pathToFile);

        int h = Keanu.getHeight();
        int w = Keanu.getWidth();

        APImage temp = new APImage(h, w);
        for (int x = 0; x < w-1; x++){
            for (int y = 0; y < h-1; y++){
                temp.setPixel(884-y, x, Keanu.getPixel(x, y));
            }
        }
        temp.draw();

    }

}
