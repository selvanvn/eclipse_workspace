package cs600.stevens.project.engine;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cs600.stevens.project.complexstructure.ComplexStructure;
import cs600.stevens.project.crawler.WebCrawler;

public class SearchEngine {
	
	static WebCrawler crawler;
	static ComplexStructure complexStructure;
	static String []ol;
	public static void main(String[] args) {
		String url="";
		String input="";
		
		System.out.println("Enter the url of the website: ");
		Scanner scanner=new Scanner(System.in);
		url = url + "" + scanner.nextLine();
		
		crawler = new WebCrawler();
		Elements links = crawler.getLinks(url);
		
		ol = new String[4];
		int index = 0;
		for(Element e: links){
			ol[index] = e.attr("abs:href").toString();
			index++;
			if(index == 4){
				break;
			}
		}
		
		complexStructure = new ComplexStructure(4, ol);
		complexStructure.insert();
		
		System.out.println("Dictionary Created!");
		
				
			System.out.println("Enter the word(s) to search: ");
			scanner=new Scanner(System.in);
			input = input + "" + scanner.nextLine();
			scanner.close();
			
			ArrayList<String> results = complexStructure.search(input.toLowerCase());
			System.out.println("<===============Search Results==============>");
			if(!results.isEmpty()){
				for(String r: results){
					System.out.println(r);
				}
			}
			else
				System.out.println("Not Found!");
			
		
			
	}
}
