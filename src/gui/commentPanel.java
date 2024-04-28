package gui;

import javax.swing.JPanel;

import components.Comment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class commentPanel extends JPanel {

	/**
	 * Creates a graphic for just one comment.
	 * can be used as a piece at comments section.
	 */
	public commentPanel(Comment comment) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 338, 43);
		add(scrollPane);
		
		JLabel commentField = new JLabel(comment.getContent());
		scrollPane.setViewportView(commentField);
		
		JLabel userNameField = new JLabel(comment.getOwner().getNickname() + ": ");
		scrollPane.setRowHeaderView(userNameField);
		
	}
}
