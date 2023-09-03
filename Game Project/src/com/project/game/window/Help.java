package com.project.game.window;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Window.Type;

//meniul help

@SuppressWarnings("unused")
public class Help extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	public Help() {
		setType(Type.POPUP);
		setTitle("Help");
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBackground(new Color(0, 51, 51));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrControlShoot = new JTextArea();
		txtrControlShoot.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtrControlShoot.setText("Control:\r\nShoot : Space Bar\r\nLeft : 'A' or 'Left-Arrow'\r\nRight : 'D' or 'Right-Arrow'\r\nUp : 'W' or 'Up-Arrow'\r\n\r\nAdditional Info:\r\nHealth Resets every level.\r\nSaws are OHK.\r\nSlimes slowly eat you.\r\n ");
		txtrControlShoot.setForeground(new Color(255, 255, 255));
		txtrControlShoot.setBackground(new Color(0, 51, 51));
		txtrControlShoot.setEditable(false);
		txtrControlShoot.setBounds(147, 132, 360, 228);
		contentPane.add(txtrControlShoot);
		
		JButton btnNewButton = new JButton("MainMenu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menu();
			}
		});
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnNewButton.setBounds(20, 20, 124, 37);
		contentPane.add(btnNewButton);

		
		
		
		
		setVisible(true);
	}
}
