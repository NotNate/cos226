import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Picture;

import java.math.BigInteger;

public class CreatePhoto {
    private Picture picture;

    public CreatePhoto() {
        this.picture = null;
    }

    public Picture getPicture() {
        return picture;
    }

    private int hextoRGB(String hex) {
        BigInteger rgb = new BigInteger(hex, 16);
        return rgb.intValue();
    }

    public Picture constructPhoto(String[] args) {
        In stream = new In(args[0]);
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        Picture photo = new Picture(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                String hex = stream.readString();
                int rgb = hextoRGB(hex);
                photo.setRGB(col, row, rgb);
            }
        }
        picture = photo;
        return photo;
    }

    public static void main(String[] args) {
    }
}
