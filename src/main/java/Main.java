import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = readFile("src/main/resources/input.txt");
        show(list);
    }

    private static List<String> readFile(String path){
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> fileLines = new ArrayList<>();
            String str;
            try {
                while ((str=br.readLine())!=null){
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

    private static void show(List<String> list){
        for (String str:list) {
            System.out.println(str);
        }
    }
}
