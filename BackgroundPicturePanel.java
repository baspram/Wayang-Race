import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class BackgroundPicturePanel extends JPanel
{
    Image image;
 
    public BackgroundPicturePanel()
    {
        loadImage();
        setBackground(Color.white);
    }
 
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        int x = (w - imageWidth)/2;
        int y = (h - imageHeight)/2;
        g.drawImage(image, x, y, this);
    }
 
    private void loadImage()
    {
        String fileName = "images/mgbg.jpg";
        try
        {
            URL url = getClass().getResource(fileName);
            image = ImageIO.read(url);
        }
        catch(MalformedURLException mue)
        {
            System.out.println("url: " + mue.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println("read: " + ioe.getMessage());
        }
    }
}
