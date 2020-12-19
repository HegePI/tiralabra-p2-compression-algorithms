package compressionAlgorithms.IO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitReader {

    private InputStream stream;
    private int currentByte;
    private int currentBitsInByte;

    public BitReader(File file) throws FileNotFoundException {
        this.stream = new BufferedInputStream(new FileInputStream(file));
        this.currentByte = 0;
        this.currentBitsInByte = 0;
    }

    /**
     * Reads single bit from current byte. If currentBitsInByte is 0, reads new byte to currentByte
     * from stream.
     * 
     * @return
     * @throws IOException
     */
    public int readBitFromFile() throws IOException {
        if (this.currentBitsInByte == 0) {
            this.currentByte = this.stream.read();
            this.currentBitsInByte = 8;
        }
        if (this.currentByte == -1) {
            return -1;
        }

        this.currentBitsInByte--;
        return (this.currentByte >>> this.currentBitsInByte) & 1;
    }

    /**
     * Closes stream.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        this.currentByte = -1;
        this.currentBitsInByte = 0;
        this.stream.close();
    }
}
