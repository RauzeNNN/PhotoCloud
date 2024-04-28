package driver;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import components.*;
import gui.*;
import photoEditing.*;
import users.*;


public class Main {

	public static void main(String[] args) throws IOException {
		
		/************** Pledge of Honor ******************************************
		I hereby certify that I have completed this programming project on my own
		without any help from anyone else. The effort in the project thus belongs
		completely to me. I did not search for a solution, or I did not consult any
		program written by others or did not copy any program from other sources. I
		read and followed the guidelines provided in the project description.
		READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
		SIGNATURE: Mehmet Harmanlı, 79269
		*************************************************************************/

// To create starting data.
//		DataPack datapack = new DataPack();
//		datapack.write();
		
		//Reads data from where it executes.
		DataPack datapack = DataPack.read();

		JFrame frame = new LogInPage(datapack);	
		frame.setVisible(true);
		
		// This provides me to execute some code just before exit. (It is a very powerful thing, I can't believe we didn't learn this in the lectures.)
		Runtime.getRuntime().addShutdownHook(new Thread()   //learned from internet
	    {
	      public void run()
	      {
	        datapack.write();
	      }
	    });
	
	}
	/*
	 * USERNAMES, PASSWORDS AND USER TYPES (I did not implement a "forgot password" part :D)
	 * RauzeNNN 134 admin
	 * freeGuy 000 Free
	 * freeGuy2 0101 Free
	 * xxKralTRxx toxicguy free
	 * LinuxPenguin kali free
	 * hoobyMonster hobbyist hobbyist
	 * funny funisfun42 hobbyist
	 * sunny light hobbyist
	 * S_Jobs apple Professional
	 * B_Gates microsoft Professional
	 * J_Bezos amazon Professional
	 * E_Musk tesla Professional
	 * 
	 * 
	 */
	
}


