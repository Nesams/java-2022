package ee.taltech.iti0202.kt;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exam {
    /**
     * Given two strings,
     * find if one string is a rotation of another string.
     * Comparison should be case insensitive ("A" and "a" are the same).
     *
     * rotatedString("piimavunts", "ntspiimavu") => true
     * rotatedString("ABC", "cab") => true
     * rotatedString("kurgid", "gikur") => false
     */
    public static boolean rotatedString(String str1, String str2) {
        String s11 = str1.toUpperCase(Locale.ROOT);
        String s22 = str2.toUpperCase(Locale.ROOT);
        String fullString = s11 + s22;
        int count = 0;
        Map<String, Long> countMap = Arrays.stream(fullString.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Map.Entry<String, Long> entry: countMap.entrySet()) {
                if (entry.getValue() % 2 != 0) {
                    count++;
                }
            }
            return count == 0;
        }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     * The duplicate can overlap with the prefix (but not 100%).
     * See the last two examples.
     *
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     * prefixExistsAgain("aaaa", 3) => true
     * prefixExistsAgain("aaaa", 4) => false
     */
    public static boolean prefixExistsAgain(String str, int n) {
        String prefix = str.substring(0, n);

        int s = str.split(prefix, -1).length - 1;

        return s >= 2;
    }
    public static void main(String[] args) {
        System.out.println(rotatedString("piimavunts", "ntspiimavu"));
        System.out.println(rotatedString("ABC", "cab"));
        System.out.println(rotatedString("kurgid", "gikur"));
    }
}
