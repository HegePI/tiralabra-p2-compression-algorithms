# 3. Week report

## What have I done

Simplified class FileReaderWriter functions [writeBitsToFile](https://github.com/HegePI/tiralabra-p2-compression-algorithms/blob/master/src/main/java/compressionAlgorithms/FileReaderWriter.java#L28) and [readBitsFromFile](https://github.com/HegePI/tiralabra-p2-compression-algorithms/blob/master/src/main/java/compressionAlgorithms/FileReaderWriter.java#L75) because they caused problems when recreating original text. The bits red from .huff file were different from the bits that were written to it. Also instead of saving character to bit represantation hashmap program saves char frequency hashmap. Because of this program can recreate huffman tree.

## Progress off an aplication

Now that FileReaderWriter is simplified, huffman algorithm seems to work now. It takes an filepath, 'compresses' it and can recreate it using .huff file and .map file.

## Learned things

First FileReaderWriter functions implementations writeBitsToFile and readBitsFromFile didn't work and caused problems.

## Unclear things

How to store hashmap and bits to same file and way to save generated bits optimally.

## Next to-do

LZW -algorithm using already made data structures. Starting to implement my own data structures to huffman (prioqueu, lookup table, etc...)
