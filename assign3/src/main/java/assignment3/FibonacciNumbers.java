package assignment3;

import java.util.*;
import org.apache.commons.lang3.tuple.Pair;
import java.util.stream.IntStream;
import java.util.HashMap;

public class FibonacciNumbers {
    
    private HashMap<Integer, Integer> cache = new HashMap<>();
    
    public int fibonacciIterative(int n)
    {
        if(n == 0 || n == 1)
        {
            return 1;
        }
        
        Pair<Integer, Integer> fiboPair = Pair.of(1, 1);        
        
        return IntStream.rangeClosed(2, n)
                        .mapToObj(i -> Pair.of(i, 0))
                        .reduce(fiboPair, (prev, ignore) -> Pair.of(prev.getRight(), prev.getLeft() + prev.getRight()))
                        .getRight();
    }
    
    public int fibonacciRecursive(int n)
    {
        if(n == 0 || n == 1)
        {
            return 1;
        }
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }
    
    public int fibonacciMemoized(int n)
    {
        if(n == 0 || n == 1)
        {
            return 1;
        }
        return cache.computeIfAbsent(n, (key) -> fibonacciMemoized(key - 1) + fibonacciMemoized(key - 2));
    }
}