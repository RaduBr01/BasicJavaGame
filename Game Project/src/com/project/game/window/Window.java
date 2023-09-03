package com.project.game.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	static JFrame frame;
	
	public Window(int w, int h, String title, Game game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		game.start();
	}

	public static void dispose() {
		frame.dispose();
	}

	public static void main(String[] args){
		new Window(800, 600, "Game", new Game());
	}
}
