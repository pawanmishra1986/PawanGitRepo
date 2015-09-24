package com.euro.fetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;

/**
 * @author Pawan Mishra
 *
 */
public class ExtractDataFromUrl {

	private static final String mainUrl = "http://api.goeuro.com/api/v2/position/suggest/en/";
	private String cityName;

	public ExtractDataFromUrl(String cityName) {
		this.cityName = cityName;
	}

	public JSONArray fetchAndExtractData() {
		String completeUrl = mainUrl + cityName;
		InputStream inputStream = null;
		JSONArray json = null;
		try {
			inputStream = new URL(completeUrl).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream,
					Charset.forName("UTF-8")));
			String jsonText = extract(rd);
			//jsonText = jsonText.substring(1, jsonText.length()-1);
			 json = new JSONArray(jsonText);
			return json;
		} catch(MalformedURLException e){
			System.out.println("IncorrectUrl : " + completeUrl);
		}catch(IOException io){
			System.out.println("Unable to fetch data from given url : " + io.getMessage());
		}
		finally {
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(IOException ex){
					System.out.println("Unable to close input stream : " + ex.getMessage());
				}
				
			}
		}
		return json;
	}

	private String extract(Reader rd) throws IOException {
		StringBuilder stringJson = new StringBuilder();
		int i;
		while ((i = rd.read()) != -1) {
			stringJson.append((char) i);
		}
		return stringJson.toString();
	}

}
