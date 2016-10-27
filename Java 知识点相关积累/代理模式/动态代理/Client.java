package com.bjpowernode.pattern;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogHandler logHandler = new LogHandler();
		UserManager userManager = (UserManager)logHandler.newProxyInstance(new UserManagerImpl());
		//userManager.addUser("0001", "ÕÅÈı");
		//userManager.delUser("0001");
		String name = userManager.findUser("0001");
		System.out.println("Client.main() --- " + name);
	}

}
