import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Exam {
    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        Integer smallest = 1;
        Integer largest = 0;
        for (Integer i: nums) {
            if (i < smallest) {
                smallest = i;
            }
            if (i > largest) {
                largest = i;
            }
        }
        ArrayList<Integer> sumList = new ArrayList<>(nums);
        sumList.remove(smallest);
        sumList.remove(largest);
        int sum = 0;
        for (Integer i: sumList) {
            sum += i;
        }
        return sum / sumList.size();
    }
    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        final int twentyOne = 21;
        if (a > twentyOne && b > twentyOne) {
            return 0;
        } else {
            int aOr = twentyOne - a;
            int bOr = twentyOne - b;
            if (a > twentyOne) {
                return b;
            }
            if (b > twentyOne) {
                return a;
            }
            if (aOr < bOr) {
                return a;
            }
            if (bOr < aOr) {
                return b;
            }
        }
        return 0;
    }
    /**

     Given a string and an int n, return a string made of n repetitions of the last n characters
     of the string. You may assume that n is between 0 and the length of the string, inclusive.

     repeatEnd("Hello", 3) → "llollollo"
     repeatEnd("Hello", 2) → "lolo"
     repeatEnd("Hello", 1) → "o"
     */
    public static String repeatEnd(String str, int n) {
        String substring = str.substring(str.length() - n);
        return substring.repeat(n);
    }

    /**

     Modify and return the given map as follows: if the keys "a" and "b" are both in the map
     and have equal values, remove them both.
     
     mapAB({"a": "aaa", "b": "aaa", "c": "cake"}) → {"c": "cake"}
     mapAB({"a": "aaa", "b": "bbb"}) → {"a": "aaa", "b": "bbb"}
     mapAB({"a": "aaa", "b": "bbb", "c": "aaa"}) → {"a": "aaa", "b": "bbb", "c": "aaa"}
     */
    public static Map<String, String> mapAB(Map<String, String> map) {
        if (Objects.equals(map.get("a"), map.get("b"))) {
            map.remove("a");
            map.remove("b");
        }
        return map;
    }
}