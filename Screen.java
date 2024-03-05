//modified from:
//https://www.instructables.com/Making-a-Basic-3D-Engine-in-Java/

import java.util.ArrayList;
import java.awt.Color;

public class Screen {
	public int[][] map;
	public int mapWidth, mapHeight, width, height; // madwidth / height never used
	public ArrayList<Texture> textures;
	// public ArrayList<Item> ItemList;
	// public static boolean Door;

	public Screen(int[][] m, int mapW, int mapH, ArrayList<Texture> tex, int w, int h) {
		map = m;
		mapWidth = mapW;
		mapHeight = mapH;
		textures = tex;
		width = w;
		height = h;
		// ItemList = i;
	}

	public int[] update(Camera camera, int[] pixels) { // recalculates how the screen should look to the user based on
														// their position in the map
		for (int n = 0; n < pixels.length / 2; n++) { // ceiling pixels ; all made 1 color //top half
			if (pixels[n] != Color.gray.getRGB())
				pixels[n] = Color.gray.getRGB();
		}
		for (int i = pixels.length / 2; i < pixels.length; i++) { // floor pixels ; make all 1 color // bottom half
			if (pixels[i] != Color.DARK_GRAY.getRGB())
				pixels[i] = Color.DARK_GRAY.getRGB();
		}

		// prints images over the ceiling and floor to make it look like a room

		for (int x = 0; x < width; x++) { // for each pixel from top down ; vertical
			double cameraX = 2 * x / (double) (width) - 1; // x-coordinate of the current vertical stripe on the camera
															// plane
			double rayDirX = camera.xDir + camera.xPlane * cameraX; // ray is the direction looking (+x = left, - =
																	// right)
			double rayDirY = camera.yDir + camera.yPlane * cameraX; // variables make a vector for the ray
			// Map position
			int mapX = (int) camera.xPos; // (integerized) where you ae on the map x pos (each spot can be from
											// 0-.99999)
			int mapY = (int) camera.yPos;
			// length of ray from current position to next x or y-side;distance from nearest
			// wall in x direction, and y direction
			double sideDistX;
			double sideDistY;
			// Length of ray from one side to next in map
			double deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX)); // program only checks for
																							// collisions at the places
																							// where collisions could
																							// possibly occur
			double deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
			double perpWallDist;
			// Direction to go in x and y
			int stepX, stepY;
			boolean hit = false;// was a wall hit
			int side = 0;// was the wall vertical or horizontal
			// Figure out the step direction and initial distance to a side
			if (rayDirX < 0) { // looking right
				stepX = -1;
				sideDistX = (camera.xPos - mapX) * deltaDistX;
			} else {
				stepX = 1;
				sideDistX = (mapX + 1.0 - camera.xPos) * deltaDistX;
			}
			if (rayDirY < 0) {
				stepY = -1;
				sideDistY = (camera.yPos - mapY) * deltaDistY;
			} else {
				stepY = 1;
				sideDistY = (mapY + 1.0 - camera.yPos) * deltaDistY;
			}
			// Loop to find where the ray hits a wall
			while (!hit) { // checks each location until it hits a wall
				// this is why the game doesn't laod witohut a sepcified bounds, if there is no
				// wall, then the game can't process because this loop never ends
				// Jump to next square
				if (sideDistX < sideDistY) {
					sideDistX += deltaDistX;
					mapX += stepX;
					side = 0;
				} else {
					sideDistY += deltaDistY;
					mapY += stepY;
					side = 1;
				}
				// Check if ray has hit a wall
				// System.out.println(mapX + ", " + mapY + ", " + map[mapX][mapY]);
				if (map[mapX][mapY] > 0) // any number greater than 0 is a wall, -1 don't count as a wall
					hit = true;
			}
			// Calculate distance to the point of impact
			if (side == 0)
				perpWallDist = Math.abs((mapX - camera.xPos + (1 - stepX) / 2) / rayDirX);
			else
				perpWallDist = Math.abs((mapY - camera.yPos + (1 - stepY) / 2) / rayDirY);
			// Now calculate the height of the wall based on the distance from the camera
			int lineHeight;
			if (perpWallDist > 0)
				lineHeight = Math.abs((int) (height / perpWallDist));
			else
				lineHeight = height;
			// calculate lowest and highest pixel to fill in current stripe
			int drawStart = -lineHeight / 2 + height / 2;
			if (drawStart < 0)
				drawStart = 0;
			int drawEnd = lineHeight / 2 + height / 2;
			if (drawEnd >= height)
				drawEnd = height - 1;
			// add a texture
			int texNum = map[mapX][mapY] - 1; // gets the type of number the wall is, and uses that to find the right
												// image
			double wallX;// Exact position of where wall was hit
			if (side == 1) {// If its a y-axis wall
				wallX = (camera.xPos + ((mapY - camera.yPos + (1 - stepY) / 2) / rayDirY) * rayDirX);
			} else {// X-axis wall
				wallX = (camera.yPos + ((mapX - camera.xPos + (1 - stepX) / 2) / rayDirX) * rayDirY);
			}
			wallX -= Math.floor(wallX);
			// x coordinate on the texture
			int texX = (int) (wallX * (textures.get(texNum).SIZE)); // intergerises the pixel value,
			if (side == 0 && rayDirX > 0)
				texX = textures.get(texNum).SIZE - texX - 1;
			if (side == 1 && rayDirY < 0)
				texX = textures.get(texNum).SIZE - texX - 1;
			// calculate y coordinate on texture
			for (int y = drawStart; y < drawEnd; y++) {
				int texY = (((y * 2 - height + lineHeight) << 6) / lineHeight) / 2;
				int color;
				if (side == 0)
					color = textures.get(texNum).pixels[texX + (texY * textures.get(texNum).SIZE)]; // normal print
				else // the basic shading effect
					color = (textures.get(texNum).pixels[texX + (texY * textures.get(texNum).SIZE)] >> 1) & 8355711;// Make
																													// y
																													// sides
																													// darker
				pixels[x + y * (width)] = color;
			}
		}
		return pixels;
	}
}