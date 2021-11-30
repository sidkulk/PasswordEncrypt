package view;

import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import model.UserCredentials;
import services.ApplicationServiceClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4181675718289261135L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	protected static LoginPane frame;
	protected static String username;
	protected static String password;
	protected ApplicationServiceClass appService = new ApplicationServiceClass();
	protected static User currentLoggedInUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginPane();
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
	public LoginPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 656);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(195, 43, 167, 45);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(42, 170, 117, 36);
		contentPane.add(lblNewLabel_1);

		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.BOLD, 20));
		usernameTxt.setBounds(186, 170, 309, 36);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(42, 253, 117, 36);
		contentPane.add(lblNewLabel_1_1);

		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordTxt.setBounds(186, 253, 309, 36);
		contentPane.add(passwordTxt);

		JButton loginBtn = new JButton("LOGIN");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernameTxt.getText();
				password = new String(passwordTxt.getPassword());

				if (username.isBlank() || password.isBlank()) {
					showMessageDialog(null, "You left some fields empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					currentLoggedInUser = appService.logUserIn(new UserCredentials(username, password));
					if (currentLoggedInUser != null) {
						dispose();
						System.out.println("User login: " + currentLoggedInUser);
//						HomePane homePane = new HomePane();
//						homePane.setVisible(true);
					} else {
						showMessageDialog(null, "User doesn't exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginBtn.setBounds(195, 360, 166, 36);
		contentPane.add(loginBtn);

		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterPane registerPane = new RegisterPane();
				registerPane.setVisible(true);
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		registerBtn.setBounds(195, 515, 166, 36);
		contentPane.add(registerBtn);

		JLabel lblNewLabel_1_1_1 = new JLabel("New user? click on register");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(133, 451, 290, 36);
		contentPane.add(lblNewLabel_1_1_1);
	}
}
