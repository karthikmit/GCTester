package in.blogspot.karthikpresumes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * This would create several objects of different lifetime to check how GC works with JVisualVM
 */
public class GCTestCore {

    public static final int SAMPLE_SIZE = 100000;

    public void start() throws InterruptedException {
        Random inputRGenerator = new Random(new Date().getTime());
        Random samplerRGeneratore = new Random(new Date().getTime() + 1000 * 60 * 60);
        List<Integer> sampledInteger = new ArrayList<Integer>(SAMPLE_SIZE);
        int counter = 0;
        while(counter++ < 100000000) {
            Integer currentInput = new Integer(Math.abs(inputRGenerator.nextInt()));
            if(sampledInteger.size() < SAMPLE_SIZE) {
                sampledInteger.add(currentInput);
            } else {
                int index = samplerRGeneratore.nextInt(counter);
                if(index < SAMPLE_SIZE) {
                    // Just to generate some long living entries.
                    if(index % 3 != 0) {
                        sampledInteger.add(index, currentInput);
                    }
                }
            }
            try {
                // Just to keep things go fast .. Wait for 1 sec once in 20 times.
                if(currentInput % 100 == 0)
                    Thread.sleep(1);
            } catch (InterruptedException e) {
                // Do nothing ...
            }
        }
        System.out.println(sampledInteger);
    }
}
