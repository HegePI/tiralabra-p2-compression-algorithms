package compressionAlgorithms.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import compressionAlgorithms.dataStructures.MyList;

public class FileReaderWriter {


    public FileReaderWriter() {
    }

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
    public Boolean writeBitsToFile(String outputPath, String bits, int[] frequencies)
            throws IOException {
        FileOutputStream mapOut = new FileOutputStream(outputPath + ".map");
        ObjectOutputStream out = new ObjectOutputStream(mapOut);
        out.writeObject(frequencies);
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
    public String readBitsFromFile(File file) throws IOException, ClassNotFoundException {
        // FileInputStream fin = new FileInputStream(inputPath);
        // String bits = "";
        // for (Byte b : fin.readAllBytes()) {
        // String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace('
        // ', '0');
        // bits = bits + s1;
        // }

        // fin.close();
        // return bits;
        String result = "";
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            result = result + reader.nextLine();
        }

        reader.close();
        return result;
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
    public int[] readFrequenciesFromFile(String inputPath)
            throws IOException, ClassNotFoundException {
        FileInputStream mapIn = new FileInputStream(inputPath);
        ObjectInputStream in = new ObjectInputStream(mapIn);
        int[] map = (int[]) in.readObject();
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
    public Boolean writeTextToFile(File file, String text) throws IOException {

        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public MyList<Integer> readLZWCompressFromFile(File file)
            throws IOException, ClassNotFoundException {
        FileInputStream listIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(listIn);
        MyList<Integer> list = (MyList<Integer>) in.readObject();
        in.close();
        listIn.close();
        return list;
    }

    public Boolean writeLZWCompressToFile(MyList<Integer> data, File file) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(out);
            writer.writeObject(data);
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String readTextFromFile(File file) throws FileNotFoundException {
        String result = "";
        Scanner reader = new Scanner(file);

        while (reader.hasNext()) {
            result = result + reader.nextLine();
        }

        reader.close();
        return result;
    }
}
