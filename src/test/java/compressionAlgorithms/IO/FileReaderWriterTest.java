package compressionAlgorithms.IO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.algorithms.Huffman;
import compressionAlgorithms.dataStructures.MyList;

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
        int[] frequencies = new int[256];
        frequencies['c'] = 3;
        Boolean success =
                frw.writeBitsToFile("src/test/resources/fileReaderWriterTest", bits, frequencies);

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
    public void testReadFrequenciesFromFile() throws ClassNotFoundException, IOException {
        int[] frequencies = new int[256];
        frequencies['c'] = 3;
        int[] frequenciesFromFile =
                frw.readFrequenciesFromFile("src/test/resources/fileReaderWriterTest.map");

        boolean same = true;

        for (int c = 0; c < 256; c++) {
            if (frequencies[c] != frequenciesFromFile[c]) {
                same = false;
                break;
            }
        }

        assertEquals(true, same);
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
        MyList<Integer> list = new MyList<>();

        for (int i = 0; i < 10; i++) {
            list.append(i);
        }

        Boolean success =
                frw.writeLZWCompressToFile(list, "src/test/resources/fileReaderWriterTest");
        assertEquals(true, success);

        File newFile = new File("src/test/resources/fileReaderWriterTest.lzw");
        assertEquals(true, newFile.exists());
    }

    @Test
    public void testReadLZWCompressFromFile() throws ClassNotFoundException, IOException {

        MyList<Integer> list = new MyList<>();

        for (int i = 0; i < 10; i++) {
            list.append(i);
        }

        frw.writeLZWCompressToFile(list, "src/test/resources/fileReaderWriterTest");

        MyList<Integer> listFromFile =
                frw.readLZWCompressFromFile("src/test/resources/fileReaderWriterTest.lzw");

        boolean same = true;
        for (int i = 0; i < 10; i++) {
            if (list.get(i).intValue() != listFromFile.get(i).intValue()) {
                same = false;
                break;
            }
        }

        assertEquals(true, same);
    }
}
