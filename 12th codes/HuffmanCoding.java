import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Scanner;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to encode: ");
        String input = scanner.nextLine();
        scanner.close();

        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        // Calculate frequencies of characters
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            pq.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        HuffmanNode root = pq.peek();

        // Traverse Huffman tree and print codes
        HashMap<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);

        System.out.println("Huffman Codes:");
        for (char c : codes.keySet()) {
            System.out.println(c + ": " + codes.get(c));
        }
    }

    public static void generateCodes(HuffmanNode node, String code, HashMap<Character, String> codes) {
        if (node == null)
            return;
        if (node.data != '\0') {
            codes.put(node.data, code);
        }
        generateCodes(node.left, code + "0", codes);
        generateCodes(node.right, code + "1", codes);
    }
}
