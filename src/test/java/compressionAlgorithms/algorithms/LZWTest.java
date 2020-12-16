package compressionAlgorithms.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.IO.FileReaderWriter;
import compressionAlgorithms.dataStructures.MyList;

public class LZWTest {

    static LZW lzw;

    @BeforeAll
    public static void init() {
        lzw = new LZW();
    }

    @AfterAll
    public static void cleanUp() {
        File newFile = new File("src/test/resources/lzwCompress.lzw");

        if (newFile.exists()) {
            newFile.delete();
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
    public void testWriteCompressToFile() throws IOException {
        FileReaderWriter frw = new FileReaderWriter();

        MyList<Integer> list = new MyList<>();

        for (int i = 0; i < 10; i++) {
            list.append(i);
        }

        Boolean success =
                frw.writeLZWCompressToFile(list, new File("src/test/resources/lzwCompress.lzw"));
        assertEquals(true, success);

        File newFile = new File("src/test/resources/lzwCompress.lzw");
        assertEquals(true, newFile.exists());
    }

    @Test
    public void testReadCompressFromFile() throws IOException, ClassNotFoundException {
        FileReaderWriter frw = new FileReaderWriter();

        MyList<Integer> list = new MyList<>();

        for (int i = 0; i < 10; i++) {
            list.append(i);
        }

        Boolean succes =
                frw.writeLZWCompressToFile(list, new File("src/test/resources/lzwCompress.lzw"));

        assertEquals(true, succes);

        File newFile = new File("src/test/resources/lzwCompress.lzw");

        assertEquals(true, newFile.exists());

        MyList<Integer> listFromFile =
                frw.readLZWCompressFromFile(new File("src/test/resources/lzwCompress.lzw"));

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
