package com.coderscampus.assignment3;

import java.util.Scanner;

public class UserLoginApplication {
	private static final int MAX_ATTEMPTS = 5;

	public static void main(String[] args) {
		UserService userService = new UserService("data.txt");
		
		Scanner scanner = new Scanner(System.in);
		int attempts = 0;
		boolean loginSuccessful = false;

		while (attempts < MAX_ATTEMPTS && !loginSuccessful) {
			System.out.println("Enter username: ");
			String username = scanner.nextLine().trim();

			System.out.println("Enter password: ");
			String password = scanner.nextLine();

			User user = userService.validateLogin(username, password);
			if (user != null) {
				System.out.println("Welcome " + user.getName() + "!");
				loginSuccessful = true;
			} else {
				attempts++;
				if (attempts < MAX_ATTEMPTS) {
					System.out.println("Invalid login, please try again.");
				}
			}
		}

		if (!loginSuccessful) {
			System.out.println("Too many failed attempts, you are locked out.");
		}
		scanner.close();
	}

}
