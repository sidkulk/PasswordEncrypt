package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import services.ApplicationServiceClass;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4420147748518204589L;
	private JPanel contentPane;
	private JTextField usernametxt;
	private JPasswordField passwordTxt;
	private JPasswordField confPasswordTxt;
	private JTextField nicknameTxt;
	private JTextField schoolNametxt;
	protected static String username;
	protected static String password;
	protected static String confPassword;
	protected static String nickname;
	protected static String schoolname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPane frame = new RegisterPane();
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
	public RegisterPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 732);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(212, 11, 166, 43);
		contentPane.add(lblNewLabel);

		JLabel lblEnterUsername = new JLabel("Enter username");
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterUsername.setBounds(36, 101, 180, 35);
		contentPane.add(lblEnterUsername);

		usernametxt = new JTextField();
		usernametxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernametxt.setBounds(253, 101, 316, 35);
		contentPane.add(usernametxt);
		usernametxt.setColumns(10);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewPassword.setBounds(36, 175, 180, 35);
		contentPane.add(lblNewPassword);

		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordTxt.setBounds(253, 175, 316, 35);
		contentPane.add(passwordTxt);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmPassword.setBounds(36, 247, 207, 35);
		contentPane.add(lblConfirmPassword);

		confPasswordTxt = new JPasswordField();
		confPasswordTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		confPasswordTxt.setBounds(253, 247, 316, 35);
		contentPane.add(confPasswordTxt);

		JLabel lblNewLabel_1 = new JLabel("Security question 1: What is your nicknme?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(105, 322, 380, 35);
		contentPane.add(lblNewLabel_1);

		nicknameTxt = new JTextField();
		nicknameTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		nicknameTxt.setBounds(121, 367, 347, 35);
		contentPane.add(nicknameTxt);
		nicknameTxt.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Security question 2: What is the name of your first school?");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(59, 437, 472, 35);
		contentPane.add(lblNewLabel_1_1);

		schoolNametxt = new JTextField();
		schoolNametxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		schoolNametxt.setColumns(10);
		schoolNametxt.setBounds(121, 482, 347, 35);
		contentPane.add(schoolNametxt);

		JButton registerBtn = new JButton("REGISTER");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernametxt.getText().trim();
				password = new String(passwordTxt.getPassword()).trim();
				confPassword = new String(confPasswordTxt.getPassword()).trim();
				nickname = nicknameTxt.getText().trim();
				schoolname = schoolNametxt.getText().trim();

				if (username.isBlank() || password.isBlank() || confPassword.isBlank() || nickname.isBlank()
						|| schoolname.isBlank()) {
					JOptionPane.showMessageDialog(null, "You left some fields BLANK!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else if (!password.equalsIgnoreCase(confPassword)) {
					JOptionPane.showMessageDialog(null, "Password didn't match! Have another go.", "WARNING",
							JOptionPane.WARNING_MESSAGE);
				} else {
					ApplicationServiceClass appService = new ApplicationServiceClass();
					boolean isRegistered = appService
							.registerNewUser(new User(appService.getRandomPasswordId(100), username,
									password, nickname, schoolname));
					if (!isRegistered) {
						JOptionPane.showMessageDialog(null, "Error registering user!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"User: " + username + " registered! Welcome, you'll be redirected to login page",
								"SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						LoginPane loginPane = new LoginPane();
						loginPane.setVisible(true);
					}
				}
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		registerBtn.setBounds(142, 528, 305, 35);
		contentPane.add(registerBtn);

		JButton loginBtn = new JButton("LOGIN INSTEAD");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginPane loginPane = new LoginPane();
				loginPane.setVisible(true);
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginBtn.setBounds(132, 622, 326, 35);
		contentPane.add(loginBtn);
	}

}
