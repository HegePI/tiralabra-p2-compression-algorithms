package compressionAlgorithms.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.dataStructures.Node;

public class HuffmanTest {


    static Huffman hf;

    @BeforeAll
    public static void init() {
        hf = new Huffman();
    }

    @Test
    public void testCountCharFrequency()
            throws FileNotFoundException, UnsupportedEncodingException {
        int[] frequencies = hf.countCharFrequency("Hello, counter!");
        assertEquals(Integer.valueOf(2), frequencies['e']);
    }

    @Test
    public void testConstructHuffmanTree() throws FileNotFoundException {
        int[] frequencies = hf.countCharFrequency("Hello, counter!");
        Node rootNode = hf.constructHuffmanTree(frequencies);
        assertEquals(Integer.valueOf(15), rootNode.getValue());
    }

    @Test
    public void testConstructBitRepresentations1() throws FileNotFoundException {
        String[] charBits = hf.constructBitRepresentations(
                hf.constructHuffmanTree(hf.countCharFrequency("Hello, counter!")));
        assertEquals("000", charBits['n']);
        assertEquals("100", charBits['l']);
        assertEquals("110", charBits['e']);
        assertEquals("111", charBits['o']);
        assertEquals("0110", charBits['r']);
        assertEquals("1010", charBits[' ']);
        assertEquals("0111", charBits['!']);
        assertEquals("0101", charBits['u']);
        assertEquals("0010", charBits['c']);
        assertEquals("1011", charBits[',']);
        assertEquals("0100", charBits['t']);
        assertEquals("0011", charBits['H']);
    }

    @Test
    public void testGetBits() throws FileNotFoundException {
        int[] frequencies = hf.countCharFrequency("Hello, counter!");
        Node root = hf.constructHuffmanTree(frequencies);
        String[] bitRepresentations = hf.constructBitRepresentations(root);
        String bits = hf.getBits("Hello, counter!", bitRepresentations);
        assertEquals("00111101001001111011101000101110101000010011001100111", bits);
    }

    @Test
    public void testGetOriginalText() throws FileNotFoundException {
        int[] frequencies = hf.countCharFrequency("Hello, counter!");
        Node root = hf.constructHuffmanTree(frequencies);
        String[] bitRepresentations = hf.constructBitRepresentations(root);
        String bits = hf.getBits("Hello, counter!", bitRepresentations);

        String originalText = hf.getOriginalText(bits, root);

        assertEquals("Hello, counter!", originalText);
    }
}
