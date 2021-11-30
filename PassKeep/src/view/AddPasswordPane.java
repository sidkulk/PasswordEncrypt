package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPasswordPane extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5012706948466462857L;
	private JPanel contentPane;
	private JTextField passwordtitleTxt;
	private JTextField passwordvalueTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPasswordPane frame = new AddPasswordPane();
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
	public AddPasswordPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(156, 11, 188, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(34, 84, 129, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password Value");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(34, 153, 129, 20);
		contentPane.add(lblNewLabel_1_1);

		passwordtitleTxt = new JTextField();
		passwordtitleTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordtitleTxt.setBounds(173, 75, 260, 31);
		contentPane.add(passwordtitleTxt);
		passwordtitleTxt.setColumns(10);

		passwordvalueTxt = new JTextField();
		passwordvalueTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordvalueTxt.setColumns(10);
		passwordvalueTxt.setBounds(173, 144, 260, 31);
		contentPane.add(passwordvalueTxt);

		JButton addPasswordBtn = new JButton("ADD");
		addPasswordBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addPasswordBtn.setBounds(50, 219, 113, 31);
		contentPane.add(addPasswordBtn);

		JButton backBtn = new JButton("BACK");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		backBtn.setBounds(352, 219, 113, 31);
		contentPane.add(backBtn);
	}
}
