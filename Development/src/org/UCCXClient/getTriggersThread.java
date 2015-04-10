package org.UCCXClient;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class getTriggersThread extends Thread {
	private String threadName;
	private Thread thread;

	getTriggersThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Getting Triggers! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.getTriggerRefs();
			Methods.getDetailedTriggerData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		DefaultTableModel model = new DefaultTableModel(
				Variables.trigTableRows, Variables.trigTableColumns) {
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false };

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