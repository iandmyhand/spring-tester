package study.hard.spring.core.account.entity;

import java.util.Date;

import study.hard.spring.core.commons.entity.BasisObject;

/**
 * @author iandmyhand@gmail.com
 */
public class Account extends BasisObject {
	private static final long serialVersionUID = 2687687850664577545L;

	private String userKey;
	private String email;
	private String firstName;
	private String lastName;
	private Date registYmdt;

	public Account() {
	}

	public Account(String userKey, String email) {
		super();
		this.userKey = userKey;
		this.email = email;
	}

	public Account(String userKey, String email, String firstName, String lastName) {
		this(userKey, email);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Account(String userKey, String email, String firstName, String lastName, Date registYmdt) {
		this(userKey, email, firstName, lastName);
		this.registYmdt = registYmdt;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public Date getRegistYmdt() {
		return registYmdt;
	}

	public void setRegistYmdt(Date registYmdt) {
		this.registYmdt = registYmdt;
	}

}
