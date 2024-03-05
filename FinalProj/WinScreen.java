import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class WinScreen extends JPanel{

	JPanel panel;
	public boolean youWin;
	public boolean win;
	public boolean quit;
	
	
	public WinScreen() {
		panel = new JPanel();
		this.setBounds(0, 0, 640, 480);
		this.setBackground(Color.blue);
		youWin = true;
		win = false;
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.cyan); //sets the color to red
		Font font = new Font("Monospaced", Font.BOLD, 30); //creates a font object for the string
		g.setFont(font); //sets the font
		//TITLE
		g.drawString("You Won!", 250, 110); //draws a string
		g.drawString("Thanks For Playing!", 150, 150); //draws a string
		
		//WHITE QUIT BUTTON
		font = new Font("Monospaced", Font.PLAIN, 50); //new font
		g.setFont(font);
		g.setColor(Color.white); //sets the color to white
		g.fillRect(125, 200, 400, 100); //creates a box for the quit button
		g.setColor(Color.black); //sets the color black
		g.drawString("Quit", 260, 265); //draws a string
		this.setBackground(Color.yellow);
		addMouseListener(new Mouse());
	}
	
	public class Mouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
//			System.out.println("test1");
			if (youWin) {
				//125, 200, 400, 100
				if (e.getX() <= 525 && e.getX() >= 125 && e.getY() >= 200 && e.getY() <= 300) {
					win = true;
					youWin = false;
//					System.out.println("test2");
					//System.out.println("Play");
					//System.out.println("Play Variable:" + play);
				}
//				
//				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625) {
//					start = false;
//					play = false;
//					quit = true;
//				}
			}
		}
		
	}
}