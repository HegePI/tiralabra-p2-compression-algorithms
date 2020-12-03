package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    public void testConstructBitRepresentations() throws FileNotFoundException {
        String[] map = hf.constructBitRepresentations(
                hf.constructHuffmanTree(hf.countCharFrequency("Hello, counter!")));
        assertEquals("111", map['o']);
    }

    @Test
    public void testGetBits() throws FileNotFoundException {
        int[] frequencies = hf.countCharFrequency("Hello, counter!");
        Node root = hf.constructHuffmanTree(frequencies);
        String[] bitRepresentations = hf.constructBitRepresentations(root);
        String bits = hf.getBits("Hello, counter!", bitRepresentations);
        assertEquals("01011010010011111001100011011110110000011110101001100", bits);
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
