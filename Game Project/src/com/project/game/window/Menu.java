package com.project.game.window;
import com.project.game.objects.*;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//programul main,
public class Menu extends JFrame {




	private static final long serialVersionUID = 1L;
	 public static  Database_Manager databaseManager= new Database_Manager();
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Main Menu");
		setBackground(new Color(0, 51, 51));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 51));        // background negru
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				new Window(1200, 800, "Platformer", new Game());
				new Window(1200, 800, "Platformer", new Game());
			}
		});
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnNewButton.setBounds(303, 98, 180, 48);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quit\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnNewButton_1.setBounds(303, 356, 180, 48);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Help();
			}
		});
		btnNewButton_2.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnNewButton_2.setBounds(303, 215, 180, 48);
		contentPane.add(btnNewButton_2);
		
		setVisible(true);
	}
}
