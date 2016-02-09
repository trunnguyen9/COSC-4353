package assignment3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public abstract class FibonacciNumbersTest
{
    private FibonacciNumbers fibonacciNumbers;
    
    @Before
    public void initialize()
    {
        fibonacciNumbers = new FibonacciNumbers();
    }
    
    protected FibonacciNumbers getFibonacciNumbers()
    {       
        return fibonacciNumbers;
    }

    public abstract int callFibonacci(int n);
    
    @Test
    public void testFibonacciAtZero()
    {        
        assertEquals(1, callFibonacci(0));
    }    
    
    @Test
    public void testFibonacciAtOne()
    {        
        assertEquals(1, callFibonacci(1));
    } 
    
    @Test
    public void testFibonacciAtTwo()
    {        
        assertEquals(2, callFibonacci(2));
    }   
    
    @Test
    public void testFibonacciAtFour()
    {        
        assertEquals(5, callFibonacci(4));
    } 
    
    @Test
    public void testFibonacciAtSeven()
    {        
        assertEquals(21, callFibonacci(7));
    } 
}