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

public class extendedDeletePage extends JFrame implements ActionListener{
	
	/*
	 * Page which is only admins can see and it allows to delete all posts saved.
	 */
	
	private User user;
	private JPanel contentPane;
	private DataPack datapack;

	public extendedDeletePage(User user, DataPack datapack) {
		this.user = user;
		this.datapack = datapack;
		Logger.traceLogger("Admin:" + user.getNickname() + " has opened admin panel.");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 70, 1088, 596);
		contentPane.add(scrollPane);
		
		JPanel mainPanel = new JPanel();
		scrollPane.setViewportView(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 9, 0, 0));
		
		JLabel infoLabel = new JLabel("Click on the post for deleting.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		infoLabel.setBounds(221, 6, 614, 52);
		contentPane.add(infoLabel);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new MainHub(user, datapack);
				frame.setVisible(true);
			}
		});
		goBackButton.setBounds(977, 6, 117, 29);
		contentPane.add(goBackButton);
		
		
		
		for (Post post : datapack.getPosts()) {
			JLabel photo = new JLabel();
			photo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					photo.setVisible(false);
					Logger.traceLogger("Admin:" + user.getNickname() + " deleted the post with id: " + Integer.toString(post.getPost_id()) + ".");
					datapack.getPosts().remove(post);
					post.getOwner().getPosts().remove(post);
					
				}
			});
			photo.setSize(120, 150);
			photo.setIcon(new ImageIcon(post.getImage().getBufferedImage().getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH)));;
			mainPanel.add(photo);
		}
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
