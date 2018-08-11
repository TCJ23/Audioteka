import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Audioteka {
    public static void main(String[] args)  {

        File folder = new File("./");
        File[] files = folder.listFiles(tylkoMP3());
        List<String> source = new ArrayList<>();
        List<String> target = new ArrayList<>();

        for (File file : files)
        {
            System.out.println(file.getName());
            source.add(file.getName());
        }
        System.out.println(source.size());
        Collections.sort(source);
        System.out.println(source);
        // to do REGEX
        try {
            target = Files.readAllLines(Paths.get("./ptaki.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(target);
        for (int i = 0; i < source.size(); i++) {
            String s = source.get(i);
            String t = target.get(i);
            Path pS = Paths.get(s);
            Path pT = Paths.get(t);
            try {
                Files.copy(pS, pT, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static FilenameFilter tylkoMP3() {
        //        FilenameFilter filenameFilter = new FilenameFilter() {
        FilenameFilter filenameFilter = (dir, name) -> {
                        if (name.endsWith(".mp3")) {
                            return true;
                        } else {
                            return false;
                        }
                    };
        return filenameFilter;
    }
}
