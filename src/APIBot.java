import java.io.IOException;
import org.jibble.pircbot.*;

public class APIBot extends PircBot{
	//boolean to switch to get input
	boolean readZip = false;
	
	public APIBot() {
		//name of the API bot when it connects to the server
		this.setName("API-Bot");
	}
	
	//overrides abstract class in from PIRCBOT
	 public void onMessage(String channel, String sender, String login, String hostname, String message) {
		 
		 //read in zip/city and display information from weather class
		  if(readZip == true) {
			  try {
				Weather weather = new Weather(message);
				sendMessage(channel, weather.DisplayTemperature());
				sendMessage(channel, weather.DisplayDescription());
				sendMessage(channel, weather.DisplayWind());
				sendMessage(channel, weather.DisplayHumidity());
				sendMessage(channel, weather.DisplayHighLow());
				
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			  readZip = false;
		  }else {
		 
		 //display commands when "commands" is entered in channel
		 if (message.equalsIgnoreCase("commands")) {
			 sendMessage(channel, "Commands for API-Bot");
			 sendMessage(channel, "\"commands\"    - displays the usable commands");
			 sendMessage(channel, "\"getweather/what's the weather\"   - will prompt for zipcode/city name and return the weather");
			 sendMessage(channel, "\"bitcoin/what's the price of bitcoin\"        - Prints out current bitcoin information");
			 sendMessage(channel, "\"exit\"   	     - Makes the bot disconnect (Make sure to do this after you're done)");
		 }
		 
		 //prompt for zip/city name and set boolean to switch
		 if ((message.equalsIgnoreCase("getweather")) || (message.equalsIgnoreCase("what's the weather"))) {
			 sendMessage(channel, "Enter the Zip Code / City name");
			 readZip = true;
		 }

		 
		 //if "bitcoin" entered call bitcoin class and display proper information with special formatting
		 if ((message.equalsIgnoreCase("bitcoin")) || message.equalsIgnoreCase("what's the price of bitcoin")){
			 sendMessage(channel, "Bitcoin Information: ");
			 try {
				BitcoinPrice bitcoin = new BitcoinPrice();
				sendMessage(channel, bitcoin.DisplayTime());
				sendMessage(channel, bitcoin.DisplayUSDVal());
				sendMessage(channel, bitcoin.DisplayGBPVal() + "£");	//add custom formatting to display symbols in webchat
				sendMessage(channel, bitcoin.DisplayEURVal() + "€");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		 }
		 
		 //if "exit" entered have bot leave from the channel
		 //IMPORTANT: have bot leave before calling again or will try to 2 bots join under same name
		 if (message.equalsIgnoreCase("exit")) {
			 this.disconnect();
		 }
	 }
	}
	 
	 
	//when bot enters display message for commands
	public void onJoin(String channel, String sender, String login, String hostname) {
		sendMessage(channel, "Hello! Type \"commands\" to view the commands avaliable");
	}
	
	 
}

