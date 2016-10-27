package com.bjpowernode.pattern;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//UserManager userManager = new UserManagerImpl();
		UserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
		userManager.addUser("0001", "ÕÅÈı");
	}

}
