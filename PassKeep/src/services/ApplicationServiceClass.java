package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crypto.AESAlgorithm;
import model.Password;
import model.User;
import model.UserCredentials;

public class ApplicationServiceClass implements ApplicationService {
	protected static PreparedStatement pstmt;
	protected static ResultSet resultSet;
	private AESAlgorithm crypto = new AESAlgorithm();
	private ArrayList<Password> userPasswordData = new ArrayList<Password>();

	@Override
	public User logUserIn(UserCredentials credentials) {
		String sqlQuery = "SELECT * FROM " + TableClass.USER_TAB_NAME + " WHERE username = ? AND password = ?";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, credentials.getUsername());
			pstmt.setString(2, crypto.encrypt(credentials.getPassword()));
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				System.out.println("User found!");
				return new User(resultSet.getInt(1), resultSet.getString(2), crypto.decrypt(resultSet.getString(3)),
						resultSet.getString(4), resultSet.getString(5));
			}

			System.out.println("User not found!");
			return null;
		} catch (Exception e) {
			System.out.println("Unable to login! Check query OR connection!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registerNewUser(User user) {
		String sqlQuery = "INSERT INTO " + TableClass.USER_TAB_NAME
				+ "(username, password, nickname, schoolname) VALUES(?, ?, ?, ?)";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, crypto.encrypt(user.getPassword()));
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getSchoolName());
			pstmt.executeUpdate();

			System.out.println("New user: " + user + " Has been registered!");
			return true;
		} catch (SQLException e) {
			System.out.println("Error while registering new user! Check connection OR query!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Encryption exception occured");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean saveNewPassword(Password password) {
		String sqlQuery = "INSERT INTO " + TableClass.PASS_TAB_NAME + "(passwordtitle, passwordvalue) VALUES(?, ?)";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, password.getPasswordTitle());
			pstmt.setString(2, crypto.encrypt(password.getPasswordValue()));
			pstmt.executeUpdate();

			System.out.println("New password: " + password + " saved to database");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to save password to database!");
		}
		return false;
	}

	@Override
	public boolean deleteSelectedRow(Password password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSelectedRow(Password password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logUserOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitProgram() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Password> getUserPassword(Integer user_id) {
		String sqlQuery = "SELECT passwordtitle, passwordvalue FROM passwordtab INNER JOIN user ON user.u_id = passwordtab.u_id WHERE user.u_id = ?";

		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, user_id);

			resultSet = pstmt.executeQuery();
			System.out.println("Query executed!");
			//System.out.println("Data returned by result set: " + resultSet.getString(1) + "\t" + resultSet.getString(2));
			
			
			while (resultSet.next()) {
				System.out.println("Inside WHILE loop");
				//userPasswordData.add(new Password(resultSet.getString(0), crypto.decrypt(resultSet.getString(1))));
				userPasswordData.add(new Password(resultSet.getString(1), resultSet.getString(2)));
			}
			if (userPasswordData == null)
				System.out.println("Result Set returned NULL");
			else
				System.out.println("Result Set returned: " + userPasswordData.toString());

			return userPasswordData;
		} catch (Exception e) {
			System.out.println("Error loading all user password database! Contact your software vendor!");
		}
		return null;
	}

}
