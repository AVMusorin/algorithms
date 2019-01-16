package greedy.huffman;

import java.util.HashMap;
import java.util.Scanner;

public class HuffmanDecode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int numLetters = Integer.valueOf(firstLine[0]);
        HashMap<String, String> dict = new HashMap<>();
        for (int i = 0; i < numLetters; i++) {
            String line = scanner.nextLine();
            dict.put(line.split(":")[1].replace(" ", ""), line.split(":")[0]);
        }
        int maxCodeLength = dict.keySet().stream().map(String::length).max(Integer::compareTo).get();
        String encodeString = "";
        String decodeString = scanner.nextLine();
        while (decodeString.length() > 0) {
            for (int i = maxCodeLength; i > 0; i--) {
                try {
                    String sample = decodeString.substring(0, i);
                    if (dict.containsKey(sample)) {
                        encodeString += dict.get(sample);
                        decodeString = decodeString.substring(i, decodeString.length());
                        break;
                    }
                } catch (java.lang.StringIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        System.out.println(encodeString);
    }
}

