

import java.io.Serializable;


/**
 * News
 * 
 * @author R.J.Y
 * @version 1.0
 */
public class News implements Serializable{

	/**
	 * generated serial version id
	 */
	private static final long serialVersionUID = 37543599397928421L;

	private String id = "";
	private String link = "";
	private String title = "";
	private String pubDate = "";
	private String author = "";
	private String source = "";
	private String description = "";
	private String showContent = "";
	private String cleanContent = "";
	private String type = "";
	private String pic = "";
	private int clickCnt = 0;
	private String keyWords = "";		// keyword:weight
	private double prediction = 0.0;	// recommendation value
	private double novelty = 0.0;		// novelty
	private double diversity = 0.0;		// diversity
	private String topicId = "";
	
	
	public String toXML(){
		String result = "<News>" + "\n";
		
		//result += "\t" + "<id>" + id + "</id>" + "\n";
		result += "\t" + "<link>" + link + "</link>" + "\n";
		result += "\t" + "<title>" + title + "</title>" + "\n";
		result += "\t" + "<pubDate>" + pubDate + "</pubDate>" + "\n";
		//result += "\t" + "<author>" + author + "</author>" + "\n";
		//result += "\t" + "<source>" + source + "</source>" + "\n";
		result += "\t" + "<description>" + description + "</description>" + "\n";
		//result += "\t" + "<showContent>" + showContent + "</showContent>" + "\n";
		result += "\t" + "<cleanContent>" + cleanContent + "</cleanContent>" + "\n";
		result += "\t" + "<type>" + type + "</type>" + "\n";
		result += "\t" + "<pic>" + pic + "</pic>" + "\n";
		//result += "\t" + "<clickCnt>" + clickCnt + "</clickCnt>" + "\n";
		result += "\t" + "<keyWords>" + keyWords + "</keyWords>" + "\n";
		//result += "\t" + "<prediction>" + prediction + "</prediction>" + "\n";
		result += "\t" + "<topicId>" + topicId + "</topicId>" + "\n";
		
		result += "</News>" + "\n";
		
		return result;
	}
	
	
	public void fromXML(String xml){
		if(xml.trim().equals(""))return;
		
		int beginIndex, endIndex;
		beginIndex = xml.indexOf("<id>") + "<id>".length(); endIndex = xml.indexOf("</id>");
		id = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<link>") + "<link>".length(); endIndex = xml.indexOf("</link>");
		link = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<title>") + "<title>".length(); endIndex = xml.indexOf("</title>");
		title = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<pubDate>") + "<pubDate>".length(); endIndex = xml.indexOf("</pubDate>");
		pubDate = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<author>") + "<author>".length(); endIndex = xml.indexOf("</author>");
		author = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<source>") + "<source>".length(); endIndex = xml.indexOf("</source>");
		source = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<description>") + "<description>".length(); endIndex = xml.indexOf("</description>");
		description = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<showContent>") + "<showContent>".length(); endIndex = xml.indexOf("</showContent>");
		showContent = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<cleanContent>") + "<cleanContent>".length(); endIndex = xml.indexOf("</cleanContent>");
		cleanContent = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<type>") + "<type>".length(); endIndex = xml.indexOf("</type>");
		type = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<pic>") + "<pic>".length(); endIndex = xml.indexOf("</pic>");
		pic = xml.substring(beginIndex, endIndex);
		
		beginIndex = xml.indexOf("<clickCnt>") + "<clickCnt>".length(); endIndex = xml.indexOf("</clickCnt>");
		clickCnt = Integer.parseInt(xml.substring(beginIndex, endIndex));
		
		beginIndex = xml.indexOf("<keyWords>") + "<keyWords>".length(); endIndex = xml.indexOf("</keyWords>");
		keyWords = xml.substring(beginIndex, endIndex);
		
		//beginIndex = xml.indexOf("<prediction>") + "<prediction>".length(); endIndex = xml.indexOf("</prediction>");
		//prediction = Double.parseDouble(xml.substring(beginIndex, endIndex));
		
		beginIndex = xml.indexOf("<topicId>") + "<topicId>".length(); endIndex = xml.indexOf("</topicId>");
		topicId = xml.substring(beginIndex, endIndex);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShowContent() {
		return showContent;
	}
	public void setShowContent(String showContent) {
		this.showContent = showContent;
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
	public int getClickCnt() {
		return clickCnt;
	}
	public void setClickCnt(int clickCnt) {
		this.clickCnt = clickCnt;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public double getPrediction() {
		return prediction;
	}
	public void setPrediction(double prediction) {
		this.prediction = prediction;
	}
	public double getNovelty() {
		return novelty;
	}
	public void setNovelty(double novelty) {
		this.novelty = novelty;
	}
	public double getDiversity() {
		return diversity;
	}
	public void setDiversity(double diversity) {
		this.diversity = diversity;
	}
	public String getTopicId(){
		return topicId;
	}
	public void setTopicId(String topicId){
		this.topicId = topicId;
	}
}
