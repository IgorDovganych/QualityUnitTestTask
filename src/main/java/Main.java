
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<String> list = FileProcessor.readFile("src/main/resources/input.txt");
        show(list);
        List<String> waitingTimes = Analyser.findWaitingTimelines(list);
        show(waitingTimes);
        FileProcessor.writeToFile("src/main/resources/output.txt", waitingTimes);
    }


    private static void show(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }
}
