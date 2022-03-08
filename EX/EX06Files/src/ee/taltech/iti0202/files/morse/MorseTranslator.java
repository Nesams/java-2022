package ee.taltech.iti0202.files.morse;
import java.util.*;


public class MorseTranslator {
    Map<String, String> charMorse;
    Map<String, String> morseChar;
    public MorseTranslator() {
        this.charMorse = new HashMap<>();
        this.morseChar = new HashMap<>();
    }

    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String l:lines) {
            List<String> splitted = List.of(l.split(" "));
            this.charMorse.put(splitted.get(0).toLowerCase(Locale.ROOT), splitted.get(1));
            this.morseChar.put(splitted.get(1), splitted.get(0).toLowerCase(Locale.ROOT));
        }
        return charMorse;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morse = new ArrayList<>();
        for (String l: lines) {
            morse.add(translateLineToMorse(l));
        }
        return morse;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> words = new ArrayList<>();
        for (String l: lines) {
            words.add(translateLineFromMorse(l));
        }
        return words;
    }

    private String translateLineToMorse(String line) {
        StringBuilder finalMorse = new StringBuilder();
        for (char character: line.toLowerCase(Locale.ROOT).toCharArray()) {
            String sCharacter = Character.toString(character);
            if (sCharacter.equals(" ")) {
                finalMorse.deleteCharAt(finalMorse.length() - 1);
                finalMorse.append("\t");
            } else {
                finalMorse.append(this.charMorse.get(sCharacter));
                finalMorse.append(" ");
            }
        }
        finalMorse.deleteCharAt(finalMorse.length() - 1);
        return finalMorse.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder finalLine = new StringBuilder();
        for (String l: line.split("\t")) {
            for (String s: l.split(" ")) {
                finalLine.append(morseChar.get(s));
            }
            finalLine.append(" ");
        }
        finalLine.deleteCharAt(finalLine.length() - 1);
        return finalLine.toString();
    }
}
