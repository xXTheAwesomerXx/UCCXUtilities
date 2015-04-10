package org.UCCXClient;

import java.awt.*;

import javax.swing.*;

/**
 * @author Hammy Joucoo
 */
public class UCCXLogGUI extends JFrame {
	private static final long serialVersionUID = -763960965427064180L;
	public UCCXLogGUI() {
		initComponents();
	}

	public static void setLogText(String s) {
		logArea.setText(s);
		JScrollBar scrollbar = scrollPane1.getVerticalScrollBar();
		scrollbar.setValue(scrollbar.getMaximum());
	}

	private void initComponents() {
		scrollPane1 = new JScrollPane();
		logArea = new JTextArea();

		// ======== this ========
		setTitle("UCCX - Log");
		Container contentPane = getContentPane();

		// ======== scrollPane1 ========
		{
			logArea.setEditable(false);
			logArea.setText(Variables.logString);
			scrollPane1.setViewportView(logArea);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane1,
										GroupLayout.DEFAULT_SIZE, 617,
										Short.MAX_VALUE).addContainerGap()));
		contentPaneLayout.setVerticalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane1,
										GroupLayout.DEFAULT_SIZE, 311,
										Short.MAX_VALUE).addContainerGap()));
		pack();
		setLocationRelativeTo(getOwner());
	}

	private static JScrollPane scrollPane1;
	public static JTextArea logArea;
}
