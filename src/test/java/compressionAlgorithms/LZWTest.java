package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
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
        assertEquals(Arrays.asList(119, 97, 98, 98, 97, 256, 258, 260, 257, 259, 261),
                lzw.constructLZWCompress("wabbawabbawabbawabba"));
    }

    @Test
    public void testDeConstructLZWCompress() {
        assertEquals(true, lzw.deCompress("test"));
    }
}
