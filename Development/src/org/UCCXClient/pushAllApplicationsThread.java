package org.UCCXClient;

import java.awt.HeadlessException;
import java.io.IOException;

public class pushAllApplicationsThread extends Thread {
	private String threadName;
	private Thread thread;

	pushAllApplicationsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Pushing Applications! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.pushAll("App");
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newBar.setVisible(false);
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}
