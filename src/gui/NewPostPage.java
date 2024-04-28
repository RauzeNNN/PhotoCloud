package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

import users.Permissions;
import users.User;
import components.*;
import driver.DataPack;
import driver.Logger;
import photoEditing.Edits;
import photoEditing.ImageMatrix;
import photoEditing.ImageSecretary;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

public class NewPostPage extends JFrame implements ActionListener{
	
	/*
	 * Page which is used for creating a post.
	 * Includes effect settings.
	 * If a user have not permission a spesific effect that effect's setting part is disabled.
	 * 
	 * Note: Most complex part of my project
	 */

	private User user;
	private DataPack datapack;
	
	private JPanel contentPane;
	private JLabel mainPhoto;
	private JLabel errorInfo;
	private JTextField descriptionField;
	private JButton photoUploadButton;
	private JButton blurUpdateButton;
	private JButton sharpenUpdateButton;
	private JButton grayScaleUpdateButton;
	private JButton brightnessUpdateButton;
	private JButton contrastUpdateButton;
	private JButton edgeDetectionUpdateButton;
	private JButton shareButton;
	private JButton goBackButton;
	
	private JButton blurLookButton;
	private JButton grayScaleLookButton;
	private JButton brightnessLookButton;
	private JButton contrastLookButton;
	private JButton edgeDetectionLookButton;
	private JButton sharpenLookButton;
	
	
	private JSlider grayScaleSlider;
	private JSlider blurSlider;
	private JSlider BrightnessSlider;
	private JSlider ContrastSlider;
	private JSlider edgeDetectionSlider;
	
	private ImageMatrix img;
	
