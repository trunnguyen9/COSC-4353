package assignment3;

import org.junit.Test;
import static org.junit.Assert.*;

public class FibonacciRecursiveTest extends FibonacciNumbersTest {
    @Override
    public int callFibonacci(int n)
    {
        return getFibonacciNumbers().fibonacciRecursive(n);
    }
}
