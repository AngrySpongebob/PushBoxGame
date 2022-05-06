package com.wgb;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

public class GameData {

	public final int elementWidth;
	public final int elementHeight;
	public final ImageIcon bg;
	public final ImageIcon box_bingo;
	public final ImageIcon box;
	public final ImageIcon role;
	public final ImageIcon destination;
	public final ImageIcon wall;


	public GameData(int containerWidth, int containerHeight, int mapWidth, int mapHeight) {
		elementWidth = containerWidth / mapWidth;
		elementHeight = containerHeight / mapHeight;

		bg = this.getImageIcon(GameData.class.getResource("/static/bg.jpg"), elementWidth, elementHeight);
		box_bingo = getImageIcon(GameData.class.getResource("/static/bingo_box.jpg"), elementWidth, elementHeight);
		box = getImageIcon(GameData.class.getResource("/static/box.jpg"), elementWidth, elementHeight);
		role = getImageIcon(GameData.class.getResource("/static/role.png"), elementWidth, elementHeight);
		destination = getImageIcon(GameData.class.getResource("/static/position.png"), elementWidth, elementHeight);
		wall = getImageIcon(GameData.class.getResource("/static/wall.jpg"), elementWidth, elementHeight);
	}

	public final static int DirectionLeft = KeyEvent.VK_LEFT;
	public final static int DirectionRight = KeyEvent.VK_RIGHT;
	public final static int DirectionDown = KeyEvent.VK_DOWN;
	public final static int DirectionUp = KeyEvent.VK_UP;

	private ImageIcon getImageIcon(URL path, int width, int height) {
		if (width == 0 || height == 0) {
			return new ImageIcon(path);
		}
		ImageIcon icon = new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}
}
