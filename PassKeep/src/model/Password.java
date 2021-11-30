package model;

public class Password {
	private Integer p_id;
	private String passwordTitle;
	private String passwordValue;

	public Password(Integer p_id, String passwordTitle, String passwordValue) {
		super();
		this.p_id = p_id;
		this.passwordTitle = passwordTitle;
		this.passwordValue = passwordValue;
	}

	public Password(String passwordTitle, String passwordValue) {
		super();
		this.passwordTitle = passwordTitle;
		this.passwordValue = passwordValue;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
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

	@Override
	public String toString() {
		return "Password [passwordTitle=" + passwordTitle + ", passwordValue=" + passwordValue + "]";
	}

}
