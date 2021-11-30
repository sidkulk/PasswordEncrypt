package model;

public class User {

	private Integer u_id;
	private String username;
	private String password;
	private String nickname;
	private String schoolName;

	public User(Integer u_id, String username, String password, String nickname, String schoolName) {
		super();
		this.u_id = u_id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.schoolName = schoolName;
	}

	public User(String username, String password, String nickname, String schoolName) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.schoolName = schoolName;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", schoolName=" + schoolName + "]";
	}
}
