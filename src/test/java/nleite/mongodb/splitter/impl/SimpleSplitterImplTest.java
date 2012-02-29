package nleite.mongodb.splitter.impl;

import static org.junit.Assert.*;

import nleite.mongodb.splitter.SimpleSplitter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mongodb.Mongo;

public class SimpleSplitterImplTest {

    SimpleSplitter splitter;
    
    @Mock
    Mongo mongo;
    
    
    @Before
    public void setUp() throws Exception {
        
        MockitoAnnotations.initMocks(this);
        
        
        splitter = new SimpleSplitterImpl(mongo);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCalculateSplits() {
        fail("Not yet implemented");
    }

}
