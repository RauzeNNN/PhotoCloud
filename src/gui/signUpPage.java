package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import photoEditing.ImageMatrix;
import photoEditing.ImageSecretary;
import users.Administrator;
import users.User;
import users.freeUser;
import users.hobbyistUser;
import users.professionalUser;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;

public class signUpPage extends JFrame implements ActionListener{
	/*
	 * Sign in page which collects needed info from user.
	 * 
	 */
	
	private DataPack datapack;
	private ImageMatrix img;
	
	private JPanel contentPane;
	private JTextField userNameText;
	private JTextField surnameText;
	private JTextField nameText;
	private JTextField emailText;
	private JTextField ageText;
	private JButton uploadButton;
	private JButton signInButton;
	private JLabel photoDisplay;
	private JComboBox userTypeBox;
	
	private JLabel userNameError;
	private JLabel emailError;
	private JLabel nameError;
	private JLabel surnameError;
	private JLabel ageError;
	private JLabel photoError;
	private JTextField passwordText;
	private JLabel passWordLabel;
	private JLabel passwordError;

	public signUpPage(DataPack datapack) {
		this.datapack = datapack;
		Logger.traceLogger("Sign up page has opened.");

		
		try {
			this.img = ImageSecretary.readResourceImage("defaultUserPhoto", ".png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("impossible error. (Couldn't read defaultUserPhoto)");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel signInPanel = new JPanel();
		signInPanel.setLayout(null);
		signInPanel.setBorder(null);
		signInPanel.setBounds(37, 51, 660, 495);
		contentPane.add(signInPanel);
		
		JLabel signInLabel = new JLabel("Sign In");
		signInLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signInLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		signInLabel.setBounds(230, 6, 200, 43);
		signInPanel.add(signInLabel);
		
		userNameText = new JTextField();
		userNameText.setColumns(10);
		userNameText.setBounds(95, 80, 130, 26);
		signInPanel.add(userNameText);
		
		surnameText = new JTextField();
		surnameText.setColumns(10);
		surnameText.setBounds(95, 229, 130, 26);
		signInPanel.add(surnameText);
		
		JLabel userNameLabel = new JLabel("User Name:");
		userNameLabel.setBounds(22, 80, 72, 26);
		signInPanel.add(userNameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(22, 229, 69, 26);
		signInPanel.add(surnameLabel);
		
		signInButton = new JButton("Sign In");
		signInButton.addActionListener(this);
		signInButton.setBounds(537, 455, 117, 29);
		signInPanel.add(signInButton);
		
		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(95, 191, 130, 26);
		signInPanel.add(nameText);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(22, 191, 72, 26);
		signInPanel.add(nameLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(95, 118, 130, 26);
		signInPanel.add(emailText);
		
		JLabel emailLabel = new JLabel("E-Mail:");
		emailLabel.setBounds(22, 118, 72, 26);
		signInPanel.add(emailLabel);
		
		ageText = new JTextField();
		ageText.setColumns(10);
		ageText.setBounds(95, 267, 130, 26);
		signInPanel.add(ageText);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(22, 267, 72, 26);
		signInPanel.add(ageLabel);
		
		
		
		JLabel photoLabel = new JLabel("Photo:");
		photoLabel.setBounds(22, 305, 72, 26);
		signInPanel.add(photoLabel);
		
		uploadButton = new JButton("Upload a photo");
		uploadButton.addActionListener(this);
		uploadButton.setBounds(95, 305, 130, 29);
		signInPanel.add(uploadButton);
		
		photoDisplay = new JLabel("");
		photoDisplay.setForeground(new Color(255, 4, 0));
		photoDisplay.setBounds(39, 343, 186, 141);
		photoDisplay.setIcon(new ImageIcon (img.getBufferedImage().getScaledInstance(photoDisplay.getWidth(), photoDisplay.getHeight(), Image.SCALE_SMOOTH)));
		signInPanel.add(photoDisplay);
		
		userNameError = new JLabel("");
		userNameError.setForeground(new Color(237, 1, 17));
		userNameError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		userNameError.setHorizontalAlignment(SwingConstants.CENTER);
		userNameError.setBounds(230, 80, 252, 26);
		signInPanel.add(userNameError);
		
		emailError = new JLabel("");
		emailError.setHorizontalAlignment(SwingConstants.CENTER);
		emailError.setForeground(new Color(237, 1, 17));
		emailError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		emailError.setBounds(230, 118, 252, 26);
		signInPanel.add(emailError);
		
		nameError = new JLabel("");
		nameError.setHorizontalAlignment(SwingConstants.CENTER);
		nameError.setForeground(new Color(237, 1, 17));
		nameError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		nameError.setBounds(230, 191, 252, 26);
		signInPanel.add(nameError);
		
		surnameError = new JLabel("");
		surnameError.setHorizontalAlignment(SwingConstants.CENTER);
		surnameError.setForeground(new Color(237, 1, 17));
		surnameError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		surnameError.setBounds(230, 234, 252, 26);
		signInPanel.add(surnameError);
		
		ageError = new JLabel("");
		ageError.setHorizontalAlignment(SwingConstants.CENTER);
		ageError.setForeground(new Color(237, 1, 17));
		ageError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		ageError.setBounds(230, 267, 252, 26);
		signInPanel.add(ageError);
		
		photoError = new JLabel("");
		photoError.setHorizontalAlignment(SwingConstants.CENTER);
		photoError.setForeground(new Color(237, 1, 17));
		photoError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		photoError.setBounds(230, 305, 200, 26);
		signInPanel.add(photoError);
		
		String[] userTypes = {"Free User", "Hobbyist User", "Proffesional User" , "Administrator"};
		userTypeBox = new JComboBox(userTypes);
		userTypeBox.setBounds(314, 343, 168, 27);
		signInPanel.add(userTypeBox);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		passwordText.setBounds(95, 154, 130, 26);
		signInPanel.add(passwordText);
		
		passWordLabel = new JLabel("Password:");
		passWordLabel.setBounds(22, 154, 72, 26);
		signInPanel.add(passWordLabel);
		
		passwordError = new JLabel("");
		passwordError.setHorizontalAlignment(SwingConstants.CENTER);
		passwordError.setForeground(new Color(237, 1, 17));
		passwordError.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		passwordError.setBounds(230, 154, 252, 26);
		signInPanel.add(passwordError);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.setBounds(640, 6, 104, 29);
		contentPane.add(goBackButton);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new LogInPage(datapack);
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == signInButton) {
			boolean canSignIn = true;
			userNameError.setText("");
			emailError.setText("");
			nameError.setText("");
			surnameError.setText("");
			ageError.setText("");
			
			// Possible Errors
			// nickname errors
			boolean isAvaliable = true;
			for (User user : datapack.getUsers()) {
				if (user.getNickname().equals(userNameText.getText())) {
					isAvaliable = false;
					break;
				}
			}
			
			boolean isAvaliableEmail = true;
			for (User user : datapack.getUsers()) {
				if (user.getEmail().equals(emailText.getText())) {
					isAvaliableEmail = false;
					break;
				}
			}
			if (!isAvaliable || userNameText.getText().isBlank()) {
				canSignIn = false;
				userNameError.setText("Invalid or used username!");
				Logger.errorLogger("SignUp page Invalid or used username!");
			}
			if (!isAvaliableEmail || !emailText.getText().matches(".+@.+")) {
				canSignIn = false;
				emailError.setText("Invalid or used E-Mail");
				Logger.errorLogger("SignUp page Invalid or used E-Mail");
			}
			if (passwordText.getText().isBlank()) {
				canSignIn = false;
				passwordError.setText("Invalid password (probably blank)");
				Logger.errorLogger("SignUp page Invalid password");
			}
			
			if (nameText.getText().isBlank()) {
				canSignIn = false;
				nameError.setText("Invalid name (probably blank)");
				Logger.errorLogger("SignUp page Invalid name");
			}
			if (surnameText.getText().isBlank()) {
				canSignIn = false;
				surnameError.setText("Invalid surname (probably blank)");
				Logger.errorLogger("SignUp page Invalid surname");
			}
			if (ageText.getText().isBlank() || !ageText.getText().matches("\\d+")) {
				canSignIn = false;
				ageError.setText("Invalid age");
				Logger.errorLogger("SignUp page Invalid age");
			}

			
			// after check at last
			if (canSignIn) {
				User newUser;
				if (userTypeBox.getSelectedItem().equals("Free User")) {
					newUser = new freeUser(emailText.getText(), userNameText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText(), Integer.valueOf(ageText.getText()), img);
					datapack.getUsers().add(newUser);
					dispose();
					JFrame frame = new MainHub(newUser, datapack);
					frame.setVisible(true);
					Logger.traceLogger("User:" + newUser.getNickname() + " has signed up.");
					
				}
				else if (userTypeBox.getSelectedItem().equals("Hobbyist User")) {
					newUser = new hobbyistUser(emailText.getText(), userNameText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText(), Integer.valueOf(ageText.getText()), img);
					datapack.getUsers().add(newUser);
					dispose();
					JFrame frame = new MainHub(newUser, datapack);
					frame.setVisible(true);
					Logger.traceLogger("User:" + newUser.getNickname() + " has signed up.");
				}
				else if (userTypeBox.getSelectedItem().equals("Proffesional User")) {
					newUser = new professionalUser(emailText.getText(), userNameText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText(), Integer.valueOf(ageText.getText()), img);
					datapack.getUsers().add(newUser);
					dispose();
					JFrame frame = new MainHub(newUser, datapack);
					frame.setVisible(true);
					Logger.traceLogger("User:" + newUser.getNickname() + " has signed up.");
				}
				else if (userTypeBox.getSelectedItem().equals("Administrator")) {
					newUser = new Administrator(emailText.getText(), userNameText.getText(), passwordText.getText(), nameText.getText(), surnameText.getText(), Integer.valueOf(ageText.getText()), img);
					datapack.getUsers().add(newUser);
					dispose();
					JFrame frame = new MainHub(newUser, datapack);
					frame.setVisible(true);
					Logger.traceLogger("User:" + newUser.getNickname() + " has signed up.");
				}
				
			}
			
		}
		
		if (e.getSource() == uploadButton) {
			photoError.setText("");
			JFileChooser fileChooser = new JFileChooser();
			int cond = fileChooser.showOpenDialog(null);
			
			if (cond == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				try {
					if (!file.getAbsolutePath().matches(".*\\.(jpg|png|jpeg|JPG|JPEG|PNG)")) {
						throw new IOException();
					}
					img = new ImageMatrix(ImageIO.read(file));
					
					photoDisplay.setIcon(new ImageIcon (img.getBufferedImage().getScaledInstance(photoDisplay.getWidth(), photoDisplay.getHeight(), Image.SCALE_SMOOTH)));
					ImageSecretary.writeFilteredImageToResources(img, "currentSignIn", ".jpg");
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					photoError.setText("File Error!");
					System.err.println("File Error!");
					Logger.errorLogger("SignUp page File Error");
				}
				
			}
		}
		
	}
}
