package org.UCCXClient;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Hammy Joucoo
 */
public class UCCXTable extends JFrame {
	private static final long serialVersionUID = -4889949394086580268L;

	public UCCXTable() {
		initComponents();
	}

	private void getAppMenuItemActionPerformed(ActionEvent e) {
		getApplicationsThread thread = new getApplicationsThread("Get Apps");
		thread.start();
	}

	private void getCSQMenuItemActionPerformed(ActionEvent e) {
		getCSQsThread thread = new getCSQsThread("Get CSQs");
		thread.start();
	}

	private void getCCGMenuItemActionPerformed(ActionEvent e) {
		getCCGsThread thread = new getCCGsThread("Get CCGs");
		thread.start();
	}
	
	private void getResourceMenuItemActionPerformed(ActionEvent e) {
		
	}

	public void getRGMenuItemActionPerformed(ActionEvent e) {
		getRGsThread thread = new getRGsThread("Get RGs");
		thread.start();
	}

	private void getSkillMenuItemActionPerformed(ActionEvent e) {
		getSkillsThread thread = new getSkillsThread("Get Skills");
		thread.start();
	}

	private void getTeamsMenuItemActionPerformed(ActionEvent e) {
		getTeamsThread thread = new getTeamsThread("Get Teams");
		thread.start();
	}

	private void getTrigMenuItemActionPerformed(ActionEvent e) {
		getTriggersThread thread = new getTriggersThread("Get Triggers");
		thread.start();
	}

	private void pullAllDataMenuItemActionPerformed(ActionEvent e) {
		pullAllDataThread thread = new pullAllDataThread("Pull all Data");
		thread.start();
	}

	private void exportTableMenuItemActionPerformed(ActionEvent e)
			throws IOException {
		File myFile = new File(System.getProperty("user.dir") + File.separator
				+ "\\csvData\\" + "csv_data.csv");
		File parentDir = myFile.getParentFile();
		if (!parentDir.exists())
			parentDir.mkdirs();
		ExcelExporter ee = new ExcelExporter(uccxTable, "", false);
		ee.storeTableAsCSV(myFile, uccxTable);
		MsgLog.write("Exported the data in table! \n");
	}

