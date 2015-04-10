package org.UCCXClient;

import java.io.*;
import java.text.*;
import java.util.*;

public class MsgLog {
	protected static String defaultLogFile = "msglog.txt";
	protected static String workingDir = System.getProperty("user.dir");
	protected static String absoluteFilePath = "";
	public static void write(String s) throws IOException {
		String your_os = System.getProperty("os.name").toLowerCase();
		 
		if (your_os.indexOf("win") >= 0) {
 
			//if windows
			absoluteFilePath = workingDir + "\\" + defaultLogFile;
 
		} else if (your_os.indexOf("nix") >= 0 || 
                           your_os.indexOf("nux") >= 0 || 
                           your_os.indexOf("mac") >= 0) {
 
			//if unix or mac 
			absoluteFilePath = workingDir + "/" + defaultLogFile;
 
		}else{
 
			//unknow os?
			absoluteFilePath = workingDir + "/" + defaultLogFile;
 
		}
		write(absoluteFilePath, s);
	}

	public static void write(String f, String s) throws IOException {
		TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy.mm.dd hh:mm:ss ");
		df.setTimeZone(tz);
		String currentTime = df.format(now);

		FileWriter aWriter = new FileWriter(f, true);
		aWriter.write(currentTime + " " + s + "\n\n\n");
		aWriter.write(System.lineSeparator());
		Variables.logString = Variables.logString + s;
		aWriter.flush();
		aWriter.close();
	}
}
