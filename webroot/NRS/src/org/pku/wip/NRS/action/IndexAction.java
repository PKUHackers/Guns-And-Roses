/**
 * Copyright 2012, PKU ICST WIP, R.J.Y
 */
package org.pku.wip.NRS.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.pku.wip.NRS.tool.Image;
import org.pku.wip.NRS.tool.Images;
import org.pku.wip.NRS.tool.MergedNews;
import org.pku.wip.NRS.tool.Video;
import org.pku.wip.NRS.tool.Videos;
import org.pku.wip.NRS.tool.merge;


/**
 * Action
 * @author R.J.Y
 * @version 1.0
 */
public class IndexAction extends DispatchAction{	
	List<MergedNews> mergedNews = new ArrayList<MergedNews>();
	HashMap<String, HashMap<String, List<String>>> topicMap = new HashMap<String, HashMap<String, List<String>>>();
	
	/**
	 * Name: IndexAction
	 * Function: create a new instance of IndexAction
	 */
	public IndexAction(){
		if(topicMap.size() <= 0 || mergedNews.size() <= 0){
			try {
				merge me = new merge();
				Images imagemap = me.json2image("C:\\data\\image_urls.json");
				Videos videomap = me.json2video("C:\\data\\video_urls.json");
				HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
				Map<String, MergedNews> mapNews = me.readXMl("C:\\data\\news.xml", hashMap);
				mergedNews = me.mergeNews(imagemap, videomap, mapNews);
				
				// load display
				LoadDisplay("C:\\data\\display.txt");
				
				//System.out.println(topicMap.size());
				//System.out.println(mergedNews.size());
				
				
				/*for(String topicId : topicMap.keySet()){
					HashMap<String, List<String>> tmpMap = topicMap.get(topicId);
					System.out.println("++++++++++topic+++++++++++++");
					for(String nodeId : tmpMap.keySet()){
						System.out.println("++++++++++node+++++++++++++");
						List<String> posting = tmpMap.get(nodeId);
						for(String url : posting){
							System.out.println(url);
						}
					}
				}*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(topicMap.size());
		//System.out.println(mergedNews.size());
	}
	
	
	public void LoadDisplay(String file){
		String topicId = "";
		try {
			HashMap<String, List<String>> pMap = new HashMap<String, List<String>>();
			
			// create buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String line = "";
			int i = 0;
			line = br.readLine();
			
			while(line != null){
				i++;
				if(line.length() == 1){
					if(pMap.size() > 0){
						topicMap.put(topicId, pMap);
					}
					
					topicId = line;
					
					i = 0;
					pMap = new HashMap<String, List<String>>();
				}
				else{
					List<String> list = new ArrayList<String>();
					String[] postingList = line.split(";");
					for(String url : postingList){
						url = url.trim();
						if(url != "" && !list.contains(url)){
							list.add(url);
						}
					}
					pMap.put(Integer.toString(i), list);
				}
				line = br.readLine();
			}
			if(pMap.size() > 0){
				topicMap.put(topicId, pMap);
			}
			
			br.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		} catch (FileNotFoundException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		} catch (IOException e) {
			System.out.println("FileOperator getFileContent: " + e.toString());
		}
	}
	
	
	/**
	 * execute
	 */
	@SuppressWarnings("unused")
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession(false);
		
		Long userId = new Long(0);
		String param = mapping.getParameter();
		String actionMethod = request.getParameter(param);
		
		

		return dispatchMethod(mapping, form, request, response, actionMethod);
	}
	
	
	
	
	
	/**
	 * direct to index.jsp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ShowDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		// get user name
		String topicId = (String)request.getParameter("topicId");
		String nodeId = (String)request.getParameter("nodeId");
		
		
		List<MergedNews> newsList = new ArrayList<MergedNews>();
		List<Image> imageList = new ArrayList<Image>();
		List<Video> videoList = new ArrayList<Video>();
		List<String> newsIds = new ArrayList<String>();
		if(topicMap.containsKey(topicId) && topicMap.get(topicId).containsKey(nodeId)){
			newsIds = topicMap.get(topicId).get(nodeId);
			for(MergedNews news : mergedNews){
				if(newsIds.contains(news.getUrl()) && !newsList.contains(news)){
					newsList.add(news);
					
					for(Image img : news.getImagelist()){
						if(imageList.size() >= 4)break;
						else
							imageList.add(img);
					}
					
					for(Video vid : news.getVideolist()){
						if(videoList.size() >= 4)break;
						else{
							String url = vid.getUrl();
							int index = url.indexOf("?v=");
							if(index >= 0){
								vid.setUrl("http://www.youtube.com/embed/"+ url.substring(index+3));
							}
							videoList.add(vid);
							
						}
					}
				}
			}
		}
		// send parameter to page
		request.setAttribute("newsList", newsList);
		request.setAttribute("imageList", imageList);
		request.setAttribute("videoList", videoList);
		
		return mapping.findForward("ShowDetail");
	}
}
