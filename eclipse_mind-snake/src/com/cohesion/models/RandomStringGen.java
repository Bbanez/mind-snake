package com.cohesion.models;

import java.util.Random;

public class RandomStringGen {

	private static final String charSet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	private static final char spliter = '$';

	public String getRandomString(int size) {
		String output = "";

		Random rn = new Random();

		int j, k;

		for (int i = 0; i < size; i++) {
			j = charSet.length();
			k = Math.abs(rn.nextInt() % j);

			output += charSet.charAt(k);
		}

		return output;
	}

	public String surroundString(String s, int size) {
		String output = "";
		Random rn = new Random();
		s = spliter + s + spliter;
		int j, k;

		for (int i = 0; i < size; i++) {

			if (i == size / 2 - s.length()) {
				for (int t = 0; t < s.length(); t++) {
					output += s.charAt(t);
					i++;
				}
			} else {
				j = charSet.length();
				k = Math.abs(rn.nextInt() % j);
				output += charSet.charAt(k);
			}
		}
		return output;
	}
}
