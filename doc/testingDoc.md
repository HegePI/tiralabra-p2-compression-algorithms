# Testing doc

## What has been tested?

**_5.12.2020_**

Algorithms has been tested to be sure that their helper functions works as intended. Also in IO directory `FileReaderWriter` has been tested to ensure its correct functionality.

Also I will test that my own data structure implementations will work properly when I have managed to implement those in my application. I will test their functionality probably by just writing unit tests for those.

What hasn't been tested is overall functionality of algorithms from file to compressed form and back. This should be tested to ensure that algorithms work overall.

Things that will not most likely to be tested is functionality of UI.

**_17.12.2020_**

The effectiveness of algorithms has been tested on few .txt files

## Huffman -algorithm

| **Test data**                      | **Time to compress (s)** | **Time to decompress (s)** | **Saved space (%)**  |
| ---------------------------------- | ------------------------ | -------------------------- | -------------------- |
| Kalevala, 1. rune (269 lines)      | 0.0070983579 s           | 0.0019242587 s             | 45.49049748462829 %  |
| Kalevala, runes 1 - 4 (2608 lines) | 0.2554340148 s           | 0.0533877781 s             | 44.03096812716565 %  |
| Edda poems, whole (12828 lines)    | 9.3173833832 s           | 1.0742291656 s             | 42.251405446353814 % |

## LZW -algorithm

| **Test data**                      | **Time to compress (s)** | **Time to decompress (s)** | **Saved space (%)**  |
| ---------------------------------- | ------------------------ | -------------------------- | -------------------- |
| Kalevala, 1. rune (269 lines)      | 0.001722265 s            | 0.0014376748 s             | 63.801006148686426 % |
| Kalevala, runes 1 - 4 (2608 lines) | 0.0133176437 s           | 0.0200303949 s             | 74.05245966902815 %  |
| Edda poems, whole (12828 lines)    | 0.2132338212 s           | 0.7874264378 s             | 78.10624321563118 %  |

| :exclamation: Note :exclamation:: Tried running benchmark also on othello (whole, 6681 lines), but threw an error: `Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 8217 out of bounds for length 256`. Exception was thrown because of `Right Single Quotation Mark`, which unicode number is 8127. Problem can be solved by swithching from using `int[256]` to using `MyHashMap<Character, Integer>` datastructure instead. |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |

## How it has been tested?

**_5.12.2020_**

At this point tests are only unit tests for helper algorithms functions using Junit. At this point test cases are pretty small and limited but it ensures that helper functions work, eg. function `int[] countCharFrequency(String text)` returns correct int array.

**_17.12.2020_**

Test has been done by running the app and choosing the benchmark option

## How to reproduce tests?

**_5.12.2020_**

Only good reproducable tests are Junit tests in test directory. These can be reproduced by running `./gradlew test`.

**_17.12.2020_**

By running the app and choosing benchmark option. User can test the algorithms also on different files.
