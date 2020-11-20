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
import java.util.Scanner;

public class FileReaderWriter {


    /**
     * Function, that writes given bit represantation into a file. Takes output path, bits and
     * character-to-bits represantation as an arguments.
     * 
     * @param outputPath file, where data will be written.
     * @param bits       original file text as bits.
     * @param map        character-to-bits represantation.
     * @return returns true, if write was succesfull, false otherwise.
     * @throws IOException
     */
    Boolean writeBitsToFile(String outputPath, String bits, HashMap<Character, Integer> map)
            throws IOException {
        FileOutputStream mapOut = new FileOutputStream(outputPath + ".map");
        ObjectOutputStream out = new ObjectOutputStream(mapOut);
        out.writeObject(map);
        out.close();
        mapOut.close();

        // FileOutputStream fos = new FileOutputStream(outputPath + ".huff");
        // String byteString = "";
        // for (String bit : bits.split("")) {
        // byteString = byteString + bit;
        // if (byteString.length() == 8) {
        // int asInt = Integer.parseInt(byteString, 2);
        // fos.write(asInt);
        // byteString = "";
        // }
        // }
        // if (byteString.length() > 0) {
        // int asInt = Integer.parseInt(byteString, 2);
        // fos.write(asInt);
        // }
        // fos.close();
        // return true;
        try {
            File bitFile = new File(outputPath + ".huff");
            bitFile.createNewFile();
            FileWriter fos = new FileWriter(outputPath + ".huff");
            fos.write(bits);
            fos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * Function, that reads stored bits from file and returns them as String. Takes input path as an
     * argument, which tells where data is stored.
     * 
     * @param inputPath location, where data is stored and where function reads it.
     * @return returns data as bit string
     * @throws IOException
     * @throws ClassNotFoundException
     */
    String readeDataFromFile(String inputPath) throws IOException, ClassNotFoundException {
        // FileInputStream fin = new FileInputStream(inputPath);
        // String bits = "";
        // for (Byte b : fin.readAllBytes()) {
        // String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        // bits = bits + s1;
        // }

        // fin.close();
        // return bits;
        String result = "";
        try {
            File f = new File(inputPath);
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                result = result + reader.nextLine();
            }
            reader.close();
            return result;

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Reads hashmap from given input path, that is needed by huffman algorithm to create original
     * file
     * 
     * @param inputPath path to saved hashmap
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    HashMap<Character, Integer> readHashMapFromFile(String inputPath)
            throws IOException, ClassNotFoundException {
        FileInputStream mapIn = new FileInputStream(inputPath);
        ObjectInputStream in = new ObjectInputStream(mapIn);
        HashMap<Character, Integer> map = (HashMap<Character, Integer>) in.readObject();
        in.close();
        mapIn.close();
        return map;
    }

    /**
     * Recreates original file content from bits and hashmap and writes it to given output path
     * 
     * @param bits       original content as bits
     * @param map        hashmap to interpret bits
     * @param outputPath path, where content will be written
     * @return
     * @throws IOException
     */
    Boolean constructOriginalFile(String outputPath, String text) throws IOException {

        File newFile = new File(outputPath + "-reconstruct.txt");
        try {
            newFile.createNewFile();
            FileWriter file = new FileWriter(outputPath + "-reconstruct.txt");
            file.write(text);
            file.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
