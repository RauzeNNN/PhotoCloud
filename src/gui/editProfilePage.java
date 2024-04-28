package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import photoEditing.ImageMatrix;
import photoEditing.ImageSecretary;
import users.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class editProfilePage extends JFrame implements ActionListener{
	/*
	 * Like ProfilePage but Info parts can be able to change and if you click on posts you can delete them easily.
	 */
	
	private User user;
	private JPanel contentPane;
	private JLabel pp;
	private DataPack datapack;
	private JTextField nameField;
	private JTextField SurnameField;
	private JTextField ageField;
	private JButton updateNameButton;
	private JButton updateSurnameButton;
	private JButton updateAgeButton;
	private JButton updatePPButton;
	private JLabel errorInfo;

	public editProfilePage(User user, DataPack datapack) {
		this.user = user;
		this.datapack = datapack;
		Logger.traceLogger("User:" + user.getNickname() + " opened profile edit page.");
		
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
		
		pp = new JLabel();
		pp.setBounds(6, 6, 157, 157);
		pp.setIcon(new ImageIcon (user.getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
		upperPanel.add(pp);
		
		JLabel userNameInfo = new JLabel("User Name: " + user.getNickname());
		userNameInfo.setBounds(175, 6, 157, 16);
		upperPanel.add(userNameInfo);
		
		JLabel nameInfo = new JLabel("Name: ");
		nameInfo.setBounds(175, 34, 44, 16);
		upperPanel.add(nameInfo);
		
		JLabel surnameInfo = new JLabel("Surname: ");
		surnameInfo.setBounds(175, 62, 62, 16);
		upperPanel.add(surnameInfo);
		
		JLabel ageInfo = new JLabel("Age: ");
		ageInfo.setBounds(175, 90, 53, 16);
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
		
		nameField = new JTextField();
		nameField.setBounds(239, 29, 130, 26);
		upperPanel.add(nameField);
		nameField.setColumns(10);
		
		SurnameField = new JTextField();
		SurnameField.setBounds(239, 57, 130, 26);
		upperPanel.add(SurnameField);
		SurnameField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setColumns(10);
		ageField.setBounds(239, 85, 130, 26);
		upperPanel.add(ageField);
		
		updateNameButton = new JButton("Update");
		updateNameButton.addActionListener(this);
		updateNameButton.setBounds(381, 29, 117, 29);
		upperPanel.add(updateNameButton);
		
		updateSurnameButton = new JButton("Update");
		updateSurnameButton.addActionListener(this);
		updateSurnameButton.setBounds(381, 57, 117, 29);
		upperPanel.add(updateSurnameButton);
		
		updateAgeButton = new JButton("Update");
		updateAgeButton.addActionListener(this);
		updateAgeButton.setBounds(381, 85, 117, 29);
		upperPanel.add(updateAgeButton);
		
		updatePPButton = new JButton("Update Profile Photo");
		updatePPButton.addActionListener(this);
		updatePPButton.setBounds(175, 118, 200, 29);
		upperPanel.add(updatePPButton);
		
		errorInfo = new JLabel("");
		errorInfo.setForeground(new Color(255, 29, 0));
		errorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		errorInfo.setBounds(597, 34, 287, 85);
		upperPanel.add(errorInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 214, 1088, 452);
		contentPane.add(scrollPane);
		
		JPanel mainPanel = new JPanel();
		scrollPane.setViewportView(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 9, 0, 0));
		
		JLabel infoLabel = new JLabel("Click on the post for deleting.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		infoLabel.setBounds(221, 176, 614, 36);
		contentPane.add(infoLabel);
		
		
		
		for (Post post : user.getPosts()) {
			JLabel photo = new JLabel();
			photo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					photo.setVisible(false);
					Logger.traceLogger("User:" + user.getNickname() + " deleted the post with id: " + Integer.toString(post.getPost_id()) + ".");
					datapack.getPosts().remove(post);
					user.getPosts().remove(post);
					
					
				}
			});
			photo.setSize(120, 150);
			photo.setIcon(new ImageIcon(post.getImage().getBufferedImage().getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH)));;
			mainPanel.add(photo);
		}
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == updateNameButton) {
			if (!nameField.getText().isBlank()) {
				user.setName(nameField.getText());
				errorInfo.setText("");
				Logger.traceLogger("User:" + user.getNickname() + " changed name info.");
			}
			else {
				errorInfo.setText("Invalid Name!");
				Logger.errorLogger("User:" + user.getNickname() + " tried to change name info but invalid.");
			}
			
			
		}
		
		if (e.getSource() == updateSurnameButton) {
			if (!SurnameField.getText().isBlank()) {
				user.setSurname(SurnameField.getText());
				errorInfo.setText("");
				Logger.traceLogger("User:" + user.getNickname() + " changed surname info.");
			}
			else {
				errorInfo.setText("Invalid Surname!");
				Logger.errorLogger("User:" + user.getNickname() + " tried to change surname info but invalid.");
			}
			
		}
		
		if (e.getSource() == updateAgeButton) {
			if (!ageField.getText().isBlank() && ageField.getText().matches("\\d+")) {
				user.setAge(Integer.valueOf(ageField.getText()));
				errorInfo.setText("");
				Logger.traceLogger("User:" + user.getNickname() + " changed age info.");
			}
			else {
				errorInfo.setText("Invalid Age!");
				Logger.errorLogger("User:" + user.getNickname() + " tried to change age info but invalid.");
			}
		}
		
		if (e.getSource() == updatePPButton) {
			errorInfo.setText("");
			ImageMatrix img;
			JFileChooser fileChooser = new JFileChooser();
			int cond = fileChooser.showOpenDialog(null);
			
			if (cond == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				try {
					if (!file.getAbsolutePath().matches(".*\\.(jpg|png|jpeg)")) {
						throw new IOException();
					}
					img = new ImageMatrix (ImageIO.read(file));
					
					pp.setIcon(new ImageIcon (img.getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
					user.setProfile_photo(img);
					ImageSecretary.writeFilteredImageToResources(img, user.getNickname() + "_" + "pp", ".jpg");
					Logger.traceLogger("User:" + user.getNickname() + " changed profile photo.");
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					errorInfo.setText("File Error!");
					System.err.println("File Error!");
					Logger.errorLogger("User:" + user.getNickname() + " tried to upload new profile photo but File Error has occured.");
				}
				
			}
			
		}
		
	}
}
