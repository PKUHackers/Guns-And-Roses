package org.pku.wip.NRS.tool;

import java.util.ArrayList;
import java.util.List;

public class MergedNews {
	private String url;
	private String title;
	private String pubdate;
	private String description;
	private String cleanContent;
	private String type;
	private String pic;
	private String keywords;
	private String topicId;
	private List<Image> imagelist = new ArrayList<Image>();
	private List<Video> videolist = new ArrayList<Video>();
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCleanContent() {
		return cleanContent;
	}
	public void setCleanContent(String cleanContent) {
		this.cleanContent = cleanContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public List<Image> getImagelist() {
		return imagelist;
	}
	public void setImagelist(List<Image> imagelist) {
		this.imagelist = imagelist;
	}
	public List<Video> getVideolist() {
		return videolist;
	}
	public void setVideolist(List<Video> videolist) {
		this.videolist = videolist;
	}
}
