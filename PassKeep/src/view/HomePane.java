package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Password;
import model.UserPasswordData;
import services.ApplicationServiceClass;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2895351700335423696L;
	private JPanel contentPane;
	private JTable table;
	private JTextField passwordTitleTxt;
	private JTextField passwordValueTxt;
	public DefaultTableModel tableModel;
	private static String passwordTitle;
	private static String passwordValue;
	private static Integer passwordId;
	private ApplicationServiceClass applicationServiceClass = new ApplicationServiceClass();
	private static final int columnZero = 0;
	private static final int columnOne = 1;
	private static final int columnTwo = 2;
	private static int row;
	private static HashMap<Integer, Password> userData;

	static {
		userData = new HashMap<>();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePane frame = new HomePane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 23, 560, 455);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();

				System.out.println("Row data selected : " + table.getValueAt(row, columnOne).toString() + "   "
						+ table.getValueAt(row, columnTwo).toString());

				passwordId = Integer.parseInt(table.getValueAt(row, columnZero).toString());
				passwordTitle = table.getValueAt(row, columnOne).toString();
				passwordValue = table.getValueAt(row, columnTwo).toString();

				passwordTitleTxt.setText(passwordTitle);
				passwordValueTxt.setText(passwordValue);
			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		tableModel = new DefaultTableModel();
		Object[] column = { "P_id", "Password Title", "Password Value" };
		String[] passwordData = new String[3];
		tableModel.setColumnIdentifiers(column);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
