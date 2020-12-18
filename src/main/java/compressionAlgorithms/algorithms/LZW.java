package compressionAlgorithms.algorithms;

import java.io.File;
import java.io.IOException;
import compressionAlgorithms.IO.FileReaderWriter;
import compressionAlgorithms.benchmark.BenchmarkObject;
import compressionAlgorithms.dataStructures.MyHashMap;
import compressionAlgorithms.dataStructures.MyHashMapEntry;
import compressionAlgorithms.dataStructures.MyList;

public class LZW {

    public LZW() {

    }

    /**
     * Function, that compresses given files content and saves it in .lzw file.
     * 
     * @param file File, which content is compressed
     * @return
     * @throws ClassNotFoundException
     * @throws IOException            if to be compressed file is not found
     */
    public Boolean compressFile(File file) throws ClassNotFoundException, IOException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readBitsFromFile(file);
        MyList<Integer> compressedData = constructLZWCompress(text);
        File lzwFile = new File(file.getCanonicalPath().split("\\.")[0] + ".lzw");
        return frw.writeLZWCompressToFile(compressedData, lzwFile);
    }

    /**
     * Function, which decompresses given files content. Must be .lzw file
     * 
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Boolean deCompressFile(File file) throws IOException, ClassNotFoundException {
        FileReaderWriter frw = new FileReaderWriter();
        MyList<Integer> compress = frw.readLZWCompressFromFile(file);
        String originalText = constructOriginalText(compress);
        File reconstruct =
                new File(file.getCanonicalPath().split("\\.")[0] + "-lzw-reconstruct.txt");
        return frw.writeTextToFile(reconstruct, originalText);
    }

    /**
     * Function, which benchmarks the effectiveness of LZW -algorithm by compressing and
     * decompressing the given files content and returns results as BenchmarkObject
     * 
     * @param file File, which is to be compressed and decompressed
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public BenchmarkObject compressAndReturnBenchmarkObject(File file)
            throws ClassNotFoundException, IOException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readBitsFromFile(file);

        Long compressStart = System.nanoTime();
        MyList<Integer> compressedData = constructLZWCompress(text);
        Long compressEnd = System.nanoTime();

        Double savedSpace =
                (1 - (double) (compressedData.getSize() * 8) / (double) (text.length() * 8)) * 100;

        Long deCompressStart = System.nanoTime();
        constructOriginalText(compressedData);
        Long deCompressEnd = System.nanoTime();
        return new BenchmarkObject((compressEnd - compressStart) / 10e9,
                (deCompressEnd - deCompressStart) / 10e9, savedSpace);
    }

    /**
     * LZW encoder. Takes text, that will be compressed.
     * 
     * @param text
     * @return
     */
    public MyList<Integer> constructLZWCompress(String text) {
        MyHashMap<String, Integer> substrings = new MyHashMap<>();
        int index = 256;
        String subString = "";
        MyList<Integer> result = new MyList<>();
        for (Character c : text.toCharArray()) {
            subString = subString + c;
            if (subString.length() > 1) {
                if (!substrings.containsKey(subString)) {
                    substrings.insertEntry(new MyHashMapEntry<String, Integer>(subString, index));
                    index++;
                    String out = subString.substring(0, subString.length() - 1);
                    if (out.length() > 1) {
                        result.append(substrings.getValue(out));
                    } else {
                        result.append(Integer.valueOf(out.charAt(0)));
                    }
                    subString = "" + c;
                }
            }
        }
        if (subString.length() > 1) {
            result.append(substrings.getValue(subString));
        } else {
            result.append((int) subString.toCharArray()[0]);
        }
        return result;
    }

    /**
     * LZW decoder. Takes text, that will be translated into original text.
     * 
     * @param compress Original text in coded format. Is a list of Integers.
     * @return Original text as string
     */
    public String constructOriginalText(MyList<Integer> compress) {
        MyList<String> substrings = new MyList<>();
        String result = "";
        Integer old = compress.get(0);
        char c = (char) old.intValue();
        result = result + c;
        String subString = "";

        for (int i = 1; i < compress.getSize(); i++) {
            Integer next = compress.get(i);
            if (next > (255 + substrings.getSize())) {
                subString = "" + (char) old.intValue();
                subString = subString + c;
            } else {
                if (next < 256) {
                    subString = "" + (char) next.intValue();
                } else {
                    subString = substrings.get(next - 256);
                }
            }
            result = result + subString;
            c = subString.toCharArray()[0];
            if (old.intValue() < 256) {
                String oldAndC = "" + (char) old.intValue() + c;
                substrings.append((oldAndC));
            } else {
                String oldAndC = substrings.get(old - 256) + c;
                substrings.append((oldAndC));
            }

            old = next;
        }
        return result;
    }

    public MyList<Integer> compressText(String text) {
        return constructLZWCompress(text);
    }
}
