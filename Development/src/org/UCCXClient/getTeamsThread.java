package org.UCCXClient;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class getTeamsThread extends Thread {
	private String threadName;
	private Thread thread;

	getTeamsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Getting all Teams \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		// Methods.getApplications();
		try {
			Methods.getTeamRefs();
			Methods.getDetailedTeamData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		DefaultTableModel model = new DefaultTableModel(
				Variables.teamTableRows, Variables.teamTableColumns) {
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		};
		UCCXTable.uccxTable.setModel(model);
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}