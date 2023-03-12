package spa.entity;

public class Member {
	private String username;
	private String password;
	private int priority; // 權限 (0:最小權限)
	
	public Member() {
		
	}

	public Member(String username, String password, int priority) {
		this.username = username;
		this.password = password;
		this.priority = priority;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
}
