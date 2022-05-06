package com.wgb.uif;

import com.wgb.GamePanel;
import com.wgb.ui.MainWindowUi;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainWindowFun extends MainWindowUi {
	private GamePanel gamePanel;
	private boolean isStart;
	private int currentStage;
	private int allStage = 100;

	public void init() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 初始化大小
		setSize(new Dimension(880, 860));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentStage = 1;
		isStart = false;
		// 配置一下使用的风格或者皮肤
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		this.addKeyListener(new FrameMonitor());
		// 载入游戏界面
		gamePanel = new GamePanel(800, 800, currentStage);
		// 将游戏模块添加到左边Panel中
		getPanelGame().add(gamePanel);
		// 绑定一下各个按钮的监听事件
		getButtonPlay().addActionListener(e -> {
			if (!isStart) {
				getButtonPlay().setText("暂停游戏");
				if (currentStage == 1) {
					getButtonPre().setEnabled(false);
				} else {
					getButtonPre().setEnabled(true);
				}
				getButtonNext().setEnabled(true);

			} else {
				// 游戏未开始上一关与下一关的按钮是不可以点击的
				getButtonNext().setEnabled(false);
				getButtonPre().setEnabled(false);
				getButtonPlay().setText("开始游戏");
			}
			isStart = !isStart;
			gamePanel.setStart(isStart);
			gamePanel.repaint();

		});
		// 上一关的动作
		getButtonPre().addActionListener(e -> {
			// 首先需要判断是否通关
			if (!gamePanel.isSuccess()) {
				return;
			}
			// 设置上一关或者下一关是否可用
			if (currentStage == 1) {
				getButtonPre().setEnabled(false);
			} else {
				getButtonPre().setEnabled(true);
				gamePanel.setStage(--currentStage);
				gamePanel.setSuccess(false);
				gamePanel.repaint();
			}
			getButtonNext().setEnabled(true);
		});
		// 点击下一关
		getButtonNext().addActionListener(e -> {
			// 首先需要判断是否通关
			if (!gamePanel.isSuccess()) {
				return;
			}
			// 设置上一关或者下一关是否可用
			if (currentStage < allStage) {
				getButtonNext().setEnabled(true);
				gamePanel.setStage(++currentStage);
				gamePanel.setSuccess(false);
				gamePanel.repaint();
			} else {
				getButtonNext().setEnabled(false);
			}
			getButtonPre().setEnabled(true);
		});
	}

	private class FrameMonitor extends KeyAdapter {
		// 好像键盘只能被frame监听，这里只能串引用给panel

		public void keyReleased(KeyEvent e) {
			gamePanel.keyReleased(e);
		}
	}

}
