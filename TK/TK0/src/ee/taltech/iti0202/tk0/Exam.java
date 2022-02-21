package ee.taltech.iti0202.tk0;

import org.junit.platform.commons.util.StringUtils;

import java.security.Key;
import java.sql.Array;
import java.util.*;
import java.util.regex.Pattern;

public class Exam {


    /**
     * Return a list that contains the exact same numbers as the given list, but rearranged so that
     * all the even numbers come before all the odd numbers. Other than that, the numbers can be in
     * any order. You may modify and return the given list, or make a new list.
     * <p>
     * <p>
     * evenOdd([1, 0, 1, 0, 0, 1, 1]) → [0, 0, 0, 1, 1, 1, 1]
     * evenOdd([3, 3, 2]) → [2, 3, 3]
     * evenOdd([2, 2, 2]) → [2, 2, 2]
     */
    public static List<Integer> evenOdd(List<Integer> nums) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for (Integer i: nums) {
            if (i % 2 == 0) {
                resultList.add(i);
            }
        }
        for (Integer i: nums) {
            if (i % 2 != 0) {
                resultList.add(i);
            }
        }
        return resultList;
    }

    /**
     * Given 3 int values, a b c, return their sum. However, if one of the values is the same as another of the values,
     * it does not count towards the sum.
     * <p>
     * loneSum(1, 2, 3) → 6
     * loneSum(3, 2, 3) → 2
     * loneSum(3, 3, 3) → 0
     */
    public static int loneSum(int a, int b, int c) {
        int sum = 0;
        if (a != b && a != c) {
            sum += a;
        }
        if (b != a && b != c) {
            sum += b;
        }
        if (c != a && c != b) {
            sum += c;
        }
        return sum;
    }


    /**
     * A sandwich is two pieces of bread with something in between. Return the string that is between the first and
     * last appearance of "bread" in the given string, or return the empty string ""
     * if there are not two pieces of bread.
     * <p>
     * getSandwich("breadjambread") → "jam"
     * getSandwich("xxbreadjambreadyy") → "jam"
     * getSandwich("xxbreadyy") → ""
     */
    public static String getSandwich(String str) {
        String result = "";
        String p = "bread";
        int count = str.split(Pattern.quote(p), -1).length - 1;
        if (count != 2) {
            return "";
        } else {
            String newString = str.substring(str.indexOf("bread") + 5);
            return newString.substring(0,newString.indexOf("bread"));
        }
    }


    /**
     * Given a map of food keys and topping values, modify and return the map as follows: if the key
     * "ice cream" is present, set its value to "cherry". In all cases, set the key "bread"
     * to have the value "butter".
     * <p>
     * <p>
     * topping({"ice cream": "peanuts"}) → {"bread": "butter", "ice cream": "cherry"}
     * topping({}) → {"bread": "butter"}
     * topping({"pancake": "syrup"}) → {"bread": "butter", "pancake": "syrup"}
     */
    public static HashMap<String, String> topping(HashMap<String, String> map) {
        map.putIfAbsent("bread", "butter");
        map.replace("ice cream", "cherry");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(evenOdd(List.of(1, 0, 1, 0, 0, 1, 1)));
        System.out.println(getSandwich("xxbreadjambreadyy"));
        System.out.println(getSandwich("xxbreadyy"));
        System.out.println(getSandwich("breadjambread"));
    }

}