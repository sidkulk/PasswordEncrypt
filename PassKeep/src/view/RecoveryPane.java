package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import services.ApplicationService;
import services.ApplicationServiceClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecoveryPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nicknameTxt;
	private JTextField schoolNameTxt;
	private JTextField usernameTxt;
	private JTextField recoveredUserTxt;
	private JTextField recoveredPassTxt;
	private static String username;
	private static String nickname;
	private static String schoolName;
	private static ApplicationService applicationService;
	private static User recoveredUser;
	private JButton logMeInBtn;

	static {
		applicationService = new ApplicationServiceClass();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecoveryPane frame = new RecoveryPane();
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
	public RecoveryPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Account Recovery");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(165, 11, 178, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Nickname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(26, 109, 146, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Enter School name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(26, 162, 158, 35);
		contentPane.add(lblNewLabel_1_1);

		nicknameTxt = new JTextField();
		nicknameTxt.setFont(new Font("Tahoma", Font.BOLD, 13));
		nicknameTxt.setBounds(208, 115, 242, 26);
		contentPane.add(nicknameTxt);
		nicknameTxt.setColumns(10);

		schoolNameTxt = new JTextField();
		schoolNameTxt.setFont(new Font("Tahoma", Font.BOLD, 13));
		schoolNameTxt.setColumns(10);
		schoolNameTxt.setBounds(208, 168, 242, 26);
		contentPane.add(schoolNameTxt);

		JButton recoverCredentialsBtn = new JButton("Recover Credentials");
		recoverCredentialsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernameTxt.getText();
				nickname = nicknameTxt.getText();
				schoolName = schoolNameTxt.getText();

				if (username.isBlank() || nickname.isBlank() || schoolName.isBlank()) {

				} else {
					recoveredUser = applicationService.recoverUserAccount(username, nickname, schoolName);
					if (recoveredUser != null) {
						recoveredUserTxt.setText(recoveredUser.getUsername());
						recoveredPassTxt.setText(recoveredUser.getPassword());
						logMeInBtn.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Could not recover user: " + usernameTxt.getText(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		recoverCredentialsBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		recoverCredentialsBtn.setBounds(10, 229, 213, 35);
		contentPane.add(recoverCredentialsBtn);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginPane loginPane = new LoginPane();
				loginPane.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		backBtn.setBounds(323, 229, 163, 35);
		contentPane.add(backBtn);

		JLabel lblNewLabel_1_2 = new JLabel("Username:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(26, 325, 112, 35);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Enter Username:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(26, 63, 146, 35);
		contentPane.add(lblNewLabel_1_3);

		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(208, 69, 242, 26);
		contentPane.add(usernameTxt);

		JLabel lblNewLabel_1_2_1 = new JLabel("Password:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(26, 374, 112, 35);
		contentPane.add(lblNewLabel_1_2_1);

		recoveredUserTxt = new JTextField();
		recoveredUserTxt.setEditable(false);
		recoveredUserTxt.setBounds(183, 334, 267, 26);
		contentPane.add(recoveredUserTxt);
		recoveredUserTxt.setColumns(10);

		recoveredPassTxt = new JTextField();
		recoveredPassTxt.setEditable(false);
		recoveredPassTxt.setColumns(10);
		recoveredPassTxt.setBounds(183, 380, 267, 26);
		contentPane.add(recoveredPassTxt);

		logMeInBtn = new JButton("Log Me In");
		logMeInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (recoveredUser != null) {
					dispose();
					System.out.println("User login: " + recoveredUser);
					HomePane homePane = new HomePane();
					homePane.setVisible(true);
				}
			}
		});
		logMeInBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		logMeInBtn.setEnabled(false);
		logMeInBtn.setBounds(175, 429, 158, 35);
		contentPane.add(logMeInBtn);

		JLabel lblNewLabel_2 = new JLabel("recovered account details will appear below..");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(114, 285, 280, 29);
		contentPane.add(lblNewLabel_2);
	}
}
