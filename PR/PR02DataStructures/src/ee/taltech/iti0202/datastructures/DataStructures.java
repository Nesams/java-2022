package ee.taltech.iti0202.datastructures;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;


public class DataStructures {
    Map<String, Integer> students = new HashMap<>();

    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely.
     * Help you to find the word which comes alphabetically first.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        List<String> longestWords = new ArrayList<>();
        List<String> wordsList = List.of(sentence.split(" "));
        for (String word: wordsList) {
            if (longestWords.size() == 0) {
                longestWords.add(word);
            } else {
                if (word.length() < longestWords.get(0).length()) {
                    continue;
                }
                if (word.length() == longestWords.get(0).length()) {
                    longestWords.add(word);
                }
                if (word.length() > longestWords.get(0).length()) {
                    longestWords.clear();
                    longestWords.add(word);
                }
            }
        }
        Collections.sort(longestWords);
        return longestWords.get(0);
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : sentence) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        return wordsMap;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        ArrayList<String> matchWords = new ArrayList<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry: wordsMap.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                int times = entry.getValue() / 2;
                for (int i = 0; i < times; i++) {
                    matchWords.add(entry.getKey());
                }
            }
            if (entry.getValue() % 2 != 0) {
                int times = (entry.getValue() - 1) / 2;
                for (int i = 0; i < times; i++) {
                    matchWords.add(entry.getKey());
                }
            }
        }
        return matchWords;
    }

    /**
     * Method to save student and student's grade(you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        List<String> student = List.of(studentInfo.split(":"));
        List<Integer> grades = List.of(0, 1, 2, 3, 4, 5);
        Integer grade = Integer.valueOf(student.get(1));
        if (grades.contains(grade)) {
            students.put(student.get(0), grade);
        }
    }

    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the user is already added(previous function with student's info already called).
     *
     * @param name String students name
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
        return students.getOrDefault(name, -1);
    }

   /**
    * Main.
    * @param args Commend line arguments.
    */
    public static void main(String[] args) {
        System.out.println(findLongestWord("nimi on salastatud"));  // "salastatud"
        System.out.println(findLongestWord("aaa bbbbb"));  // "bbbbb"
        System.out.println(findLongestWord("hello ahllo")); // "ahllo"

        System.out.println(wordCount(new String[]{})); // empty
        System.out.println(wordCount(new String[]{"eggs", "SPAM", "eggs", "bacon", "SPAM", "bacon", "SPAM"}));

        System.out.println(onlyEvenWords(Arrays.asList("foo", "bar", "baz", "baz", "bar", "foo")));
        System.out.println(onlyEvenWords(Arrays.asList("a", "b", "b", "a"))); // [b, a] any order
        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM", "ham", "SPAM", "SPAM"))); // [SPAM]
        System.out.println(onlyEvenWords(Arrays.asList("tere", "hello", "hello", "tere", "tere")));

        DataStructures dataStructures = new DataStructures();

        dataStructures.addStudent("Ago:5");
        dataStructures.addStudent("Martin:0");
        dataStructures.addStudent("Margo:3");
        dataStructures.addStudent("Cheater:6");

        System.out.println(dataStructures.getStudentGrade("Ago")); // 5
        System.out.println(dataStructures.getStudentGrade("Martin")); // 0
        System.out.println(dataStructures.getStudentGrade("Margo")); // 3
        System.out.println(dataStructures.getStudentGrade("Cheater")); // -1
    }
}
