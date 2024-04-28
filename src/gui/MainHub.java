package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import users.Permissions;
import users.User;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainHub extends JFrame implements ActionListener{
	
	/*
	 * Main page which is linked with other pages.
	 */

	private JPanel contentPane;
	private User user;
	private DataPack datapack;
	private JButton LogoutButton;
	private JButton editProfileButton;
	private JButton newPostButton;
	private JButton myProfileButton;
	private JButton discoverButton;
	private JButton profilesButton;
	private JButton adminPanelButton;
	

	public MainHub(User user, DataPack datapack) {
		this.user = user;
		this.datapack = datapack;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1087, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBounds(6, 6, 1075, 58);
		contentPane.add(upperPanel);
		upperPanel.setLayout(null);
		
		JLabel pp = new JLabel();
		pp.setBounds(6, 6, 46, 46);
		pp.setIcon(new ImageIcon (user.getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
		upperPanel.add(pp);
		
		JLabel userName = new JLabel(user.getNickname());
		userName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		userName.setBounds(64, 6, 196, 46);
		upperPanel.add(userName);
		
		LogoutButton = new JButton("Logout");
		LogoutButton.setBounds(887, 6, 182, 46);
		LogoutButton.addActionListener(this);
		upperPanel.add(LogoutButton);
		
		editProfileButton = new JButton("Edit Profile");
		editProfileButton.addActionListener(this);
		editProfileButton.setBounds(272, 6, 182, 46);
		upperPanel.add(editProfileButton);
		
		newPostButton = new JButton("New Post");
		newPostButton.addActionListener(this);
		newPostButton.setBounds(466, 6, 182, 46);
		upperPanel.add(newPostButton);
		
		adminPanelButton = new JButton("Admin Panel");
		adminPanelButton.addActionListener(this);
		adminPanelButton.setVisible(false);
		
		for (Permissions perm : user.getPerms()) {
			if (perm == Permissions.EXTENDED_DELETE) {
				adminPanelButton.setVisible(true);
			}
		}
		
		adminPanelButton.setBounds(660, 6, 182, 46);
		upperPanel.add(adminPanelButton);
		
		JPanel midPanel = new JPanel();
		midPanel.setBounds(6, 76, 1075, 540);
		contentPane.add(midPanel);
		midPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		myProfileButton = new JButton("My Profile");
		myProfileButton.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		myProfileButton.addActionListener(this);
		midPanel.add(myProfileButton);
		
		discoverButton = new JButton("Discovery");
		discoverButton.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		discoverButton.addActionListener(this);
		midPanel.add(discoverButton);

		profilesButton = new JButton("Profiles");
		profilesButton.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		profilesButton.addActionListener(this);
		midPanel.add(profilesButton);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == LogoutButton) {
			this.dispose();
			JFrame frame = new LogInPage(datapack);
			frame.setVisible(true);
			Logger.traceLogger("User:" + user.getNickname() + " has logged out.");

		}

		if (e.getSource() == editProfileButton) {
			this.dispose();
			JFrame frame = new editProfilePage(user, datapack);
			frame.setVisible(true);
		}

		if (e.getSource() == newPostButton) {
			this.dispose();
			JFrame frame = new NewPostPage(user, datapack);
			frame.setVisible(true);
		}

		if (e.getSource() == myProfileButton) {
			this.dispose();
			JFrame frame = new profilePage(user, datapack);
			frame.setVisible(true);
		}

		if (e.getSource() == discoverButton) {
			this.dispose();
			JFrame discovery = new discoveryPage(user, datapack);
			discovery.setVisible(true);

		}

		if (e.getSource() == profilesButton) {
			this.dispose();
			JFrame frame = new profileListPage(user, datapack);
			frame.setVisible(true);
		}
		
		if (e.getSource() == adminPanelButton) {
			this.dispose();
			JFrame frame = new extendedDeletePage(user, datapack);
			frame.setVisible(true);
		}

	}
}
