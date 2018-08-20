import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CUE_write_all {

    static int count = 0;

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

    public static void main(String[] args) throws IOException {
        File folder = new File("./");
        File[] cue = folder.listFiles(findCUE());
        List<String> source = new ArrayList<>();

        for (File file : cue) {
            System.out.println(file.getName());
            source.add(file.getName());
        }
        /* wrzucić w lupę ? */
        Path path = Paths.get(source.get(0));

        List<String> cueLines = Files.readAllLines(path);
//        cueLines.forEach(System.out::println);
/*        System.out.println("");
        Path good = Paths.get("good cue.cue");
        Files.write(good, cueLines);*/
/*
        ========= by bytes
        String cuebytes = new String(Files.readAllBytes(path));
*/
//        Files.write(Paths.get("bytesCue.cue"), Files.readAllBytes(path));

        List<String> replaceList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        for (String t : cueLines
                ) {
            if (t.contains("TITLE") && !t.startsWith("TITLE")) {
                titleList.add(t);
            }
        }
//        titleList.forEach(System.out::println);
        System.out.println("\nlista tytułów rozmiar" + titleList.size());

        for (String s : cueLines) {
            if (s.contains("REM") && !s.startsWith("REM")) {
//                replaceList.add(titleList.get(i));
//            for (int i = 0; i < titleList.size(); i++) {
                /*replaceList.add(titleList.get(0));
                titleList.remove(0);*/
//                    String title = titleList.get(i);
//                    String a = s.replace(s, title);
//                    replaceList.add(a);
                count++;
            } else {
                replaceList.add(s);

            }
//        replaceList.forEach(System.out::println);
            System.out.println("Liczba zmian: " + count);
            Files.write(Paths.get("adam.cue"), replaceList);
        }
    }
}
