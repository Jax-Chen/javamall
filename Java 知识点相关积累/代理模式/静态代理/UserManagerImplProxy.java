package com.bjpowernode.pattern;

public class UserManagerImplProxy implements UserManager {

	private UserManager userManager;
	
	public UserManagerImplProxy(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void addUser(String userId, String userName) {
		try {
			System.out.println("start-->>addUser() userId-->>" + userId);
			userManager.addUser(userId, userName);
			System.out.println("success-->>addUser()");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("error-->>addUser()");
		}	
	}

	public void delUser(String userId) {

	}

	public String findUser(String userId) {
		return null;
	}

	public void modifyUser(String userId, String userName) {

	}

}
