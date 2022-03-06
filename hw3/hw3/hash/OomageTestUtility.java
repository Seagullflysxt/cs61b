package hw3.hash;

//import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* DONE:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buckets = new int[M];
        int osSize = oomages.size();
        for (int i = 0; i < osSize; i += 1) {
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }
        for (int i = 0; i < M; i += 1) {
            if ((osSize / 50 > buckets[i])
                    || (buckets[i] > osSize / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
