package org.UCCXClient;

public class UCCXClient {
	public static void main(String[] args) {
		UCCXClientMain connectionGUI = new UCCXClientMain();
		connectionGUI.setVisible(true);
		while (!Variables.connected) {
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}