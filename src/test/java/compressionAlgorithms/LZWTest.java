package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LZWTest {

    static LZW lzw;

    @BeforeAll
    public static void init() {
        lzw = new LZW();
    }

    @Test
    public void testConstructLZWCompress() {
        assertEquals(Arrays.asList(66, 65, 256, 257, 65, 260),
                lzw.constructLZWCompress("BABAABAAA"));
    }

    @Test
    public void testDeConstructLZWCompress() throws IOException {
        ArrayList<Integer> codes = lzw.constructLZWCompress("BABAABAAA");
        assertEquals("BABAABAAA", lzw.constructOriginalText(codes));
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

        ArrayList<Integer> codes = lzw.constructLZWCompress(text);
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

        ArrayList<Integer> codes = lzw.constructLZWCompress(text);
        assertEquals(text, lzw.constructOriginalText(codes));
    }
}
