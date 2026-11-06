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
		System.out.println(symbol + "Biggest Drop: " + monthName(indexOfBiggestDrop(prices)));
		double result = simulate(years, months, prices);
		System.out.printf("Final value: $%,.2f%n", result);
		
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


public static int indexOfBiggestDrop (double[] prices) {
	int largePerDrop = 1;
	double maxPerDrop = (prices[0] - prices[1]) / prices[0];
	for (int i = 1; i < prices.length; i++) {
		double currentPerDrop = (prices[i - 1] - prices[i]) / prices[i - 1];
		if (currentPerDrop > maxPerDrop) {
            maxPerDrop = currentPerDrop;
            largePerDrop= i+1;
		}
	}
	return largePerDrop;
}

 //public static String decide(double[] history, boolean hasShares, double boughtAt) {
	//if (hasShares == true) {
	//	return "hold";
	//} else {
	//return "BUY";
	//	}
// }
	
	
	public static String decide(double[] history, boolean hasShares, double boughtAt) {
	String result= "HOLD";
	
	if (hasShares == false) {
		for (int i = 0; i < history.length-2; i++) {
		if (history[i] > history [i+1] && history [i+1] > history[i+2])
		result = "BUY";
		boughtAt = history[i];
	}
}
	else {
		for (int i = 0; i < history.length; i++) {
			if (history[i] - boughtAt / boughtAt >= 0.1) {
				return result = "SELL";
				
				
				}
		}
	}
	return result;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


public static final double START_CASH = 10000.0;
public static final double FEE = 10.0;

public static double simulate(int[] years, int[] months, double[] prices) {
    double cash = START_CASH;
    int shares = 0;
    double boughtAt = 0.0;

    for (int i = 0; i < prices.length; i++) {
        double[] history = Arrays.copyOf(prices, i + 1);
        String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
            int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
        }
    }
    return cash + shares * prices[prices.length - 1];
}






}




