package compressionAlgorithms.benchmark;

/**
 * Object, which stores the time taken by compression and decompression and percentage of saved
 * space by a algorithm
 */
public class BenchmarkObject {

    private Double compressTime;
    private Double deCompressTime;
    private Double savedSpace;

    public BenchmarkObject(Double compressTime, Double deCompressTime, Double savedSpace) {
        this.compressTime = compressTime;
        this.deCompressTime = deCompressTime;
        this.savedSpace = savedSpace;
    }

    /**
     * Function, which returns time taken by compression
     * 
     * @return
     */
    public Double getCompressTime() {
        return this.compressTime;
    }

    /**
     * Function, which returns time taken by decompression
     * 
     * @return
     */
    public Double getDeCompressTime() {
        return this.deCompressTime;
    }

    /**
     * Function, which returns percentage of saved space
     * 
     * @return
     */
    public Double getSavedSpace() {
        return this.savedSpace;
    }
}
