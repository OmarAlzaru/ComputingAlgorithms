import java.util.*;

public class buyandsell {
    public static int maxProfit(List<Integer> prices) {
        int n = prices.size();
        int[][] dp = new int[n][2];
        dp[0][0] = -prices.get(0);
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices.get(i));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices.get(i));
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of days: ");
        int n = scanner.nextInt();
        List<Integer> prices = new ArrayList<>();
        System.out.println("Enter the prices for each day:");
        for (int i = 0; i < n; i++) {
            int price = scanner.nextInt();
            prices.add(price);
        }
        int ans = maxProfit(prices);
        System.out.println("Maximum profit: " + ans);
        scanner.close();
    }
}