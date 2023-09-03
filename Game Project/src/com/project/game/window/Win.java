package com.project.game.window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Win extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Win() {
		setTitle("Win Screen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 610);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					new Menu();
				}
			}
		});
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Continue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menu();
			}
		});
		btnNewButton.setBounds(33, 533, 97, 38);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("You Won");
		lblNewLabel.setForeground(new Color(0, 102, 255));
		lblNewLabel.setFont(new Font("Lithos Pro Regular", Font.PLAIN, 86));
		lblNewLabel.setBounds(133, 165, 525, 124);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Press Enter to Continue\r\n");
		lblNewLabel_1.setForeground(new Color(0, 204, 255));
		lblNewLabel_1.setFont(new Font("Lithos Pro Regular", Font.PLAIN, 36));
		lblNewLabel_1.setBounds(143, 270, 517, 61);
		contentPane.add(lblNewLabel_1);
		
		setVisible(true);
	}
}
