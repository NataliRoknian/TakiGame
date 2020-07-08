import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

//Image class
public class Img 
{
	private Image image;
	private int x, y, width, height;
	
	//constructor
	public Img(String path, int x, int y, int width, int height)
	{	
		image = new ImageIcon(this.getClass().getClassLoader().getResource(path)).getImage();
		setImgCords(x, y);
		setImgSize(width, height);
	}
	
	//draw image method
	public void drawImg(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, y, width, height, null);
	}
	
	//change image place method
	public void setImgCords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	//change image size method
	public void setImgSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	
	public void setImg(Image image)
	{
		image = image;
	}
	
	
	public void setImg(String path)
	{
    	image= new ImageIcon(this.getClass().getClassLoader().getResource(path)).getImage();
    }
	
	/**
     * Rotates an image. Actually rotates a new copy of the image.
     * 
     * @param img The image to be rotated
     * @param angle The angle in degrees
     * @return The rotated image
     */
	
    public Img rotate(double angle)
    {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = image.getWidth(null), h = image.getHeight(null);
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                * cos + w * sin);
        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
        Graphics2D g = bimg.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(toBufferedImage(image), null);
        g.dispose();
        this.image=toImage(bimg);
        return this;
    }
    
    /**
     * Creates an empty image with transparency
     * 
     * @param width The width of required image
     * @param height The height of required image
     * @return The created image
     */
    
    public static Image getEmptyImage(int width, int height)
    {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }
    
    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage) 
        {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }
    
    /**
     * Converts a given BufferedImage into an Image
     * 
     * @param bimage The BufferedImage to be converted
     * @return The converted Image
     */
    
    public static Image toImage(BufferedImage bimage)
    {
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
}

