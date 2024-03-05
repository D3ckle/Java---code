//modified from:
//https://www.instructables.com/Making-a-Basic-3D-Engine-in-Java/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

//public class Game extends JFrame { 
public class Game extends JFrame implements Runnable, MouseListener { // parent class; takes camera, screen, to create the map

	private static final long serialVersionUID = 1L; // ???
	public int mapWidth = 15; // curretly does nothing
	public int mapHeight = 15; // currently does nothing
	private Thread thread; // allows multiple things to accur at once
	public boolean running; // if the program is running or not
	private BufferedImage image; // creating a single image with all the other images on it
	public int[] pixels; // arr of int values that give the number of
	public ArrayList<Texture> textures; // array list holding all the images for the walls
	public Camera camera; // camera class handling the "user / player", key inputs, mouse, math dealing
							// with all that
	public Screen screen; // screen class handling the screen and what you can see
	public JLabel text;
	public TitleScreen title; // object holding the title screen
	public WinScreen win; // // object holding the winning creen
	public boolean pause = false; // pause switch to allow the switching between keypad and normal walking; thread
									// must be paused because you can't stop it
	public NumPadScreen room1Lock;
	public NumPadScreen[] roomLocks = { // array storing all the locks in the program
			new NumPadScreen("0"), // puzzle 1
			new NumPadScreen("14"), // puzzle2
			new NumPadScreen("10"), // puzzle 3
			new NumPadScreen("3"), // puzzle 4
			new NumPadScreen("3"), // puzzle 5
			new NumPadScreen("24") }; // puzzle 6
	public static int[][] map = { // 0 = spaces, anything bigger than 0 is a wall, think of this as the top down
									// of the map
									// different numbers represents the different textures to use; could ad more if
									// needed
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1 }, //
			{ 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1 }, //
			{ 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, //
			{ 1, 2, 1, 3, 2, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1 }, //
			{ 2, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 2, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1 }, //
			{ 2, 0, 0, 0, 0, 2, 1, 0, 0, 1, 0, 0, 0, 2, 1 }, //
			{ 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1 }, //
			{ 1, 2, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 3, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1 }, //
			{ 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } // x is up-down, y is left - right
															// possible
															// to
															// have
															// an
															// unbounded
															// map,
															// but
															// impossible
															// to
															// look
															// at it
	}; // possible to walk out of bounds this way
		// spawning on top of a wall removes player ability to move
		// negative numbers create a wall but you can see through it // barrier block
		// from minecraft?

	public Game() {
		thread = new Thread(this);// from runnable
		image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB); // gets the RGB values of the pixels from the screen class
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		textures = new ArrayList<Texture>(); // contruct the array of textures to be used for the walls etc.
		textures.add(Texture.defWall); // 1
		textures.add(Texture.mouradov);// 2
		textures.add(Texture.door);// 3
		textures.add(Texture.puz5);// 4
		text = new JLabel();
		//text.setText("Press F to Open Door");

		camera = new Camera(2.5, 2.5, 1, 0, 0, -.66, this); // spawn x location, spawn y location, x direction facing, y
		// direction facing, distortion x, distortiony ,

		screen = new Screen(map, mapWidth, mapHeight, textures, 640, 480); // dont change those last 2
																			// numbers...
		addKeyListener(camera);
		// addMouseListener(textures.get(4));

		setSize(640, 480); // size of window
		setResizable(false); // can not change window size
		setTitle("ESCAPE ROOM"); // title of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //binds window close with ending program
		setBackground(Color.black); // changing the default color to black
		setLocationRelativeTo(null); // puts window in the middle of the screen
		setVisible(true); // window can be seen

