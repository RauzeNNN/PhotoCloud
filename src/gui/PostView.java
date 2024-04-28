package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Comment;
import components.Post;
import driver.DataPack;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PostView extends JFrame {
	/*
	 * The page when you clicked on an image on discovery page.
	 * Includes photo, comments, like and dislike counts.
	 */

	private JPanel contentPane;

	public PostView(Post post, DataPack datapack) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1053, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainPhotoLabel = new JLabel("");
		mainPhotoLabel.setBounds(6, 6, 652, 710);
		mainPhotoLabel.setIcon(new ImageIcon (post.getImage().getBufferedImage().getScaledInstance(mainPhotoLabel.getWidth(), mainPhotoLabel.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(mainPhotoLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(670, 6, 375, 710);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel likeLabel = new JLabel("");
		likeLabel.setBounds(16, 673, 31, 31);
		likeLabel.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/LikeButton.png")).getImage().getScaledInstance(likeLabel.getWidth(), likeLabel.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(likeLabel);
		
		JLabel likeCount = new JLabel(Integer.toString(post.getLike()));
		likeCount.setBounds(59, 684, 61, 16);
		panel.add(likeCount);
		
		JLabel disLikeLabel = new JLabel("");
		disLikeLabel.setBounds(175, 673, 31, 31);
		disLikeLabel.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/disLikeButton.png")).getImage().getScaledInstance(disLikeLabel.getWidth(), disLikeLabel.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(disLikeLabel);
		
		JLabel dislikeCount = new JLabel(Integer.toString(post.getDislike()));
		dislikeCount.setBounds(218, 684, 61, 16);
		panel.add(dislikeCount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 363, 610);
		panel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel commentPanel = new JPanel();
		scrollPane.setViewportView(commentPanel);
		commentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(279, 675, 96, 29);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane_Description = new JScrollPane();
		scrollPane_Description.setBounds(6, 624, 363, 31);
		panel.add(scrollPane_Description);
		
		JLabel descriptionLabel = new JLabel(post.getOwner().getNickname() + ": ");
		scrollPane_Description.setRowHeaderView(descriptionLabel);
		
		JLabel descriptionContentLabel = new JLabel(post.getDescription());
		scrollPane_Description.setViewportView(descriptionContentLabel);
		
		
		for (Comment comment : post.getComments()) {
			JPanel comPanel = new commentPanel(comment);
			commentPanel.add(comPanel);
		}
		
	}
}
