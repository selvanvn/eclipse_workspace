package complexstructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        String index;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
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
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.index = current.index + "" + index + " ";
        current.endOfWord = true;
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
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        //if node does not exists in map then create one and put it into map
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
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
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return "-1";
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        if(!current.endOfWord){
        	current.endOfWord=true;
        	return current.index;
        }
        if(current.endOfWord){
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
        TrieNode node = current.children.get(ch);
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
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //if true is returned then delete the mapping of character and trienode reference from map.
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
    }
}