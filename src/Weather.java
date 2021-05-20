import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Weather {
	static String JsonInput;
	String URL;	//once formatted will be stored here
	String APIkey = "8d5dcf4b8be641184c17f4d2d5bbb62c";	//personal key to access API
	String City;
	
//-------------Creates object and gets correct URL and JSONInput from it---------------
	Weather(String CityInput) throws IOException{
		this.City = CityInput;
		URL = createURL(City, APIkey, "us");
		JsonInput = getJson(URL, APIkey);
		
		
	}
	
//-------------Create the API URL given key city and string (default us)---------------
		String createURL(String City, String APIKEY, String Country) {
		//--------Debug-----------(find the API URL that's being created)
		System.out.println("http://api.openweathermap.org/data/2.5/weather?q=" + City + ",us&appid=" + APIKEY);
		
		//Get the URL given the City and API-key(only US for this program)
		String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + City + ",us&appid=" + APIKEY;
		
		return URL;
	}
	
//-------------Get the JSON from the REST API---------------
	String getJson(String URL, String APIkey) throws IOException{
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
	
	
//-------------Print the Temperature in F---------------
	void displayAll() {
		DisplayTemperature();
		DisplayDescription();
		DisplayWind();
		DisplayHumidity();
		DisplayHighLow();
	}
	

//-------------Print the Temperature in F---------------
	String DisplayTemperature() {
		
		//Prints nested information, finds a specific EX| main --> temp
        JsonParser jsonParser = new JsonParser();
        JsonObject weather = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("main");
        
        String temp = weather.get("temp").getAsString();
        
        //Store and format tempearture
        double temperature = Double.parseDouble(temp);	//convert to double
        temperature = temperature*9/5-459.67;			//Formula to convert kelvin to F
        temperature = Math.round(temperature*100)/100;	//round result
        
        String Ftemp = "Temperature: " + temperature + "F";
        System.out.println(Ftemp);
        return Ftemp;
       
	}
	
//-------------Print the Description of the weather---------------
	String DisplayDescription() {
		
		//Has to create an JsonArray due to formatting
        JsonParser jsonParser = new JsonParser();
        JsonElement weather = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonArray("weather").get(0)
        .getAsJsonObject().get("description");
        
        String desc = weather.getAsString();
        System.out.println(desc);
        
        return desc;
	}
//-------------Print Wind Speed in M/S---------------
	String DisplayWind() {
		//Prints nested information, finds a specific EX| main --> temp
        JsonParser jsonParser = new JsonParser();
        JsonObject weather = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("wind");
        
        String wSpeed = weather.get("speed").getAsString();
        String FwSpeed = "Wind Speed: " + wSpeed + " m/s";
        System.out.println(FwSpeed);	
        return FwSpeed;
	}
	
//-------------Display the % Humidity---------------
	String DisplayHumidity() {
		//Prints nested information, finds a specific EX| main --> temp
        JsonParser jsonParser = new JsonParser();
        JsonObject weather = jsonParser.parse(JsonInput)	
        .getAsJsonObject().getAsJsonObject("main");
        
        String wSpeed = weather.get("humidity").getAsString();
        String FwSpeed = "Humidity " + wSpeed + "%";
        
        System.out.println(FwSpeed);
        return FwSpeed;
	}
	
//-------------Display the Min and Max temp---------------
		String DisplayHighLow() {
			//Prints nested information, finds a specific EX| main --> temp
	        JsonParser jsonParser = new JsonParser();
	        JsonObject weather = jsonParser.parse(JsonInput)	
	        .getAsJsonObject().getAsJsonObject("main");
	        
	        String tempMin = weather.get("temp_min").getAsString();
	        String tempMax = weather.get("temp_max").getAsString();
	        
	        double tempMinD = Double.parseDouble(tempMin);	//convert to double
	        tempMinD = tempMinD*9/5-459.67;			//Formula to convert kelvin to F
	        tempMinD = Math.round(tempMinD*100)/100;
	        
	        double tempMaxD = Double.parseDouble(tempMax);	//convert to double
	        tempMaxD = tempMaxD*9/5-459.67;			//Formula to convert kelvin to F
	        tempMaxD = Math.round(tempMaxD*100)/100;
	        
	        
	        String FMaxMin = "Today the weather will be a low of " + tempMinD + "F and a high of " + tempMaxD + "F";
	        System.out.println(FMaxMin);
	        return FMaxMin;
		}
	
}

