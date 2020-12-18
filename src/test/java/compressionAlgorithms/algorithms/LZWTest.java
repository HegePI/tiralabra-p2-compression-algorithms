package compressionAlgorithms.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.benchmark.BenchmarkObject;
import compressionAlgorithms.dataStructures.MyList;

public class LZWTest {

    static LZW lzw;

    @BeforeAll
    public static void init() {
        lzw = new LZW();
    }

    @AfterAll
    public static void cleanUp() {
        File smallReconstruct = new File("src/test/resources/small-lzw-reconstruct.txt");
        File smallLzw = new File("src/test/resources/small.lzw");
        File bigReconstruct = new File("src/test/resources/big-lzw-reconstruct.txt");
        File bigLzw = new File("src/test/resources/big.lzw");

        if (smallReconstruct.exists()) {
            smallReconstruct.delete();
        }
        if (smallLzw.exists()) {
            smallLzw.delete();
        }
        if (bigReconstruct.exists()) {
            bigReconstruct.delete();
        }
        if (bigLzw.exists()) {
            bigLzw.exists();
        }
    }

    @Test
    public void testConstructLZWCompress() {
        MyList<Integer> list = lzw.constructLZWCompress("BABAABAAA");
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(66, 65, 256, 257, 65, 260));

        boolean same = true;

        for (int i = 0; i < list.getSize(); i++) {
            if (list.get(i).intValue() != expected.get(i).intValue()) {
                same = false;
                break;
            }
        }
        assertEquals(true, same);
    }

    @Test
    public void testDeConstructLZWCompress() throws IOException {
        MyList<Integer> codes = lzw.constructLZWCompress("abraabraabraabra");

        assertEquals("abraabraabraabra", lzw.constructOriginalText(codes));
    }

    @Test
    public void testLZWWithSmallFile() throws FileNotFoundException {
        File file = new File("src/test/resources/small.txt");
        Scanner reader = new Scanner(file);
        String text = "";
        while (reader.hasNextLine()) {
            text = text + reader.nextLine();
        }
        reader.close();

        MyList<Integer> codes = lzw.constructLZWCompress(text);
        assertEquals(text, lzw.constructOriginalText(codes));
    }

    @Test
    public void testLZWWithBigFile() throws FileNotFoundException {
        File file = new File("src/test/resources/big.txt");
        Scanner reader = new Scanner(file);
        String text = "";
        while (reader.hasNextLine()) {
            text = text + reader.nextLine();
        }
        reader.close();

        MyList<Integer> codes = lzw.constructLZWCompress(text);
        assertEquals(text, lzw.constructOriginalText(codes));
    }

    @Test
    public void testCompressFile() throws ClassNotFoundException, IOException {
        File testFile = new File("src/test/resources/small.txt");

        boolean success = lzw.compressFile(testFile);
        assertEquals(true, success);

        File outFile = new File("src/test/resources/small.lzw");
        assertEquals(true, outFile.exists());

    }

    @Test
    public void testDeCompressFile() throws ClassNotFoundException, IOException {
        File testFile = new File("src/test/resources/small.txt");

        boolean success = lzw.compressFile(testFile);
        assertEquals(true, success);

        File outFile = new File("src/test/resources/small.lzw");
        assertEquals(true, outFile.exists());

        boolean deCompessSuccess = lzw.deCompressFile(outFile);
        assertEquals(true, deCompessSuccess);

        File deCompressOut = new File("src/test/resources/small-lzw-reconstruct.txt");
        assertEquals(true, deCompressOut.exists());
    }

    @Test
    public void testCompressAndReturnBenchmark() throws ClassNotFoundException, IOException {
        File file = new File("src/test/resources/big.txt");

        BenchmarkObject bmo = lzw.compressAndReturnBenchmarkObject(file);

        assertTrue(bmo instanceof BenchmarkObject);
        assertTrue(bmo.getCompressTime() instanceof Double);
        assertTrue(bmo.getDeCompressTime() instanceof Double);
        assertTrue(bmo.getSavedSpace() instanceof Double);
    }
}
