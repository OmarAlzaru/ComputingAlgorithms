import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BinPacking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int itemCount = scanner.nextInt();

        List<Integer> items = new ArrayList<>();
        System.out.println("Enter the sizes of items:");
        for (int i = 0; i < itemCount; i++) {
            int size = scanner.nextInt();
            items.add(size);
        }

        System.out.print("Enter the capacity of each bin: ");
        int binCapacity = scanner.nextInt();

        scanner.close();

        List<List<Integer>> bins = firstFitDecreasing(items, binCapacity);

        System.out.println("Bins required: " + bins.size());
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
        }
    }

    public static List<List<Integer>> firstFitDecreasing(List<Integer> items, int binCapacity) {
        // Sort items in non-increasing order
        Collections.sort(items, Collections.reverseOrder());

        // Initialize bins
        List<List<Integer>> bins = new ArrayList<>();
        bins.add(new ArrayList<>());

        // Pack items into bins using First Fit Decreasing algorithm
        for (Integer item : items) {
            boolean packed = false;
            for (List<Integer> bin : bins) {
                int remainingCapacity = binCapacity - bin.stream().mapToInt(Integer::intValue).sum();
                if (item <= remainingCapacity) {
                    bin.add(item);
                    packed = true;
                    break;
                }
            }
            if (!packed) {
                List<Integer> newBin = new ArrayList<>();
                newBin.add(item);
                bins.add(newBin);
            }
        }
        return bins;
    }
}
