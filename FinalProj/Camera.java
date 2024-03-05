//modified from:
//https://www.instructables.com/Making-a-Basic-3D-Engine-in-Java/
//https://stackoverflow.com/questions/2941324/how-do-i-set-the-position-of-the-mouse-in-java
//https://www.geeksforgeeks.org/java-program-to-print-screen-resolution/

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class Camera implements KeyListener {
	public double xPos, yPos, xDir, yDir, xPlane, yPlane;
	public boolean left, right, forward, back, esc;
	public boolean openDoor = false;
	public boolean numpad = false;
	public double MOVE_SPEED = .09; // Movement Speed
	public final double ROTATION_SPEED = .04; // Mouse Sensitivity
	public boolean clicked;
	public Point[] doorCoords = { //
			new Point(4, 6), //
			new Point(10, 12), //
			new Point(19, 11), //
			new Point(25, 6), //
			new Point(23, 2), //
			new Point(15, 3),//
	};

	int closestDoor = -1;
	Game frame;
	NumPadScreen room1Lock;
	public boolean mouseUnlock;

	public Camera(double x, double y, double xd, double yd, double xp, double yp, Game frame1) {
		xPos = x;
		yPos = y;
		xDir = xd;
		yDir = yd;
		xPlane = xp;
		yPlane = yp;
		clicked = false;
		frame = frame1;
		room1Lock = new NumPadScreen("1234");

	}

	// get the middle of the screen
	public Dimension getCenterScreen() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		return size;
	}

	// get the mouse location
	public Point getMousePoint() {
		return MouseInfo.getPointerInfo().getLocation();
	}

	// to see how far the mouse is from the center of the screen
	public Point moveAngle(Point p) {
		int x = p.x;
		int y = p.y;
		Point temp = new Point((int) getCenterScreen().getWidth() / 2 - x, (int) getCenterScreen().getHeight() / 2 - y);
		return temp;
	}

	// move the mouse cursor to point
	public void moveMouse(Point p) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		// Search the devices for the one that draws the specified point.
		for (GraphicsDevice device : gs) {
			GraphicsConfiguration[] configurations = device.getConfigurations();
			for (GraphicsConfiguration config : configurations) {
				Rectangle bounds = config.getBounds();
				if (bounds.contains(p)) {
					// Set point to screen coordinates.
					Point b = bounds.getLocation();
					Point s = new Point(p.x - b.x, p.y - b.y);

					try {
						Robot r = new Robot(device);
						r.mouseMove(s.x, s.y);
					} catch (AWTException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		return;
	}

	public double getDistanceToDoor(int index) { 
		double distance = Math.sqrt(
				Math.pow(doorCoords[index].getX() - this.xPos, 2) + Math.pow(doorCoords[index].getY() - this.yPos, 2));
		return distance;
	}

	public int findClosestDoor() {
		double shortestDistance = 100000; // tracks the distance of the door with the shortest distance
		int shortestIndex = -1; // tracks the index of the door with the shortest distance
		for (int i = 0; i < doorCoords.length; i++) {
			if (doorCoords[i] != null) { // door doesnt exist anymore
				double distance = getDistanceToDoor(i);
				// System.out.println(distance);
				if ((int) (shortestDistance * 100) > (int) (distance * 100)) {
					shortestDistance = distance;
					shortestIndex = i;
				}
			}

		}
		return shortestIndex;
	}

	public void keyPressed(KeyEvent key) { // if the key is pressed or held, tell program that the action is allowed
		if ((key.getKeyCode() == KeyEvent.VK_A))
			left = true;
		if ((key.getKeyCode() == KeyEvent.VK_D))
			right = true;
		if ((key.getKeyCode() == KeyEvent.VK_W))
			forward = true;
		if ((key.getKeyCode() == KeyEvent.VK_S))
			back = true;

		if ((key.getKeyCode() == KeyEvent.VK_F) && getDistanceToDoor(closestDoor) <= 2) {
			openDoor = true;
			mouseUnlock = true;
		}

	}

	public void keyReleased(KeyEvent key) { // upon release, the action is stopped
		if ((key.getKeyCode() == KeyEvent.VK_A))
			left = false;
		if ((key.getKeyCode() == KeyEvent.VK_D))
			right = false;
		if ((key.getKeyCode() == KeyEvent.VK_W))
			forward = false;
		if ((key.getKeyCode() == KeyEvent.VK_S))
			back = false;
		if ((key.getKeyCode() == KeyEvent.VK_ESCAPE))
			esc = !esc;
	}

	public void mousePressed(MouseEvent e) {
		// implement is inside method
		// if (e.getX(), e.getY())

		// System.out.println("Test");
	}

	public void update(int[][] map) { // only updates when a key press/hold is done

		if (forward) {
			if (map[(int) (xPos + xDir * MOVE_SPEED)][(int) yPos] == 0) {
				xPos += xDir * MOVE_SPEED;
			}
			if (map[(int) xPos][(int) (yPos + yDir * MOVE_SPEED)] == 0)
				yPos += yDir * MOVE_SPEED;
		}
		if (back) {
			if (map[(int) (xPos - xDir * MOVE_SPEED)][(int) yPos] == 0)
				xPos -= xDir * MOVE_SPEED;
			if (map[(int) xPos][(int) (yPos - yDir * MOVE_SPEED)] == 0)
				yPos -= yDir * MOVE_SPEED;
		}
		if (left) {
			if (xDir > 0 && yDir > 0) { // positive, postive (first quadrant)
				if (map[(int) (xPos + (xDir - 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += (xDir - 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + Math.abs(yDir - 1) * MOVE_SPEED)] == 0)
					yPos += Math.abs(yDir - 1) * MOVE_SPEED;
			}

			if (xDir < 0 && yDir > 0) { // negative, postive (second quadrant)
				if (map[(int) (xPos + ((xDir + 1) * -1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += ((xDir + 1) * -1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + (yDir - 1) * MOVE_SPEED)] == 0)
					yPos += (yDir - 1) * MOVE_SPEED;
			}

			if (xDir < 0 && yDir < 0) { // negative, negative (third quadrant)
				if (map[(int) (xPos + (xDir + 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += (xDir + 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + ((yDir + 1) * -1) * MOVE_SPEED)] == 0)
					yPos += ((yDir + 1) * -1) * MOVE_SPEED;
			}

			if (xDir > 0 && yDir < 0) { // positive, negative (forth quadrant)
				if (map[(int) (xPos + Math.abs(xDir - 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += Math.abs(xDir - 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + (yDir + 1) * MOVE_SPEED)] == 0)
					yPos += (yDir + 1) * MOVE_SPEED;
			}

		}
		if (right) {
			if (xDir > 0 && yDir > 0) { // positive, postive (first quadrant)
				if (map[(int) (xPos + Math.abs(xDir - 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += Math.abs(xDir - 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + (yDir - 1) * MOVE_SPEED)] == 0)
					yPos += (yDir - 1) * MOVE_SPEED;
			}

			if (xDir < 0 && yDir > 0) { // negative, postive (second quadrant)
				if (map[(int) (xPos + (xDir + 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += (xDir + 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + Math.abs(yDir - 1) * MOVE_SPEED)] == 0)
					yPos += Math.abs(yDir - 1) * MOVE_SPEED;
			}

			if (xDir < 0 && yDir < 0) { // negative, negative (third quadrant)
				if (map[(int) (xPos + ((xDir + 1) * -1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += ((xDir + 1) * -1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + (yDir + 1) * MOVE_SPEED)] == 0)
					yPos += (yDir + 1) * MOVE_SPEED;
			}

			if (xDir > 0 && yDir < 0) { // positive, negative (forth quadrant)
				if (map[(int) (xPos + (xDir - 1) * MOVE_SPEED)][(int) yPos] == 0)
					xPos += (xDir - 1) * MOVE_SPEED;
				if (map[(int) xPos][(int) (yPos + ((yDir + 1) * -1) * MOVE_SPEED)] == 0)
					yPos += ((yDir + 1) * -1) * MOVE_SPEED;
			}
		}

		if (openDoor) {
			// map[(int) doorCoords[closestDoor].getX()][(int)
			// doorCoords[closestDoor].getY()] = 0;
			// frame.pause();
			// frame.add(room1Lock);
			openDoor = false;
			numpad = true;
			frame.addMouseListener(frame.roomLocks[this.findClosestDoor()].numPadMouseListener);

		}

		closestDoor = findClosestDoor();

		// if (right) {
		// if (yDir > .5)
		// xPos += MOVE_SPEED * 2;
		// if (xDir < -.5)
		// xPos -= MOVE_SPEED * 2;
		// yPos += MOVE_SPEED * 2;
		// }

		// if (left) {
		// if (yDir > .5)
		// xPos -= MOVE_SPEED * 2;
		// if (xDir < -.5)
		// xPos+= MOVE_SPEED * 2;
		// yPos -= MOVE_SPEED * 2;

		// }

		if (mouseUnlock == false) {
			// gets the point of where the mouse is
			Point mouse = new Point(moveAngle(getMousePoint()));

			// checks if mouse is to the right of screen
			if (mouse.x > 0) {
				double oldxDir = xDir;
				xDir = xDir * Math.cos(ROTATION_SPEED) - yDir * Math.sin(ROTATION_SPEED);
				yDir = oldxDir * Math.sin(ROTATION_SPEED) + yDir * Math.cos(ROTATION_SPEED);
				double oldxPlane = xPlane;
				xPlane = xPlane * Math.cos(ROTATION_SPEED) - yPlane * Math.sin(ROTATION_SPEED);
				yPlane = oldxPlane * Math.sin(ROTATION_SPEED) + yPlane * Math.cos(ROTATION_SPEED);
			}

			// checks if mouse is to the left of screen
			if (mouse.x < 0) {
				double oldxDir = xDir;
				xDir = xDir * Math.cos(-ROTATION_SPEED) - yDir * Math.sin(-ROTATION_SPEED);
				yDir = oldxDir * Math.sin(-ROTATION_SPEED) + yDir * Math.cos(-ROTATION_SPEED);
				double oldxPlane = xPlane;
				xPlane = xPlane * Math.cos(-ROTATION_SPEED) - yPlane * Math.sin(-ROTATION_SPEED);
				yPlane = oldxPlane * Math.sin(-ROTATION_SPEED) + yPlane * Math.cos(-ROTATION_SPEED);
			}

			// keeps mouse in center of the screen
			moveMouse(new Point((int) getCenterScreen().getWidth() / 2, (int) getCenterScreen().getHeight() / 2));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}