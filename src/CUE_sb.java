import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CUE_sb {
    public static void main(String[] args) throws Exception {
        File folder = new File("./");
        File[] cue = folder.listFiles(findCUE());
        List<String> plikiCUE = new ArrayList<>();

        for (File file : cue) {
            System.out.println(file.getName());
            plikiCUE.add(file.getName());
        }
        for (int i = 0; i < plikiCUE.size(); i++) {
            Path path = Paths.get(plikiCUE.get(i));

            List<String> lines = Files.readAllLines(path);
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
                    result.add(line.substring(0, start + 1) + titles.get(count) + ".mp3" + line.substring(end));
                    count++;
                } else {
                    result.add(line);
                }
            }
            result.forEach(System.out::println);
            Files.write(path, result);
        }
    }

    private static FilenameFilter findCUE() {
        //        FilenameFilter filenameFilter = new FilenameFilter() {
        FilenameFilter filenameFilter = (dir, name) -> {
            if (name.endsWith(".cue")) {
                return true;
            } else {
                return false;
            }
        };
        return filenameFilter;
    }
}