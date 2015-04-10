package org.UCCXClient;

import java.io.IOException;

import javax.swing.JOptionPane;

public class pullAllDataThread extends Thread {
	private String threadName;
	private Thread thread;

	pullAllDataThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Pulling all Data! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.getAppRefs();
			Methods.getCSQRefs();
			Methods.getRGRefs();
			Methods.getSkillRefs();
			Methods.getTeamRefs();
			Methods.getTriggerRefs();
			Methods.getDetailedAppData();
			Methods.getDetailedCSQData();
			Methods.getDetailedRGData();
			Methods.getDetailedSkillData();
			Methods.getDetailedTeamData();
			Methods.getDetailedTriggerData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		JOptionPane
		.showMessageDialog(
				UCCXTable.uccxTable,
				"All UCCX Data pulled from: " + Variables.ip,
				"Message: Success", 1);
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}