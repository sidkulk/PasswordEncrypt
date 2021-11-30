package services;

import java.util.ArrayList;

import model.Password;
import model.User;
import model.UserCredentials;

public interface ApplicationService {
	public User logUserIn(UserCredentials credentials);
	public boolean registerNewUser(User user);
	public boolean saveNewPassword(Password password);
	public boolean deleteSelectedRow(Password password);
	public boolean updateSelectedRow(Password password);
	public void logUserOut();
	public void exitProgram();
	public ArrayList<Password> getUserPassword(Integer user_id);
}
