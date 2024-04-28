package components;

import users.User;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import photoEditing.ImageMatrix;

public class Post implements Serializable{
	public static int ID = 1;
	public int post_id;
	public ImageMatrix image;
	public String description;
	public User owner;
	public int like;
	public int dislike;
	public ArrayList<Comment> comments;
	
	
	public Post(ImageMatrix image, String description, User owner) {
		super();
		this.image = image;
		this.description = description;
		this.post_id = ID++;
		this.owner = owner;
		this.comments = new ArrayList<>();
		owner.getPosts().add(this);
	}
	
	public int getPost_id() {
		return post_id;
	}

	public String getDescription() {
		return description;
	}

	public User getOwner() {
		return owner;
	}

	public int getLike() {
		return like;
	}
	
	public void incrementLike() {
		this.like += 1;
	}
	
	public void decreaseLike() {
		this.like -= 1;
	}

	public int getDislike() {
		return dislike;
	}
	
	public void incrementDislike() {
		this.dislike += 1;
	}
	
	public void decreaseDislike() {
		this.dislike -= 1;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public ImageMatrix getImage() {
		return image;
	}

	public void setImage(ImageMatrix image) {
		this.image = image;
	}
}
