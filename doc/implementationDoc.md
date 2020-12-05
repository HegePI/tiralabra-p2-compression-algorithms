# Implementation doc

## Structure of an application

_5.12.2020_

Application is divided into three distinct parts; Algorithms, datastructures and IO. Algorithms directory contains implemented algorithms which are Huffman and LZW. Datastuctures directory contains the datastructures that before mentioned algorithms need. It contains only MyList datastructure but I will try ti implement both MyHashMap and MyHeap datastructures also. IO directory contains logic which is used to save the outputs of algorithms into files and also read those outputs from those files.

## Time and space complexities of algorithms

_5.12.2020_

| algorithm | time complexity | space complexity |
| --------- | --------------- | ---------------- |
| Huffman   | O(nlogn)        | O(n)             |
| LZW       | O(n^2)          | O(n)             |

In LZW time complexity is O(n^2), because it uses at this point Arraylist to store substrings and it uses `indexOf(String substring)` method to get their respective indices which is O(n) method. This happens in for loop which takes O(n) time to run. This is intended to change to use MyHashMap implementation instead of ArrayList.

## Performance

_5.12.2020_

Can not yet compare to algorithms because of lacking UI.

## Limitations

_5.12.2020_

- MyHashMap and MyHeap datastructures are not yet implemented.

- Lacking UI. Missing performance output and LZW option not yet working.
