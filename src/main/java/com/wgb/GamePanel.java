package com.wgb;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
	private final int width;
	private final int height;
	private GameData gameData;
	private boolean isStart;
	// 是否通关
	private boolean isSuccess;
	// 当前所在的关卡
	private int stage;
	private short[][] myMap;
	// 各个元素的位置
	// 角色的位置
	private short roleX;
	private short roleY;
	// 箱子们的位置
	private List<PublicData> boxList;
	// 目标地点的位置
	private List<PublicData> destinationList;

	public GamePanel(int width, int height, int stage) {
		this.width = width;
		this.height = height;
		this.stage = stage;
		// 加载地图
		init();
		initStage();
		// 获取键盘的监听事件
		this.addKeyListener(this);
		setVisible(true);
	}

	public void setStart(boolean start) {
		isStart = start;
	}

	public void setStage(int stage) {
		this.stage = stage;
		initStage();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public void init() {
		// 默认游戏未开始
		isStart = false;
		isSuccess = false;
		setSize(width, height);
		roleX = 0;
		roleY = 0;
	}

	public void initStage() {
		switch (stage) {
			case 1:
				myMap = MyMap.map_1;
				break;
			case 2:
				myMap = MyMap.map_2;
			default:
				break;
		}
		initMapData();
	}

	public void initMapData() {
		boxList = new ArrayList<>();
		destinationList = new ArrayList<>();
		// 初始化地图数据
		gameData = new GameData(width, height, myMap[0].length, myMap.length);

		for (short y = 0; y < myMap.length; y++) {
			for (short x = 0; x < myMap[y].length; x++) {
				// 初始化角色的位置
				if (myMap[y][x] == 4) {
					roleX = x;
					roleY = y;
				} else if (myMap[y][x] == PublicData.MAP_CODE_BOX) {
					// 设置箱子们的初始位置
					boxList.add(new PublicData(x, y));
				} else if (myMap[y][x] == PublicData.MAP_CODE_DESTINATION) {
					destinationList.add(new PublicData(x, y));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 判断游戏开始与结束
		if (!isStart) {
			return;
		}
		// 上下左右方向键控制小人移动
		switch (e.getKeyCode()) {
			case GameData.DirectionLeft:
				// 判断左边是否是墙
				if (roleX >= 0 && myMap[roleY][roleX - 1] != 1) {
					PublicData box = new PublicData((short) (roleX - 1), roleY); // 左边的箱子
					PublicData boxLeft = new PublicData((short) (box.getX() - 1), box.getY()); // 左边箱子的左边箱子
					if (boxList.contains(box)) {
						if (myMap[box.getY()][box.getX() - 1] != PublicData.MAP_CODE_WALL && !boxList.contains(boxLeft)) {
							// 箱子的左边不是墙和箱子才能移动箱子和角色
							boxList.get(boxList.indexOf(box)).setX((short) (roleX - 2));
							roleX -= 1;
						}
					} else {
						roleX -= 1;
					}
				}
				break;
			case GameData.DirectionRight:
				// 小人向右移动
				// 无脑移动
				// 撞墙判断
				if (roleX < myMap[roleY].length - 1 && myMap[roleY][roleX + 1] != 1) {
					// 判断角色右边是否有箱子，如果有让箱子跟随移动
					PublicData box = new PublicData((short) (roleX + 1), roleY); // 右边的箱子
					PublicData boxRight = new PublicData((short) (box.getX() + 1), box.getY()); // 右边箱子的右边箱子
					if (boxList.contains(box)) {
						// 是箱子，判断箱子的右边是否有墙和箱子
						if (myMap[box.getY()][box.getX() + 1] != PublicData.MAP_CODE_WALL && !boxList.contains(boxRight)) {
							// 右边没有墙且没有箱子，移动角色和右边的箱子
							boxList.get(boxList.indexOf(box)).setX((short) (roleX + 2));
							roleX += 1;
						}
					} else {
						roleX += 1;
					}
				}
				break;
			case GameData.DirectionUp:
				//  判断上方是墙，只有上面不是墙的时候才可以进行移动
				if (roleY >= 0 && myMap[roleY - 1][roleX] != PublicData.MAP_CODE_WALL) {
					// 判断角色上方是否是箱子，如果是箱子就顺便移动箱子
					PublicData box = new PublicData(roleX, (short) (roleY - 1));
					PublicData boxUp = new PublicData(box.getX(), (short) (box.getY() - 1)); // 右边箱子的右边箱子
					if (boxList.contains(box)) {
						//TODO 需要判断箱子的上面是否有墙，和箱子
						if (myMap[box.getY() - 1][box.getX()] != PublicData.MAP_CODE_WALL && !boxList.contains(boxUp)) {
							// 箱子移动
							boxList.get(boxList.indexOf(box)).setY((short) (roleY - 2));
							roleY -= 1;
						}
					} else {
						roleY -= 1;
					}
				}
				break;
			case GameData.DirectionDown:
				// 不能撞墙
				if (roleY < myMap.length && myMap[roleY + 1][roleX] != PublicData.MAP_CODE_WALL) {
					// 向下移动的时候判断是否碰到箱子，如果碰到箱子箱子向下移动
					PublicData box = new PublicData(roleX, (short) (roleY + 1));
					PublicData boxDown = new PublicData(box.getX(), (short) (box.getY() + 1)); // 右边箱子的右边箱子
					if (boxList.contains(box)) {
						// 判断箱子下面是否有墙 和箱子
						if (myMap[box.getY() + 1][box.getX()] != PublicData.MAP_CODE_WALL && !boxList.contains(boxDown)) {
							// 箱子移动
							boxList.get(boxList.indexOf(box)).setY((short) (roleY + 2));
							roleY += 1;
						}
					} else {
						roleY += 1;
					}
				}
				break;
			default:
				break;
		}
		// 判断所有箱子是否归位
		if (boxList.containsAll(destinationList)) {
			isSuccess = true;
		}
		// 重新绘图
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		// 先画一遍背景和墙
		for (int y = 0; y < myMap.length; y++) {
			for (int x = 0; x < myMap[y].length; x++) {
				gameData.bg.paintIcon(this, g, x * gameData.elementWidth, y * gameData.elementHeight);
				if (myMap[y][x] == 1) {
					gameData.wall.paintIcon(this, g, x * gameData.elementWidth, y * gameData.elementHeight);
				}
			}
		}

		// 画目的地
		for (PublicData destination : destinationList) {
			// 画目的地
			gameData.destination.paintIcon(this, g, destination.getX() * gameData.elementWidth, destination.getY() * gameData.elementHeight);
		}

		for (PublicData box : boxList) {
			// 画箱子
			// 如果当前的位置为目的地，那么是另一种箱子
			if (destinationList.contains(box)) {
				gameData.box_bingo.paintIcon(this, g, box.getX() * gameData.elementWidth, box.getY() * gameData.elementHeight);
			} else {
				gameData.box.paintIcon(this, g, box.getX() * gameData.elementWidth, box.getY() * gameData.elementHeight);
			}
		}

		// 画角色
		gameData.role.paintIcon(this, g, roleX * gameData.elementWidth, roleY * gameData.elementHeight);
		if (!isStart) {
			// 画一个String, 设置画笔的颜色
			g.setColor(Color.WHITE);
			g.setFont(new Font("宋体", Font.BOLD, 40));
			g.drawString("点击开始按钮开始游戏", 300, 300);
		} else {
			if (isSuccess) {
				// 画一个String, 设置画笔的颜色
				g.setColor(Color.WHITE);
				g.setFont(new Font("宋体", Font.BOLD, 40));
				g.drawString("恭喜你，过关！！！", 300, 300);
			}

		}
	}
}
