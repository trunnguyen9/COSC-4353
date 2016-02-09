package assigment2.model;

import java.net.*;
import java.io.*;
import java.lang.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import java.util.*;

public class ReviewsReader implements ISBNService {
    private final static String link = "http://www.amazon.com/exec/obidos/ASIN/ISBN";
    private final static String ELEMENT_ID_TO_GET_BOOK_TITLE = "productTitle";
    private final static String ELEMENT_ID_TO_GET_REVIEWS = "acrCustomerReviewText";
    
    public String linkToGetReviews(String isbn)
    {
        return link.replace("ISBN", isbn.trim());
    }
    
    @Override
    public ImmutablePair<ImmutablePair<String, Integer>, Exception> parseForISBN(String isbn)
    {
        return getTitleAndReviews(linkToGetReviews(isbn));
    }
    
    private ImmutablePair<ImmutablePair<String, Integer>, Exception> getTitleAndReviews(String urlText)
    {
        Element titleElement = null;
        Element reviewsElement = null;
        int reviewsCount = 0;
        
        try{
            Document doc = Jsoup.connect(urlText).get();
            titleElement = doc.getElementById(ELEMENT_ID_TO_GET_BOOK_TITLE);
            reviewsElement = doc.getElementById(ELEMENT_ID_TO_GET_REVIEWS);
            
            reviewsCount = extractReviewsCount(reviewsElement.text());
        }catch(Exception e)
        {
            return ImmutablePair.of(null, e);
        }
        
        return ImmutablePair.of(ImmutablePair.of(titleElement.text(), reviewsCount), null);
    }
    
    private int extractReviewsCount(String reviewText)
    {
        StringTokenizer stkn = new StringTokenizer(reviewText, " ");
        return Integer.parseInt(stkn.nextToken());
    }
}
