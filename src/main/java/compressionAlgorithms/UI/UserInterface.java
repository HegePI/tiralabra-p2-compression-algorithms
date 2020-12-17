package compressionAlgorithms.UI;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import compressionAlgorithms.algorithms.Huffman;
import compressionAlgorithms.algorithms.LZW;
import compressionAlgorithms.benchmark.BenchmarkObject;
import compressionAlgorithms.dataStructures.MyList;

public class UserInterface {

    /**
     * Functions, which starts and runs UI
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void run() throws ClassNotFoundException, IOException {
        System.out.println("Welcome to use the compressor! \n");

        Scanner reader = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println(
                    "Available commands: \n[1] Compress file \n[2] Decompress file \n[3] Compress given input \n[4] benchmark algorithms \n[q] quit \n");

            System.out.print("Command: ");
            String cmd = reader.nextLine();

            switch (cmd) {
                case "1":
                    compressFile(reader);
                    break;
                case "2":
                    deCompressFile(reader);
                    break;
                case "3":
                    compressInput(reader);
                    break;
                case "4":
                    benchmarkAlgorithms(reader);
                    break;
                case "q":
                    System.out.println("terminating...\n");
                    quit = true;
                    break;
                default:

            }
        }
        reader.close();
    }

    /**
     * Functons, which prompts to the user the decompress dialog.
     * 
     * @param reader Takes Scanner object as an argument
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deCompressFile(Scanner reader) throws IOException, ClassNotFoundException {
        System.out.println("Decompressing file...\n");

        boolean validPath = false;
        File file = null;

        while (!validPath) {
            System.out.println(
                    "Give a path to a file, which you want to decompress\npress q to quit\n");
            String path = reader.nextLine();

            if (path.equals("q")) {
                System.out.println("terminating...");
                break;
            }

            file = new File(path);

            if (file.exists()) {
                if (file.getCanonicalPath().split("\\.")[1].equals("huff")
                        || file.getCanonicalPath().split("\\.")[1].equals("lzw")) {
                    break;
                } else {
                    System.out.println("Can only decompress .huff files or .lzw files\n");
                }
            } else {
                System.out.println("Given file doesn't exist, try again\npress q to quit\n");
            }
        }

        System.out.println("decompressing file...");

        if (file.getCanonicalPath().split("\\.")[1].equals("huff")) {
            Huffman hf = new Huffman();
            long start = System.nanoTime();
            boolean success = hf.deCompressFile(file);
            long end = System.nanoTime();
            if (success) {
                System.out.println("succesfully decompressed file...\n");
                System.out.println("Time elapsed: " + (end - start) / 10e9 + "s\n");
            } else {
                System.out.println("something went wrong...\n");
            }
        } else {
            LZW lzw = new LZW();
            long start = System.nanoTime();
            boolean success = lzw.deCompress(file);
            long end = System.nanoTime();
            if (success) {
                System.out.println("succesfully decompressed file...\n");
                System.out.println("Time elapsed: " + (end - start) / 10e9 + "s\n");
            } else {
                System.out.println("something went wrong...\n");
            }
        }
    }

    /**
     * Function, which prompts to the user the compress file dialog
     * 
     * @param reader Takes Scanner object as argument
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void compressFile(Scanner reader) throws ClassNotFoundException, IOException {
        System.out.println("Compressing file...\n");
        boolean quit = false;

        boolean validPath = false;
        File file = null;
        while (!validPath) {
            System.out.println(
                    "Give a path to a file, which you want to compress\npress q to quit\n");
            String path = reader.nextLine();

            if (path.equals("q")) {
                quit = true;
                break;
            }

            file = new File(path);

            if (file.exists()) {
                if (file.getCanonicalPath().split("\\.")[1].equals("txt")) {
                    break;
                } else {
                    System.out.println("Can only compress .txt files\n");
                }
            } else {
                System.out.println("Given file doesn't exist, try again\npress q to quit\n");
            }
        }

        if (quit) {
            System.out.println("terminating...\n");
            return;
        }

        boolean validAlg = false;
        while (!validAlg) {
            System.out.println("Which compression algorithm you want to use?\n");
            System.out.println("[1] Huffman\n[2] Lempel-Ziv-Welch (LZW)\npress q to quit\n");
            String alg = reader.nextLine();
            switch (alg) {
                case "1":
                    System.out.println("Compressing using Huffman algorithm...\n");
                    Huffman hf = new Huffman();
                    long huffmanStart = System.nanoTime();
                    boolean huffmanSuccess = hf.compressAndSaveToFile(file);
                    long huffmanEnd = System.nanoTime();
                    if (huffmanSuccess) {
                        System.out.println("succesfully compressed file...\n");
                        System.out.println(
                                "Time elapsed: " + (huffmanEnd - huffmanStart) / 10e9 + "s\n");
                    } else {
                        System.out.println("something went wrong...\n");
                    }
                    validAlg = true;
                    break;

                case "2":
                    System.out.println("Compressing using LZW -algorithm...\n");
                    LZW lzw = new LZW();
                    long lzwStart = System.nanoTime();
                    boolean lzwSuccess = lzw.compressFile(file);
                    long lzwEnd = System.nanoTime();
                    if (lzwSuccess) {
                        System.out.println("succesfully compressed file...\n");
                        System.out.println("Time elapsed: " + (lzwEnd - lzwStart) / 10e9 + "s\n");
                    } else {
                        System.out.println("something went wrong...\n");
                    }
                    validAlg = true;
                    break;

                case "q":
                    quit = true;
                    break;

                default:
                    System.out.println("Choose either option 1 or 2...\n");
                    break;
            }
        }

        if (quit) {
            System.out.println("terminating...\n");
            return;
        }
    }

    /**
     * Function, which prompts the compress input dialog to the user. Can be used to test algorithms
     * ton the small inputs
     * 
     * @param reader Takes Scanner object as a argument
     */
    public void compressInput(Scanner reader) {
        System.out.println("Compressing given input...\n");

        System.out.println("write text you wish to compress\npress q to quit\n");
        String text = reader.nextLine();

        if (text.equals("q")) {
            System.out.println("terminating...\n");
            return;
        }

        boolean validAlg = false;
        while (!validAlg) {
            System.out.println("Which compression algorithm you want to use?\n");
            System.out.println("[1] Huffman\n[2] Lempel-Ziv-Welch (LZW)\npress q to quit\n");
            String alg = reader.nextLine();

            switch (alg) {
                case "1":
                    System.out.println("Compressing text using Huffman -algorithm...\n");
                    Huffman hf = new Huffman();
                    huffmanResult(text, hf.compressText(text));
                    validAlg = true;
                    break;
                case "2":
                    System.out.println("Compressing text using LZW -algorithm...\n");
                    LZW lzw = new LZW();
                    lzwResult(text, lzw.compressText(text));
                    validAlg = true;
                    break;
                case "q":
                    System.out.println("terminating...\n");
                    return;

                default:
                    System.out.println(
                            "Choose either\n[1] Huffman\n[2] Lempel-Ziv-Welch (LZW)\npress q to quit\n");

            }
        }
    }

