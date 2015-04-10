package org.UCCXClient;

import java.awt.HeadlessException;
import java.io.IOException;

public class pushAllCCGsThread extends Thread {
	private String threadName;
	private Thread thread;

	pushAllCCGsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Pushing CCGs! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.pushAll("CCG");
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
