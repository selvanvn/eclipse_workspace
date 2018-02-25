package ComplxStruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebCrawler.WordCrawler;


public class ComplxStruct {
	
	private int n;
	private String []htmls;
	private Trie T;
	WordCrawler crawler;
	
	public int getCount() {
		return n;
	}

	public void setCount(int count) {
		this.n = count;
	}

	public String[] getHtmls() {
		return htmls;
	}

	public void setHtmls(String[] htmls) {
		this.htmls = htmls;
	}
	
	public ComplxStruct(int count, String []htmls) {
		// TODO Auto-generated constructor stub
		setCount(count);
		setHtmls(htmls);
		T = new Trie();
		
	}
	
	public int insert(){
		
		int index = 0;
		for(String html : getHtmls()){
			crawler = new WordCrawler(html);
			
			ArrayList<String> list = crawler.getWords();
			for(String l: list){
				T.insert(l.toLowerCase(), index);
			}
			index++;
			if(index == n){
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
				list.add(key);
			}
		}
		
		return list;
		
	}
	
	public class Trie {

	    private class TrieNode {
	        Map<Character, TrieNode> child;
	        boolean end;
	        String index;
	        public TrieNode() {
	            child = new HashMap<>();
	            end = false;
	            index = "";
	        }
	    }

	    private final TrieNode root;
	    public Trie() {
	        root = new TrieNode();
	    }

	    /**
	     * Iterative implementation of insert into trie
	     */
	    public void insert(String word,int index) {
	        TrieNode current = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode node = current.child.get(ch);
	            if (node == null) {
	                node = new TrieNode();
	                current.child.put(ch, node);
	            }
	            current = node;
	        }
	        //mark the current nodes endOfWord as true
	        current.index = current.index + "" + index + " ";
	        current.end = true;
	        return;
	    }

	    /**
	     * Recursive implementation of insert into trie
	     */
	    public void insertRecursive(String word,int index) {
	        insertRecursive(root, word, 0, index);
	        return;
	    }


	    private void insertRecursive(TrieNode current, String word, int index, int i) {
	        if (index == word.length()) {
	            //if end of word is reached then mark endOfWord as true on current node
	        	current.index = current.index + "" + index + " ";
	            current.end = true;
	            return;
	        }
	        char ch = word.charAt(index);
	        TrieNode node = current.child.get(ch);

	        //if node does not exists in map then create one and put it into map
	        if (node == null) {
	            node = new TrieNode();
	            current.child.put(ch, node);
	        }
	        insertRecursive(node, word, index + 1, i);
	        
	    }

	    /**
	     * Iterative implementation of search into trie.
	     */
	    public String search(String word) {
	        TrieNode current = root;
	        for (int i = 0; i < word.length(); i++) {
	            char ch = word.charAt(i);
	            TrieNode node = current.child.get(ch);
	            //if node does not exist for given char then return false
	            if (node == null) {
	                return "-1";
	            }
	            current = node;
	        }
	        //return true of current's endOfWord is true else return false.
	        if(!current.end){
	        	current.end=true;
	        	return current.index;
	        }
	        if(current.end){
	        	return current.index;
	        }
	        return "-1";
	    }

	    /**
	     * Recursive implementation of search into trie.
	     */
	    public String searchRecursive(String word) {
	        return searchRecursive(root, word, 0);
	    }
	    private String searchRecursive(TrieNode current, String word, int index) {
	        if (index == word.length()) {
	            //return true of current's endOfWord is true else return false.
	            return current.index;
	        }
	        char ch = word.charAt(index);
	        TrieNode node = current.child.get(ch);
	        //if node does not exist for given char then return false
	        if (node == null) {
	            return "-1";
	        }
	        return searchRecursive(node, word, index + 1);
	    }

	    /**
	     * Delete word from trie.
	     */
	    public void delete(String word) {
	        delete(root, word, 0);
	    }

	    /**
	     * Returns true if parent should delete the mapping
	     */
	    private boolean delete(TrieNode curr, String word, int index) {
	        if (index == word.length()) {
	            //when end of word is reached only delete if currrent.endOfWord is true.
	            if (!curr.end) {
	                return false;
	            }
	            curr.end = false;
	            //if current has no other mapping then return true
	            return curr.child.size() == 0;
	        }
	        char ch = word.charAt(index);
	        TrieNode node = curr.child.get(ch);
	        if (node == null) {
	            return false;
	        }
	        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

	        //if true is returned then delete the mapping of character and trienode reference from map.
	        if (shouldDeleteCurrentNode) {
	            curr.child.remove(ch);
	            //return true if no mappings are left in the map.
	            return curr.child.size() == 0;
	        }
	        return false;
	    }
	}
}
