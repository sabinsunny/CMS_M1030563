/**
 * 
 */
package com.mindtree.cms.pojo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Sabin
 *
 */
public class GuestUser {
	@NotEmpty(message = "Please enter your Name.")
	@Size(min = 3, max = 20, message = "Your Name must between 3 and 20 characters")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
