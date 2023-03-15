package practice.model;

import java.time.LocalDateTime;

public class Member {

	private long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime ldTime;
	
	public Member() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLdTime() {
		return ldTime;
	}

	public void setLdTime(LocalDateTime ldTime) {
		this.ldTime = ldTime;
	}
	
	
}
