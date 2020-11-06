package compressionAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    public Huffman() {
    }

    Boolean compress(String filePath) throws IOException {

        if (filePath.split("\\.")[1].equals("txt")) {
            File file = new File(filePath);
            HashMap<Character, Integer> charFrequencies = countCharFrequencyInFile(file);
            Node rootNode = constructHuffmanTree(charFrequencies);
            HashMap<Character, String> charsBitRepresentations =
                    constructBitRepresentations(rootNode);
            return saveToFile(filePath, charsBitRepresentations);
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
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for (Character c : map.keySet()) {
            nodes.add(new Node(c, map.get(c)));
        }

        while (nodes.size() > 1) {
            Node first = nodes.poll();
            Node second = nodes.poll();
            nodes.add(new Node(first, second));
        }
        return nodes.poll();
    }

    HashMap<Character, String> constructBitRepresentations(Node node) {
        HashMap<Character, String> bitRepresentations = new HashMap<>();
        return searchChars(node, "", bitRepresentations);
    }

    private HashMap<Character, String> searchChars(Node node, String bits,
            HashMap<Character, String> map) {
        if (node.getCharacter() != null) {
            map.put(node.getCharacter(), bits);
            System.out.println(node.getCharacter() + ", " + bits);
            return map;
        }
        map = searchChars(node.getRightChilNode(), bits.concat("1"), map);
        map = searchChars(node.getLeftChildNode(), bits.concat("0"), map);
        return map;
    }

    Boolean saveToFile(String path, HashMap<Character, String> map) throws FileNotFoundException {
        File file = new File(path);
        String fileName = path.split(".")[0];
        Scanner fileReader = new Scanner(file);
        String asBits = "";
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            for (Character c : line.toCharArray()) {
                asBits = asBits.concat(map.get(c));
            }
        }
        fileReader.close();
        try {
            FileReaderWriter frw = new FileReaderWriter();
            return frw.writeBitsToFile(fileName, asBits, map);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
