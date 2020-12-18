package compressionAlgorithms.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import compressionAlgorithms.IO.FileReaderWriter;
import compressionAlgorithms.benchmark.BenchmarkObject;
import compressionAlgorithms.dataStructures.MyMinHeap;
import compressionAlgorithms.dataStructures.Node;

public class Huffman {

    public Huffman() {
    }

    /**
     * Compresses the content of a given file and saves the result to a new file
     * 
     * @param file To be comrpessed file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Boolean compressFile(File file) throws IOException, ClassNotFoundException {

        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readTextFromFile(file);

        int[] charFrequencies = countCharFrequency(text);
        Node rootNode = constructHuffmanTree(charFrequencies);
        String[] charsBitRepresentations = constructBitRepresentations(rootNode);
        String bits = getBits(text, charsBitRepresentations);
        String outPath = file.getCanonicalPath().split("\\.")[0];
        return frw.writeBitsToFile(outPath, bits, charFrequencies);
    }

    /**
     * Decompresses the given files content and saves the result to a new file
     * 
     * @param file To be decompressed file
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
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

    /**
     * Benchmarks the effectiveness of Huffman -algorithm by compressing and decompressing the given
     * files content and returns results as BenchmarkObject
     * 
     * @param file File, which is to be compressed and decompressed
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public BenchmarkObject compressAndReturnBenchmarkObject(File file)
            throws FileNotFoundException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readTextFromFile(file);

        Long compressStart = System.nanoTime();
        int[] charFrequencies = countCharFrequency(text);
        Node rootNode = constructHuffmanTree(charFrequencies);
        String[] charsBitRepresentations = constructBitRepresentations(rootNode);
        String bits = getBits(text, charsBitRepresentations);
        Long compressEnd = System.nanoTime();

        double savedSpace =
                (1 - (double) bits.length() / (double) (text.getBytes().length * 8)) * 100;

        Long deCompressStart = System.nanoTime();
        Node root = constructHuffmanTree(charFrequencies);
        getOriginalText(bits, root);
        Long deCompressEnd = System.nanoTime();

        return new BenchmarkObject((compressEnd - compressStart) / 10e9,
                (deCompressEnd - deCompressStart) / 10e9, savedSpace);
    }

    /**
     * Counts the frequencies of individual characters of a given text and returns a int[] as a
     * result
     * 
     * @param text
     * @return
     */
    public int[] countCharFrequency(String text) {
        int[] frequencies = new int[256];
        for (Character c : text.toCharArray()) {
            frequencies[c]++;
        }
        return frequencies;
    }

    /**
     * Constructs the huffman tree from given character frequencies. Returns the root of the tree
     * 
     * @param frequencies
     * @return
     */
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

    /**
     * Creates for each individual character its respective bit representation. Takes root of a
     * Huffman tree as a argument
     * 
     * @param node
     * @return
     */
    public String[] constructBitRepresentations(Node node) {
        String[] bitRepresentations = new String[256];
        return searchChars(node, "", bitRepresentations);
    }

    /**
     * Helper function, which recursively goes through the given huffman tree and creates bit
     * representations for each individual character
     * 
     * @param node
     * @param bits
     * @param representations
     * @return
     */
    private String[] searchChars(Node node, String bits, String[] representations) {
        if (node.getCharacter() != null) {
            representations[node.getCharacter()] = bits;
            return representations;
        }
        representations = searchChars(node.getRightChildNode(), bits + "1", representations);
        representations = searchChars(node.getLeftChildNode(), bits + "0", representations);
        return representations;
    }

    /**
     * Creates the bit representation for a whole text
     * 
     * @param text            Given text
     * @param representations Characters bit representations
     * @return
     */
    public String getBits(String text, String[] representations) {
        String result = "";
        for (Character c : text.toCharArray()) {
            result = result + representations[c];
        }
        return result;
    }

    /**
     * Creates the original text by traversing given huffman tree
     * 
     * @param bits
     * @param root
     * @return
     */
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

    /**
     * Compresses given text and returns its bit representations
     * 
     * @param text
     * @return
     */
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
