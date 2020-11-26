package compressionAlgorithms;

import java.io.IOException;
import java.util.ArrayList;

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
    Boolean compress(String file) throws ClassNotFoundException, IOException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readeDataFromFile(file);
        ArrayList<Integer> compressedData = constructLZWCompress(text);
        return true;
    }

    /**
     * LZW encoder. Takes text, that will be compressed.
     * 
     * @param text
     * @return
     */
    ArrayList<Integer> constructLZWCompress(String text) {
        ArrayList<String> list = new ArrayList<>();
        String subString = "";
        ArrayList<Integer> result = new ArrayList<>();
        for (Character c : text.toCharArray()) {
            subString = subString + c;
            if (subString.length() > 1) {
                if (!list.contains(subString)) {
                    list.add(subString);
                    String out = subString.substring(0, subString.length() - 1);
                    if (out.length() > 1) {
                        result.add(list.indexOf(out) + 256);
                    } else {
                        result.add(Integer.valueOf(out.charAt(0)));
                    }
                    subString = "" + c;
                }
            }
        }
        if (subString.length() > 1) {
            result.add(list.indexOf(subString) + 256);
        } else {
            result.add((int) subString.toCharArray()[0]);
        }
        return result;
    }

    /**
     * 
     * @param file
     * @return
     * @throws IOException
     */
    Boolean deCompress(String file) throws IOException {
        FileReaderWriter frw = new FileReaderWriter();
        ArrayList<Integer> compress = frw.readLZWCompressFromFile(file);
        String originalText = constructOriginalText(compress);
        String outputPath = file.split("\\.")[0] + ".txt";
        return frw.constructOriginalFile(outputPath, originalText);
    }

    /**
     * LZW decoder. Takes text, that will be translated into original text.
     * 
     * @param compress Original text in coded format. Is a list of Integers.
     * @return Original text as string
     */
    String constructOriginalText(ArrayList<Integer> compress) {
        ArrayList<String> list = new ArrayList<>();
        String result = "";
        Integer old = compress.get(0);
        char c = (char) old.intValue();
        result = result + c;
        String subString = "";

        for (int i = 1; i < compress.size(); i++) {
            Integer next = compress.get(i);
            if (next > (255 + list.size())) {
                subString = "" + (char) old.intValue();
                subString = subString + c;
            } else {
                if (next < 256) {
                    subString = "" + (char) next.intValue();
                } else {
                    subString = list.get(next - 256);
                }

            }
            result = result + subString;
            c = subString.toCharArray()[0];
            if (old.intValue() < 256) {
                String oldAndC = "" + (char) old.intValue() + c;
                list.add((oldAndC));
            } else {
                String oldAndC = list.get(old - 256) + c;
                list.add((oldAndC));
            }

            old = next;
        }
        return result;
    }
}
