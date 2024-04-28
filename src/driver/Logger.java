package driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Logger {
	
	public static void traceLogger(String message) {
		try {
			File logfile = new File("traceLog.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			
			FileWriter logger = new FileWriter(logfile, true);
			DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			logger.write("[" + time.format(now) + "][INFO]" + message + "\n");
			logger.close();
			
		}
		catch(IOException e) {
			// this is impossible to happen
			System.err.println("Trace log file error.");
		}
		
	}
	
	public static void errorLogger(String message) {
		try {
			File logfile = new File("errorLog.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			
			FileWriter logger = new FileWriter(logfile, true);
			DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			logger.write("[" + time.format(now) + "][ERROR]" + message + "\n");
			logger.close();
			
		}
		catch(IOException e) {
			// this is impossible to happen
			System.err.println("Error log file error.");
		}
		
	}
}
