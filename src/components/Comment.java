package components;

import java.io.Serializable;

import users.User;

public class Comment implements Serializable{
	private User owner;
	private String content;
	
	public Comment(User owner, String content) {
		super();
		this.owner = owner;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getOwner() {
		return owner;
	}
	
	
	
}
