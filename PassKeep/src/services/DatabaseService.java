package services;

public interface DatabaseService {
	public boolean connectToDatabase();
	public boolean createAllTables();
	public boolean closeAllConnections();
}
