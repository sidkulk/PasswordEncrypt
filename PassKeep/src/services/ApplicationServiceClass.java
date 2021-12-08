package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import crypto.AESAlgorithm;
import model.Password;
import model.User;
import model.UserCredentials;
import model.UserPasswordData;

public class ApplicationServiceClass implements ApplicationService {
	protected static PreparedStatement pstmt;
	protected static ResultSet resultSet;
	private AESAlgorithm crypto = new AESAlgorithm();
	public static User currentlyLoggedInUser;

	private HashMap<Integer, Password> userPasswordData = new HashMap<>();

	public static HashMap<Integer, Password> cachedUserData = new HashMap<>();

	@Override
	public User logUserIn(UserCredentials credentials) {
		String sqlQuery = "SELECT * FROM " + TableClass.USER_TAB_NAME + " WHERE username = ? AND password = ?";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, credentials.getUsername());
			pstmt.setString(2, crypto.encrypt(credentials.getPassword()));
			resultSet = pstmt.executeQuery();

			currentlyLoggedInUser = new User(resultSet.getInt(1), resultSet.getString(2),
					crypto.decrypt(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5));

			if (resultSet.next()) {
				return new User(resultSet.getInt(1), resultSet.getString(2), crypto.decrypt(resultSet.getString(3)),
						resultSet.getString(4), resultSet.getString(5));
			}
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
				+ "(u_id, username, password, nickname, schoolname) VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, user.getU_id());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, crypto.encrypt(user.getPassword()));
			pstmt.setString(4, user.getNickname());
			pstmt.setString(5, user.getSchoolName());
			pstmt.executeUpdate();

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
	public boolean saveNewPassword(UserPasswordData password) {
		String sqlQuery = "INSERT INTO " + TableClass.PASS_TAB_NAME
				+ "(p_id, passwordtitle, passwordvalue, u_id) VALUES(?, ?, ?, ?)";
		System.out.println("Entry to be added shortly (inside saveNewpassword method): " + password);
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, password.getP_id());
			pstmt.setString(2, password.getPasswordTitle());
			pstmt.setString(3, crypto.encrypt(password.getPasswordValue()));
			pstmt.setInt(4, password.getU_id());
			pstmt.executeUpdate();
			cachedUserData.put(password.getP_id(),
					new Password(password.getPasswordTitle(), password.getPasswordValue()));
			System.out.println("New password: " + password + " saved to database");
			return true;
		} catch (Exception e) {
			System.out.println("Unable to save password to database!");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteSelectedRow(Password password) {
		String sqlQuery = "DELETE FROM " + TableClass.PASS_TAB_NAME + " WHERE p_id = ?";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, password.getP_id());
			pstmt.executeUpdate();

			cachedUserData.remove(password.getP_id());

			System.out.println("entry: " + password + " DELETED!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateSelectedRow(Password password) {
		String sqlQuery = "UPDATE " + TableClass.PASS_TAB_NAME
				+ " SET passwordtitle = ?, passwordvalue = ? WHERE p_id = ?";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, password.getPasswordTitle());
			pstmt.setString(2, password.getPasswordValue());
			pstmt.setInt(3, password.getP_id());
			pstmt.executeUpdate();

			System.out.println("Entry updated: " + password);
			cachedUserData.replace(password.getP_id(),
					new Password(password.getPasswordTitle(), password.getPasswordValue()));
			return true;
		} catch (Exception e) {
			System.out.println("Unable to update entry to database!");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public HashMap<Integer, Password> getUserPassword(Integer user_id) {
		String sqlQuery = "SELECT p_id, passwordtitle, passwordvalue FROM passwordtab INNER JOIN user ON user.u_id = passwordtab.u_id WHERE user.u_id = ?";
		cachedUserData.clear();
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, user_id);

			resultSet = pstmt.executeQuery();
			System.out.println("Query executed!");

			while (resultSet.next()) {
				System.out.println("Inside WHILE loop");

				userPasswordData.put(resultSet.getInt(1),
						new Password(resultSet.getString(2), crypto.decrypt(resultSet.getString(3))));
				cachedUserData.put(resultSet.getInt(1),
						new Password(resultSet.getString(2), crypto.decrypt(resultSet.getString(3))));
			}
			if (userPasswordData == null || userPasswordData.isEmpty())
				System.out.println("new user, No passwords saved yet.");
			else
				System.out.println("Result Set returned: " + userPasswordData.toString());

			return userPasswordData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRandomPasswordId(int upperBound) {
		Random random = new Random();
		return random.nextInt(upperBound);
	}

	@Override
	public User recoverUserAccount(String username, String nickname, String schoolName) {
		String sqlQuery = "SELECT u_id, username, password, nickname, schoolname FROM user WHERE username = ? OR (nickname = ? AND schoolname = ?)";
		try {
			pstmt = DBInit.connection.prepareStatement(sqlQuery);
			pstmt.setString(1, username);
			pstmt.setString(2, nickname);
			pstmt.setString(3, schoolName);
			resultSet = pstmt.executeQuery();

			currentlyLoggedInUser = new User(resultSet.getInt(1), resultSet.getString(2),
					crypto.decrypt(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5));

			if (resultSet.next()) {
				return new User(resultSet.getInt(1), resultSet.getString(2), crypto.decrypt(resultSet.getString(3)),
						resultSet.getString(4), resultSet.getString(5));
			}

			return null;
		} catch (Exception e) {
			System.out.println("Unable to recover user: " + username);
		}
		return null;
	}
}
