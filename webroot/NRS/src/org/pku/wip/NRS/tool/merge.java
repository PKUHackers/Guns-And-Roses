package org.pku.wip.NRS.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class merge {

	public  Images json2image(String jsonfile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
		try {
			Images temp = gson.fromJson(IOUtils.toString(reader), Images.class);
			return temp;
		} finally {
			reader.close();
		}
	}

	public  Videos json2video(String jsonfile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
		try {
			Videos temp = gson.fromJson(IOUtils.toString(reader), Videos.class);
			return temp;
		} finally {
			reader.close();
		}
	}

	public  MergedNews AllNewsFromXML(String xml) {
		MergedNews mergedNews = new MergedNews();

		int beginIndex, endIndex;
		beginIndex = xml.indexOf("<link>") + "<link>".length();
		endIndex = xml.indexOf("</link>");
		mergedNews.setUrl(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<title>") + "<title>".length();
		endIndex = xml.indexOf("</title>");
		mergedNews.setTitle(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<keyWords>") + "<keyWords>".length();
		endIndex = xml.indexOf("</keyWords>");
		mergedNews.setKeywords(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<pubDate>") + "<pubDate>".length();
		endIndex = xml.indexOf("</pubDate>");
		mergedNews.setPubdate(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<description>") + "<description>".length();
		endIndex = xml.indexOf("</description>");
		mergedNews.setDescription(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<cleanContent>") + "<cleanContent>".length();
		endIndex = xml.indexOf("</cleanContent>");
		mergedNews.setCleanContent(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<type>") + "<type>".length();
		endIndex = xml.indexOf("</type>");
		mergedNews.setType(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<pic>") + "<pic>".length();
		endIndex = xml.indexOf("</pic>");
		mergedNews.setPic(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<keyWords>") + "<keyWords>".length();
		endIndex = xml.indexOf("</keyWords>");
		mergedNews.setKeywords(xml.substring(beginIndex, endIndex));

		beginIndex = xml.indexOf("<topicId>") + "<topicId>".length();
		endIndex = xml.indexOf("</topicId>");
		mergedNews.setTopicId(xml.substring(beginIndex, endIndex));

		return mergedNews;
	}

	public  Map<String, MergedNews> readXMl(String strXML,
			HashMap<String, Integer> hashMap) throws IOException {
		File file = new File(strXML);
		String result = "";
		String line = "";
		Map<String, MergedNews> mapNews = new HashMap<String, MergedNews>();

		// create buffered reader
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			line = br.readLine();
			while (line != null) {
				result += line;
				if (line.endsWith("</News>")) {
					// System.out.println(result);
					MergedNews tmp = AllNewsFromXML(result);
					if (!mapNews.containsKey(tmp.getUrl())) {
						mapNews.put(tmp.getUrl(), tmp);
					}
					if (!hashMap.containsKey(tmp.getUrl())) {
						hashMap.put(tmp.getUrl(), 1);
					}
					result = "";
				}
				line = br.readLine();
			}
			br.close();
			return mapNews;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapNews;
	}
	
	

	 public List<MergedNews> mergeNews(Images images, Videos videos,
			Map<String, MergedNews> mapNews) {
		Set<String> urls = new HashSet<String>();
		urls.addAll(images.get(0).keySet());
		urls.addAll(videos.get(0).keySet());
		urls.addAll(mapNews.keySet());

		List<MergedNews> mergeNewsList = new ArrayList<MergedNews>();

		for (String url : urls) {
			MergedNews m = mapNews.get(url);
			if (m == null) {
				// ???
				m = new MergedNews();
				m.setUrl(url);
				mapNews.put(url, m);
			}
//			List<Image> i = images.get(0).get(url);
//			List<Video> v = videos.get(0).get(url);
//			List<Image> i = images.get(0).get(url);
//			List<Video> v = videos.get(0).get(url);
//			if (i != null) {
//				m.imagelist = i;
//				System.out.println(i.size());
//			}
//			if (v != null) {
//				m.videolist = v;
//				System.out.println(i.size());
//			}
			
			for( int i=0 ; i<images.size(); i++){
				if(null != images.get(i).get(url)){
					m.setImagelist(images.get(i).get(url));
				//	System.out.println(m.imagelist.size());
				}
			}
			for( int i=0 ; i<videos.size(); i++){
				if(null != videos.get(i).get(url)){
					m.setVideolist(videos.get(i).get(url));
				//	System.out.println(m.videolist.size());
				}
			}
			

			mergeNewsList.add(m);
		}
		return mergeNewsList;
	}

	public  void json2file(String json, String fileName)
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

	public static String md5(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	private  void seperateJson(List<MergedNews> mergedNews)
			throws IOException {
		for (MergedNews news : mergedNews) {
			if (news.getImagelist().isEmpty() || news.getVideolist().isEmpty()) {
				continue;
			}
			String filename = "filterjsons/" + md5(news.getUrl()) + ".json";
			System.out.println(filename);
			String strXMLJson = gson.toJson(news);
			json2file(strXMLJson, filename);
		}
	}

	private  Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void main(String[] args) throws IOException {
		merge me = new merge();
		Images imagemap = me.json2image("./data/image_urls.json");
		Videos videomap = me.json2video("./data/video_urls.json");
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Map<String, MergedNews> mapNews = me.readXMl("./data/news.xml", hashMap);
		List<MergedNews> mergedNews = me.mergeNews(imagemap, videomap, mapNews);
		System.out.println(imagemap.size());
		System.out.println(videomap.size());
		
		// Now mapNews is merged.

		String strXMLJson = me.gson.toJson(mergedNews);
		System.out.println(strXMLJson);
		me.json2file(strXMLJson, "./merged_news.json");

		me.seperateJson(mergedNews);

	}
}
