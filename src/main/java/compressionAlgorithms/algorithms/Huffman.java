package compressionAlgorithms.algorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import compressionAlgorithms.IO.FileReaderWriter;
import compressionAlgorithms.datastructures.Node;

public class Huffman {

    public Huffman() {
    }

    public Boolean compress(String filePath) throws IOException, ClassNotFoundException {
        if (filePath.split("\\.")[1].equals("txt")) {
            String outPath = filePath.split("\\.")[0];
            FileReaderWriter frw = new FileReaderWriter();
            String text = frw.readTextFromFile(filePath);

            int[] charFrequencies = countCharFrequency(text);
            Node rootNode = constructHuffmanTree(charFrequencies);
            String[] charsBitRepresentations = constructBitRepresentations(rootNode);
            String bits = getBits(text, charsBitRepresentations);
            return frw.writeBitsToFile(outPath, bits, charFrequencies);
        }
        System.out.println("You can only compress txt files");
        return false;
    }

    public Boolean deCompress(String filePath) throws ClassNotFoundException, IOException {
        if (filePath.split("\\.")[1].equals("huff")) {
            FileReaderWriter frw = new FileReaderWriter();
            String bits = frw.readBitsFromFile(filePath);
            int[] charFrequencies = frw.readFrequenciesFromFile(filePath.split("\\.")[0] + ".map");
            Node root = constructHuffmanTree(charFrequencies);
            String originalText = getOriginalText(bits, root);
            String outputPath = filePath.split("\\.")[0];
            return frw.writeTextToFile(outputPath, originalText);
        }
        return false;
    }

    public int[] countCharFrequency(String text) throws FileNotFoundException {
        int[] frequencies = new int[256];
        for (Character c : text.toCharArray()) {
            frequencies[c]++;
        }
        return frequencies;
    }

    public Node constructHuffmanTree(int[] frequencies) {
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for (int c = 0; c < 256; c++) {
            if (frequencies[c] > 0) {
                nodes.add(new Node((char) c, frequencies[c]));
            }
        }

        while (nodes.size() > 1) {
            Node first = nodes.poll();
            Node second = nodes.poll();
            nodes.add(new Node(first, second));
        }
        return nodes.poll();
    }

    public String[] constructBitRepresentations(Node node) {
        String[] bitRepresentations = new String[256];
        return searchChars(node, "", bitRepresentations);
    }

    private String[] searchChars(Node node, String bits, String[] representations) {
        if (node.getCharacter() != null) {
            representations[node.getCharacter()] = bits;
            return representations;
        }
        representations = searchChars(node.getRightChildNode(), bits + "1", representations);
        representations = searchChars(node.getLeftChildNode(), bits + "0", representations);
        return representations;
    }

    public String getBits(String text, String[] representations) throws FileNotFoundException {
        String result = "";
        for (Character c : text.toCharArray()) {
            result = result + representations[c];
        }
        return result;
    }

    public String getOriginalText(String bits, Node root) {
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
