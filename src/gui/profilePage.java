package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import users.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.ScrollPaneConstants;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class profilePage extends JFrame {
	
	/*
	 * Public info for a spesific profile.
	 * Includes basic info like name surname etc. and posts.
	 */

	private JPanel contentPane;
	private DataPack datapack;

	public profilePage(User user, DataPack datapack) {  //posts mean totalposts
		Logger.traceLogger("User:" + user.getNickname() + " has opened profile page.");
		this.datapack = datapack;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBounds(6, 6, 1088, 169);
		contentPane.add(upperPanel);
		upperPanel.setLayout(null);
		
		JLabel pp = new JLabel();
		pp.setBounds(6, 6, 157, 157);
		pp.setIcon(new ImageIcon (user.getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
		upperPanel.add(pp);
		
		JLabel userNameInfo = new JLabel("User Name: " + user.getNickname());
		userNameInfo.setBounds(175, 6, 157, 16);
		upperPanel.add(userNameInfo);
		
		JLabel nameInfo = new JLabel("Name: " + user.getName());
		nameInfo.setBounds(175, 34, 157, 16);
		upperPanel.add(nameInfo);
		
		JLabel surnameInfo = new JLabel("Surname: " + user.getSurname());
		surnameInfo.setBounds(175, 62, 157, 16);
		upperPanel.add(surnameInfo);
		
		JLabel ageInfo = new JLabel("Age: " + Integer.toString(user.getAge()));
		ageInfo.setBounds(175, 90, 157, 16);
		upperPanel.add(ageInfo);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new MainHub(user, datapack);
				frame.setVisible(true);
			}
		});
		goBackButton.setBounds(994, 6, 94, 29);
		upperPanel.add(goBackButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 187, 1088, 479);
		contentPane.add(scrollPane);
		
		JPanel mainPanel = new JPanel();
		scrollPane.setViewportView(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 9, 0, 0));
		
		
		for (Post post : user.getPosts()) {
			JLabel photo = new JLabel();
			photo.setSize(120, 150);
			photo.setIcon(new ImageIcon(post.getImage().getBufferedImage().getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH)));;
			mainPanel.add(photo);
		}
		
		
		
	}
}
