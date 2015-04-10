package org.UCCXClient;

import java.awt.HeadlessException;
import java.io.IOException;

public class pushAllTriggersThread extends Thread {
	private String threadName;
	private Thread thread;

	pushAllTriggersThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Pushing Triggers!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.pushAll("Trigger");
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
