package model;

public class UserPasswordData {
	private int p_id;
	private String passwordTitle;
	private String passwordValue;
	private int u_id;

	public UserPasswordData(int p_id, String passwordTitle, String passwordValue, int u_id) {
		super();
		this.p_id = p_id;
		this.passwordTitle = passwordTitle;
		this.passwordValue = passwordValue;
		this.u_id = u_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getPasswordTitle() {
		return passwordTitle;
	}

	public void setPasswordTitle(String passwordTitle) {
		this.passwordTitle = passwordTitle;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

}
