package assigment2.model;

import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.*;

public interface ISBNService {
    public ImmutablePair<ImmutablePair<String, Integer>, Exception>  parseForISBN(String isbn);
}
