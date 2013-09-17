package intparser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerParserTest {

    private void testParseInt(int e, String s, int b) {
        assertEquals(e, IntegerParser.parseInt(s, b));
    }

    private void testParseIntMagic(int e, String s, int b) {
        assertEquals(e, IntegerParser.parseIntMagic(s, b));
    }

    private void testParseIntThrow(int e, String s, int b) {
        assertEquals(e, IntegerParser.parseIntThrow(s, b));
    }

    private void testAll(int e, String s, int b) {
        testParseInt(e, s, b);
        testParseIntMagic(e, s, b);
        testParseIntThrow(e, s, b);
    }

    // Sanity check of all three implementations                                

    @Test public void testAllSimple10() { testAll(1234, "1234", 10); }

    // Checking basic implementation                                            
    @Test public void test1_10_a_16() { testParseInt(10, "a", 16); }
    @Test public void test1_15_f_16() { testParseInt(15, "f", 16); }
    @Test public void test1_16_g_17() { testParseInt(16, "g", 17); }
    @Test public void test1_2_10_2() { testParseInt(2, "10", 2); }
    @Test public void test1_3_11_2() { testParseInt(3, "11", 2); }
    @Test public void test1_4_100_2() { testParseInt(4, "100", 2); }
    @Test public void test1_4312_4312_10() { testParseInt(4312, "4312", 10); }

    // Checking "magic" implementation                                          
    @Test public void test2_10_a_16() { testParseIntMagic(10, "a", 16); }
    @Test public void test2_15_f_16() { testParseIntMagic(15, "f", 16); }
    @Test public void test2_16_g_17() { testParseIntMagic(16, "g", 17); }
    @Test public void test2_2_10_2() { testParseIntMagic(2, "10", 2); }
    @Test public void test2_3_11_2() { testParseIntMagic(3, "11", 2); }
    @Test public void test2_4_100_2() { testParseIntMagic(4, "100", 2); }
    @Test public void test2_4312_4312_10() { testParseIntMagic(4312, "4312", 10); }

    // Checking "throw" implementation                                          
    @Test public void test3_10_a_16() { testParseIntThrow(10, "a", 16); }
    @Test public void test3_15_f_16() { testParseIntThrow(15, "f", 16); }
    @Test public void test3_16_g_17() { testParseIntThrow(16, "g", 17); }
    @Test public void test3_2_10_2() { testParseIntThrow(2, "10", 2); }
    @Test public void test3_3_11_2() { testParseIntThrow(3, "11", 2); }
    @Test public void test3_4_100_2() { testParseIntThrow(4, "100", 2); }
    @Test public void test3_4312_4312_10() { testParseIntThrow(4312, "4312", 10); }

    // What is missing in this test suite?                                      
}