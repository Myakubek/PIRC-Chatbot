import java.io.IOException;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

//HOW TO USE:
//WEBSITE TO ACCESS CHATBOT
//https://webchat.freenode.net/

//1) Start APIBotMain
//2) Go to website to access chatbot
//3) Join the server #WeatherBot-777 NOTE:Make sure you include the #
//4) type commands to get started




public class APIBotMain {

	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		
		APIBot bot = new APIBot();
		
		 // Enable debugging output
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");
        
        // Join the #pircbot channel
        bot.joinChannel("#WeatherBot-777");
 
	}

}
