import java.util.*;

class BinPacking {
    static int nextFit(int[] weight, int n, int c)
    {

        // Initialize result (Count of bins) and remaining
        // capacity in current bin.
        int res = 0, bin_rem = c;

        // Place items one by one
        for (int i = 0; i < n; i++) {
            // If this item can't fit in current bin
            if (weight[i] > bin_rem) {
                res++; // Use a new bin
                bin_rem = c - weight[i];
            }
            else
                bin_rem -= weight[i];
        }
        return res;
    }
    static int firstFit(int weight[], int n, int c)
    {
        // Initialize result (Count of bins)
        int res = 0;

        // Create an array to store remaining space in bins
        // there can be at most n bins
        int []bin_rem = new int[n];

        // Place items one by one
        for (int i = 0; i < n; i++)
        {
            // Find the first bin that can accommodate
            // weight[i]
            int j;
            for (j = 0; j < res; j++)
            {
                if (bin_rem[j] >= weight[i])
                {
                    bin_rem[j] = bin_rem[j] - weight[i];
                    break;
                }
            }

            // If no bin could accommodate weight[i]
            if (j == res)
            {
                bin_rem[res] = c - weight[i];
                res++;
            }
        }
        return res;
    }


    static int bestFit(int weight[], int n, int c)
    {

        // Initialize result (Count of bins)
        int res = 0;

        // Create an array to store
        // remaining space in bins
        // there can be at most n bins
        int []bin_rem = new int[n];

        // Place items one by one
        for (int i = 0; i < n; i++)
        {

            // Find the best bin that
            // can accommodate
            // weight[i]
            int j;

            // Initialize minimum space
            // left and index
            // of best bin
            int min = c + 1, bi = 0;

            for (j = 0; j < res; j++)
            {
                if (bin_rem[j] >= weight[i] &&
                        bin_rem[j] - weight[i] < min)
                {
                    bi = j;
                    min = bin_rem[j] - weight[i];
                }
            }

            // If no bin could accommodate weight[i],
            // create a new bin
            if (min == c + 1)
            {
                bin_rem[res] = c - weight[i];
                res++;
            }
            else // Assign the item to best bin
                bin_rem[bi] -= weight[i];
        }
        return res;
    }
    static int firstFitDec(int weight[], int n, int c)
    {

        Arrays.sort(weight);
        reverseArray(weight);

        // Now call first fit for sorted items
        return firstFit(weight, n, c);
    }
    static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    static int bestFitDec(int weight[], int n, int c)
    {

        Arrays.sort(weight);
        reverseArray(weight);

        // Now call first fit for sorted items
        return bestFit(weight, n, c);
    }



    // Driver code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        int[] weight = new int[n];
        System.out.println("Enter the weight of each item:");
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }
        System.out.print("Enter the capacity of each bin: ");
        int c = scanner.nextInt();
        System.out.println("Number of bins required in Next Fit : " + nextFit(weight, n, c));
        System.out.println("Number of bins required in Next Fit : " + firstFit(weight, n, c));
        System.out.println("Number of bins required in Next Fit : " + bestFit(weight, n, c));
        System.out.println("Number of bins required in Next Fit : " + firstFitDec(weight, n, c));
        System.out.println("Number of bins required in Next Fit : " + bestFitDec(weight, n, c));
    }
}
