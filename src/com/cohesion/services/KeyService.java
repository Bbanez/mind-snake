package com.cohesion.services;

import java.util.ArrayList;
import java.util.List;

import com.cohesion.exceptions.CustomException;

public class KeyService {

	List<Character> keys = new ArrayList<>();

	public boolean available() {
		if (keys.size() > 0)
			return true;
		return false;
	}

	public void append(char key) {
		keys.add(key);
	}

	public char read() throws CustomException {
		if (keys.size() > 0) {
			char c = keys.get(0);
			keys.remove(0);
			return c;
		}
		throw new CustomException("Nothing to read!");
	}

	public List<Character> readAll() {
		List<Character> c = keys;
		keys = new ArrayList<>();
		return c;
	}

	public String readAllString() {
		String s = "";
		for (char c : keys) {
			s += c;
		}
		keys = new ArrayList<>();
		return s;
	}

	public void clear() {
		keys = new ArrayList<>();
	}
}
