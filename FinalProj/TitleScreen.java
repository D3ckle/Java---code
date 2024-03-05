import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class TitleScreen extends JPanel{
	JPanel panel;
	public boolean start = true;
	public boolean play = false;
	public boolean quit = false;
	
	public TitleScreen() {
		panel = new JPanel();
		//System.out.println("hi");
		this.setBounds(0, 0, 640, 480);
		this.setBackground(Color.blue);
	}
	
	public void start(Graphics g) { //creates the start screen for the game
		this.setBackground(Color.red);
		g.setColor(Color.blue); //sets the color to red
		Font font = new Font("Monospaced", Font.BOLD, 30); //creates a font object for the string
		g.setFont(font); //sets the font
		//TITLE
		g.drawString("Escape Room", 225, 100); //draws a string
		//WHITE PLAY BUTTON
		g.setColor(Color.white); 
		g.fillRect(225, 200, 200, 100); //creates a box for the play button
		g.fillRect(225, 325, 200, 100); //creates a box for the play button
		font = new Font("Monospaced", Font.PLAIN, 10); //new font
		g.setColor(Color.black);
		g.drawString("Play", 290, 260); //draws a string
//		g.drawString("Controls", 260, 440);
		//WHITE QUIT BUTTON
		g.setColor(Color.white); //sets the color to white
//		g.fillRect(175, 525, 400, 100); //creates a box for the quit button
		g.setColor(Color.black); //sets the color black
		g.drawString("Quit", 290, 385); //draws a string
		addMouseListener(new Mouse());
	}
	
	public void paintComponent(Graphics g) {
		start(g);
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
			if (start) {
				//225, 200, 200, 100
				if (e.getX() <= 425 && e.getX() >= 225 && e.getY() >= 200 && e.getY() <= 300) {
					play = true;
				}
				//225, 325, 200, 100
				if (e.getX() <= 425 && e.getX() >= 225 && e.getY() >= 325 && e.getY() <= 425) {
					quit = true;
				}
			}
		}
		
	}
	
}

