package compressionAlgorithms.benchamrk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.benchmark.BenchmarkObject;

public class BenchmarkObjectTest {

    static BenchmarkObject testObject = new BenchmarkObject(1.2, 0.9, 34.7);

    @Test
    public void testGetCompressTime() {
        assertEquals(1.2, testObject.getCompressTime());
    }

    @Test
    public void testGetDeCompressTime() {
        assertEquals(0.9, testObject.getDeCompressTime());
    }

    @Test
    public void testGetSavedSpace() {
        assertEquals(34.7, testObject.getSavedSpace());
    }

}
