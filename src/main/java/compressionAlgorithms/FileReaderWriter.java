package compressionAlgorithms;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FileReaderWriter {

    public Boolean writeBitsToFile(String fileName, String bits, HashMap<Character, String> map)
            throws IOException {

        FileOutputStream mapOut = new FileOutputStream(fileName + ".prop.huff");
        ObjectOutputStream out = new ObjectOutputStream(mapOut);
        out.writeObject(map);
        out.close();
        mapOut.close();

        FileOutputStream fos = new FileOutputStream(fileName + ".huff");
        String byteString = "";
        for (String bit : bits.split("")) {
            byteString = byteString + bit;
            if (byteString.length() == 8) {
                int asInt = Integer.parseInt(byteString, 2);
                fos.write(asInt);
                byteString = "";
            }
        }
        if (byteString.length() > 0) {
            int asInt = Integer.parseInt(byteString, 2);
            fos.write(asInt);
        }
        fos.close();
        return true;
    }

    public Boolean readBitsFromFile(String path) {
        return true;
    }
}
