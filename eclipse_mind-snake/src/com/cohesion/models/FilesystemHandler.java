package com.cohesion.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilesystemHandler {

	public FilesystemHandler() {
	}

	public int write(String data, String folder, String fileName) {
		File file = new File(folder);
		if (!file.exists()) {
			System.out.println("Folder don't exists. Making...");
			file.mkdirs();
			System.out.println("Done.");
			file = new File(folder + "/" + fileName);
			if (!file.exists()) {
				try {
					System.out.println("File don't exists. Making...");
					PrintWriter maker = new PrintWriter(folder + "/" + fileName);
					maker.write("");
					maker.flush();
					maker.close();

					System.out.println("Done.");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return 3;
				}
			}
		} else {
			file = new File(folder + "/" + fileName);
		}

		try {
			if (file.exists()) {
				FileWriter output = new FileWriter(file, true);
				output.write(data);

				output.flush();
				output.close();
			} else {
				FileWriter output = new FileWriter(file, true);
				output.write(data);

				output.flush();
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 2;
		}

		return 1;
	}

	public int write(String data, String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				System.out.println("File don't exists. Making...");
				PrintWriter maker = new PrintWriter(filePath);
				maker.write("");
				maker.flush();
				maker.close();
				System.out.println("Done.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return 3;
			}
		} else {
			file = new File(filePath);
		}
		try {
			FileWriter output = new FileWriter(file, true);
			output.write(data);

			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return 2;
		}

		return 1;
	}

	public String read(String path) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String sCurrentLine;
			String s = "";
			while ((sCurrentLine = br.readLine()) != null) {
				s += sCurrentLine.replaceAll("\t", "@") + "\n";
			}
			return s;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public void delete(String thisFile) {
		File file = new File(thisFile);
		if (file.exists()) {

			thisFile = file.getAbsolutePath();

			file = new File(thisFile);

			if (file.delete()) {
				System.out.println(file.getAbsolutePath() + " Deleted.");
			} else {
				System.out.println("Unsuccessful deleting " + file.getAbsolutePath());
			}
		}
	}

	public void clear(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			try {
				PrintWriter writer = new PrintWriter(file);

				writer.write("");
				writer.flush();
				writer.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
