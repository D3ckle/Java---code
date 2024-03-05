////entire class is not my work: obtained from:
//https://www.instructables.com/Making-a-Basic-3D-Engine-in-Java/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture{
	public int[] pixels;
	private String loc;
	public final int SIZE;

	public Texture(String location, int size) { // constructor
		loc = location;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() { // converts images into int values to then use to print each pizel on the wall
		try {
			BufferedImage image = ImageIO.read(new File(loc));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); //-------------------------- this is the problem
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Texture defWall = new Texture("Images/stone.png", 64); //64x64 // so far only .png or .jpg work
    public static Texture mouradov = new Texture("Images/mouradov64.jpg", 64);// make sure size is big enough for the image, else it wont work

    public static Texture door = new Texture("Images/Door.png", 64);

    public static Texture puz5 = new Texture("Images/rev3.png", 64);
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		//System.out.println("You Clicked1");
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

}