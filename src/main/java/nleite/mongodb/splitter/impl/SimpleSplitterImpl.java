package nleite.mongodb.splitter.impl;

import java.util.Collection;
import java.util.HashSet;

import nleite.mongodb.splitter.SimpleSplit;
import nleite.mongodb.splitter.SimpleSplitter;
import nleite.mongodb.splitter.SplitFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Implementation to split the normal, unsharded mongo instance queries.
 * @author norberto
 *
 */
public class SimpleSplitterImpl implements SimpleSplitter{
    private static final String SHARDED = "sharded";
    /**
     * Database instance.
     */
    private Mongo mongo;

    private Logger log = LoggerFactory.getLogger(SimpleSplitterImpl.class);
    
    public SimpleSplitterImpl(final Mongo mongo){
        this.mongo = mongo;
    }
    
    
    public Collection<SimpleSplit> calculateSplits(DBCollection collection, DBObject query, DBObject fields, DBObject sort) {
        
        final CommandResult stats = collection.getStats();
        
        final boolean isSharded = stats.getBoolean(SHARDED, false);
        
        final int numDocuments = stats.getInt("objects");
        //collect number of extents. I use this value to determine the number of splits due to the fact the extents have approximately the same number of objects.
        final int numExtents = stats.getInt("numExtents");
    
        int numSplits = numExtents;
        
        if ( numDocuments < numExtents){
            numSplits = 1;
        }
        
        int splitSize = numDocuments / numSplits;
        
        return buildSplits(splitSize, numSplits, query, fields, sort, collection);
    }

    
    private Collection<SimpleSplit> buildSplits(int splitSize, int numSplits, DBObject query, DBObject fields, DBObject sort, DBCollection coll ){
        Collection<SimpleSplit> splits = new HashSet<SimpleSplit>(numSplits);
        
        int skip = 0;
        
        int limit = splitSize;
        
        int rounds = numSplits;
        
        SplitFactory factory = new SplitFactory();
        
        do{
            DBCursor cursor = coll.find(query, fields);
            cursor.limit(limit);
            cursor.skip(skip);
            cursor.sort(sort);
            splits.add(factory.buildSplit(cursor));
            //calculate new skip
            skip += splitSize;
            //based on that calculate new limit
            limit = skip + splitSize;
            rounds--;
        } while(rounds > 0);
        
        return splits;
    }
    
    
}
