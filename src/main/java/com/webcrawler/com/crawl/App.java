package com.webcrawler.com.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{	
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	public void siteTraverse(ArrayList<String> list){
		try {
	
			int i =1;
		for(String url: list) {
			Document doc = Jsoup.connect(url).get();
			String text = doc.body().text();
			CountWords(text);
			System.out.println(i+" \t "+url);
			i++;
			Thread.sleep(50);
			}
		}catch(Exception e) {}
		}

	//method to print word and count
    public void PrintAllWordsAndCount() {

        for (String key : map.keySet()) {
        	 System.out.println(map.get(key) + "\t" + key);
        }
    }

    private void CountWords(String text) {

       
        String[] lines = text.split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");

        for (String word : lines) {

            if (map.containsKey(word)) {
                int val = map.get(word);
                val += 1;
                map.remove(word);
                map.put(word, val);
            } else {
                map.put(word, 1);
            }

        }
    }
	
	
	
    public static void main( String[] args ) throws IOException 
    
    {
    	
    	Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Computer").get();
    	Elements links = doc.select( "a[href^=\"/wiki/\"]");  
    	int i=0;
    	ArrayList<String> list=new ArrayList<String>();
    	for (Element link : links) {  
    		if(i<1000) {
    			if(!(link.attr("href").contains(".png")) && !(link.attr("href").contains(".JPG")) && !(link.attr("href").contains(".jpg"))) {
    			list.add("https://en.wikipedia.org/" + link.attr("href"));
    			}		 	
    		}
    	    i++;
    	}  
    	
    	App app=new App();
    	System.out.println("Wikipedia Sites are: \n");
    	app.siteTraverse(list);
    	
    	System.out.println("\nWords from above Wikipedia Sites are: \n");
    	app.PrintAllWordsAndCount();
    	
    	
       
    }
    
}
