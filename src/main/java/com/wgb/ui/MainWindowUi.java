/*
 * Created by JFormDesigner on Fri May 06 15:33:57 CST 2022
 */

package com.wgb.ui;

import org.jdesktop.swingx.VerticalLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ResourceBundle;

/**
 * @author unknown
 */
public class MainWindowUi extends JFrame {
	public MainWindowUi() {
		initComponents();
	}

	public JPanel getPanelGame() {
		return panelGame;
	}

	public JPanel getPanelOperation() {
		return panelOperation;
	}

	public JButton getButtonPlay() {
		return buttonPlay;
	}

	public JButton getButtonPre() {
		return buttonPre;
	}

	public JButton getButtonNext() {
		return buttonNext;
	}

	public JButton getButtonPlayMusic() {
		return buttonPlayMusic;
	}

	public JButton getButtonExit() {
		return buttonExit;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - unknown
		ResourceBundle bundle = ResourceBundle.getBundle("gui");
		panelGame = new JPanel();
		panelOperation = new JPanel();
		buttonPlay = new JButton();
		buttonPre = new JButton();
		buttonNext = new JButton();
		buttonPlayMusic = new JButton();
		buttonExit = new JButton();

		//======== this ========
		setAlwaysOnTop(true);
		var contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(5, 5));

		//======== panelGame ========
		{
			panelGame.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
			EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
			. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
			java. awt. Color. red) ,panelGame. getBorder( )) ); panelGame. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
			{ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
			throw new RuntimeException( ); }} );
			panelGame.setLayout(new BorderLayout());
		}
		contentPane.add(panelGame, BorderLayout.CENTER);

		//======== panelOperation ========
		{
			panelOperation.setLayout(new VerticalLayout(10));

			//---- buttonPlay ----
			buttonPlay.setText(bundle.getString("MainWindowUi.buttonPlay.text"));
			buttonPlay.setFocusable(false);
			panelOperation.add(buttonPlay);

			//---- buttonPre ----
			buttonPre.setText(bundle.getString("MainWindowUi.buttonPre.text"));
			buttonPre.setEnabled(false);
			buttonPre.setFocusable(false);
			panelOperation.add(buttonPre);

			//---- buttonNext ----
			buttonNext.setText(bundle.getString("MainWindowUi.buttonNext.text"));
			buttonNext.setEnabled(false);
			buttonNext.setFocusable(false);
			panelOperation.add(buttonNext);

			//---- buttonPlayMusic ----
			buttonPlayMusic.setText(bundle.getString("MainWindowUi.buttonPlayMusic.text"));
			buttonPlayMusic.setFocusable(false);
			panelOperation.add(buttonPlayMusic);

			//---- buttonExit ----
			buttonExit.setText(bundle.getString("MainWindowUi.buttonExit.text"));
			buttonExit.setFocusable(false);
			panelOperation.add(buttonExit);
		}
		contentPane.add(panelOperation, BorderLayout.EAST);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - unknown
	private JPanel panelGame;
	private JPanel panelOperation;
	private JButton buttonPlay;
	private JButton buttonPre;
	private JButton buttonNext;
	private JButton buttonPlayMusic;
	private JButton buttonExit;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
