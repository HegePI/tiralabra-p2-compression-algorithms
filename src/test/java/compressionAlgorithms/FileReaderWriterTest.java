package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileReaderWriterTest {

    static File testFile;
    static File helloFile;
    static Huffman hf;
    static FileReaderWriter frw;

    @BeforeAll
    public static void init() {
        hf = new Huffman();
        frw = new FileReaderWriter();
    }

    @AfterAll
    public static void clean() {
        File huff = new File("src/test/resources/fileReaderWriterTest.huff");
        File map = new File("src/test/resources/fileReaderWriterTest.map");
        File lzw = new File("src/test/resources/fileReaderWriterTest.lzw");
        File reconstruct = new File("src/test/resources/fileReaderWriterTest-reconstruct.txt");

        if (huff.exists()) {
            huff.delete();
        }
        if (map.exists()) {
            map.delete();
        }
        if (lzw.exists()) {
            lzw.delete();
        }
        if (reconstruct.exists()) {
            reconstruct.delete();
        }
    }

    @Test
    public void testWriteBitsToFile() throws IOException {
        String bits = "1010101010101000010110111111";
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('c', 3);
        Boolean success = frw.writeBitsToFile("src/test/resources/fileReaderWriterTest", bits, map);

        assertEquals(true, success);

        File mapFile = new File("src/test/resources/fileReaderWriterTest.map");
        File huffFile = new File("src/test/resources/fileReaderWriterTest.huff");

        assertEquals(true, mapFile.exists());
        assertEquals(true, huffFile.exists());
    }

    @Test
    public void testReadBitsFromFile() throws ClassNotFoundException, IOException {
        String bits = "1010101010101000010110111111";
        String bitsFromFile = frw.readBitsFromFile("src/test/resources/fileReaderWriterTest.huff");

        assertEquals(bits, bitsFromFile);
    }

    @Test
    public void testReadHashMapFromFile() throws ClassNotFoundException, IOException {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('c', 3);
        HashMap<Character, Integer> mapFromFile =
                frw.readHashMapFromFile("src/test/resources/fileReaderWriterTest.map");

        assertEquals(map, mapFromFile);
    }

    @Test
    public void testWriteTextToFile() throws IOException {
        Boolean success = frw.writeTextToFile("src/test/resources/fileReaderWriterTest", "Hello");
        assertEquals(true, success);

        File newFile = new File("src/test/resources/fileReaderWriterTest-reconstruct.txt");
        assertEquals(true, newFile.exists());
    }

    @Test
    public void testReadTextFromFile() throws FileNotFoundException {
        String text = frw.readTextFromFile("src/test/resources/small.txt");
        assertEquals("Hello, Huffman and LZW!", text);
    }

    @Test
    public void testWriteLZWCompressToFile() throws IOException {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Boolean success = frw.writeLZWCompressToFile(list, "src/test/resources/lzwCompressTest");
        assertEquals(true, success);

        File newFile = new File("src/test/resources/lzwCompressTest.lzw");
        assertEquals(true, newFile.exists());

    }

    @Test
    public void testReadLZWCompressFromFile() throws ClassNotFoundException, IOException {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> listFromFile =
                frw.readLZWCompressFromFile("src/test/resources/lzwCompressTest.lzw");

        assertEquals(list, listFromFile);
    }
}
