package org.UCCXClient;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class getRGsThread extends Thread {
	private String threadName;
	private Thread thread;

	getRGsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Getting Resource Groups! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.getRGRefs();
			Methods.getDetailedRGData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		DefaultTableModel model = new DefaultTableModel(Variables.rgTableRows,
				Variables.rgTableColumns) {
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false };

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