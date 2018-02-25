package crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

	public Elements getLinks(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a");
			Set<Element> s = new HashSet<>();
			s.addAll(links);
			Elements links2 = new Elements(s);
			links.clear();
			links.addAll(s);
			
			return links2;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
