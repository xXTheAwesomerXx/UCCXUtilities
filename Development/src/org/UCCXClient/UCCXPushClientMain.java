package org.UCCXClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Hammy Joucoo
 */
public class UCCXPushClientMain extends JFrame {
	private static final long serialVersionUID = -1721741526330260629L;

	public UCCXPushClientMain() {
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		try {
			try {
				if (serverField.getText() != null
						&& usernameField.getText() != null
						&& String.valueOf(serverPasswordField.getPassword()) != null) {
					Variables.puship = serverField.getText();
					Variables.pushuser = usernameField.getText();
					Variables.pushpass = String.valueOf(serverPasswordField
							.getPassword());
					MsgLog.write("Establishing Connection - "
							+ Variables.pushuser + "@" + Variables.puship
							+ " \n");
					if (Variables.pushconnected == false) {
						testConnectionThread connectThread = new testConnectionThread(
								"CONNECTION");
						connectThread.start();
					}
				} else {
					JOptionPane.showMessageDialog(okButton,
							"Please enter required fields!",
							"Uninformed Message: L4ZY1", 0);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initComponents() {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		serverLabel = new JLabel();
		userLabel = new JLabel();
		passwordLabel = new JLabel();
		serverField = new JTextField();
		usernameField = new JTextField();
		serverPasswordField = new JPasswordField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

		// ======== this ========
		setTitle("UCCX Server Credentials (Push)");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{

				// ---- serverLabel ----
				serverLabel.setText("UCCX Server:");

				// ---- userLabel ----
				userLabel.setText("UCCX Username:");

				// ---- passwordLabel ----
				passwordLabel.setText("UCCX Password:");

				GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
				contentPanel.setLayout(contentPanelLayout);
				contentPanelLayout
						.setHorizontalGroup(contentPanelLayout
								.createParallelGroup()
								.addGroup(
										contentPanelLayout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														contentPanelLayout
																.createParallelGroup()
																.addGroup(
																		contentPanelLayout
																				.createSequentialGroup()
																				.addComponent(
																						serverLabel)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						56,
																						Short.MAX_VALUE)
																				.addComponent(
																						serverField,
																						GroupLayout.PREFERRED_SIZE,
																						121,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		contentPanelLayout
																				.createSequentialGroup()
																				.addGroup(
																						contentPanelLayout
																								.createParallelGroup()
																								.addComponent(
																										userLabel)
																								.addComponent(
																										passwordLabel))
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						40,
																						Short.MAX_VALUE)
																				.addGroup(
																						contentPanelLayout
																								.createParallelGroup(
																										GroupLayout.Alignment.LEADING,
																										false)
																								.addComponent(
																										usernameField,
																										GroupLayout.DEFAULT_SIZE,
																										121,
																										Short.MAX_VALUE)
																								.addComponent(
																										serverPasswordField,
																										GroupLayout.DEFAULT_SIZE,
																										121,
																										Short.MAX_VALUE))))
												.addContainerGap()));
				contentPanelLayout
						.setVerticalGroup(contentPanelLayout
								.createParallelGroup()
								.addGroup(
										contentPanelLayout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														contentPanelLayout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		serverLabel)
																.addComponent(
																		serverField,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(
														contentPanelLayout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		userLabel)
																.addComponent(
																		usernameField,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(
														contentPanelLayout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		passwordLabel)
																.addComponent(
																		serverPasswordField,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addContainerGap(22,
														Short.MAX_VALUE)));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {
						0, 85, 80 };
				((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 0.0 };

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane
								.showMessageDialog(
										cancelButton,
										"You have canceled adding a server to push data to!",
										"Uninformed Message: L4ZY1", 0);
						dispose();
					}
				});
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel serverLabel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField serverField;
	private JTextField usernameField;
	private JPasswordField serverPasswordField;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;

	public class testConnectionThread extends Thread {
		private String threadName;
		private Thread thread;

		testConnectionThread(String name) {
			threadName = name;
		}

		@Override
		public void run() {
			progressBarConnect newBar = new progressBarConnect(
					"Please while a SSL Connection to " + Variables.puship
							+ " is established [push server]");
			newBar.setVisible(true);
			try {
				if (Methods.testPushConnection()) {
					Variables.pushconnected = true;
					Variables.pushconnectionFailed = false;
					if (UCCXPushClientMain.this.isVisible()) {
						UCCXPushClientMain.this.setVisible(false);
						ccgTable ccgs = new ccgTable();
						ccgs.setVisible(true);
					}
				} else {
					Variables.pushconnected = false;
					Variables.pushconnectionFailed = true;
					newBar.setVisible(false);
					// Reset variables to null
					Variables.puship = null;
					Variables.pushpass = null;
					Variables.pushuser = null;
					MsgLog.write("Connection to push server failed! \n");
					JOptionPane
							.showMessageDialog(
									UCCXPushClientMain.this.contentPanel,
									"Connection failed, check your connection and try again!",
									"Connection Error", 0);
					
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newBar.setVisible(false);
		}

		@Override
		public void start() {
			if (thread == null) {
				thread = new Thread(this, threadName);
				thread.start();
			}
		}
	}

	// ProgressBar
	public final class progressBarConnect extends JFrame {
		private static final long serialVersionUID = 6374162146237387106L;
		private JLabel a;
		private JProgressBar b;

		public progressBarConnect(String text) {
			progressBarConnect localw = this;
			this.a = new JLabel();
			localw.b = new JProgressBar();
			localw.setTitle("UCCX - Establishing Connection");
			localw.setResizable(false);
			localw.setAlwaysOnTop(true);
			localw.setDefaultCloseOperation(0);
			Container localContainer = localw.getContentPane();
			localw.a.setText(text);
			localw.b.setMaximum(0);
			localw.b.setIndeterminate(true);
			GroupLayout localGroupLayout = new GroupLayout(localContainer);
			localContainer.setLayout(localGroupLayout);
			localGroupLayout
					.setHorizontalGroup(localGroupLayout
							.createParallelGroup()
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									localGroupLayout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													localGroupLayout
															.createParallelGroup(
																	GroupLayout.Alignment.TRAILING)
															.addComponent(
																	localw.b,
																	GroupLayout.Alignment.LEADING,
																	-1, 232,
																	32767)
															.addComponent(
																	localw.a,
																	GroupLayout.Alignment.LEADING,
																	-1, 232,
																	32767))
											.addContainerGap()));
			localGroupLayout
					.setVerticalGroup(localGroupLayout
							.createParallelGroup()
							.addGroup(
									localGroupLayout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(localw.a)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED,
													16, 32767)
											.addComponent(localw.b, -2, -1, -2)
											.addContainerGap()));
			localw.pack();
			localw.setLocationRelativeTo(localw.getOwner());
		}
	}
}
