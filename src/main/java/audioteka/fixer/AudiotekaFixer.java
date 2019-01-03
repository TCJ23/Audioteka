package audioteka.fixer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudiotekaFixer {
    static String PAT_REM = "\\s.(REM).+?\\\"(.+)\\\"";
    static String PAT_TITLE = "\\s+?(TITLE).+?\\\"(.+)\\\"";

    public static void main(String[] args) throws IOException {
        File folder = new File("./");
        File[] cues = folder.listFiles((dir, name) -> name.endsWith(".cue"));
        List<String> plikiCUE = findCueFiles(cues);

        for (int i = 0; i < plikiCUE.size(); i++) {
            Path path = Paths.get(plikiCUE.get(i));
            List<String> linie = Files.readAllLines(path);
            Files.write(path, swapRemTitles(linie));
            Runtime.getRuntime().exec("./AudiotekaSplit.exe", null, new File("./"));
//                Files.delete(path);
        }
    }

    private static List<String> findCueFiles(File[] cues) {
        List<String> plikiCUE = new ArrayList<>();
        for (File plik : cues
                ) {
            plikiCUE.add(plik.getName());
            System.out.println("Znalazłem takie plik z rodziałami " + plik.getName());
        }
        return plikiCUE;
    }

    private static List<String> swapRemTitles(List<String> linie) {
        List<String> zamiana = new ArrayList<>();
        Pattern remPatern = Pattern.compile(PAT_REM);
        int index = 0;
        for (String linia : linie) {
            StringBuffer sbLinia = new StringBuffer(linia);
            Matcher matchREM = remPatern.matcher(linia);
            if (matchREM.find()) {
                int start = sbLinia.indexOf("\"");
                int end = sbLinia.lastIndexOf("\"");
                zamiana.add(sbLinia.substring(0, start + 1)
                        + getCorrectTitles(linie).get(index) + ".mp3" + sbLinia.substring(end));
                index++;
            } else zamiana.add(linia);
        }
        System.out.println("\n Wprowadziłem następujące zmiany ");
        zamiana.forEach(s -> System.out.println(s));
        return zamiana;
    }

    private static List<String> getCorrectTitles(List<String> linie) {
        List<String> titles = new ArrayList<>();
        Pattern titlesPattern = Pattern.compile(PAT_TITLE);
        for (String linia : linie
                ) {
            Matcher matchTitle = titlesPattern.matcher(linia);
            while (matchTitle.find()) {
                titles.add(matchTitle.group(2));
            }
        }
        System.out.println("\n Znalazłem następujące tytuły rozdziałów: ");
        titles.forEach(s -> System.out.println(s));
        return titles;
    }
}
