# Specification document

In this project I will implement an application which can be used to compress a given text file. User can specify if he wants to use either Huffman or LZW algorithm. Application reads a given text file and produces a compressed version of that file which will be saved in other file. First implementation of an application will just be command line application, but if time allows, a GUI implementation could be considered/drafted.

Application will be done using Java programming language.

## Datastuctures

The Huffman algorithm uses a list and a priority string. The number of occurrences of different characters in the input is stored in the list, and nodes with information about the character and its occurrence are stored in the priority queue. The priority queue is then used to form a Huffman tree, which is used to compress the given input. The compressed input and tree are then saved to a file. When a tree is saved with a compressed data, it is possible to form the original input afterwards.

The LZW algorithm implements a list data structure that stores various strings of the input that can be retrieved based on the index.

## Time and space complexity of algorithms

Time and space complexity table

| algorithm | time complexity | space complexity |
| --------- | --------------- | ---------------- |
| Huffman   | O(nlogn)        | O(n)             |
| LZW       | O(n)            | O(n)             |

## Sources

https://en.wikipedia.org/wiki/Huffman_coding

https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch

Pu, I. M., & Pu, I. M. (2005). Fundamental data compression. ProQuest Ebook Central https://ebookcentral-proquest-com.libproxy.helsinki.fi

### study program

TKT
