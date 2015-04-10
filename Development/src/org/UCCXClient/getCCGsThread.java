package org.UCCXClient;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class getCCGsThread extends Thread {
	private String threadName;
	private Thread thread;

	getCCGsThread(String name) {
		threadName = name;
	}

	public void run() {
		try {
			MsgLog.write("Getting CCGs! \n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progressBar newBar = new progressBar();
		newBar.setVisible(true);
		// CODE
		try {
			Methods.getCCGRefs();
			Methods.getDetailedCCGData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newBar.setVisible(false);
		// Finished getting applications
		DefaultTableModel model = new DefaultTableModel(Variables.ccgTableRows,
				Variables.ccgTableColumns) {
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		};
		UCCXTable.uccxTable.setModel(model);
		JOptionPane
				.showMessageDialog(
						UCCXTable.uccxTable,
						"Looks like a little bit of data? \n That's because there is! \n CCGs have a lot of fields that would have taken longer to code.",
						"Uninformed Message: L4ZY", 1);
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}