    /**
     * Function, which benchmarks Huffman and Lempel-Ziv-Welch algorithms. User gives path to the
     * file which will be compressed by both algorithms and effectiveness of both algorithms is
     * measured and can be compared
     * 
     * @param reader Takes Scanner object as an argument
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void benchmarkAlgorithms(Scanner reader) throws IOException, ClassNotFoundException {
        System.out.println("Benchmarkign algorithms...\n");

        boolean validPath = false;
        File file = null;
        while (!validPath) {
            System.out.println(
                    "Give a path to a file, which you want to compress\npress q to quit\n");
            String path = reader.nextLine();

            if (path.equals("q")) {
                System.out.println("terminating...");
                return;
            }

            file = new File(path);

            if (file.exists()) {
                if (file.getCanonicalPath().split("\\.")[1].equals("txt")) {
                    break;
                } else {
                    System.out.println("Can only compress .txt files\n");
                }
            } else {
                System.out.println("Given file doesn't exist, try again\npress q to quit\n");
            }
        }
        Huffman hf = new Huffman();
        BenchmarkObject huffmanBenchmark = hf.compressAndReturnBenchmarkObject(file);

        LZW lzw = new LZW();
        BenchmarkObject lzwBenchmark = lzw.compressAndReturnBenchmarkObject(file);

        System.out.println("Benchmarking results\n-----------------------------\n");
        System.out.println("Huffman algorithm\n_________________\nCompress time: "
                + huffmanBenchmark.getCompressTime() + "s\nDecompress time: "
                + huffmanBenchmark.getDeCompressTime() + "s\nsaved space: "
                + huffmanBenchmark.getSavedSpace() + "%\n");
        System.out.println("LZW -algorithm\n_________________\nCompress time: "
                + lzwBenchmark.getCompressTime() + "\nDecompress time: "
                + lzwBenchmark.getDeCompressTime() + "\nsaved space: "
                + lzwBenchmark.getSavedSpace() + "%\n");

    }

    /**
     * Helper function, which prints results of a Huffman algorithm to readable format
     * 
     * @param text Users given input text which is compressed
     * @param bits Result of compression
     */
    private void huffmanResult(String text, String bits) {
        System.out.println("Original text: " + text);
        System.out.println("Bits : " + bits);
        System.out.println("saved space: (1 - (compressed size / uncompressed size)) * 100%\n");
        System.out.println("Space saved: " + huffmanSpaceSaved(text, bits) + "\n");
    }

    /**
     * Helper function, which prints results of a Lempel-Ziv-Welch algorithm to readable format
     * 
     * @param text Users given input text which is compressed
     * @param bits Result of compression
     */
    private void lzwResult(String text, MyList<Integer> list) {
        System.out.println("Original text: " + text);
        System.out.println("LZW compress: " + list.toString());
        System.out.println("saved space: (1 - (compressed size / uncompressed size)) * 100%\n");
        System.out.println("Space saved: " + lzwSpaceSaved(text, list) + "\n");
    }

    /**
     * Helper function which calculates the percentage of saved space of LZW -algorihtm
     * 
     * @param text Input text
     * @param list Result list
     * @return
     */
    private String lzwSpaceSaved(String text, MyList<Integer> list) {
        return (1 - (double) (list.getSize() * 8) / (double) (text.length() * 8)) * 100 + "%";
    }

    /**
     * Helper function which calculates the percentage of saved space of LZW -algorihtm
     * 
     * @param text Input text
     * @param bits Result bits
     * @return
     */
    private String huffmanSpaceSaved(String text, String bits) {
        return (1 - (double) bits.length() / (double) (text.length() * 8)) * 100 + "%";

    }
}
