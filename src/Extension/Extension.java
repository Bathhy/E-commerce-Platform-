package Extension;

import constant.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Extension {
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Resize the image
        return new ImageIcon(resizedImage);
    }

    //    public void setLogoAppilcation(URL logourl){
//
//        try {
//            logourl = new URL(Constant.imgurl);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            setIconImage(ImageIO.read(logourl));
//        } catch (IOException | RuntimeException e) {
//            e.printStackTrace();
//        }
//    }
    public static void setFrameIcon(JFrame frame, String imageUrl) {
        try {
            URL logourl = new URL(imageUrl); // Create URL object from the image URL string
            Image iconImage = ImageIO.read(logourl); // Read the image from the URL
            frame.setIconImage(iconImage); // Set the icon image for the frame
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + imageUrl); // Log error if URL is invalid
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Unable to load image from URL: " + imageUrl); // Log error if image cannot be loaded
            e.printStackTrace();
        }
    }
}
