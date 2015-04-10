package org.UCCXClient;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class getApplicationsThread extends Thread {
	private String threadName;
	private Thread thread;

	getApplicationsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Getting Applications! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.getAppRefs();
			Methods.getDetailedAppData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		DefaultTableModel model = new DefaultTableModel(
				Variables.applicationTableRows,
				Variables.applicationTableColumns) {
			
			private static final long serialVersionUID = 7595516555737220458L;
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