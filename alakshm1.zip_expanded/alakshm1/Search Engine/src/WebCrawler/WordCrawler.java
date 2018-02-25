package WebCrawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordCrawler {
	
	private String url;
	ArrayList<String> arrayList;
	
	public WordCrawler(String url) {
		// TODO Auto-generated constructor stub
		setUrl(url);
		arrayList = new ArrayList<>();
		initializeArrayList(getUrl());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<String> getWords(){
		return arrayList;
	}
	
	public void initializeArrayList(String html){
		Document doc;
		String text = "";
		try {
			doc = Jsoup.parse(html);
			Elements body = doc.select("body");
			for(org.jsoup.nodes.Element e: body){
				text = text + "" + e.text();
			}
			
		} catch (IllegalArgumentException e){
			
			e.printStackTrace();
		}
		
		String []words = text.split(" ");
		for(String word: words){
			arrayList.add(word);
		}
	}

}
