
package assignment3;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FibonacciMemoizedTest extends FibonacciNumbersTest{
    
    @Override
    public int callFibonacci(int n)
    {
        return getFibonacciNumbers().fibonacciMemoized(n);
    } 
    
    @Test
    public void testPerformanceMemoizedFasterThanRecursiveAtPosition40()
    {
        int position = 40;
        long recursiveTime = computeTime(() -> getFibonacciNumbers().fibonacciRecursive(position)); 
        long memoizedTime = computeTime(() -> getFibonacciNumbers().fibonacciMemoized(position));
        assertTrue(recursiveTime > 10 * memoizedTime);
    }
    
    public long computeTime(Runnable code) 
    {
        long start = System.nanoTime();
        code.run();
        return System.nanoTime() - start;
    }
}