	public NewPostPage(User user, DataPack datapack) {
		this.user = user;
		this.datapack = datapack;
		Logger.traceLogger("User:" + user.getNickname() + " has opened new post page.");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(65, 80, 386, 467);
		contentPane.add(panel);
		panel.setLayout(null);
		
		mainPhoto = new JLabel("");
		mainPhoto.setBounds(6, 6, 374, 395);
		panel.add(mainPhoto);
		
		photoUploadButton = new JButton("Upload Photo");
		photoUploadButton.addActionListener(this);
		photoUploadButton.setBounds(79, 413, 218, 48);
		panel.add(photoUploadButton);
		
		blurSlider = new JSlider();
		blurSlider.setEnabled(false);
		blurSlider.setMaximum(11);
		blurSlider.setMinimum(1);
		blurSlider.setValue(1);
		blurSlider.setMajorTickSpacing(2);
		blurSlider.setSnapToTicks(true);
		blurSlider.setPaintTicks(true);
		blurSlider.setMinorTickSpacing(2);
		blurSlider.setBackground(new Color(0, 0, 0));
		blurSlider.setBorder(new LineBorder(new Color(0, 0, 0)));
		blurSlider.setPaintLabels(true);
		blurSlider.setBounds(508, 59, 363, 40);
		contentPane.add(blurSlider);
		
		JLabel blurInfo = new JLabel("Blur");
		blurInfo.setHorizontalAlignment(SwingConstants.CENTER);
		blurInfo.setBounds(643, 18, 80, 29);
		contentPane.add(blurInfo);
		
		blurUpdateButton = new JButton("Update");
		blurUpdateButton.setEnabled(false);
		blurUpdateButton.addActionListener(this);
		blurUpdateButton.setBounds(977, 60, 117, 29);
		contentPane.add(blurUpdateButton);
		
		sharpenUpdateButton = new JButton("Sharpen");
		sharpenUpdateButton.setEnabled(false);
		sharpenUpdateButton.addActionListener(this);
		sharpenUpdateButton.setBounds(976, 464, 117, 29);
		contentPane.add(sharpenUpdateButton);
		
		grayScaleSlider = new JSlider();
		grayScaleSlider.setEnabled(false);
		grayScaleSlider.setMajorTickSpacing(1);
		grayScaleSlider.setSnapToTicks(true);
		grayScaleSlider.setPaintTicks(true);
		grayScaleSlider.setPaintLabels(true);
		grayScaleSlider.setMinorTickSpacing(1);
		grayScaleSlider.setMaximum(10);
		grayScaleSlider.setBorder(new LineBorder(new Color(0, 0, 0)));
		grayScaleSlider.setBackground(Color.BLACK);
		grayScaleSlider.setBounds(508, 141, 363, 40);
		contentPane.add(grayScaleSlider);
		
		JLabel grayScaleInfo = new JLabel("GrayScale");
		grayScaleInfo.setHorizontalAlignment(SwingConstants.CENTER);
		grayScaleInfo.setBounds(643, 100, 80, 29);
		contentPane.add(grayScaleInfo);
		
		grayScaleUpdateButton = new JButton("Update");
		grayScaleUpdateButton.setEnabled(false);
		grayScaleUpdateButton.addActionListener(this);
		grayScaleUpdateButton.setBounds(977, 142, 117, 29);
		contentPane.add(grayScaleUpdateButton);
		
		BrightnessSlider = new JSlider();
		BrightnessSlider.setEnabled(false);
		BrightnessSlider.setMaximum(510);
		BrightnessSlider.setValue(255);
		BrightnessSlider.setMajorTickSpacing(50);
		BrightnessSlider.setSnapToTicks(true);
		BrightnessSlider.setPaintTicks(true);
		BrightnessSlider.setPaintLabels(true);
		BrightnessSlider.setMinorTickSpacing(5);
		
		BrightnessSlider.setBorder(new LineBorder(new Color(0, 0, 0)));
		BrightnessSlider.setBackground(Color.BLACK);
		BrightnessSlider.setBounds(508, 223, 363, 40);
		contentPane.add(BrightnessSlider);
		
		JLabel brightnessInfo = new JLabel("Brightness");
		brightnessInfo.setHorizontalAlignment(SwingConstants.CENTER);
		brightnessInfo.setBounds(643, 182, 80, 29);
		contentPane.add(brightnessInfo);
		
		brightnessUpdateButton = new JButton("Update");
		brightnessUpdateButton.setEnabled(false);
		brightnessUpdateButton.addActionListener(this);
		brightnessUpdateButton.setBounds(977, 224, 117, 29);
		contentPane.add(brightnessUpdateButton);
		
		ContrastSlider = new JSlider();
		ContrastSlider.setEnabled(false);
		ContrastSlider.setValue(20);
		ContrastSlider.setMajorTickSpacing(10);
		ContrastSlider.setSnapToTicks(true);
		ContrastSlider.setPaintTicks(true);
		ContrastSlider.setPaintLabels(true);
		ContrastSlider.setMinorTickSpacing(5);
		ContrastSlider.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContrastSlider.setBackground(Color.BLACK);
		ContrastSlider.setBounds(508, 305, 363, 40);
		contentPane.add(ContrastSlider);
		
		JLabel contrastInfo = new JLabel("Contrast");
		contrastInfo.setHorizontalAlignment(SwingConstants.CENTER);
		contrastInfo.setBounds(643, 264, 80, 29);
		contentPane.add(contrastInfo);
		
		contrastUpdateButton = new JButton("Update");
		contrastUpdateButton.setEnabled(false);
		contrastUpdateButton.addActionListener(this);
		contrastUpdateButton.setBounds(977, 306, 117, 29);
		contentPane.add(contrastUpdateButton);
		
		edgeDetectionSlider = new JSlider();
		edgeDetectionSlider.setEnabled(false);
		edgeDetectionSlider.setValue(0);
		edgeDetectionSlider.setMajorTickSpacing(25);
		edgeDetectionSlider.setSnapToTicks(true);
		edgeDetectionSlider.setPaintTicks(true);
		edgeDetectionSlider.setPaintLabels(true);
		edgeDetectionSlider.setMinorTickSpacing(1);
		edgeDetectionSlider.setMaximum(250);
		edgeDetectionSlider.setBorder(new LineBorder(new Color(0, 0, 0)));
		edgeDetectionSlider.setBackground(Color.BLACK);
		edgeDetectionSlider.setBounds(508, 387, 363, 40);
		contentPane.add(edgeDetectionSlider);
		
		JLabel edgeDetectionInfo = new JLabel("Edge Detection");
		edgeDetectionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		edgeDetectionInfo.setBounds(624, 346, 127, 29);
		contentPane.add(edgeDetectionInfo);
		
		edgeDetectionUpdateButton = new JButton("Update");
		edgeDetectionUpdateButton.setEnabled(false);
		edgeDetectionUpdateButton.addActionListener(this);
		edgeDetectionUpdateButton.setBounds(977, 388, 117, 29);
		contentPane.add(edgeDetectionUpdateButton);
		
		errorInfo = new JLabel("");
		errorInfo.setForeground(new Color(255, 26, 0));
		errorInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		errorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		errorInfo.setBounds(65, 557, 386, 95);
		contentPane.add(errorInfo);
		
		shareButton = new JButton("Share");
		shareButton.addActionListener(this);
		shareButton.setBounds(878, 613, 197, 53);
		contentPane.add(shareButton);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(689, 521, 386, 26);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel descriptionInfo = new JLabel("Description:");
		descriptionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionInfo.setBounds(602, 520, 80, 29);
		contentPane.add(descriptionInfo);
		
		goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new MainHub(user, datapack);
				frame.setVisible(true);
			}
		});
		goBackButton.setBounds(991, 6, 103, 29);
		contentPane.add(goBackButton);
		
		blurLookButton = new JButton("Look");
		blurLookButton.setEnabled(false);
		blurLookButton.addActionListener(this);
		blurLookButton.setBounds(890, 59, 88, 29);
		contentPane.add(blurLookButton);
		
		grayScaleLookButton = new JButton("Look");
		grayScaleLookButton.setEnabled(false);
		grayScaleLookButton.addActionListener(this);
		grayScaleLookButton.setBounds(890, 142, 88, 29);
		contentPane.add(grayScaleLookButton);
		
		brightnessLookButton = new JButton("Look");
		brightnessLookButton.setEnabled(false);
		brightnessLookButton.addActionListener(this);
		brightnessLookButton.setBounds(890, 224, 88, 29);
		contentPane.add(brightnessLookButton);
		
		contrastLookButton = new JButton("Look");
		contrastLookButton.setEnabled(false);
		contrastLookButton.addActionListener(this);
		contrastLookButton.setBounds(890, 305, 88, 29);
		contentPane.add(contrastLookButton);
		
		edgeDetectionLookButton = new JButton("Look");
		edgeDetectionLookButton.setEnabled(false);
		edgeDetectionLookButton.addActionListener(this);
		edgeDetectionLookButton.setBounds(890, 388, 88, 29);
		contentPane.add(edgeDetectionLookButton);
		
		sharpenLookButton = new JButton("Look");
		sharpenLookButton.setEnabled(false);
		sharpenLookButton.addActionListener(this);
		sharpenLookButton.setBounds(890, 464, 88, 29);
		contentPane.add(sharpenLookButton);
		
		for (Permissions perm : user.getPerms()) {
			if (perm == Permissions.BLURRING) {
				blurLookButton.setEnabled(true);
				blurSlider.setEnabled(true);
				blurUpdateButton.setEnabled(true);
			}
			
			else if (perm == Permissions.SHARPENING) {
				sharpenLookButton.setEnabled(true);
				sharpenUpdateButton.setEnabled(true);
			}
			
			else if (perm == Permissions.BRIGHTNESS) {
				brightnessLookButton.setEnabled(true);
				BrightnessSlider.setEnabled(true);
				brightnessUpdateButton.setEnabled(true);
			}
			
			else if (perm == Permissions.CONTRAST) {
				contrastLookButton.setEnabled(true);
				ContrastSlider.setEnabled(true);
				contrastUpdateButton.setEnabled(true);
			}
			
			else if (perm == Permissions.EDGE_DETECTION) {
				edgeDetectionLookButton.setEnabled(true);
				edgeDetectionSlider.setEnabled(true);
				edgeDetectionUpdateButton.setEnabled(true);
			}
			
			else if (perm == Permissions.GRAYSCALE) {
				grayScaleLookButton.setEnabled(true);
				grayScaleSlider.setEnabled(true);
				grayScaleUpdateButton.setEnabled(true);
			}
			
		}
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == photoUploadButton) {
			errorInfo.setText("");
			JFileChooser fileChooser = new JFileChooser();
			int cond = fileChooser.showOpenDialog(null);
			
			if (cond == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				try {
					if (!file.getAbsolutePath().matches(".*\\.(jpg|png|jpeg|JPG|JPEG|PNG)")) {
						throw new IOException();
					}
					img = new ImageMatrix (ImageIO.read(file));
					
					mainPhoto.setIcon(new ImageIcon (img.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
					ImageSecretary.writeFilteredImageToResources(img, "currentWorking", ".jpg");
					Logger.traceLogger("User:" + user.getNickname() + " uploaded a new image for a new post.");
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					errorInfo.setText("File Error!");
					System.err.println("File Error!");
					Logger.errorLogger("User:" + user.getNickname() + " tried to upload a photo for a new post but file error occured.");
				}
				
			}
			
		}
		
		if (e.getSource() == blurUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix blurred =  Edits.blur(image, blurSlider.getValue());
			mainPhoto.setIcon(new ImageIcon (blurred.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(blurred, "currentWorking", ".jpg");
			Logger.traceLogger("User:" + user.getNickname() + " applied blur to an image");
		}
		
		if (e.getSource() == blurLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix blurred =  Edits.blur(image, blurSlider.getValue());
			mainPhoto.setIcon(new ImageIcon (blurred.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == grayScaleUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix grayScaled =  Edits.grayScale(image,((double) grayScaleSlider.getValue())/10);
			mainPhoto.setIcon(new ImageIcon (grayScaled.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(grayScaled, "currentWorking", ".jpg");
			Logger.traceLogger("User:" + user.getNickname() + " applied grayscale to an image");
		}
		
		if (e.getSource() == grayScaleLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix grayScaled =  Edits.grayScale(image, ((double) grayScaleSlider.getValue())/10);
			mainPhoto.setIcon(new ImageIcon (grayScaled.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == brightnessUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix brightness =  Edits.brightness(image, BrightnessSlider.getValue()-255);
			mainPhoto.setIcon(new ImageIcon (brightness.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(brightness, "currentWorking", ".jpg");
			Logger.traceLogger("User:" + user.getNickname() + " applied brightness to an image");

		}
		
		if (e.getSource() == brightnessLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix brightness =  Edits.brightness(image, BrightnessSlider.getValue()-255);
			mainPhoto.setIcon(new ImageIcon (brightness.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == contrastUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix contrast =  Edits.contrast(image, ((double) ContrastSlider.getValue())/20);
			mainPhoto.setIcon(new ImageIcon (contrast.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(contrast, "currentWorking", ".jpg");
			Logger.traceLogger("User:" + user.getNickname() + " applied contrast to an image");
		}

		if (e.getSource() == contrastLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix contrast =  Edits.contrast(image, ((double) ContrastSlider.getValue())/20);
			mainPhoto.setIcon(new ImageIcon (contrast.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == edgeDetectionUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix edge =  Edits.edgeDetection(image, edgeDetectionSlider.getValue());
			mainPhoto.setIcon(new ImageIcon (edge.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(edge, "currentWorking", ".jpg");
			Logger.traceLogger("User:" + user.getNickname() + " applied edge detection to an image");
		}
		
		if (e.getSource() == edgeDetectionLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix edge =  Edits.edgeDetection(image, edgeDetectionSlider.getValue());
			mainPhoto.setIcon(new ImageIcon (edge.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == sharpenUpdateButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
				Logger.traceLogger("User:" + user.getNickname() + " applied sharpen to an image");
			}
			ImageMatrix sharp =  Edits.sharpen(image);
			mainPhoto.setIcon(new ImageIcon (sharp.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
			ImageSecretary.writeFilteredImageToResources(sharp, "currentWorking", ".jpg");
		}
		
		if (e.getSource() == sharpenLookButton) {
			ImageMatrix image = img;
			try {
				image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
			} catch (IOException e1) {
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
			ImageMatrix sharp =  Edits.sharpen(image);
			mainPhoto.setIcon(new ImageIcon (sharp.getBufferedImage().getScaledInstance(mainPhoto.getWidth(), mainPhoto.getHeight(), Image.SCALE_SMOOTH)));
		}
		
		if (e.getSource() == shareButton) {
			try {
				ImageMatrix last_image = ImageSecretary.readResourceImage("currentWorking", ".jpg");
				Post post = new Post(last_image, descriptionField.getText(), user);
				
				datapack.getPosts().add(post);
				
				ImageSecretary.writeFilteredImageToResources(last_image, user.getNickname() + "_" + Integer.toString(post.getPost_id()), ".jpg");
				Logger.traceLogger("User:" + user.getNickname() + " has shared a new post with id: " + post.getPost_id() + ".");
				dispose();
				JFrame frame = new MainHub(user, datapack);
				frame.setVisible(true);
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				errorInfo.setText("Error but it shouldnt't happen ;(");
				System.err.println("Error but it shouldnt't happen ;(");
			}
		}
		
		
	}
}
