

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;


/**
 * File Operator
 * 
 * @author R.J.Y
 * @version 1.0
 */
public class FileOperator {
	
	
	/**
	 * read file content
	 * 
	 * @param file			-the target file
	 * @param encodeType	-encode type of the target file
	 * @return String		-file content
	 */
	public static String getFileContent(File file, String encodeType){
		String content = "";
		try {
			// create buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encodeType));
			String line = "";
			line = br.readLine();
			while(line != null){
				content += line + "\n";
				line = br.readLine();
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		} catch (FileNotFoundException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		} catch (IOException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		}
		return content;
	}
	
	
	/**
	 * append message to file
	 * 
	 * @param file	-target file
	 * @param msg	-message
	 */
	public static void append(String file, String msg){
		try {
			// append to file
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			// write message
			bw.write(msg);
			bw.flush();
			
			// close file
			bw.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("FileOperator append: " + e.toString());
		} catch (FileNotFoundException e) {
			System.out.println("FileOperator append: " + e.toString());
		} catch (IOException e) {
			System.out.println("FileOperator append: " + e.toString());
		}
	}
	
	
	/**
	 * write content to a new file
	 * 
	 * @param content		-content
	 * @param file			-file name
	 * @param encodeType	-encode type
	 */
	public static void writeToFile(String content, String file, String encodeType){
		try {
			// create buffer
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encodeType));
			bw.write(content);
			bw.flush();
			
			// close the buffer
			bw.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("FileOperator writeToFile: " + e);
		} catch (FileNotFoundException e) {
			System.out.println("FileOperator writeToFile: " + e);
		} catch (IOException e) {
			System.out.println("FileOperator writeToFile: " + e);
		}
	}
	
	
	
	/**
	 * format title to make the title valid
	 * 
	 * @param title		-the title
	 * @return String	-the result
	 */
	public static String formatTitle(String line){
		//illegal file name if the name contains these character  \ / : * ? " < > |
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("\\", "_");
		map.put("/", "_");
		map.put(":", "_");
		map.put("*", "_");
		map.put("?", "_");
		map.put("\"", "_");
		map.put("<", "_");
		map.put(">", "_");
		map.put("|", "_");
		
		// change illegal characters to "_"
		int length = line.length();
		for (int i = 0; i < length; i++) {
			String charat = line.substring(i, i + 1);
			if (map.get(charat) != null) {
				line = line.replace(charat, (String) map.get(charat));
			}
		}

		return line;
	}
}