	private void pushAppsMenuItemActionPerformed(ActionEvent e) throws IOException {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllApplicationsThread thread = new pushAllApplicationsThread(
						"Push Apps");
				MsgLog.write("Pushing apps");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushCSQsMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllCSQsThread thread = new pushAllCSQsThread("Push CSQs");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushCCGsMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllCCGsThread thread = new pushAllCCGsThread("Push CCG");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushRGsMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllRGsThread thread = new pushAllRGsThread("Push RGs");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushSkillsMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllSkillsThread thread = new pushAllSkillsThread(
						"Push Skills");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushTeamsMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllTeamsThread thread = new pushAllTeamsThread("Push Team");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void pushTriggersMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				pushAllTriggersThread thread = new pushAllTriggersThread(
						"Push Triggers");
				thread.start();
			} else {
				ccgTable ccgTable = new ccgTable();
				ccgTable.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void setCCGsMenuItemActionPerformed(ActionEvent e) {
		ccgTable gui = new ccgTable();
		gui.setVisible(true);
	}

	private void pushAllDataMenuItemMenuItemActionPerformed(ActionEvent e) {
		if (Variables.puship != null && Variables.pushuser != null
				&& Variables.pushpass != null) {
			if (Variables.replaceCCGVariables != null) {
				try {
					MsgLog.write("Got this far! 1234567890");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				ccgTable gui = new ccgTable();
				gui.setVisible(true);
			}
		} else {
			UCCXPushClientMain pushclient = new UCCXPushClientMain();
			pushclient.setVisible(true);
		}
	}

	private void initComponents() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu();
		pushMenu = new JMenu();
		appMenu = new JMenu();
		getAppMenuItem = new JMenuItem();
		getTrigMenuItem = new JMenuItem();
		getCSQMenuItem = new JMenuItem();
		getCCGMenuItem = new JMenuItem();
		getResourceMenuItem = new JMenuItem();
		getRGMenuItem = new JMenuItem();
		getSkillMenuItem = new JMenuItem();
		getTeamsMenuItem = new JMenuItem();
		pullAllDataMenuItem = new JMenuItem();
		exportMenuItem = new JMenuItem();
		pushAppsMenuItem = new JMenuItem();
		pushCSQsMenuItem = new JMenuItem();
		pushCCGsMenuItem = new JMenuItem();
		pushRGsMenuItem = new JMenuItem();
		pushSkillsMenuItem = new JMenuItem();
		pushTeamsMenuItem = new JMenuItem();
		pushTriggersMenuItem = new JMenuItem();
		pushAllDataMenuItem = new JMenuItem();
		setCCGsMenuItem = new JMenuItem();
		tableScrollPane = new JScrollPane();
		uccxTable = new JTable();

		// ======== this ========
		setTitle("UCCX Client");
		Container contentPane = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ======== menuBar ========
		{

			// ======== pushMenu ========
			{
				pushMenu.setText("Push Data");

				// ======== fileMenu ========
				{
					fileMenu.setText("File");

					// ======== appMenu ========
					{
						appMenu.setText("Get Applications");

						// ---- getAppMenuItem ----
						getAppMenuItem.setText("Get Applications");
						getAppMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								getAppMenuItemActionPerformed(e);
							}
						});
						appMenu.add(getAppMenuItem);

						// ---- getTrigMenuItem ----
						getTrigMenuItem.setText("Get Triggers");
						getTrigMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								getTrigMenuItemActionPerformed(e);
							}
						});
						appMenu.add(getTrigMenuItem);
					}
					fileMenu.add(appMenu);

					// ---- getCSQMenuItem ----
					getCSQMenuItem.setText("Get CSQs");
					getCSQMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getCSQMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getCSQMenuItem);

					// ---- getCCGMenuItem ----
					getCCGMenuItem.setText("Get CCGs");
					getCCGMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getCCGMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getCCGMenuItem);

					// ---- getResourceMenuItem ----
					getResourceMenuItem.setText("Get Resources");
					getResourceMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getResourceMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getResourceMenuItem);

					// ---- getRGMenuItem ----
					getRGMenuItem.setText("Get Resource Groups");
					getRGMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getRGMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getRGMenuItem);

					// ---- getSkillMenuItem ----
					getSkillMenuItem.setText("Get Skills");
					getSkillMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getSkillMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getSkillMenuItem);

					// ---- getTeamsMenuItem ----
					getTeamsMenuItem.setText("Get Teams");
					getTeamsMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getTeamsMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(getTeamsMenuItem);

					// ---- pullAllDataMenuItem ----
					pullAllDataMenuItem.setText("Pull All Data");
					pullAllDataMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							pullAllDataMenuItemActionPerformed(e);
						}
					});
					fileMenu.add(pullAllDataMenuItem);

					// ---- exportMenuItem ----
					exportMenuItem.setText("Export Table");
					exportMenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								exportTableMenuItemActionPerformed(e);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
					fileMenu.add(exportMenuItem);

				}

				// ---- pushAppsMenuItem ----
				pushAppsMenuItem.setText("Push Applications");
				pushAppsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							pushAppsMenuItemActionPerformed(e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				pushMenu.add(pushAppsMenuItem);

				// ---- pushCSQsMenuItem ----
				pushCSQsMenuItem.setText("Push CSQs");
				pushCSQsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushCSQsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushCSQsMenuItem);

				// ---- pushCCGsMenuItem ----
				pushCCGsMenuItem.setText("Push CCGs");
				pushCCGsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushCCGsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushCCGsMenuItem);

				// ---- pushRGsMenuItem ----
				pushRGsMenuItem.setText("Push Resource Groups");
				pushRGsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushRGsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushRGsMenuItem);

				// ---- pushSkillsMenuItem ----
				pushSkillsMenuItem.setText("Push Skills");
				pushSkillsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushSkillsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushSkillsMenuItem);

				// ---- pushTeamsMenuItem ----
				pushTeamsMenuItem.setText("Push Teams");
				pushTeamsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushTeamsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushTeamsMenuItem);

				// ---- pushTriggersMenuItem ----
				pushTriggersMenuItem.setText("Push Triggers");
				pushTriggersMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushTriggersMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushTriggersMenuItem);

				// ---- setCCGsMenuItem ----
				setCCGsMenuItem.setText("Set CCGs");
				setCCGsMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setCCGsMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(setCCGsMenuItem);

				// ---- pushAllDataMenuItem ----
				pushAllDataMenuItem.setText("Push All Data");
				pushAllDataMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pushAllDataMenuItemMenuItemActionPerformed(e);
					}
				});
				pushMenu.add(pushAllDataMenuItem);

			}
			menuBar.add(fileMenu);
			menuBar.add(pushMenu);
		}
		setJMenuBar(menuBar);

		// ======== tableScrollPane ========
		{

			// ---- uccxTable ----
			uccxTable.setAutoCreateRowSorter(true);
			uccxTable.setModel(new DefaultTableModel(
					Variables.applicationTableRows,
					Variables.applicationTableColumns));
			tableScrollPane.setViewportView(uccxTable);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(tableScrollPane,
										GroupLayout.DEFAULT_SIZE, 747,
										Short.MAX_VALUE).addContainerGap()));
		contentPaneLayout.setVerticalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(tableScrollPane,
										GroupLayout.DEFAULT_SIZE, 390,
										Short.MAX_VALUE).addContainerGap()));
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu appMenu;
	private JMenu pushMenu;
	private JMenuItem getAppMenuItem;
	private JMenuItem getTrigMenuItem;
	private JMenuItem getCSQMenuItem;
	private JMenuItem getCCGMenuItem;
	private JMenuItem getResourceMenuItem;
	private JMenuItem getRGMenuItem;
	private JMenuItem getSkillMenuItem;
	private JMenuItem getTeamsMenuItem;
	private JMenuItem pullAllDataMenuItem;
	private JMenuItem exportMenuItem;
	private JMenuItem pushAppsMenuItem;
	private JMenuItem pushCSQsMenuItem;
	private JMenuItem pushCCGsMenuItem;
	private JMenuItem pushRGsMenuItem;
	private JMenuItem pushSkillsMenuItem;
	private JMenuItem pushTeamsMenuItem;
	private JMenuItem pushTriggersMenuItem;
	private JMenuItem setCCGsMenuItem;
	private JMenuItem pushAllDataMenuItem;
	private JScrollPane tableScrollPane;
	static JTable uccxTable;

}