//		userData = applicationServiceClass.getUserPassword(LoginPane.currentLoggedInUser.getU_id());
		userData = applicationServiceClass.getUserPassword(ApplicationServiceClass.currentlyLoggedInUser.getU_id());

		for (Map.Entry<Integer, Password> iter : userData.entrySet()) {
			passwordData[0] = Integer.toString(iter.getKey());
			passwordData[1] = iter.getValue().getPasswordTitle();
			passwordData[2] = iter.getValue().getPasswordValue();
			tableModel.addRow(passwordData);
		}

		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		/*
		 * ADD NEW ENTRY ACTION
		 */

		JButton addNewEntryBtn = new JButton("Add Entry");
		addNewEntryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordTitle = passwordTitleTxt.getText();
				passwordValue = passwordValueTxt.getText();

				if (passwordTitle.isBlank() || passwordValue.isBlank()) {
					JOptionPane.showMessageDialog(null, "Check input fields!", "Blank Fields",
							JOptionPane.ERROR_MESSAGE);
				} else {
					UserPasswordData password = new UserPasswordData(
							applicationServiceClass.getRandomPasswordId(100), passwordTitle,
							passwordValue, ApplicationServiceClass.currentlyLoggedInUser.getU_id());
					System.out.println("Entry to be saved: " + password);
					boolean isPasswordSaved = applicationServiceClass.saveNewPassword(password);

					if (!isPasswordSaved) {
						JOptionPane.showMessageDialog(null, "Oops! Unable to save entry!", "Fatal Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Success! New password added!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						tableModel.setRowCount(0);
						passwordTitleTxt.setText("");
						passwordValueTxt.setText("");
					}
				}
				try {
					Thread.sleep(800);
					showUpdatedUserData();

				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		addNewEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addNewEntryBtn.setBounds(10, 279, 178, 35);
		contentPane.add(addNewEntryBtn);

		/*
		 * REMOVE SELECTED FIELD ACTION
		 */

		JButton removeEntryBtn = new JButton("Remove Selected");
		removeEntryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					row = table.getSelectedRow();
					passwordId = Integer.parseInt(table.getModel().getValueAt(row, columnZero).toString());
					passwordTitle = table.getModel().getValueAt(row, columnOne).toString();
					passwordValue = table.getModel().getValueAt(row, columnTwo).toString();

					int getResponse = JOptionPane.showConfirmDialog(null, "Confirm delete entry?", "Confirmation",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (getResponse == 1) {
						System.out.println("Deletion canceled");
					} else {
						Password password = new Password(passwordId, passwordTitle, passwordValue);
						boolean isDeleted = applicationServiceClass.deleteSelectedRow(password);
						if (!isDeleted) {
							JOptionPane.showMessageDialog(null, "Oops! Unable to delete entry!", "Fatal Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Success! password Deleted!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Select a row to delete the entry!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				try {
					tableModel.setRowCount(0);
					Thread.sleep(500);
					showUpdatedUserData();
					passwordTitleTxt.setText("");
					passwordValueTxt.setText("");
					row = -1;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		removeEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		removeEntryBtn.setBounds(10, 365, 178, 35);
		contentPane.add(removeEntryBtn);

		JLabel lblNewLabel = new JLabel("Password Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 80, 124, 27);
		contentPane.add(lblNewLabel);

		passwordTitleTxt = new JTextField();
		passwordTitleTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordTitleTxt.setBounds(144, 80, 249, 35);
		contentPane.add(passwordTitleTxt);
		passwordTitleTxt.setColumns(10);

		JLabel lblPasswordValue = new JLabel("Password Value");
		lblPasswordValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPasswordValue.setBounds(10, 142, 124, 27);
		contentPane.add(lblPasswordValue);

		passwordValueTxt = new JTextField();
		passwordValueTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordValueTxt.setColumns(10);
		passwordValueTxt.setBounds(144, 142, 249, 35);
		contentPane.add(passwordValueTxt);

		/*
		 * UPDATE SELECTED FIELD ACTION
		 */

		JButton updateEntryBtn = new JButton("Update");
		updateEntryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(
							"Row data selected: " + passwordId + "   " + passwordTitle + "   " + passwordValue);
					passwordTitle = passwordTitleTxt.getText();
					passwordValue = passwordValueTxt.getText();

					if (passwordTitle.isBlank() || passwordValue.isBlank()) {
						JOptionPane.showMessageDialog(null, "Check input fields!", "Blank Fields",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int getResponse = JOptionPane.showConfirmDialog(null, "Confirm update entry?", "Confirmation",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (getResponse == 1) {
							System.out.println("Update canceled!");
						} else {
							tableModel.setNumRows(0);
							boolean isUpdated = applicationServiceClass
									.updateSelectedRow(new Password(passwordId, passwordTitle, passwordValue));

							if (!isUpdated) {
								JOptionPane.showMessageDialog(null, "Oops! Unable to Update entry!", "Fatal Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Select a row to Update the entry!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				tableModel.setRowCount(0);
				passwordTitleTxt.setText("");
				passwordValueTxt.setText("");
				row = -1;
				try {
					Thread.sleep(500);
					showUpdatedUserData();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		updateEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		updateEntryBtn.setBounds(232, 279, 161, 35);
		contentPane.add(updateEntryBtn);

		/*
		 * CLEAR TEXTFIELD ACTION
		 */

		JButton clearTextBtn = new JButton("Clear Textfield");
		clearTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = -1;
				passwordTitleTxt.setText("");
				passwordValueTxt.setText("");
			}
		});
		clearTextBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		clearTextBtn.setBounds(232, 365, 161, 35);
		contentPane.add(clearTextBtn);

		/*
		 * LOGOUT ACTION
		 */

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationServiceClass.cachedUserData.clear();
				System.out.println("Cached data cleared");
				dispose();
				LoginPane pane = new LoginPane();
				pane.setVisible(true);
			}
		});
		logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		logoutBtn.setBounds(131, 443, 161, 35);
		contentPane.add(logoutBtn);
	}

	public void showUpdatedUserData() {
		String[] passwordData = new String[3];
		userData = ApplicationServiceClass.cachedUserData;

		for (Map.Entry<Integer, Password> iter : userData.entrySet()) {
			passwordData[0] = Integer.toString(iter.getKey());
			passwordData[1] = iter.getValue().getPasswordTitle();
			passwordData[2] = iter.getValue().getPasswordValue();
			tableModel.addRow(passwordData);
		}
	}
}
