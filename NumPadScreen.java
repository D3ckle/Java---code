import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NumPadScreen extends JPanel {

	JPanel panel;
	public boolean mouseUnlock = true; // true while on the screen
	public boolean lock = true; // true = locked, false = unlocked (refers to the lock for the door)
	public boolean back = false;
	Image lockClosed, lockOpen;
	NumPadMouse numPadMouseListener;
	Color brown;
	public int length;
	public String realCode;
	public String code;

	public NumPadScreen(String actualCode) {
		panel = new JPanel();
		brown = new Color(153, 102, 0);
		this.setBounds(0, 0, 640, 480);
		this.setBackground(brown);
		realCode = actualCode;
		code = "";
		length = actualCode.length();
		try {
			lockClosed = ImageIO.read(new File("lockClosedClear.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lockOpen = ImageIO.read(new File("lockOpenClear.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// addMouseListener(new NumPadMouse()); //adding listener
		numPadMouseListener = new NumPadMouse();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		// background
		g.setColor(brown);
		g.fillRect(0, 0, 640, 480);

		// back button
		g.setColor(Color.white);
		g.fillRect(0, 380, 100, 70);
		Font font = new Font("Monospaced", Font.BOLD, 30); // creates a font object for the string
		g.setFont(font); // sets the font
		g.setColor(Color.black);
		g.drawString("BACK", 13, 420);

		// actual numpad
		g.setColor(Color.white);
		g.fillRect(175, 100, 400, 300);

		// black bar
		g.setColor(Color.black);
		g.fillRect(175, 25, 400, 75);
		g2.setColor(brown);

		// changing thickness for separators
		g2.setStroke(new BasicStroke(10));

		// vertical separators on numpad
		g2.drawLine(275, 105, 275, 400);
		g2.drawLine(375, 105, 375, 400);
		g2.drawLine(475, 105, 475, 400);

		// horizontal separators on numpad
		g2.drawLine(175, 200, 475, 200);
		g2.drawLine(175, 300, 575, 300);

		// the numbers
		g.setColor(Color.black);
		font = new Font("Monospaced", Font.BOLD, 50); // creates a font object for the string
		g.setFont(font); // sets the font

		// first row
		g.drawString("1", 208, 160);
		g.drawString("2", 312, 160);
		g.drawString("3", 412, 160);

		// second row
		g.drawString("4", 208, 265);
		g.drawString("5", 310, 265);
		g.drawString("6", 410, 265);

		// third row
		g.drawString("7", 208, 367);
		g.drawString("8", 310, 367);
		g.drawString("9", 410, 367);
		g.drawString("0", 512, 367);

		// ENTER button
		font = new Font("Monospaced", Font.BOLD, 30); // creates a font object for the string
		g.setFont(font); // sets the font
		int x = 518;
		int yStep = 20;
		int yBase = 145;

		// IMAGES
		if (lock)
			g.drawImage(lockClosed, 50, 200, null);
		else {
			g.setColor(brown);
			g.fillRect(50, 200, 70, 70);
			g.drawImage(lockOpen, 18, 193, null);
		}

		// ENTER
		g.setColor(Color.black);
		g.drawString("E", x, yBase + yStep);
		g.drawString("N", x, yBase + 2 * yStep);
		g.drawString("T", x - 1, yBase + 3 * yStep);
		g.drawString("E", x, yBase + 4 * yStep);
		g.drawString("R", x, yBase + 5 * yStep);

		font = new Font("Monospaced", Font.BOLD, 50); // creates a font object for the string
		g.setFont(font); // sets the font
		g.setColor(Color.green);
		g.drawString(code, 188, 78);

	}

	public boolean getMouseUnlock() { // getter for the mouse (un)lock variable
		return mouseUnlock;
	}

	public class NumPadMouse implements MouseListener {

		int count = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 100 && e.getY() <= 200) {
//				code += "1";
//				System.out.println(count);
//				repaint();
//			}
//			if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 100 && e.getY() <= 200) {
//				code += "2";
//				count++;
//				repaint();
//			}	
//			if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 100 && e.getY() <= 200) {
//				code += "3";
//				count++;
//				repaint();
//			}
//			//second row
//			if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 200 && e.getY() <= 300) {
//				code += "4";
//				count++;
//				repaint();
//			}
//			if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 200 && e.getY() <= 300) {
//				code += "5";
//				count++;
//				repaint();
//			}
//			if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 200 && e.getY() <= 300) {
//				code += "6";
//				count++;
//				repaint();
//			}
//			//third row
//			if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 300 && e.getY() <= 400) {
//				code += "7";
//				count++;
//				repaint();
//			}
//			if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 300 && e.getY() <= 400) {
//				code += "8";
//				count++;
//				repaint();
//			}
//			if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 300 && e.getY() <= 400 && count == 0) {
//				code += "9";
//				count++;
//				repaint();
//			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
//			count = 0;
//			if (count == 0) {
//				count++;
//				System.out.println("aweuibfguoqef");
//			}
			// System.out.println("MousePressed");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

//			//first row
//			g.drawString("1", 208, 160);
//			g.drawString("2", 312, 160);
//			g.drawString("3", 412, 160);
//			
//			//second row
//			g.drawString("4", 208, 265);
//			g.drawString("5", 310, 265);
//			g.drawString("6", 410, 265);
//			
//			//third row
//			g.drawString("7", 208, 367);
//			g.drawString("8", 310, 367);
//			g.drawString("9", 410, 367);

//			//vertical separators on numpad
//			g2.drawLine(275, 105, 275, 400);
//			g2.drawLine(375, 105, 375, 400);
//			
//			//horizontal separators on numpad
//			g2.drawLine(175, 200, 475, 200);
//			g2.drawLine(175, 300, 475, 300);

			// 175, 100, 300, 300

			if (code.length() <= length) {
				// first row of numpad
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 100 && e.getY() <= 200) {
					code += "1";
					// System.out.println(count);
					repaint();
				}
				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 100 && e.getY() <= 200) {
					code += "2";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 100 && e.getY() <= 200) {
					code += "3";
					repaint();
				}
				// second row
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 200 && e.getY() <= 300) {
					code += "4";
					repaint();
				}
				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 200 && e.getY() <= 300) {
					code += "5";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 200 && e.getY() <= 300) {
					code += "6";
					repaint();
				}
				// third row
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 300 && e.getY() <= 400) {
					code += "7";
					repaint();
				}
				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 300 && e.getY() <= 400) {
					code += "8";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 300 && e.getY() <= 400) {
					code += "9";
					repaint();
				}
				if (e.getX() <= 575 && e.getX() >= 475 && e.getY() >= 300 && e.getY() <= 400) {
					code += "0";
					repaint();
				}
			}
			// BACK
			// 0, 380, 100, 70
			if (e.getX() <= 100 && e.getX() >= 0 && e.getY() >= 380 && e.getY() <= 480) {
				back = true;
				repaint();
			}
			// ENTER
			if (e.getX() <= 575 && e.getX() >= 475 && e.getY() >= 100 && e.getY() <= 300) {

				// System.out.println(realCode + ", " + code);
				if (realCode.equals(code)) {
					lock = false;
				}
				code = "";
				repaint();
			}
			// g.drawString("0", 512, 367);
			// g2.drawLine(475, 105, 475, 400);
			// g2.drawLine(175, 300, 575, 300);
		}

	}

}