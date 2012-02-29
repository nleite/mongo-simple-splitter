package nleite.mongodb.splitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DBCursor;
/**
 * Factory that generates the SimpleSplit objects according with the defined type.
 * The type indicates which kind of SimpleSplit implementation will be used.
 * By default uses type = 0;
 * @author norberto
 *
 */
public class SplitFactory {
    //This should probably be an Enumeration
    //TODO review this element.
    private int type = 0;

    private static Logger log = LoggerFactory.getLogger(SplitFactory.class);
    
    public SplitFactory() {
        log.info("Creating the factory with default type");
    }

    public SplitFactory(int type) {
        super();
        this.type = type;
    }
    
    public SimpleSplit buildSplit(DBCursor cursor){
        SimpleSplit split = null;
        
        
        return split;
    }
    
    
    
    
}
