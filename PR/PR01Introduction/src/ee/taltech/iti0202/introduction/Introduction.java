package ee.taltech.iti0202.introduction;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Introduction {


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        }
        if (valueOne == valueTwo * 2) {
            return "good";
        }
        if (valueTwo == valueOne * 2) {
            return "good";
        } else {
            return "ok";
        }
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        List<Integer> Even = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        return Even;
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     * <p>
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        List<Integer> evenNumbers = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                evenNumbers.add(numbers[i]);
            }
        }
        int lenght = evenNumbers.size();
        int[] finalArray = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            finalArray[i] = evenNumbers.get(i).intValue();
        }
        return finalArray;
    }

    /**
     * Method gets two Strings as parameters.
     * If two words have the same length, just put them together. If the length is
     * different, remove so many letters from the beginning of the longer word that the two words are the same length, and
     * then put them together.
     * If the first word was longer, return the answer in lower case. If the second word was longer,
     * return the answer in capital letters.
     * If both words are empty or with spaces, return FALSE.
     *
     * @param first  String
     * @param second String
     * @return String based on the values of first and second
     */
    public String findTheString(String first, String second) {
        int firstLenght = first.length();
        int secondLenght = second.length();
        if (firstLenght == secondLenght && firstLenght != 0) {
            return first + second;
        }
        if (firstLenght > secondLenght && !second.contains(" ") && secondLenght != 0) {
            return (first.substring(firstLenght - secondLenght) + second).toLowerCase(Locale.ROOT);
        }
        if (firstLenght < secondLenght && !first.contains(" ") && firstLenght != 0) {
            return (first + second.substring(secondLenght - firstLenght)).toUpperCase(Locale.ROOT);
        }
        if (firstLenght == 0 || secondLenght == 0) {
            return "FALSE";
        }
        if (first.contains(" ") && second.contains(" ") || first.contains(" ") && secondLenght == 0 || second.contains(" ") && firstLenght == 0) {
            return "FALSE";
        }
        return null;
    }


    /**
     * Method gets one String as a parameter.
     * In a given string, count the number of characters that appear exactly three times in a row.
     *
     * @param word String
     * @return The number of triples
     */
    public int countTripleChars(String word) {
        int triplets = 0;
        int wordLenght = word.length();
        while (wordLenght > 2) {
            for (int i = 0; i < wordLenght - 2; i++) {
                char chr = word.charAt(i);
                if (chr * 3 == word.charAt(i) + word.charAt(i + 1) + word.charAt(i + 2)) {
                    triplets++;
                    String word = new word.replaceAll(String.valueOf(chr * 3), "");
                }
            }
        }
        return triplets;
}

    /**
     * Run tests.
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        Introduction introduction = new Introduction();
        System.out.println(introduction.howIsOutcome(3, 6)); // "bad"
        System.out.println(introduction.howIsOutcome(1, 10));
        System.out.println(introduction.howIsOutcome(6, 12));
        System.out.println(introduction.howIsOutcome(8, 9));
        
        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 7, 5, 2, 1, 2, -2, 0));
        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]
        
        int[] array = {9, 0, 24, -6, 3};
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]

        String result = introduction.findTheString("Good", "afternoon");
        System.out.println(result);  // GOODNOON
        result = introduction.findTheString("Hello", "lo");
        System.out.println(result);  // lolo
        System.out.println(introduction.findTheString("", ""));  // FALSE
        System.out.println(introduction.findTheString("", "   "));  // FALSE
        System.out.println(introduction.findTheString("  ", "a"));  //  a  (with space in front)

        System.out.println(introduction.countTripleChars("aaabbbabbb"));  // 3
        System.out.println(introduction.countTripleChars("aaa"));  // 1
        System.out.println(introduction.countTripleChars("aaaa"));  // 0
        System.out.println(introduction.countTripleChars("aaaabbbabbbcCc"));  // 2
    }
}
