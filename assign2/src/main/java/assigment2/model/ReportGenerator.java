package assigment2.model;

import java.util.*;
import java.lang.RuntimeException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class ReportGenerator {    
    private ISBNService isbnService; 
    
    public void setISBNService(ISBNService theService) 
    {
        isbnService = theService;
    }
    
    public List<ImmutablePair<String, Integer>> sortByTitle(List<ImmutablePair<String, Integer>> bookList)
    {        
        return bookList.stream()
                       .sorted(comparing(ImmutablePair::getLeft))
                       .collect(toList());
    } 
    
    public int reviewsCount(List<ImmutablePair<String, Integer>> bookList)
    {
        return bookList.stream()
                       .mapToInt(ImmutablePair::getRight)
                       .sum();
    }
    
    public ImmutablePair<ImmutablePair<String, Integer>, Exception> getISBNInfo(String isbn) 
    {
        ImmutablePair<ImmutablePair<String, Integer>, Exception> result = isbnService.parseForISBN(isbn);
 
        if(result. getRight() != null)
        {
            return ImmutablePair.of(null, new RuntimeException(isbn + ":" + result. getRight().getMessage()));
        }
        
        return result;
    }
    
    public ImmutableTriple<List<ImmutablePair<String, Integer>>, Integer, List<Exception>> getReviews(List<String> isbnList)
    {
         List<ImmutablePair<ImmutablePair<String, Integer>, Exception>> result =
           isbnList.stream()
                   .map(isbn -> getISBNInfo(isbn))
                   .collect(toList());
                   
         List<ImmutablePair<String, Integer>> validRawList = 
           result.stream()
                 .filter(info -> info.getLeft() != null)
                 .map(ImmutablePair::getLeft)
                 .collect(toList());
                 
         List<Exception> invalidRawList = 
           result.stream()
                 .filter(info -> info.getRight() != null)
                 .map(ImmutablePair::getRight)
                 .collect(toList());

        return ImmutableTriple.of(sortByTitle(validRawList), reviewsCount(validRawList), invalidRawList);
    }
}
