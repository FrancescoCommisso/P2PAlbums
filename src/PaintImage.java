import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class PaintImage extends JPanel
{
    public static BufferedImage image;

    public PaintImage ()
    {
        super();
        try
        {
            image = ImageIO.read(new File("/Users/Francesco/Desktop/School/ComputerNetworks/Project/P2PAlbum/assets/dog.jpeg"));
        }
        catch (IOException e)
        {
            //Not handled.
        }
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
        repaint();
    }

    public static void main(String [] args)
    {
        JFrame f = new JFrame("Window");
        f.add(new PaintImage());
        f.setSize(image.getWidth(), image.getHeight() + 30);
        f.setVisible(true);
    }
}