import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Aadam_CUE {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("test.cue"));
        List<String> titles = new ArrayList<>();
        for (String line : lines) {
            if (line.contains("TITLE")) {
                int start = line.indexOf("\"");
                int end = line.lastIndexOf("\"");
                titles.add(line.substring(start + 1, end));
            }
        }
        titles.remove(0);
        List<String> result = new ArrayList<>();
        int count = 0;
        for (String line : lines) {
            if (line.contains("REM \"")) {
                int start = line.indexOf("\"");
                int end = line.lastIndexOf("\"");
                result.add(line.substring(0, start + 1) + titles.get(count) + line.substring(end));
                count++;
            } else {
                result.add(line);
            }
        }
        result.forEach(System.out::println);
    }
}