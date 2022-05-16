package ee.taltech.iti0202.exam;

import java.util.*;

public class Exam {

    /**
     * 01
     *
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     *
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        ArrayList<Integer> finalList = new ArrayList<>();
        int lastNum = 0;
        for (int i: nums) {
            if (i % 10 == 0) {
                finalList.add(i);
                lastNum = i;
            } else {
                if (nums.indexOf(i) == 0 || lastNum == 0) {
                    finalList.add(i);
                } else {
                    finalList.add(lastNum);
                }
            }
        }
        return finalList;
    }


    /**
     * 02
     *
     * Write a method that analyzes input String and returns all pairs of same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order. If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        StringBuilder finalPairs = new StringBuilder();
        ArrayList<Character> pairsArray = new ArrayList<>();
        ArrayList<Character> inputArray = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            inputArray.add(input.charAt(i));
        }
        for (int i = 0; i < input.length(); i++) {
            if (inputArray.contains(Character.toUpperCase(input.charAt(i))) && inputArray.contains(Character.toLowerCase(input.charAt(i)))) {
                if (!pairsArray.contains(input.charAt(i))) {
                    pairsArray.add(input.charAt(i));
                }
            }
        }
        Collections.sort(pairsArray);
        for (int i = 0; i < pairsArray.size() / 2; i++) {
            finalPairs.append(pairsArray.get(i));
            finalPairs.append(Character.toLowerCase(pairsArray.get(i)));
        }
        return finalPairs.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 0, 9, 20));
        System.out.println(tenRun(nums));
        System.out.println(mixedPairs("bBaacA"));
    }

}
