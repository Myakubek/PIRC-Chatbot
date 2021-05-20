import java.io.IOException;
import java.util.Scanner;

public class BitcoinPriceMain {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		BitcoinPrice bitcoin = new BitcoinPrice();
		//display the source of calculations and REST API
		bitcoin.DisplayDisclaimer();
		
		
		System.out.println("\nPress Enter to get current bitcoin price (Updated every minute)");
			
		//repeat display every time entered is pressed
		do {
			input.nextLine();
			
			bitcoin.DisplayTime();
			bitcoin.DisplayUSDVal();
			bitcoin.DisplayGBPVal();
			bitcoin.DisplayEURVal();
			
		}while(true);
		

	}

}
