package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HuffmanTest {


    static Huffman hf;

    @BeforeAll
    public static void init() {
        hf = new Huffman();
    }

    @Test
    public void testHuffmanCompressWithInCorrectFileExtension()
            throws IOException, ClassNotFoundException {
        assertEquals(false, hf.compress("testFile.csv"));
    }

    @Test
    public void testHuffmanDecompressWithInCorrectFileExtension()
            throws ClassNotFoundException, IOException {
        assertEquals(false, hf.deCompress("testFile.csv"));
    }

    @Test
    public void testCountCharFrequency()
            throws FileNotFoundException, UnsupportedEncodingException {
        HashMap<Character, Integer> map = hf.countCharFrequency("Hello, counter!");
        assertEquals(Integer.valueOf(2), map.get('e'));
    }

    @Test
    public void testConstructHuffmanTree() throws FileNotFoundException {
        HashMap<Character, Integer> map = hf.countCharFrequency("Hello, counter!");
        Node rootNode = hf.constructHuffmanTree(map);
        assertEquals(Integer.valueOf(15), rootNode.getValue());
    }

    @Test
    public void testConstructBitRepresentations() throws FileNotFoundException {
        HashMap<Character, String> map = hf.constructBitRepresentations(
                hf.constructHuffmanTree(hf.countCharFrequency("Hello, counter!")));
        assertEquals("001", map.get('o'));
    }

    @Test
    public void testGetBits() throws FileNotFoundException {
        HashMap<Character, Integer> freq = hf.countCharFrequency("Hello, counter!");
        Node root = hf.constructHuffmanTree(freq);
        HashMap<Character, String> bitRepresentations = hf.constructBitRepresentations(root);
        String bits = hf.getBits("Hello, counter!", bitRepresentations);
        assertEquals("00010111111100101001000010100101100111110110110011100", bits);
    }

    @Test
    public void testGetOriginalText() throws FileNotFoundException {
        HashMap<Character, Integer> freq = hf.countCharFrequency("Hello, counter!");
        Node root = hf.constructHuffmanTree(freq);
        HashMap<Character, String> bitRepresentations = hf.constructBitRepresentations(root);
        String bits = hf.getBits("Hello, counter!", bitRepresentations);

        String originalText = hf.getOriginalText(bits, root);

        assertEquals("Hello, counter!", originalText);
    }
}
