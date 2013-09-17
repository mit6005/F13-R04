import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class StreamParserTest {

    @Test
    public void basicTest() {
        String stream = "NumVals: 3 Base: 16 8e 11 12\n";
        int[] vals = new int[] {142, 17, 18};
        ArrayList<Integer> correctVals = new ArrayList<Integer>();
        for (int i = 0; i < vals.length; i++) {
            correctVals.add(vals[i]);
        }
        ArrayList<Integer> resultVals = StreamParser.parseStream(stream);
        assertEquals(correctVals, resultVals);
               
    }
    
    @Test
    public void basicTestImproved() {
        String stream = "NumVals: 3 Base: 16 8e 11 12\n";
        int[] vals = new int[] {142, 17, 18};
        ArrayList<Integer> correctVals = new ArrayList<Integer>();
        for (int i = 0; i < vals.length; i++) {
            correctVals.add(vals[i]);
        }
        ArrayList<Integer> resultVals = StreamParserImproved.parseStream(stream);
        assertEquals(correctVals, resultVals);
               
    }

}
