package com.cg.eauction.utils;

public class PasswordManagement {

	public static String encryptPassword(String password) {
		StringBuilder encryptedPassword = new StringBuilder("");
		for (int i = 0; i < password.length(); i++) {
			if (i <= password.length() / 2)
				encryptedPassword.append((char) (password.charAt(i) - 13));
			else
				encryptedPassword.append((char) (password.charAt(i) - 5));
		}
		return encryptedPassword.toString();
	}

	private PasswordManagement() {

	}

}
