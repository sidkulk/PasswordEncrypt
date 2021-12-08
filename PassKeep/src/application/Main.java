package application;

import services.DBInit;
import view.LoginPane;

public class Main {

	public static void main(String[] args) {
		DBInit dbInit = new DBInit();
		dbInit.connectToDatabase();
		dbInit.createAllTables();

		LoginPane loginPane = new LoginPane();
		loginPane.setVisible(true);
	}
}
