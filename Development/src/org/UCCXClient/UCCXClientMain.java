package org.UCCXClient;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;

/**
 * @author Hammy Joucoo
 */
public class UCCXClientMain extends JFrame {
	private static final long serialVersionUID = -171707579146619366L;

	public UCCXClientMain() {
		initComponents();
		UCCXLogGUI logGUI = new UCCXLogGUI();
		logGUI.setVisible(true);
	}

	// Custom Methods:
	private void okButtonActionPerformed(ActionEvent e) {
		try {
			try {
				if (serverField.getText() != null
						&& usernameField.getText() != null
						&& String.valueOf(serverPasswordField.getPassword()) != null) {
					Variables.ip = serverField.getText();
					Variables.user = usernameField.getText();
					Variables.pass = String.valueOf(serverPasswordField
							.getPassword());
					MsgLog.write("Establishing Connection - " + Variables.user
							+ "@" + Variables.ip + " \n");
					if (Variables.connected == false) {
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

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
		setTitle("UCCX Client Credentials");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				okButton.setText("Connect");
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
						cancelButtonActionPerformed(e);
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
			UCCXTable gui = new UCCXTable();
			progressBarConnect newBar = new progressBarConnect(
					"Please while a SSL Connection to " + Variables.ip
							+ " is established");
			newBar.setVisible(true);
			try {
				if (Methods.testConnection()) {
					Variables.connected = true;
					Variables.connectionFailed = false;
					if (UCCXClientMain.this.isVisible()) {
						UCCXClientMain.this.setVisible(false);
					}
					if (!gui.isVisible()) {
						gui.setVisible(true);
					}
				} else {
					Variables.connected = false;
					Variables.connectionFailed = true;
					newBar.setVisible(false);
					
					JOptionPane
							.showMessageDialog(
									UCCXClientMain.this.contentPanel,
									"Connection failed, check your connection and try again!",
									"Connection Error", 0);
					
				}
			} catch (HeadlessException e) {
				try {
					MsgLog.write("Connection failed! \n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e) {
				try {
					MsgLog.write("Connection failed! \n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
