package assigment2.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class ReviewsReaderTest {
    ReviewsReader reviewsReader = new ReviewsReader();
    
    @Test
    public void testGetTitleOfABookWithAValidISBN()
    {
        ImmutablePair<ImmutablePair<String, Integer>, Exception> result = reviewsReader.parseForISBN("1507707614");
        Exception exception = result.getRight();
        
        if(exception == null)
        {
            assertEquals("C# Programming for Beginners: An Introduction and Step-by-Step Guide to Programming in C#", result.getLeft().getLeft());
            assertTrue((int)result.getLeft().getRight() >= 64);
        }else
        {
            assertEquals(null, result.getLeft());
        }
    }
    
    @Test
    public void testGetTitleOfABookWithAInvalidISBN()
    {
        ImmutablePair<ImmutablePair<String, Integer>, Exception> result = reviewsReader.parseForISBN("999999");
                
        assertEquals(null, result.getLeft());
        assertTrue(result.getRight() != null);
    }
}

