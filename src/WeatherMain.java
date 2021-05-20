import java.io.IOException;
import java.util.Scanner;

public class WeatherMain {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		//get input to pass to weather
		System.out.println("Enter a city/Zip-Code(in US): ");
		String city = input.nextLine();
		
		//create weather object with city and display all
		Weather weather = new Weather(city);	
		weather.displayAll();
		

	}

}
