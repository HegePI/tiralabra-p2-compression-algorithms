package compressionAlgorithms.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import compressionAlgorithms.IO.FileReaderWriter;
import compressionAlgorithms.dataStructures.MyMinHeap;
import compressionAlgorithms.dataStructures.Node;

public class Huffman {

    public Huffman() {
    }

    public Boolean compressAndSaveToFile(File file) throws IOException, ClassNotFoundException {

        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readTextFromFile(file);

        int[] charFrequencies = countCharFrequency(text);
        Node rootNode = constructHuffmanTree(charFrequencies);
        String[] charsBitRepresentations = constructBitRepresentations(rootNode);
        String bits = getBits(text, charsBitRepresentations);
        String outPath = file.getCanonicalPath().split("\\.")[0];
        return frw.writeBitsToFile(outPath, bits, charFrequencies);
    }

    public Double compressAndReturnSaveRatio(File file) throws FileNotFoundException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readTextFromFile(file);

        int[] charFrequencies = countCharFrequency(text);
        Node rootNode = constructHuffmanTree(charFrequencies);
        String[] charsBitRepresentations = constructBitRepresentations(rootNode);
        String bits = getBits(text, charsBitRepresentations);
        double ratio = (1 - (double) bits.length() / (double) (text.length() * 8)) * 100;
        return ratio;
    }

    public Boolean deCompressFile(File file) throws ClassNotFoundException, IOException {
        FileReaderWriter frw = new FileReaderWriter();
        String bits = frw.readBitsFromFile(file);
        int[] charFrequencies =
                frw.readFrequenciesFromFile(file.getCanonicalPath().split("\\.")[0] + ".map");
        Node root = constructHuffmanTree(charFrequencies);
        String originalText = getOriginalText(bits, root);
        File reconstruct =
                new File(file.getCanonicalPath().split("\\.")[0] + "-huffman-reconstruct.txt");
        return frw.writeTextToFile(reconstruct, originalText);
    }

    public int[] countCharFrequency(String text) {
        int[] frequencies = new int[256];
        for (Character c : text.toCharArray()) {
            frequencies[c]++;
        }
        return frequencies;
    }

    public Node constructHuffmanTree(int[] frequencies) {
        MyMinHeap nodes = new MyMinHeap();
        for (int c = 0; c < 256; c++) {
            if (frequencies[c] > 0) {
                nodes.insertNode(new Node((char) c, frequencies[c]));
            }
        }

        nodes.minHeap();

        while (nodes.getSize() > 1) {
            Node first = nodes.getFirstNode();
            Node second = nodes.getFirstNode();
            nodes.insertNode(new Node(first, second));
        }
        return nodes.getFirstNode();
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

    public String getBits(String text, String[] representations) {
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

    public String compressText(String text) {
        int[] freq = countCharFrequency(text);
        Node root = constructHuffmanTree(freq);
        String[] bitRepresentations = constructBitRepresentations(root);
        String bits = "";
        for (char c : text.toCharArray()) {
            bits = bits + bitRepresentations[c];
        }
        return bits;
    }
}
