package assigment2.model;

import java.util.ArrayList;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.*;

public class ReportGeneratorTest 
{
    ReportGenerator reportGenerator = new ReportGenerator();
    
    @Test
    public void testEmptyReviewList()
    {
        List<ImmutablePair<String, Integer>> reviewList = new ArrayList<>();
        List<ImmutablePair<String, Integer>> sortedReviewList = reportGenerator.sortByTitle(reviewList);
        
        assertEquals(reviewList, sortedReviewList);
    }    

    @Test
    public void testOneItemReviewList()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(ImmutablePair.of("C", 100));
        List<ImmutablePair<String, Integer>> sortedReviewList = reportGenerator.sortByTitle(reviewList);  
        
        assertEquals(reviewList, sortedReviewList);
    }
    
    private ImmutablePair<String, Integer> cSharp = ImmutablePair.of("C#", 100);
    private ImmutablePair<String, Integer> java = ImmutablePair.of("Java", 100);
    private ImmutablePair<String, Integer> php = ImmutablePair.of("PHP", 100);
    
    @Test
    public void testTwoItemsReviewList()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(cSharp, java);
        List<ImmutablePair<String, Integer>> expectedReviewList = Arrays.asList(cSharp, java);
        List<ImmutablePair<String, Integer>> sortedReviewList = reportGenerator.sortByTitle(reviewList);  
        
        assertEquals(expectedReviewList, sortedReviewList);
    }
    
    @Test
    public void testThreeItemsReviewList()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, cSharp, php); 
        List<ImmutablePair<String, Integer>> expectedReviewList = Arrays.asList(cSharp, java, php);
        List<ImmutablePair<String, Integer>> sortedReviewList = reportGenerator.sortByTitle(reviewList);
        
        assertEquals(expectedReviewList, sortedReviewList);
    }
    
    @Test
    public void testThreeItemsWithDuplicateReviewList()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, cSharp, java); 
        List<ImmutablePair<String, Integer>> expectedReviewList = Arrays.asList(cSharp, java, java);
        List<ImmutablePair<String, Integer>> sortedReviewList = reportGenerator.sortByTitle(reviewList);
        
        assertEquals(expectedReviewList, sortedReviewList);
    }
    
    @Test
    public void testTotalNumberOfReviewsEmptyList()
    {
        List<ImmutablePair<String, Integer>> reviewList = new ArrayList<>();
        assertEquals(0, reportGenerator.reviewsCount(reviewList));
    }
    
    @Test
    public void testTotalNumberOfReviewsOneItemList()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java);
        assertEquals(100, reportGenerator.reviewsCount(reviewList));
    }
    
    @Test
    public void testTotalNumberOfReviewsTwoItemsListWithNoDuplicateItem()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, cSharp);
        assertEquals(200, reportGenerator.reviewsCount(reviewList));
    }
    
    @Test
    public void testTotalNumberOfReviewsTwoItemsListWithDuplicateItem()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, java);
        assertEquals(200, reportGenerator.reviewsCount(reviewList));
    }
    
     @Test
    public void testTotalNumberOfReviewsThreeItemsListWithNoDuplicateItem()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, cSharp, php);
        assertEquals(300, reportGenerator.reviewsCount(reviewList));
    }
    
    @Test
    public void testTotalNumberOfReviewsThreeItemsListWithDuplicateItem()
    {
        List<ImmutablePair<String, Integer>> reviewList = Arrays.asList(java, cSharp, java);
        assertEquals(300, reportGenerator.reviewsCount(reviewList));
    }
    
    String testISBN = "1507707614";
    String testTitle = "C# Programming for Beginners: An Introduction and Step-by-Step Guide to Programming in C#";
    
    @Test
    public void testGetISBNInfoWithNoException()
    {
        ISBNService isbnService = Mockito.mock(ISBNService.class); 
        when(isbnService.parseForISBN(testISBN)).thenReturn(ImmutablePair.of(ImmutablePair.of(testTitle, 100), null)); 
        
        reportGenerator.setISBNService(isbnService);
        assertEquals(ImmutablePair.of(testTitle, 100), reportGenerator.getISBNInfo(testISBN).getLeft()); 
        assertEquals(null, reportGenerator.getISBNInfo(testISBN).getRight());
    }
    
    @Test
    public void testGetISBNInfoWithException()
    {
        ISBNService isbnService = Mockito.mock(ISBNService.class);
        when(isbnService.parseForISBN(testISBN)).thenReturn(ImmutablePair.of(null, new RuntimeException("INVALID ISBN")));
        
        reportGenerator.setISBNService(isbnService);
        ImmutablePair returnedItem = reportGenerator.getISBNInfo(testISBN);
        
        assertEquals(testISBN + ":INVALID ISBN", ((RuntimeException)returnedItem.getRight()).getMessage());
        assertEquals(null, reportGenerator.getISBNInfo(testISBN).getLeft());
      
    }
    
    String testISBN1 = "123";
    String testISBN2 = "234";
    String testISBN3 = "345";
    
    @Test
    public void testGetISBNInfo3ItemAllValid()
    {
        ISBNService isbnService = Mockito.mock(ISBNService.class); 
        when(isbnService.parseForISBN(testISBN1)).thenReturn(ImmutablePair.of(java, null));
        when(isbnService.parseForISBN(testISBN2)).thenReturn(ImmutablePair.of(cSharp, null));
        when(isbnService.parseForISBN(testISBN3)).thenReturn(ImmutablePair.of(php, null));
        
        List<ImmutablePair<String, Integer>> sortedList = Arrays.asList(cSharp, java, php);
        
        reportGenerator.setISBNService(isbnService);
        assertEquals(sortedList, reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getLeft()); 
        assertEquals(300, (int)reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getMiddle()); 
        assertEquals(0, reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getRight().size()); 
    }
    
    @Test
    public void testGetISBNInfo3ItemAllInvalid()
    {
        ISBNService isbnService = Mockito.mock(ISBNService.class); 
        when(isbnService.parseForISBN(testISBN1)).thenReturn(ImmutablePair.of(null, new RuntimeException("INVALID ISBN")));
        when(isbnService.parseForISBN(testISBN2)).thenReturn(ImmutablePair.of(null, new RuntimeException("INVALID ISBN")));
        when(isbnService.parseForISBN(testISBN3)).thenReturn(ImmutablePair.of(null, new RuntimeException("INVALID ISBN")));
        
        List<Exception> exceptionList = Arrays.asList(
                                                        new RuntimeException(testISBN1 + ":INVALID ISBN"),
                                                        new RuntimeException(testISBN2 + ":INVALID ISBN"),
                                                        new RuntimeException(testISBN3 + ":INVALID ISBN")
                                                     );
        
        reportGenerator.setISBNService(isbnService);
        assertEquals(0, reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getLeft().size()); 
        assertEquals(0, (int)reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getMiddle()); 
        
        assertTrue(compareTwoExceptionList(exceptionList, 
          reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getRight())); 
    }
    
    ImmutablePair<String, String> invalidItem = ImmutablePair.of(testISBN3, "INVALID ISBN");
    
    @Test
    public void testGetISBNInfo3ItemOneInvalid()
    {
        ISBNService isbnService = Mockito.mock(ISBNService.class); 
        when(isbnService.parseForISBN(testISBN1)).thenReturn(ImmutablePair.of(java, null));
        when(isbnService.parseForISBN(testISBN2)).thenReturn(ImmutablePair.of(cSharp, null));
        when(isbnService.parseForISBN(testISBN3)).thenReturn(ImmutablePair.of(null, new RuntimeException("INVALID ISBN")));
        
        List<ImmutablePair<String, Integer>> sortedList = Arrays.asList(cSharp, java);
        List<Exception> exceptionList = Arrays.asList(new RuntimeException(testISBN3 + ":INVALID ISBN"));
        
        reportGenerator.setISBNService(isbnService);
        assertEquals(sortedList, reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getLeft()); 
        assertEquals(200, (int)reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getMiddle()); 
        
        assertTrue(compareTwoExceptionList(exceptionList, reportGenerator.getReviews(Arrays.asList(testISBN1, testISBN2, testISBN3)).getRight())); 
    }
    
    boolean compareTwoExceptionList(List<Exception> exceptionList1, List<Exception> exceptionList2)
    {
        for(int i = 0; i < exceptionList1.size(); i++)
        {            
            return exceptionList1.get(i).getMessage().equals(exceptionList2.get(i).getMessage());
        }
        
        return true;
    }
}
