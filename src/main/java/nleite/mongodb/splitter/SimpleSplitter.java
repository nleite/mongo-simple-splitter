package nleite.mongodb.splitter;

import java.util.Collection;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface SimpleSplitter {

    
    /**
     * For a given mongo collection instance the system calculates the different splits on which the query can be separated.
     * @param collection - database instance.
     * @param query - query to be split.
     * @param fields TODO
     * @param sort TODO
     * @return Collection of splits.
     */
    Collection<SimpleSplit> calculateSplits(DBCollection collection, DBObject query, DBObject fields, DBObject sort);
    
    
}
