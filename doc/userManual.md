# User manual

### Downloading .JAR file

- You can run the app by running `java -jar <PATH/TO/THE/FILE.jar>`

### Cloning project:

- You can run project by running `./gradlew run`
- You can run tests by running `./gradlew test`

### Running the app

Appliaction starts and you can choose which actions to take. You can either

- [1] Compress file
- [2] Decompress file
- [3] Compress given input
- [4] Benchmark algorithms

**[1] Compress file**

You can compress file. Choosing this option app prompts you to write the path to the file (`</PATH/TO/YOUR/FILE.txt>`). You can only compress .txt files. After giving the path app prompts you to choose the compression algorithm. After choosing the algorithm app starts to compress the file and informs when done.

**[2] Decompress file**

You can decompress already compressed file. Choosing this option app prompts you to give the path to the file (`</PATH/TO/YOUR/FILE.txt>`). After giving the path app starts the decompress and informs when ready.

**[3] Compress given input**

You can give input to the app and it compresses it. Choosing this option app prompts you to give input to the app. After giving the input app prompts you to choose the algorithm to use in compression. After choosing the algorithm app compresses the input and displays the result.

**[4] Benchmark algorithms**

You can benchmark algorithms. Choosing this option app prompts you to give the path you wish to be used in benchmarking the algorithms (`</PATH/TO/YOUR/FILE.txt>`). After giving the path app both compresses and decompresses given file without writing the output to the file. Then app displays the result (compression time, decompression time and saved space)
