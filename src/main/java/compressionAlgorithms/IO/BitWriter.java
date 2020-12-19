package compressionAlgorithms.IO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitWriter {

    private OutputStream stream;
    private int currentByte;
    private int currentBitsInByte;

    public BitWriter(File file) throws FileNotFoundException {
        this.stream = new BufferedOutputStream(new FileOutputStream(file));
        this.currentByte = 0;
        this.currentBitsInByte = 0;
    }

    /**
     * Alters the byte by shifting all bits in current byte one to the left and adds given bit to
     * the end of byte. If bits in byte is over than eight, stream writes byte to file
     * 
     * @param bit
     * @throws IOException
     */
    public void writeBitToFile(int bit) throws IOException {
        if (bit == 1 || bit == 0) {
            currentByte = (currentByte << 1) | bit;
            currentBitsInByte++;
            if (currentBitsInByte == 8) {
                stream.write(currentByte);
                currentByte = 0;
                currentBitsInByte = 0;
            }
        }
    }

    /**
     * Writes given bit string to file. Uses writeBitToFile function. Throws error if string
     * contains something else than 1 or 0;
     * 
     * @param bits
     * @throws IOException
     */
    public void writeBitStringToFile(String bits) throws IOException {
        for (char bit : bits.toCharArray()) {
            if (bit == '1') {
                writeBitToFile(1);
            } else if (bit == '0') {
                writeBitToFile(0);
            } else {
                throw new Error("Can only write 1 and 0.");
            }
        }
    }

    /**
     * Closes stream. If any bits in byte, writes zeros to the end of byte and writes byte to file
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        while (currentBitsInByte > 0 && currentBitsInByte <= 8) {
            writeBitToFile(0);
        }
        stream.close();
    }
}
