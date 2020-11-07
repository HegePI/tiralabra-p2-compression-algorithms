package compressionAlgorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FileReaderWriter {

    public Boolean writeBitsToFile(String outputPath, String bits, HashMap<Character, String> map)
            throws IOException {
        FileOutputStream mapOut = new FileOutputStream(outputPath + ".map");
        ObjectOutputStream out = new ObjectOutputStream(mapOut);
        out.writeObject(map);
        out.close();
        mapOut.close();

        FileOutputStream fos = new FileOutputStream(outputPath + ".huff");
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

    public String readBitsFromFile(String inputPath) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(inputPath);
        String bits = "";
        for (Byte b : fin.readAllBytes()) {
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            bits = bits + s1;
        }

        fin.close();

        return bits;
    }

    public HashMap<Character, String> readHashMapFromFile(String inputPath)
            throws IOException, ClassNotFoundException {
        FileInputStream mapIn = new FileInputStream(inputPath + ".map");
        ObjectInputStream in = new ObjectInputStream(mapIn);
        HashMap<Character, String> map = (HashMap<Character, String>) in.readObject();
        in.close();
        mapIn.close();
        return map;
    }

    public Boolean constructOriginalFile(String bits, HashMap<Character, String> map,
            String outputPath) throws IOException {
        FileWriter file = new FileWriter(outputPath);
        String c = "";
        for (String bit : bits.split("")) {
            c = c + bit;
            if (map.containsValue(c)) {
                for (Character mapC : map.keySet()) {
                    if (map.get(mapC).equals(c)) {
                        file.write(mapC);
                    }
                }
            }
        }
        file.close();
        return true;
    }
}
