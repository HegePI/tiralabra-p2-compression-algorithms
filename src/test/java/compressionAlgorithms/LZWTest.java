package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LZWTest {

    static LZW lzw;

    @BeforeAll
    public static void init() {
        lzw = new LZW();
    }

    @Test
    public void testCompress() {
        assertEquals(true, lzw.compress("test"));
    }

    @Test
    public void testDeCompress() {
        assertEquals(true, lzw.deCompress("test"));
    }
}
