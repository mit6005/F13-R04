import java.util.ArrayList;
import java.util.List;

public class StreamParserImproved {

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
        
        // parse the number of values
        int startIndex = numvalString.length();
        int endIndex = findEndIndex(startIndex, inputStream);
        int numVals = parseIntFromLocations(startIndex, endIndex, inputStream,
                10);

        // parse the base if it exists, otherwise is 16
        int base = 16;
        if (baseString.equals(inputStream.substring(endIndex + 1, endIndex + 1
                + baseString.length()))) {
            
            startIndex = endIndex + 1 + baseString.length();
            endIndex = findEndIndex(startIndex, inputStream);
            base = parseIntFromLocations(startIndex, endIndex, inputStream, 10);
        }

        
        // iterate, parsing each int
        ArrayList<Integer> intVals = new ArrayList<Integer>();
        startIndex = endIndex;
        for (int s = 0; s < numVals; s++) {
            startIndex = endIndex + 1;
            endIndex = findEndIndex(startIndex, inputStream);
            int number = parseIntFromLocations(startIndex, endIndex,
                    inputStream, base);
            intVals.add(number);
        }
        return intVals;
    }

    // takes the start and end locations of a string, and parses the integer
    private static int parseIntFromLocations(int startIndex, int endIndex,
            String inputStream, int base) {
        String intString = inputStream.substring(startIndex, endIndex);
        return parseInt(intString, base);
    }

    
    // finds the end of an integer given the start
    private static int findEndIndex(int searchStart, String inputStream) {
        int curIndex = searchStart;
        char curChar = inputStream.charAt(curIndex);
        while (!Character.isWhitespace(curChar)) {
            curIndex++;
            curChar = inputStream.charAt(curIndex);
        }
        return curIndex;
    }

    
    // using a base and string version of an integer, parse and integer
    private static int parseInt(String intString, int base) {
        int number = 0;
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
            number = number * base + nextVal;
        }
        return number;
    }
}
