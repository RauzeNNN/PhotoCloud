package gui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import components.*;
import driver.DataPack;
import driver.Logger;

import java.awt.Color;
import java.awt.Dimension;

import users.*;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PostPanel extends JPanel {
	/*
	 * For a spesific post it creates a panel which includes owner info, photo, comment, like, dislike parts.
	 */
	
	private JTextField commentLine;

	/**
	 * Create the panel.
	 */
	public PostPanel(Post post, User user, DataPack datapack) {
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setLayout(null);
		setSize(new Dimension(350,500));
	
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBounds(6, 6, 338, 43);
		add(upperPanel);
		upperPanel.setLayout(null);
		
		JLabel pp = new JLabel();
		pp.setBounds(6, 6, 32, 32);
		pp.setIcon(new ImageIcon (post.getOwner().getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
		upperPanel.add(pp);
		
		JLabel UserNamePanel = new JLabel(post.getOwner().getNickname());
		UserNamePanel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		UserNamePanel.setBounds(49, 6, 200, 31);
		upperPanel.add(UserNamePanel);
		
		
		
		JPanel midPanel = new JPanel();
		midPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = new PostView(post, datapack);
				frame.setVisible(true);
			}
		});
		midPanel.setBounds(6, 55, 338, 388);
		add(midPanel);
		midPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel mainPhoto = new JLabel();
		mainPhoto.setIcon(new ImageIcon(post.getImage().getBufferedImage().getScaledInstance(midPanel.getWidth(), midPanel.getHeight(), Image.SCALE_SMOOTH)));
		midPanel.add(mainPhoto);
		
		
		
		JPanel downPanel = new JPanel();
		downPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		downPanel.setBounds(6, 455, 338, 39);
		add(downPanel);
		downPanel.setLayout(null);
		
		commentLine = new JTextField();
		commentLine.setBounds(6, 6, 168, 27);
		downPanel.add(commentLine);
		commentLine.setColumns(10);
		
		JButton commentButton = new JButton("");
		commentButton.setFocusable(false);
		
		commentButton.setBounds(176, 6, 25, 25);
		commentButton.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/CommentButton.png")).getImage().getScaledInstance(commentButton.getWidth(), commentButton.getHeight(), Image.SCALE_SMOOTH)));
		downPanel.add(commentButton);
		
		
		
		
		JCheckBox likeButton = new JCheckBox();
		
		likeButton.setFocusable(false);
		
		likeButton.setBounds(213, 7, 53, 25);
		likeButton.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/LikeButton.png")).getImage().getScaledInstance(commentButton.getWidth(), commentButton.getHeight(), Image.SCALE_SMOOTH)));
		likeButton.setSelectedIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/LikeSelected.png")).getImage().getScaledInstance(commentButton.getWidth(), commentButton.getHeight(), Image.SCALE_SMOOTH)));
		likeButton.setText(Integer.toString(post.getLike()));
		downPanel.add(likeButton);
		
		JCheckBox disLikeButton = new JCheckBox();
		
		disLikeButton.setFocusable(false);
		disLikeButton.setBounds(278, 8, 54, 25);
		disLikeButton.setIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/disLikeButton.png")).getImage().getScaledInstance(commentButton.getWidth(), commentButton.getHeight(), Image.SCALE_SMOOTH)));
		disLikeButton.setSelectedIcon(new ImageIcon(new ImageIcon(PostPanel.class.getResource("/gui/icons/DislikeSelected.png")).getImage().getScaledInstance(commentButton.getWidth(), commentButton.getHeight(), Image.SCALE_SMOOTH)));
		disLikeButton.setText(Integer.toString(post.getDislike()));
		
		commentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == commentButton) {
					post.getComments().add(new Comment(user, commentLine.getText()));
					Logger.traceLogger("User:" + user.getNickname() + " commented the post with id: " + post.getPost_id() + ".");
					commentLine.disable();
					commentButton.disable();
				}
			}
		});
		
		likeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == likeButton) {
					if(likeButton.isSelected()) {
						post.incrementLike();
						likeButton.setText(Integer.toString(post.getLike()));
						disLikeButton.setVisible(false);
						Logger.traceLogger("User:" + user.getNickname() + " liked the post with id: " + post.getPost_id() + ".");
					}
					else {
						post.decreaseLike();
						likeButton.setText(Integer.toString(post.getLike()));
						disLikeButton.setVisible(true);
						Logger.traceLogger("User:" + user.getNickname() + " take the like back from post with id: " + post.getPost_id() + ".");
					}
				}
			}
		});
		
		disLikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == disLikeButton) {
					if(disLikeButton.isSelected()) {
						post.incrementDislike();
						disLikeButton.setText(Integer.toString(post.getDislike()));
						likeButton.setVisible(false);
						Logger.traceLogger("User:" + user.getNickname() + " disliked the post with id: " + post.getPost_id() + ".");

					}
					else {
						post.decreaseDislike();
						disLikeButton.setText(Integer.toString(post.getDislike()));
						likeButton.setVisible(true);
						Logger.traceLogger("User:" + user.getNickname() + " take the dislike back from post with id: " + post.getPost_id() + ".");

					}
				}
			}
		});
		
		downPanel.add(disLikeButton);
		
		
	}
}
