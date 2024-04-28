package driver;

import java.io.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import components.Post;
import users.User;

public class DataPack implements Serializable{
	/*
	 * General data which should be carried across program.
	 * It allows to write to file.
	 */
	
	private ArrayList<Post> posts;
	private ArrayList<User> users;
	
	public DataPack() {
		super();
		this.posts = new ArrayList<>();
		this.users = new ArrayList<>();
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void write() {
		try{
		    FileOutputStream wData = new FileOutputStream("allData");
		    ObjectOutputStream wStream = new ObjectOutputStream(wData);
		    
		    wStream.writeObject(this);
		    wStream.flush();
		    wStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public static DataPack read() {
		DataPack pack = null;
		try{
		    FileInputStream rData = new FileInputStream("allData");
		    ObjectInputStream rStream = new ObjectInputStream(rData);

		    pack = (DataPack) rStream.readObject();
		    rStream.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
		return pack;
	}
	
}
