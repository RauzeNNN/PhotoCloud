package users;

import components.Post;
import photoEditing.ImageMatrix;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable{
	/*
	 * abstract class for all users.
	 */
	protected String email;		//unique
	protected String nickname;	//unique
	protected String password;
	protected String name;
	protected String surname;
	protected int age;
	protected Permissions[] perms;
	private ArrayList<Post> pPosts;
	

	protected ImageMatrix profile_photo;
	
	public User(String email, String nickname, String password, String name, String surname, int age, ImageMatrix pp) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.pPosts = new ArrayList<>();
		this.profile_photo = pp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Permissions[] getPerms() {
		return perms;
	}

	public String getNickname() {
		return nickname;
	}

	public ImageMatrix getProfile_photo() {
		return profile_photo;
	}

	public void setProfile_photo(ImageMatrix profile_photo) {
		this.profile_photo = profile_photo;
	}
	
	public ArrayList<Post> getPosts() {
		return pPosts;
	}
	
	
}
