import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorPanel extends JPanel { // extends the JavaPanel object which already exists
	private int w, h, x, y;
	private Color bgColor;
	double xspeed, yspeed;
	final static double acel = 1.04;
	private double ceiling;

	public ColorPanel(Color c, int width, int height, int x, int y, double xs, double ys) {
		bgColor = c;
		setBackground(bgColor);
		w = width; // witdh of window
		h = height; // h of window
		setSize(w, h);
		xspeed = xs;
		yspeed = ys;
		ceiling = y;
		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(w, 0, w, h);
		g.drawLine(0, h, w, h);
		g.setColor(Color.black);

		drawBall(g);
		movex();
		movey();
		// g.fillOval(x, y, 5, 5);
	}

	public void drawBall(Graphics g) {
		g.setColor(Color.black);
		
		//if (y <= h - 50)
			//g.drawOval(x, y, 50, 20);	
		//else
		g.drawOval(x, y, 50, 50);
		// ball compression on wall hit; more like an oval sgape when it does
	}

	public void movex() { // left and right movement
		if (x >= w - 50)
			xspeed *= -1;
		// System.out.println(x + ", " + w);
		if (x <= 0)
			xspeed *= -1;
		x += xspeed;
		//xspeed/= acel;
	}

	public void movey() { // add acelity // boolean for up and down; make it go up and down less; ball
							// will eventually reach 0 and not bounce anymore
		//System.out.println(y);
		if (yspeed <= 0) {// going up
			//yspeed /= acel; // reduce in speed // this... isnt how gravity works i guess
			// if (yspeed <= .5) {// reach the peak, start going down
				//// yspeed *= -1;
				// 
			// }
		} else {// going down
			yspeed *= acel; // increase in speed
		}
		
		if (y >= h - 50) { // floor
			y = h-50;
			yspeed *= -.1; // this is where im reducing speed
			ceiling+= 100; // reduce ceiling; goes up less each bounce
			if (ceiling > 550) // prevent going out of bounds;  
				ceiling = 549;//keeps ball from disappearing
		}
		
		if (y <= ceiling) { // top
			y= (int)ceiling;
			yspeed *= -1;
			//ceiling += 50;
		}
		y += yspeed;
		//System.out.println(yspeed);

	}
}
