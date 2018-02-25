package test;

import java.util.AbstractMap.SimpleEntry;

public class LongestString {
	
	String sentence = null;
	
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public SimpleEntry<Integer, String> getLongestString() {
		if(sentence == null) {
			return null;
		}
		SimpleEntry<Integer, String> stringLength =new SimpleEntry<Integer, String>(0, "");
		String [] words = sentence.replaceAll("[,\\']", "").replace("\\.", " ").split(" ");
		for(String word: words) {
			if(!validateWord(word)) {  //skips the word if contains anything other english alphabets and hyphen	
				continue;
			}
			if(word.length() > stringLength.getKey()) {
				stringLength = new SimpleEntry<Integer, String>(word.length(), word);
			}
		}
		
		return stringLength;
	}
	
	private boolean validateWord(String word) {
		if(word.matches("[A-Za-z\\-]+"))
			return true;
		else
			return false;	
	}
	
	public static void main(String args[]){
		LongestString ls=new LongestString();
		ls.setSentence("The cow jumped over the moon.");
		SimpleEntry<Integer, String> longestWord = ls.getLongestString();
		System.out.println("The longest word is "+longestWord.getValue()+ " with length "+ longestWord.getKey());
	}

}
