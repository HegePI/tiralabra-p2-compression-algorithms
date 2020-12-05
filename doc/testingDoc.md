# Testing doc

## What has been tested?

**_5.12.2020_**

Algorithms has been tested to be sure that their helper functions works as intended. Also in IO directory `FileReaderWriter` has been tested to ensure its correct functionality.

Also I will test that my own data structure implementations will work properly when I have managed to implement those in my application. I will test their functionality probably by just writing unit tests for those.

What hasn't been tested is overall functionality of algorithms from file to compressed form and back. This should be tested to ensure that algorithms work overall.

Things that will not most likely to be tested is functionality of UI.

## How it has been tested?

**_5.12.2020_**

At this point tests are only unit tests for helper algorithms functions using Junit. At this point test cases are pretty small and limited but it ensures that helper functions work, eg. function `int[] countCharFrequency(String text)` returns correct int array.

## How to reproduce tests?

**_5.12.2020_**

Only good reproducable tests are Junit tests in test directory. These can be reproduced by running `./gradlew test`.
