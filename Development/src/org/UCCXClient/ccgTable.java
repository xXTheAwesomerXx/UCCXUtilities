package org.UCCXClient;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author Hammy Joucoo
 */

public class ccgTable extends JFrame {
	private static final long serialVersionUID = -6754286508019030389L;
	public ccgTable() {
		initComponents();
	}

	private void addButtonActionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) ccgTable.getModel();
		model.addRow(new Object[] { "", "" });
	}

	private void deleteButtonActionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) ccgTable.getModel();
		model.removeRow(ccgTable.getSelectedRow());
	}

	private void okButtonActionPerformed(ActionEvent e) throws IOException {
		Variables.replaceCCGVariables = new String[ccgTable.getRowCount()][2];
		for (int i = 0; i < ccgTable.getRowCount(); i++) {
			Variables.replaceCCGVariables[i][0] = ccgTable.getValueAt(i, 0)
					.toString();
			Variables.replaceCCGVariables[i][1] = ccgTable.getValueAt(i, 1)
					.toString();
			MsgLog.write("Old: " + Variables.replaceCCGVariables[i][0]
					+ " New: " + Variables.replaceCCGVariables[i][1]);
		}
		dispose();
	}

	private void initComponents() {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		ccgTable = new JTable();
		buttonBar = new JPanel();
		addButton = new JButton();
		deleteButton = new JButton();
		okButton = new JButton();

		// ======== this ========
		setTitle("UCCX - Custom Call Control Groups");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{

				// ======== scrollPane1 ========
				{

					// ---- ccgTable ----
					ccgTable.setModel(new DefaultTableModel(new Object[][] { {
							null, "" }, }, new String[] { "Old CCG Id",
							"New CCG Id" }) {
						/**
								 * 
								 */
								private static final long serialVersionUID = 6009154357208420156L;
						Class<?>[] columnTypes = new Class<?>[] { String.class,
								String.class };

						@Override
						public Class<?> getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
					scrollPane1.setViewportView(ccgTable);
				}

				GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
				contentPanel.setLayout(contentPanelLayout);
				contentPanelLayout
						.setHorizontalGroup(contentPanelLayout
								.createParallelGroup().addComponent(
										scrollPane1, GroupLayout.DEFAULT_SIZE,
										508, Short.MAX_VALUE));
				contentPanelLayout
						.setVerticalGroup(contentPanelLayout
								.createParallelGroup().addComponent(
										scrollPane1, GroupLayout.DEFAULT_SIZE,
										194, Short.MAX_VALUE));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {
						0, 85, 0, 0 };
				((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 0.0, 0.0 };

				// ---- addButton ----
				addButton.setText("Add Row");
				addButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addButtonActionPerformed(e);
					}
				});
				buttonBar.add(addButton, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- deleteButton ----
				deleteButton.setText("Remove Row");
				deleteButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						deleteButtonActionPerformed(e);
					}
				});
				buttonBar.add(deleteButton, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							okButtonActionPerformed(e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(3, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
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
	private JScrollPane scrollPane1;
	private JTable ccgTable;
	private JPanel buttonBar;
	private JButton addButton;
	private JButton deleteButton;
	private JButton okButton;
}