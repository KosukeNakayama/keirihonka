package bean;

//ユーザー情報
public class Users implements java.io.Serializable {

	private String userId;
	private String password;
	private boolean isManaged;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isManaged() {
		return isManaged;
	}
	public void setManaged(boolean isManaged) {
		this.isManaged = isManaged;
	}

}
