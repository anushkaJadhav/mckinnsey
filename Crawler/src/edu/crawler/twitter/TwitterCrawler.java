package edu.crawler.twitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TwitterCrawler {
	
	/*keeps a track of unique URLs*/
	private static HashMap urlMap = new HashMap();
	/*gives the json object page count*/
	private static int pageCount=1;
	
	/**
	 * this methods hit the ywitte webpage with the  hashtag and retuns json object
	 * it gets the text from the json object and extract url if any.
	 * @param hashTag
	 */
	public void getUniqueUrl(String hashTag){
		
		String formattedHashTag=formatHashTag(hashTag);
		String url = "http://twitter.com/search.json?q=%23"+hashTag;
		
		BufferedReader in;
		try {
			while(urlMap.size()<=99){
				String pageCountUrl="&page="+pageCount;
				URL source = new URL(url+pageCountUrl);
				in = new BufferedReader(new InputStreamReader(source.openStream(),
						"UTF-8"));
				JsonElement jse = new JsonParser().parse(in);
				in.close();
				JsonObject mainObj = jse.getAsJsonObject();
				JsonArray jsonResultArray = mainObj.getAsJsonArray("results");
				for (int i = 0; i < jsonResultArray.size(); i++) {
					JsonElement jsonElement = jsonResultArray.get(i);
					JsonObject elementObject = jsonElement.getAsJsonObject();
					String text = elementObject.get("text").toString();
					String extractedUrl = extractUrls(text);
					if (extractedUrl != null && extractedUrl.length() > 0) {
						if (urlMap.size() <= 99) {
							if(!urlMap.containsKey(extractedUrl))
							urlMap.put(extractedUrl,extractedUrl);
						} else {
							i = jsonResultArray.size();
						}
					}
				}
				System.out.println("page count is : "+pageCount);
				System.out.println("map size : "+urlMap.size());
				pageCount++;
			}
			
			printExtractedUrl(urlMap);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	/**
	 * user inserts hashtag as #<string>
	 * it formats it to <string> eliminating the #
	 * @param hashTag
	 * @return
	 */
	private String formatHashTag(String hashTag) {
		int indexOfHash=hashTag.indexOf("#");
		if(indexOfHash!=0){
			throw new IllegalArgumentException();
		}else{
			String newHasgTag=hashTag.substring(1);
			return newHasgTag;	
		}
	}
	
	/**
	 * gets the key set of the map 
	 * iterates over the key set to print the unique Urls
	 * @param urlMap
	 */
	private  void printExtractedUrl(Map urlMap) {
		int count=1;
		Set urlSet =urlMap.keySet();
		Iterator<String> itr = urlSet.iterator();
		while (itr.hasNext()) {
			System.out.println("url "+(count++)+" " + itr.next());
		}

	}
	/**
	 * finds a substring that matches the regular expression
	 * once found it return the matching expression
	 * String regex gives a regular expression to find a url in format http://*
	 * @param input
	 * @return
	 */
	public  String extractUrls(String input) {
		ArrayList<String> result = new ArrayList<String>();
		String url = null;
		String regex = "\\b(http)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {

			url = matcher.group();
		}

		return url;
	}

}
