package compressionAlgorithms.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.benchmark.BenchmarkObject;
import compressionAlgorithms.dataStructures.Node;

public class HuffmanTest {


    static Huffman hf;

    @BeforeAll
    public static void init() {
        hf = new Huffman();
    }

    @AfterAll
    public static void cleanUp() {
        File smallReconstruct = new File("src/test/resources/small-huffman-reconstruct.txt");
        File smallHuff = new File("src/test/resources/small.huff");
        File smallMap = new File("src/test/resources/small.map");
        File bigReconstruct = new File("src/test/resources/big-huffman-reconstruct.txt");
        File bigHuff = new File("src/test/resources/big.huff");
        File bigMap = new File("src/test/resources/big.map");

        if (smallReconstruct.exists()) {
            smallReconstruct.delete();
        }
        if (smallHuff.exists()) {
            smallHuff.delete();
        }
        if (smallMap.exists()) {
            smallMap.delete();
        }
        if (bigReconstruct.exists()) {
            bigReconstruct.delete();
        }
        if (bigHuff.exists()) {
            bigHuff.exists();
        }
        if (bigMap.exists()) {
            bigMap.exists();
        }
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

    @Test
    public void testCompressFile() throws ClassNotFoundException, IOException {
        File testFile = new File("src/test/resources/small.txt");

        boolean success = hf.compressFile(testFile);
        assertEquals(true, success);

        File outFile = new File("src/test/resources/small.huff");
        assertEquals(true, outFile.exists());

    }

    @Test
    public void testDeCompressFile() throws ClassNotFoundException, IOException {
        File testFile = new File("src/test/resources/small.txt");

        boolean success = hf.compressFile(testFile);
        assertEquals(true, success);

        File outFile = new File("src/test/resources/small.huff");
        assertEquals(true, outFile.exists());

        boolean deCompessSuccess = hf.deCompressFile(outFile);
        assertEquals(true, deCompessSuccess);

        File deCompressOut = new File("src/test/resources/small-huffman-reconstruct.txt");
        assertEquals(true, deCompressOut.exists());
    }

    @Test
    public void testCompressAndReturnBenchmark() throws ClassNotFoundException, IOException {
        File file = new File("src/test/resources/big.txt");

        BenchmarkObject bmo = hf.compressAndReturnBenchmarkObject(file);

        assertTrue(bmo instanceof BenchmarkObject);
        assertTrue(bmo.getCompressTime() instanceof Double);
        assertTrue(bmo.getDeCompressTime() instanceof Double);
        assertTrue(bmo.getSavedSpace() instanceof Double);
    }
}
