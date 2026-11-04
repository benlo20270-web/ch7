import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();
		
		int[] years = new int[n];
		int[] months = new int[n];
		double[] prices = new double[n];
		
		loadData ( in, years, months, prices);
		printData(years, months,prices);
		
		System.out.println(symbol + " lowest: $" + prices[indexOfLowest(prices)] + " in " + monthName(months[indexOfLowest(prices)]) + " " +  years[indexOfLowest(prices)]);
		System.out.println(symbol + " highest: $" + prices[indexOfHighest(prices)] + " in " + monthName(months[indexOfHighest(prices)]) + " " +  years[indexOfHighest(prices)]);
		
		
		
    }
    
    
    public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
		for (int i = 0; i < years.length; i++) {
			
			years[i] = in.nextInt();
			months[i] = in.nextInt();
			prices[i] = in.nextDouble();
			}
		
		
}

public static String monthName(int month) {
	String[] monthname = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	return monthname[month-1];
	
	
}
   
   
   public static void printData(int[] years, int[] months, double[] prices) {
	   for ( int i = 0; i < years.length; i++) {
	   System.out.println(monthName(months[i]) + " " + years[i] + ": $" + prices[i]); }
	   
	   
	   
	   
	   
   }


public static int indexOfLowest(double[] prices) {
	int minIndex = 0;
	for (int i = 0; i < prices.length; i++)
	 if (prices[i] < prices[minIndex]) {
		 minIndex = i;
		 
	 }
	 
	return minIndex;
	
	
}

public static int indexOfHighest(double[] prices) {
	int maxIndex = 0;
	for (int i = 0; i < prices.length; i++)
	 if (prices[i] > prices[maxIndex]) {
		 maxIndex = i;
		 
	 }
	 
	return maxIndex;
	
	
}








}
