package com.wgb;

import com.wgb.uif.MainWindowFun;

import javax.swing.UnsupportedLookAndFeelException;

public class LaunchGame {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			MainWindowFun mainWindowFun = new MainWindowFun();
			try {
				mainWindowFun.init();
			} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			mainWindowFun.setVisible(true);
		});
	}
}
