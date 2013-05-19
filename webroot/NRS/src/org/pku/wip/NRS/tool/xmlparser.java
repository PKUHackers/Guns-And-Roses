package org.pku.wip.NRS.tool;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class xmlparser {
	
	public static class Piece {
		String url;
		String title;
		String pubdate;
		String keywords;
	}
	
	public static List<Piece> readXMl(String strXML) throws IOException {
		File file = new File(strXML);
		String result = "";
		String line = "";
		List<Piece> news_List = new ArrayList<Piece>();

		// create buffered reader
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			line = br.readLine();
			while (line != null) {
				result += line;
				if (line.endsWith("</News>")) {
					//System.out.println(result);
					news_List.add(PieceFromXML(result));
					result = "";
				}
				line = br.readLine();
			}
			br.close();
			return news_List;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news_List;
	}

	private static Piece PieceFromXML(String xml) {
		Piece piece = new Piece();
		
		int beginIndex, endIndex;
		beginIndex = xml.indexOf("<link>") + "<link>".length();
		endIndex = xml.indexOf("</link>");
		piece.url = xml.substring(beginIndex, endIndex);

		beginIndex = xml.indexOf("<title>") + "<title>".length();
		endIndex = xml.indexOf("</title>");
		piece.title = xml.substring(beginIndex, endIndex);

		beginIndex = xml.indexOf("<keyWords>") + "<keyWords>".length();
		endIndex = xml.indexOf("</keyWords>");
		piece.keywords = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<pubDate>") + "<pubDate>".length();
		endIndex = xml.indexOf("</pubDate>");
		piece.pubdate = xml.substring(beginIndex, endIndex);
		
		return piece;
	}
	
	public static void json2file(String json, String fileName)
			throws IOException {
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file, false);
		if (!file.exists()) {
			file.createNewFile();
		}
		fos.write(json.getBytes("UTF-8"));
		fos.flush();
		fos.close();
	}
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static void main(String arge[]) throws Exception, Exception {
		
		List<Piece> newsxml = readXMl("news.xml");
		System.out.println(newsxml.size());
		String strXMLJson = gson.toJson(newsxml);
		//System.out.println(strXMLJson);
		json2file(strXMLJson, "piece.json");
		System.out.println(newsxml.size());
	}
}
