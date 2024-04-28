package users;

import java.awt.image.BufferedImage;

import photoEditing.ImageMatrix;

public class professionalUser extends User{

	public professionalUser(String email, String nickname, String password, String name, String surname, int age, ImageMatrix pp) {
		super(email, nickname, password, name, surname, age, pp);
		// TODO Auto-generated constructor stub
		perms = new Permissions[]{Permissions.BLURRING, Permissions.SHARPENING,
				Permissions.BRIGHTNESS, Permissions.CONTRAST,
				Permissions.EDGE_DETECTION, Permissions.GRAYSCALE};
	}
	
}
