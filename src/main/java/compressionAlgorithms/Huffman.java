package compressionAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Huffman {

    String filePath;

    public Huffman() {
    }

    Boolean compress(String filePath) throws FileNotFoundException {

        if (filePath.split("\\.")[1].equals("txt")) {
            File file = new File(filePath);
            HashMap<Character, Integer> charFrequencies = countCharFrequencyInFile(file);
            Node rootNode = constructHuffmanTree(charFrequencies);
            HashMap<String, String> charsBitRepresentations = constructBitRepresentations(rootNode);
            return saveBitsToFile(file, charsBitRepresentations);
        }
        System.out.println("You can only compress txt files");
        return false;
    }

    Boolean deCompress(String filePath) {
        return true;
    }

    HashMap<Character, Integer> countCharFrequencyInFile(File file) throws FileNotFoundException {
        HashMap<Character, Integer> map = new HashMap<>();
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (Character c : line.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
        }
        reader.close();
        return map;
    }

    Node constructHuffmanTree(HashMap<Character, Integer> map) {
        return new Node('a', 3);
    }

    HashMap<String, String> constructBitRepresentations(Node node) {
        return new HashMap<>();
    }

    Boolean saveBitsToFile(File file, HashMap<String, String> map) {
        return true;
    }
}
