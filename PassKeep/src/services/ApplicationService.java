package services;

import java.util.HashMap;

import model.Password;
import model.User;
import model.UserCredentials;
import model.UserPasswordData;

public interface ApplicationService {
	public User logUserIn(UserCredentials credentials);
	public boolean registerNewUser(User user);
	public boolean saveNewPassword(UserPasswordData password);
	public boolean deleteSelectedRow(Password password);
	public boolean updateSelectedRow(Password password);
	public HashMap<Integer, Password> getUserPassword(Integer user_id);
	public User recoverUserAccount(String username, String nickname, String schoolName);
}
