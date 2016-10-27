package com.bjpowernode.pattern;

public class UserManagerImpl implements UserManager {

	public void addUser(String userId, String userName) {
		//System.out.println("start-->>addUser() userId-->>" + userId);
		try {
			System.out.println("UserManagerImpl.addUser() userId-->>" + userId);
			
			//System.out.println("success-->>addUser()");
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("error-->>addUser()");
			throw new RuntimeException();
		}	
	}

	public void delUser(String userId) {
		System.out.println("UserManagerImpl.delUser() userId-->>" + userId);
	}

	public String findUser(String userId) {
		System.out.println("UserManagerImpl.findUser() userId-->>" + userId);
		return "ÕÅÈý";
	}

	public void modifyUser(String userId, String userName) {
		System.out.println("UserManagerImpl.modifyUser() userId-->>" + userId);
	}

}
