import java.util.ArrayList;
import java.util.List;


public class StreamParser {
    final static String numvalString = "NumVals: ";
    final static String baseString = "Base: ";
    
    /**
     * Takes in a stream with the number of integers that will be encountered, optionally the base, and then a series
     * of integers. Returns them as a list of Java Integers.
     * @param inputStream: text of the format "NumVals: [base 10 integer] [integer] [integer] ... [integer]\n"
     *                      or "NumVals: [base 10 integer] Base: [base 10 integer] [integer] [integer] ... [integer]\n"
     *                      If "Base: [integer]" is not included, then base 16 is assumed for the integers.
     * @return An ArrayList<Integer> which contains all the integers in the stream (past the metadata) as Java Integers.
     */
    public static ArrayList<Integer> parseStream(String inputStream) {
        
        // find the number of values we will see first.
        int intStart = numvalString.length();
        int curIndex = intStart;
        char curChar = inputStream.charAt(curIndex);
        // parse based on the space.
        while (!Character.isWhitespace(curChar)) {
            curIndex++;
            curChar = inputStream.charAt(curIndex);
        }
        int intEnd = curIndex;
        String intString = inputStream.substring(intStart, intEnd);
        // parse the text integer into an int, base 10.
        int numVals = 0;
        int nextVal = 0;
        for (int i = 0; i < intString.length(); i++) {
            char nextChar = intString.charAt(i);
            if ('0' <= nextChar && nextChar <= '9') {
                nextVal = nextChar - '0';
            } else if ('a' <= nextChar && nextChar <= 'z') {
                nextVal = 10 + nextChar - 'a';
            } else if ('A' <= nextChar && nextChar <= 'Z') {
                nextVal = 10 + nextChar - 'A';
            }
            numVals = numVals * 10 + nextVal;
        }
        
        int base = 16;
        int baseIntEnd = intEnd;
        // find the base if it exists
        if (baseString.equals(inputStream.substring(intEnd+1, intEnd + 1 + baseString.length()))) {
            int baseIntStart = intEnd + 1 + baseString.length();
            curIndex = baseIntStart;
            curChar = inputStream.charAt(curIndex);
            // paste based on whitespace
            while (!Character.isWhitespace(curChar)) {
                curIndex++;
                curChar = inputStream.charAt(curIndex);
            }
            baseIntEnd = curIndex;
            String baseIntString = inputStream.substring(baseIntStart, baseIntEnd);
            // parse string into base 10 int
            base = 0;
            nextVal = 0;
            for (int i = 0; i < baseIntString.length(); i++) {
                char nextChar = baseIntString.charAt(i);
                if ('0' <= nextChar && nextChar <= '9') {
                    nextVal = nextChar - '0';
                } else if ('a' <= nextChar && nextChar <= 'z') {
                    nextVal = 10 + nextChar - 'a';
                } else if ('A' <= nextChar && nextChar <= 'Z') {
                    nextVal = 10 + nextChar - 'A';
                }
                base = base * 10 + nextVal;
            }
            
        }
        
        ArrayList<Integer> intVals = new ArrayList<Integer>();
        curIndex = baseIntEnd;
        int prevEnd;
        // interate for the number of values, finding all ints
        for (int s = 0; s < numVals; s++) {
            curIndex++;
            curChar = inputStream.charAt(curIndex);
            prevEnd = curIndex;
            while (!Character.isWhitespace(curChar)) {
                curIndex++;
                curChar = inputStream.charAt(curIndex);
            }
            intString = inputStream.substring(prevEnd, curIndex);
            int number = 0;
            nextVal = 0;
            // parse strings into ints, assuming specified base
            for (int i = 0; i < intString.length(); i++) {
                char nextChar = intString.charAt(i);
                if ('0' <= nextChar && nextChar <= '9') {
                    nextVal = nextChar - '0';
                } else if ('a' <= nextChar && nextChar <= 'z') {
                    nextVal = 10 + nextChar - 'a';
                } else if ('A' <= nextChar && nextChar <= 'Z') {
                    nextVal = 10 + nextChar - 'A';
                }
                number = number * base + nextVal;
            }
            intVals.add(number);
        }
        return intVals;
    }
}
