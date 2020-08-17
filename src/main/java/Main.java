

import java.util.List;


public class Main {

    /**
     * main method
     * result of the execution you can see in output.txt into resource folder
     */
    public static void main(String[] args) {
        List<String> list = FileProcessor.readFile("src/main/resources/input.txt");
        show(list);
        List<String> waitingTimes = Analyser.findWaitingTimelines(list);
        show(waitingTimes);
        FileProcessor.writeToFile("src/main/resources/output.txt", waitingTimes);
    }

    /**
     * prints elements of list
     * @param list
     */
    private static void show(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("==============================");
    }
}
