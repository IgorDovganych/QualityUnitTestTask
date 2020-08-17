import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class helper, needed for operations with file
 */
public class FileProcessor {

    /**
     * reads content from file
     * @param path  - path of the input file with waiting timelines and queries
     * @return content from the file
     */
    public static List<String> readFile(String path) {
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> fileLines = new ArrayList<>();
            String str;
            try {
                while ((str = br.readLine()) != null) {
                    fileLines.add(str);
                }
                return fileLines;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("an error occured while reading a line ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("an error occured while reading a file");
        }
        return null;
    }

    /**
     * writes info to file
     * @param path - path of the result file with waiting times
     * @param contentToWrite content to be written
     */

    public static void writeToFile(String path, List<String> contentToWrite) {
        try (FileWriter fw = new FileWriter(path);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String str:contentToWrite) {
                bw.write(str+System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
