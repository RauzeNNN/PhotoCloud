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
import java.util.ArrayList;
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

public class ProfilePanel extends JPanel {

	/**
	 * Profile info piece for ProfilePage.
	 * it applies just for a spesific profile for each call.
	 */
	public ProfilePanel(User user, User user_selected, DataPack datapack) {
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setLayout(null);
		setSize(new Dimension(380, 90));
	
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 366, 79);
		add(panel);
		panel.setLayout(null);
		
		JLabel pp = new JLabel();
		pp.setBounds(6, 6, 70, 67);
		pp.setIcon(new ImageIcon (user_selected.getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(pp);
		
		JLabel UserNamePanel = new JLabel(user_selected.getNickname());
		UserNamePanel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		UserNamePanel.setBounds(88, 6, 244, 31);
		panel.add(UserNamePanel);
		
		JButton viewProfileButton = new JButton("View Profile");
		viewProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewProfileButton) {
					JFrame frame = new profileViewPage(user_selected, datapack);
					Logger.traceLogger("User:" + user.getNickname() + " has accesed to the profile: " + user_selected.getNickname() + ".");
					frame.setVisible(true);
				}
			}
		});
		viewProfileButton.setBounds(88, 44, 117, 29);
		panel.add(viewProfileButton);
		
		
	}
}
