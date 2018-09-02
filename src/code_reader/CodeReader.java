package code_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeReader {

	public ArrayList<String> redeCode(String path) {
		ArrayList<String> allLinesOfCurrentFile = new ArrayList<>();
		File file = new File(path);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			System.out.println("Can not prepare BufferReader :(");
			e1.printStackTrace();
		}

		String st;
		try {
			while ((st = br.readLine()) != null) {
				allLinesOfCurrentFile.add(st);
				// System.out.println(st);
			}

		} catch (IOException e) {
			System.out.println("Can not read Line from files :(");
			e.printStackTrace();
		}
		return allLinesOfCurrentFile;

	}

}
