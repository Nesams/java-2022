package ee.taltech.iti0202.exam;
import java.util.List;
import java.util.Objects;

public class Exam {

    /**
     * Given a list of numbers, count how many 2-s are alone (no 2 before or after it).
     *
     * countSingleTwos([2, 2, 1, 3]) => 0
     * countSingleTwos([7, 6, 1, 3]) => 0
     * countSingleTwos([2, 2, 1, 2]) => 1
     * countSingleTwos([2, 2, 2, 1, 3, 2, 1, 2]) => 2
     */
    public static int countSingleTwos(List<Integer> numbers) {
        int twos = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0) {
                if (numbers.size() != 1) {
                    if (!Objects.equals(numbers.get(i), numbers.get(i + 1)) && numbers.get(i) == 2) {
                        twos++;
                    }
                } else {
                    if (numbers.get(i) == 2) {
                        twos++;
                    }
                }
            } else {
                if (i != numbers.size() - 1) {
                    if (numbers.get(i) == 2 && numbers.get(i - 1) != 2 && numbers.get(i + 1) != 2) {
                        twos++;
                    }
                } else {
                    if (numbers.get(i) == 2 && numbers.get(i - 1) != 2) {
                        twos++;
                    }
                }
            }
        }
        return twos;
    }

    /**
     * Write a method that takes a string and decodes it.
     * The string may contain some numbers. 
     * All numbers need to be replaced with a corresponding letter from the alphabet.
     * Each number n references to n-th character of the lowercase alphabet (abcdefghijklmnopqrstuvwxyz).
     * If n is out of bounds, then it should start from "a" again. (0, 26 and 52 correspond to "a")
     *
     * Examples:
     * decodeMessage("0") => "a"
     * decodeMessage("0b2d4f6") => "abcdefg"
     * decodeMessage("h8") => "hi"
     * decodeMessage("11o11") => "lol"
     * decodeMessage("h8 th4r30 p17ogramme43") => "hi there programmer"
     * decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423") => ":o this is getting cooler"
     * decodeMessage("This one doesn't need to be changed!") => "This one doesn't need to be changed!"
     *
     * @param message the message that needs to be decoded
     * @return decoded message
     */
    public static String decodeMessage(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder number = new StringBuilder();
        StringBuilder finalString = new StringBuilder();
        Character lastChar = null;
        for (int i = 0; i < message.length(); i++) {
            Character charr = message.charAt(i);
            if (Character.isDigit(charr)) {
                if (i != message.length() - 1) {
                    lastChar = charr;
                    number.append(charr);
                } else {
                    number.append(charr);
                    int alphaNr = Integer.parseInt(number.toString()) % 26;
                    Character numberChar = alphabet.charAt(alphaNr);
                    finalString.append(numberChar);
                }
            } else {
                if (lastChar == null) {
                    finalString.append(charr);
                } else {
                    int alphaNr = Integer.parseInt(number.toString()) % 26;
                    Character numberChar = alphabet.charAt(alphaNr);
                    finalString.append(numberChar);
                    number = new StringBuilder();
                    lastChar = null;
                    finalString.append(charr);
                }
            }
        }
        return finalString.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeMessage("h8 th4r30 p17ogramme43"));
        System.out.println(decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423"));
    }
}
