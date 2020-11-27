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

    Boolean compress(String filePath) throws IOException, ClassNotFoundException {
        if (filePath.split("\\.")[1].equals("txt")) {
            String outPath = filePath.split("\\.")[0];
            FileReaderWriter frw = new FileReaderWriter();
            String text = frw.readTextFromFile(filePath);

            HashMap<Character, Integer> charFrequencies = countCharFrequency(text);
            Node rootNode = constructHuffmanTree(charFrequencies);
            HashMap<Character, String> charsBitRepresentations =
                    constructBitRepresentations(rootNode);
            String bits = getBits(text, charsBitRepresentations);
            return frw.writeBitsToFile(outPath, bits, charFrequencies);
        }
        System.out.println("You can only compress txt files");
        return false;
    }

    Boolean deCompress(String filePath) throws ClassNotFoundException, IOException {
        if (filePath.split("\\.")[1].equals("huff")) {
            FileReaderWriter frw = new FileReaderWriter();
            String bits = frw.readBitsFromFile(filePath);
            System.out.println(bits);
            HashMap<Character, Integer> charFrequencies =
                    frw.readHashMapFromFile(filePath.split("\\.")[0] + ".map");
            Node root = constructHuffmanTree(charFrequencies);
            String originalText = getOriginalText(bits, root);
            String outputPath = filePath.split("\\.")[0];
            return frw.writeTextToFile(outputPath, originalText);
        }
        return false;
    }

    HashMap<Character, Integer> countCharFrequency(String text) throws FileNotFoundException {
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : text.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
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
            return map;
        }
        map = searchChars(node.getRightChildNode(), bits + "1", map);
        map = searchChars(node.getLeftChildNode(), bits + "0", map);
        return map;
    }

    String getBits(String text, HashMap<Character, String> map) throws FileNotFoundException {
        String result = "";

        for (Character c : text.toCharArray()) {
            result = result + map.get(c);
        }

        return result;
    }

    String getOriginalText(String bits, Node root) {
        String result = "";

        Node current = root;
        for (Character c : bits.toCharArray()) {
            if (c == '1') {
                current = current.getRightChildNode();
            } else {
                current = current.getLeftChildNode();
            }

            if (current.isLeaf()) {
                result = result + current.getCharacter();
                current = root;
            }
        }

        return result;
    }
}