		title = new TitleScreen(); // creating the title page
		win = new WinScreen(); // creating the win screen
		room1Lock = new NumPadScreen("123456");
		start(); //starting the game itself
	}

	private synchronized void start() {
		running = true;
		thread.start();
	}

	public synchronized void stop() { // ends the program
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pause() { // pauses the thread
		pause = true;
		running = false;
	}

	public void unpause() { // resumes the thread
		pause = false;
		running = true;
	}

	public void render() { //does most of the actual rendering of the game
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		if (camera.findClosestDoor() == -1 && !win.win) { // final win screen pop up
			//System.out.println("1");
			camera.mouseUnlock = true;
			win.win = true;
			addMouseListener(this);
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if (!win.win) {
			if (!camera.numpad) {
				g.drawImage(image, 0, 0, null);

			} else {
				roomLocks[camera.findClosestDoor()].paintComponent(g);
				if (camera.xDir > .25 && camera.xDir <= 1 && camera.yDir > -.7 && camera.yDir < .8 && (camera.findClosestDoor() == 4)) {
					g.setColor(roomLocks[0].brown);
					g.fillRect(50, 200, 70, 70);
					g.drawImage(roomLocks[0].lockClosed, 50, 200, null);
					roomLocks[camera.findClosestDoor()].paintComponent(g);
				} else if ((camera.xDir < .25 || camera.xDir > 1 || camera.yDir < -.7 || camera.yDir > .8) && (camera.findClosestDoor() == 4)) {
					roomLocks[camera.findClosestDoor()].lock = true;
					roomLocks[camera.findClosestDoor()].paintComponent(g);
				}
			}
			if (camera.findClosestDoor() >= 0) { // interacts with the door
				if (roomLocks[camera.findClosestDoor()] != null && roomLocks[camera.findClosestDoor()].back) { //door is there
					camera.numpad = false;
					removeMouseListener(roomLocks[camera.findClosestDoor()].numPadMouseListener);// unbinds the keys
					roomLocks[camera.findClosestDoor()].back = false;
					if (!roomLocks[camera.findClosestDoor()].lock) {
						map[(int) camera.doorCoords[camera.closestDoor]
									.getX()][(int) camera.doorCoords[camera.closestDoor].getY()] = 0;// removes the wall
						camera.doorCoords[camera.findClosestDoor()] = null;// deletes the door object
					}
					camera.mouseUnlock = false; // giving some of the functionality back once you leave the lock screen
				}
			}
		}else if (win.win){
			camera.mouseUnlock = true;
			g.setColor(Color.yellow);
			g.fillRect(0, 0, 640, 480);
			win.paintComponent(g);
		}
		
		
		bs.show();
		

	}

	public void run() { // updates the screen
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;// 60 times per second
		double delta = 0; // number of times the program has to update
		requestFocus();

		add(title);
//		setBackground(room1Lock.brown);
//		add(room1Lock);
//		add(win);
//		setBackground(Color.yellow);	

		while (running) {

//			if (camera.openDoor) {
//				stop();
////				add(room1Lock);
////				System.out.println(camera.openDoor);
//			}

			if (camera.esc == true) { // because mouse is bounded to the middle, esc key is included to leave the program / game at any point
				System.exit(0);
			}
			// System.out.println(win.win);
			if (win.quit) { // if the player has reached the end
				System.exit(0);
			}

			if (title.quit && title.start) { //if quit is pressed on the title screen to leave
				title.start = false;
				System.exit(0);
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (title.play) { // continues with the porgram after the play button is pressed
				title.start = false; // stops showing the title screen
				remove(title); // removes the title
				long now = System.nanoTime();
				delta = delta + ((now - lastTime) / ns); //counts how many times the program must refresh 
				lastTime = now;
				while (delta >= 1)// Make sure update is only happening 60 times a second
				{
					// handles all of the logic restricted time

					screen.update(camera, pixels);
					camera.update(map);

//					if (camera.getDistanceToDoor(camera.closestDoor) <= 1) {
//						
//					}

					// System.out.println(textures.size());
//					System.out.println("x = " + camera.xPos);
//					System.out.println("y = " + camera.yPos);
//					if (camera.xPos >=2 && camera.xPos< 3 && camera.yPos >= 2 && camera.yPos < 3)
					// System.out.println(camera.xDir + " , " + camera.yDir);
					delta--;
				}

				render();// displays to the screen unrestricted time

			}

		}

	}

	public class Mouse implements MouseListener {

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
			if (room1Lock.code.length() <= room1Lock.length) {
				// first row of numpad
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 100 && e.getY() <= 200) {
					roomLocks[camera.findClosestDoor()].code += "1";
					// System.out.println(count);
					repaint();
				}
				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 100 && e.getY() <= 200) {
					roomLocks[camera.findClosestDoor()].code += "2";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 100 && e.getY() <= 200) {
					roomLocks[camera.findClosestDoor()].code += "3";
					repaint();
				}

				// second row
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 200 && e.getY() <= 300) {
					roomLocks[camera.findClosestDoor()].code += "4";
					repaint();
				}

				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 200 && e.getY() <= 300) {
					roomLocks[camera.findClosestDoor()].code += "5";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 200 && e.getY() <= 300) {
					roomLocks[camera.findClosestDoor()].code += "6";
					repaint();
				}

				// third row
				if (e.getX() <= 275 && e.getX() >= 175 && e.getY() >= 300 && e.getY() <= 400) {
					roomLocks[camera.findClosestDoor()].code += "7";
					repaint();
				}
				if (e.getX() <= 375 && e.getX() >= 275 && e.getY() >= 300 && e.getY() <= 400) {
					roomLocks[camera.findClosestDoor()].code += "8";
					repaint();
				}
				if (e.getX() <= 475 && e.getX() >= 375 && e.getY() >= 300 && e.getY() <= 400) {
					roomLocks[camera.findClosestDoor()].code += "9";
					repaint();
				}
				if (e.getX() <= 575 && e.getX() >= 475 && e.getY() >= 300 && e.getY() <= 400) {
					roomLocks[camera.findClosestDoor()].code += "0";
					repaint();
				}

				// BACK
				// 0, 380, 100, 70
				if (e.getX() <= 100 && e.getX() >= 0 && e.getY() >= 380 && e.getY() <= 480) {

					repaint();
				}

				// ENTER
				if (e.getX() <= 575 && e.getX() >= 475 && e.getY() >= 100 && e.getY() <= 300) {
					if (roomLocks[camera.findClosestDoor()].realCode.equals(roomLocks[camera.findClosestDoor()].code)) {

						roomLocks[camera.findClosestDoor()].lock = false;
					}

					roomLocks[camera.findClosestDoor()].code = "";
					repaint();

				}
			}
		}

	}

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
		if (win.win && e.getX() <= 525 && e.getX() >= 125 && e.getY() >= 200 && e.getY() <= 300)
			win.quit = true;
	}

}