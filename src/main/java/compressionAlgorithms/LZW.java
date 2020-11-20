package compressionAlgorithms;

import java.io.IOException;
import java.util.ArrayList;

public class LZW {

    public LZW() {

    }

    Boolean compress(String file) throws ClassNotFoundException, IOException {
        FileReaderWriter frw = new FileReaderWriter();
        String text = frw.readeDataFromFile(file);
        ArrayList<Integer> compressedData = constructLZWCompress(text);
        return true;
    }

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
                    System.out.println(subString + " : " + out);
                    if (out.length() > 1) {
                        result.add(list.indexOf(out) + 256);
                    } else {
                        result.add(Integer.valueOf(out.charAt(0)));
                    }
                    subString = "" + c;
                }
            }
        }
        return result;
    }

    Boolean deCompress(String file) {
        return true;
    }
}
