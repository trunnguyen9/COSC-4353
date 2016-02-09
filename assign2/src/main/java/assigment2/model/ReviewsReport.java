package assigment2.model;
import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import java.util.*;
import java.io.*;

public class ReviewsReport {
    public static void main(String[] args)
    {
        List<String> isbnList = new ArrayList<String>(); 
        
        try
        {
            isbnList = readInputFileForISBNList("isbn.txt");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
        printReviewsList(getReviews(isbnList));
    }
    
    public static List<String> readInputFileForISBNList(String path) throws IOException
    {
        List<String> isbnList = new ArrayList<String>();
        
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String line = null;
        while( (line = bufferedReader.readLine()) != null)
        {
            isbnList.add(line);
        }
        
        return isbnList;
    }
    
    public static ImmutableTriple<List<ImmutablePair<String, Integer>>, Integer, List<Exception>> getReviews(List<String> isbnList)
    {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.setISBNService(new ReviewsReader());
        
        return reportGenerator.getReviews(isbnList);
    }
    
    public static void printReviewsList(ImmutableTriple<List<ImmutablePair<String, Integer>>, Integer, List<Exception>> reviewsResult)
    {
        List<ImmutablePair<String, Integer>> validList = reviewsResult.getLeft();
        List<Exception> exceptionList = reviewsResult.getRight();
        
        System.out.println("Title: reviews");
        System.out.println("---------------");
        validList.stream()
                 .forEach(item -> System.out.println(item.getLeft() + " : " + item.getRight()));
        
        System.out.println();
        System.out.println("Total Reviews: " + reviewsResult.getMiddle());
        
        System.out.println();
        System.out.println("ISBN: error message");
        System.out.println("--------------------");
        exceptionList.stream()
                     .forEach(item -> System.out.println(item.getMessage()));
        
        
    }
    
}
