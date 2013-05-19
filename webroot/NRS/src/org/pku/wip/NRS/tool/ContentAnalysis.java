package org.pku.wip.NRS.tool;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ContentAnalysis {

	public static class YQLResult {
		Query query;
	}

	public static class Query {
		Results results;
	}

	public static class Results {
		Entities entities;
	}

	public static class Entities {
		List<Entity> entity;
	}

	public static class Entity {
		String score;
		Text text;
	}

	public static class Text {
		String content;
	}

	public static class News {
		String url;
		String title;
		Map<String, String> entity = new HashMap<String, String>();
		Map<String, String> keyword = new HashMap<String, String>();
	}

	public static String getContentAnalysisQueryURL(String url)
			throws IOException {
		String query = "select * from contentanalysis.analyze where url=\'"
				+ url + "\'";
		String queryurl = "http://query.yahooapis.com/v1/public/yql?q="
				+ URLEncoder.encode(query, "UTF-8") + "&format=json";
		return queryurl;
	}

	public static Entities getEntitiesFromYahoo(String url) throws IOException {

		String queryurl = getContentAnalysisQueryURL(url);
		// System.out.println(queryurl);

		URL url_obj = new URL(queryurl);
		String html = NetUtil.wget(url_obj);
		// System.out.println(html);

		Gson gson = new Gson();
		YQLResult result = gson.fromJson(html, YQLResult.class);
		// if (result != null && result.query != null
		// && result.query.results != null
		// && result.query.results.entities != null) {
		// for (Entity e : result.query.results.entities.entity) {
		// System.out.println(e.score + " " + e.text.content);
		// }
		// }
		return result.query.results.entities;
	}

	private static News convertToNews(String url, Entities entities) {
		News news = new News();
		news.url = url;
		news.title = "";
		for (Entity e : entities.entity) {
			news.entity.put(e.text.content, "" + e.score); // ??
			// keyword
		}
		return news;
	}

	private static void addToNewsResult(List<News> news, String url) {
		try {
			Entities entities = getEntitiesFromYahoo(url);
			if (entities != null) {
				news.add(convertToNews(url, entities));
			} else {
				// If error, skip?
			}
		} catch (MalformedURLException e) {
			// If error, skip?
			e.printStackTrace();
		} catch (IOException e) {
			// If error, skip?
			e.printStackTrace();
		}
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

	public static News NewsFromXML(String xml) throws IOException {
		News news = new News();
		int beginIndex, endIndex;
		beginIndex = xml.indexOf("<link>") + "<link>".length();
		endIndex = xml.indexOf("</link>");
		news.url = xml.substring(beginIndex, endIndex);

		beginIndex = xml.indexOf("<title>") + "<title>".length();
		endIndex = xml.indexOf("</title>");
		news.title = xml.substring(beginIndex, endIndex);

		String keyWords = new String();
		beginIndex = xml.indexOf("<keyWords>") + "<keyWords>".length();
		endIndex = xml.indexOf("</keyWords>");
		keyWords = xml.substring(beginIndex, endIndex);

		news.keyword = new HashMap<String, String>();
		String[] keywordlist = keyWords.split(";");

		for (String keyword : keywordlist) {
			String[] items = keyword.split(":");
			if (items.length > 2) {
				if (!news.keyword.containsKey(items[0])) {
					news.keyword.put(items[0], items[2]);
				}
			}
		}
		
		// entities;
		Entities entities = getEntitiesFromYahoo(news.url);
		for (Entity e : entities.entity) {
			news.entity.put(e.text.content, "" + e.score);
		}
		
		return news;
	}

	public static List<News> readXMl(String strXML) throws IOException {
		File file = new File(strXML);
		String result = "";
		String line = "";
		List<News> news_List = new ArrayList<News>();

		// create buffered reader
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			line = br.readLine();
			while (line != null) {
				result += line;
				if (line.endsWith("</News>")) {
					//System.out.println(result);
					news_List.add(NewsFromXML(result));
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

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void main(String[] args) throws IOException {
//		List<News> news = new ArrayList<News>();
//		addToNewsResult(news,
//				"http://www.cnn.com/2011/11/11/world/europe/greece-main/index.html");
//		String strJson = gson.toJson(news);
//		System.out.println(strJson);
//		json2file(strJson, "news.json");

		List<News> newsxml = readXMl("news_sample.xml");
		String strXMLJson = gson.toJson(newsxml);
		System.out.println(strXMLJson);
		json2file(strXMLJson, "news_sample.json");
		
		// getEntitiesFromYahoo("http://www.cnn.com/2011/11/11/world/europe/greece-main/index.html");

	}
}
