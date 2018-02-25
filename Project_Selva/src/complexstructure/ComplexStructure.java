package complexstructure;

import java.util.ArrayList;
import java.util.HashMap;

import crawler.CrawlWords;

public class ComplexStructure {
	
	private int count;
	private String []OL;
	private Trie T;
	CrawlWords crawlWords;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String[] getOL() {
		return OL;
	}

	public void setOL(String[] oL) {
		OL = oL;
	}
	
	public ComplexStructure(int count, String []ol) {
		// TODO Auto-generated constructor stub
		setCount(count);
		setOL(ol);
		T = new Trie();
		
	}
	
	public int insert(){
		
		int index = 0;
		for(String o : getOL()){
			crawlWords = new CrawlWords(o);
			ArrayList<String> list = crawlWords.getWords();
			for(String l: list){
				T.insert(l.toLowerCase(), index);
			}
			index++;
			if(index == count){
				break;
			}
		}
		return 0;
		
	}
	
	public ArrayList<String> search(String s){
		
		String []words = s.split(" ");
		String []sresults = new String[words.length];
		int len = 0;
		for(; len < words.length; len++){
			sresults[len] = T.search(words[len]);
		}
		HashMap<String, Integer> map = new HashMap<>();
		String temp = "";
		for(len = 0; len < words.length; len++){
			String[] spl = sresults[len].split(" ");
			for(String sp :spl){
				if(!temp.equals(sp)){
					temp = "" + sp;
					if(map.containsKey(sp)){
						int val = map.get(sp);
						map.put(sp, ++val);
					}
					else{
						map.put(sp, 1);
					}
				}
			}
		}
		
		ArrayList<String> list = new ArrayList<>();
				
		for(String key: map.keySet()){
			if(map.get(key)==words.length && !key.equals("-1")){
				list.add(getOL()[Integer.parseInt(key)]);
			}
		}
		
		return list;
		
	}
	
	
}
