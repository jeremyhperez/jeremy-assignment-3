package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserService {
	private User[] users;

	public UserService(String fileName) {
		loadUsers(fileName);
	}

	private void loadUsers(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int lineCount = 0;
			while (br.readLine() != null) {
				lineCount++;
			}

			users = new User[lineCount];

			br.close();
			BufferedReader br2 = new BufferedReader(new FileReader(fileName));
			String line;
			int index = 0;

			while ((line = br2.readLine()) != null) {
//				System.out.println("Raw line: " + line); // Debug statement to print raw line
				line = line.trim(); // Remove any leading/trailing whitespace
				
//				 for (int i = 0; i < line.length(); i++) {
//	                    System.out.println("Char " + i + ": " + (int) line.charAt(i) + " (" + line.charAt(i) + ")");
//	                }
				
				
				String[] userData = line.split("\\s*,\\s*"); // Split by comma, removing extra spaces
//				System.out.println("Parsed elements: " + userData.length); // Debug statement to print number of
																			// elements

//				for (int i = 0; i < userData.length; i++) {
//					System.out.println("userData[" + i + "]: '" + userData[i] + "'"); // Print each element
//				}

				if (userData.length == 3) {
					String username = userData[0].trim();
					String password = userData[1].trim();
					String name = userData[2].trim();
					users[index] = new User(username, password, name);
//					System.out.println("Loaded user: " + users[index].getUsername() + "," + users[index].getName());
					index++;
				} else {
					System.out.println("Skipping invalid line: " + line);
				}
//			}

//			System.out.println("All loaded users:");
//			for (User user : users) {
//				if (user != null) {
//					System.out.println("User: " + user.getUsername() + ", " + user.getName());
//				} else {
//					System.out.println("Null user found in array");
//				}
			}

			br2.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("General error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public User validateLogin(String username, String password) {
		for (User user : users) {
			if (user != null) {
//				System.out.println("Checking user: " + user.getUsername());
				if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
					return user;
				}
			} else {
				System.out.println("User is null at some index in users array");
			}
		}
		return null;
	}
}
