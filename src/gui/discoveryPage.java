package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Post;
import driver.DataPack;
import driver.Logger;
import users.User;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class discoveryPage extends JFrame implements ActionListener{

	private DataPack datapack;
	private JPanel contentPane;
	private JButton goBackButton;
	private User user;
	

	/**
	 * Creates discovery pages (pages include all posts) by using PostPanel.
	 */
	public discoveryPage(User user, DataPack datapack) {
		this.user = user;
		this.datapack = datapack;
		Logger.traceLogger("User:" + user.getNickname() + " opened discovery page.");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 760);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
	    JPanel middlePanel = new JPanel();
	    int count = datapack.getPosts().size();
	    GridBagLayout gbl_middlePanel = new GridBagLayout();
	    gbl_middlePanel.columnWidths = giveArr(350, count);
	    gbl_middlePanel.rowHeights = giveArr(505, count); 
	    gbl_middlePanel.columnWeights = new double[]{};
	    gbl_middlePanel.rowWeights = new double[]{};
	    middlePanel.setLayout(gbl_middlePanel);
	    
	    
	    int i = 0;
	    for (Post post : datapack.getPosts()) {
	    	JPanel panel = new PostPanel(post, user, datapack);
		    GridBagConstraints gbc_panel = new GridBagConstraints();
		    gbc_panel.fill = GridBagConstraints.BOTH;
		    gbc_panel.insets = new Insets(0, 0, 5, 0);
		    gbc_panel.gridx = 0;
		    gbc_panel.gridy = i++;
		    middlePanel.add(panel, gbc_panel);
	    }
	   
	    
	    JScrollPane scrollPane = new JScrollPane(middlePanel);
	    scrollPane.setBounds(5, 52, 369, 674);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    contentPane.add(scrollPane);
	    	    
	    JPanel upperPanel = new JPanel();
	    upperPanel.setBounds(5, 6, 369, 41);
	    contentPane.add(upperPanel);
	    upperPanel.setLayout(null);
	    
	    JLabel pp = new JLabel();
	    pp.setBounds(333, 6, 30, 29);
	    pp.setIcon(new ImageIcon (user.getProfile_photo().getBufferedImage().getScaledInstance(pp.getWidth(), pp.getHeight(), Image.SCALE_SMOOTH)));
	    upperPanel.add(pp);
	    
	    JLabel userName = new JLabel(user.getNickname());
	    userName.setHorizontalAlignment(SwingConstants.TRAILING);
	    userName.setBounds(205, 6, 116, 29);
	    upperPanel.add(userName);
	    
	    goBackButton = new JButton("Go Back");
	    goBackButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	    goBackButton.setFocusable(false);
	    
	    goBackButton.addActionListener(this);
	    goBackButton.setBounds(6, 6, 69, 29);
	    upperPanel.add(goBackButton);
	    
	    
		
		
	}
	
	private static int[] giveArr(int val, int count) {
		int[] ret = new int[count];
		for (int i = 0; i < count; i++) {
			ret[i] = val;
		}
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == goBackButton) {
			dispose();
			JFrame frame = new MainHub(user, datapack);
			frame.setVisible(true);
		}
		
	}
}
