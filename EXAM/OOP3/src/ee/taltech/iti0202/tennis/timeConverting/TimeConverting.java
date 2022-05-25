package ee.taltech.iti0202.tennis.timeConverting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TimeConverting {
    public TimeConverting(){}

    public List<Integer> convertTimeStringToList(String time) {
        List<String> stringList = Arrays.stream(time.split(":")).toList();
        List<Integer> integerList = new ArrayList<>();
        for (String string:stringList) {
            integerList.add(Integer.parseInt(string));
        }
        return integerList;
    }
    public List<Integer> getTimePeriod(String start, String end) {
        int startInt = convertTimeStringToList(start).get(0);
        int endInt = convertTimeStringToList(end).get(0);
        List<Integer> timePeriodList = new ArrayList<>();
        for (int i = startInt - 1; i < endInt; i++) {
            timePeriodList.add(i + 1);
        }
        return timePeriodList;
    }
}
