package crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlWords {
	
	private String url;
	ArrayList<String> arrayList;
	
	public CrawlWords(String url) {
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
	
	public void initializeArrayList(String url){
		Document doc;
		String text = "";
		try {
			doc = Jsoup.connect(url).get();
			Elements body = doc.select("body");
			for(org.jsoup.nodes.Element e: body){
				text = text + "" + e.text();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"++++++++++++"+url);
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage()+"++++++++++++"+url);
			e.printStackTrace();
		}
		
		String []words = text.split(" ");
		for(String word: words){
			arrayList.add(word);
		}
	}
}
