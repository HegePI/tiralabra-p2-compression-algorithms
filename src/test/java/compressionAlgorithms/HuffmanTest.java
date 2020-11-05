/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package compressionAlgorithms;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class HuffmanTest {

    @Test
    public void testHuffmanCompressWithInCorrectFileExtension() throws FileNotFoundException {
        Huffman hf = new Huffman();
        assertEquals(false, hf.compress("testFile.csv"));
    }

    @Test
    public void testHuffmanDecompressWithCorrectFileExtension() {
        Huffman hf = new Huffman();
        assertEquals(true, hf.deCompress("testFile.huff"));
    }

    @Test
    public void testCountCharFrequencyInFile() throws FileNotFoundException, UnsupportedEncodingException {
        File testFile = new File("src/test/resources/test.txt");

        Huffman hf = new Huffman();
        HashMap<Character, Integer> map = hf.countCharFrequencyInFile(testFile);
        assertEquals(Integer.valueOf(69), map.get('e'));
    }
}