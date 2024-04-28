package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import users.User;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LogInPage extends JFrame implements ActionListener{
	
	/*
	 * Beginning of the program.
	 * Call this to start program.
	 * Simple username password entry and sign up direction.
	 * No need to explain more
	 */
	
	private DataPack datapack;
	
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton logInButton;
	private JButton signUpButton;
	private JLabel errorInfo;
	
	public LogInPage(DataPack datapack) {
		this.datapack = datapack;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 680);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel logInPanel = new JPanel();
		logInPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		logInPanel.setBounds(65, 195, 250, 220);
		contentPane.add(logInPanel);
		logInPanel.setLayout(null);
		
		JLabel logInLabel = new JLabel("Log In");
		logInLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		logInLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logInLabel.setBounds(25, 6, 200, 40);
		logInPanel.add(logInLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(95, 80, 130, 26);
		logInPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(95, 118, 130, 26);
		logInPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel userNameLabel = new JLabel("User Name:");
		userNameLabel.setBounds(22, 80, 72, 26);
		logInPanel.add(userNameLabel);
		
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setBounds(25, 118, 69, 26);
		logInPanel.add(PasswordLabel);
		
		logInButton = new JButton("Log In");
		logInButton.addActionListener(this);
		logInButton.setBounds(108, 156, 117, 29);
		logInPanel.add(logInButton);
		
		JPanel signInPanel = new JPanel();
		signInPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		signInPanel.setBounds(65, 415, 250, 55);
		contentPane.add(signInPanel);
		signInPanel.setLayout(null);
		
		JLabel signUpLabel = new JLabel("Don't have an account?");
		signUpLabel.setBounds(6, 6, 153, 43);
		signInPanel.add(signUpLabel);
		
		signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(this);
		signUpButton.setBounds(158, 11, 86, 35);
		signInPanel.add(signUpButton);
		
		JLabel logo = new JLabel();
		logo.setBounds(65, 30, 250, 115);
		logo.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/MainLogo.png")).getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(logo);
		
		errorInfo = new JLabel("");
		errorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		errorInfo.setForeground(new Color(237, 1, 17));
		errorInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		errorInfo.setBounds(65, 492, 253, 118);
		contentPane.add(errorInfo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == logInButton) {
			 String username = usernameField.getText();
			 String password = passwordField.getText();
			 Boolean loggedIn = false;
			 
			 for (User user : datapack.getUsers()) {
				 if (user.getNickname().equals(username)) {
					 if (user.getPassword().equals(password)) {
						 errorInfo.setText("");
						 loggedIn = true;
						 Logger.traceLogger("user:" + user.getNickname() + " has logged in.");
						 dispose();
						 JFrame frame = new MainHub(user, datapack);
						 frame.setVisible(true);
						 break;
					 }
				 }
			 }
			 if (loggedIn == false) {
				 errorInfo.setText("Invalid username or password!");
				 Logger.errorLogger("Log in error.");

			 }
			  
		 }
		 
		 if (e.getSource() == signUpButton) {
			 dispose();
			 JFrame frame = new signUpPage(datapack);
			 frame.setVisible(true);
		 }
	}
}
