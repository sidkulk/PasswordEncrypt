package services;

public class TableClass {
	protected static final String USER_TAB_NAME = "user";
	protected static final String PASS_TAB_NAME = "passwordtab";
	protected static final String USER_PASS_TAB_NAME = "userpass";

	protected static final String CREATE_USER_TAB_QUERY = "CREATE TABLE IF NOT EXISTS " + USER_TAB_NAME + "("
			+ "u_id INTEGER PRIMARY KEY, " + "username TEXT NOT NULL UNIQUE, " + "password TEXT NOT NULL, "
			+ "nickname TEXT NOT NULL, " + "schoolname TEXT NOT NULL" + ")";

	protected static final String CREATE_PASS_TAB_QUERY = "CREATE TABLE IF NOT EXISTS " + PASS_TAB_NAME + "("
			+ "p_id INTEGER PRIMARY KEY, " + "passwordtitle TEXT NOT NULL, " + "passwordvalue TEXT NOT NULL, "
			+ "u_id INTEGER NOT NULL, FOREIGN KEY(u_id) REFERENCES " + USER_TAB_NAME
			+ " ON DELETE CASCADE ON UPDATE CASCADE)";

//	protected static final String CREATE_USER_PASS_TAB_QUERY = "CREATE TABLE IF NOT EXISTS " + USER_PASS_TAB_NAME
//			+ "(p_id, username, FOREIGN KEY(p_id) REFERENCES " + PASS_TAB_NAME
//			+ " ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(username) REFERENCES " + USER_TAB_NAME
//			+ " ON DELETE CASCADE ON UPDATE CASCADE)";
}
