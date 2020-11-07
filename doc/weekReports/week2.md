# 2. Week report

## What have I done

Started to implement Huffman algorithm and a way to store compressed data into a file. At this point compressed data and a hashmap will be stored in separate files untill I figure out how to store them into a single file and read them from there reliably. [writer implementations]("https://github.com/HegePI/tiralabra-p2-compression-algorithms/pull/1/files#diff-af4d7cc59cf3b4ad1606dd7adec942e6327ddcec4a3ddcba4fe0536431e8f779R10), [.map file]("https://github.com/HegePI/tiralabra-p2-compression-algorithms/pull/1/files#diff-af4d7cc59cf3b4ad1606dd7adec942e6327ddcec4a3ddcba4fe0536431e8f779R12) and [.huff file]("https://github.com/HegePI/tiralabra-p2-compression-algorithms/pull/1/files#diff-af4d7cc59cf3b4ad1606dd7adec942e6327ddcec4a3ddcba4fe0536431e8f779R18)

## Progress aff an aplication

Most important aspects of a Huffman algorithm has been done. [Compress function]("https://github.com/HegePI/tiralabra-p2-compression-algorithms/blob/master/src/main/java/compressionAlgorithms/Huffman.java#L15")

## Learned things

Java Object serialization can be used to store needed data structures into a file and to read them from there. [Implementation]("https://github.com/HegePI/tiralabra-p2-compression-algorithms/pull/1/files#diff-af4d7cc59cf3b4ad1606dd7adec942e6327ddcec4a3ddcba4fe0536431e8f779R13")

## Unclear things

The way to reliably write and read needed data to/from single file.

## Next to-do

Start to figure out how to implement LZW -algorithm. Quicker I start to make it faster I figure out possible problems with it. Also I shoud start to switch from using already made data stuctures (HashMap, PriorityQueu) to my using my own versions of these.
