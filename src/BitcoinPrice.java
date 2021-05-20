import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//Note: It Returns strings on the displays so we can pass them on to the API bot to print through IRC
//Note: Return GBP and EUR without symbol because IRC has different formatting and will result in a invalid character
public class BitcoinPrice {
	//Standard URL no API Key
	String URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
	String JsonInput;
	
	//Update the JsonInput
	BitcoinPrice() throws IOException{
		JsonInput = getJson(URL);
	}

//-------------Get the JSON from the API URL return as String---------------
	String getJson(String URL) throws IOException{
		URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        //set request type to GET
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        //Throw a runetime exception if failure to connect
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }  
        //Read in the JSON from the API 
        String JsonInput = "";
        Scanner input = new Scanner(url.openStream());
        while(input.hasNext()) {
        	JsonInput += input.nextLine();
        }    
        
		return JsonInput;
	}
	
//-------------Display API info on where information is from and how it's calculated---------------
	String DisplayDisclaimer() throws IOException {
		//use Gson to read Json from the API
		JsonInput = getJson(URL);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonobject = jsonParser.parse(JsonInput)	
        .getAsJsonObject();
        String Disclaimer = jsonobject.get("disclaimer").getAsString();
        
        System.out.println(Disclaimer); 
        
        return Disclaimer;
	}

//-------------Display the time for the last update(updated every minute)---------------
	String DisplayTime() throws IOException {
		//use Gson to read Json from the API
		JsonInput = getJson(URL);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonobject = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("time");
        String time = jsonobject.get("updated").getAsString();
        
        String FTime = "Updated: " + time;
        
        System.out.println(FTime);
        return FTime;
	}
	
//-------------Display the US value of bitcoin at according time---------------
	String DisplayUSDVal() throws IOException {
		//use Gson to read Json from the API
		JsonInput = getJson(URL);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonobject = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("bpi")
        .getAsJsonObject().getAsJsonObject("USD"); 
        String USDVal = jsonobject.get("rate").getAsString();
        
        String FUSDVal = "United States Dollar: " + USDVal + "$";
        System.out.println(FUSDVal); 
        
        return FUSDVal;
        
	}
	
	
//-------------Display GBP value of bitcoin at according time---------------
	String DisplayGBPVal() throws IOException {
		//use Gson to read Json from the API
		JsonInput = getJson(URL);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonobject = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("bpi")
        .getAsJsonObject().getAsJsonObject("GBP"); 
        String GBPVal = jsonobject.get("rate").getAsString();
        
        String FGBPVal = "British Pound Sterling: " + GBPVal;
        System.out.println("British Pound Sterling: " + GBPVal + "£");  
        return FGBPVal;
	}
	
//-------------Display EUR value of bitcoin at according time---------------
	String DisplayEURVal() throws IOException {
		//use Gson to read Json from the API
		JsonInput = getJson(URL);	
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonobject = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("bpi")
        .getAsJsonObject().getAsJsonObject("EUR"); 
        String EURVal = jsonobject.get("rate").getAsString();
        
        String FEURVal = "Euro: " + EURVal + "";
        System.out.println("Euro: " + EURVal + "€");  
        return FEURVal;
	}
		
}
