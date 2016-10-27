package com.bjpowernode.pattern;

public interface UserManager {

	public void addUser(String userId, String userName);
	
	public void delUser(String userId);
	
	public void modifyUser(String userId, String userName);
	
	public String findUser(String userId);
}
