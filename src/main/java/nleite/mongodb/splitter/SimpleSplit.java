package nleite.mongodb.splitter;

import com.mongodb.DBCursor;

/**
 * SimpleSplit represents the access point to the a fraction of the query wanted to be processed. 
 * Each split enables the access to the documents through the usage of normal DBCursors. 
 * @author norberto
 *
 */
public interface SimpleSplit {

    /**
     * Provides a simple <code>DBCursor</code> to access the data corresponding to this split.
     * @return
     */
    DBCursor getCursor();
    
}
