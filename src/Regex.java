import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    static String patREM = "\\s.(REM).+?\\\"(.+)\\\"";
    static String patTITLE = "\\s+?(TITLE).+?\\\"(.+)\\\"";

    public static void main(String[] args) throws IOException {
        File folder = new File("./");
        File[] cues = folder.listFiles(filter_CUE_ext());
        List<String> plikiCUE = find_CUE_files(cues);

        for (int i = 0; i < plikiCUE.size(); i++) {
            Path path = Paths.get(plikiCUE.get(i));
            List<String> linie = Files.readAllLines(path);
            Files.write(path, changeREM_to_Titles(linie));
        }
    }

    private static FilenameFilter filter_CUE_ext() {
        FilenameFilter filenameFilter = (dir, name) -> {
            return name.endsWith(".cue");
        };
        return filenameFilter;
    }

    private static List<String> find_CUE_files(File[] cues) {
        List<String> plikiCUE = new ArrayList<>();
        for (File plik : cues
                ) {
            plikiCUE.add(plik.getName());
            System.out.println("Znalazłem takie plik z rodziałami " + plik.getName());
        }
        return plikiCUE;
    }

    private static List<String> changeREM_to_Titles(List<String> linie) {
        List<String> zamiana = new ArrayList<>();
        Pattern remPatern = Pattern.compile(patREM);
        int index = 0;
        for (String linia : linie) {
            Matcher matchREM = remPatern.matcher(linia);
            if (matchREM.find()) {
                int start = linia.indexOf("\"");
                int end = linia.lastIndexOf("\"");
                zamiana.add(linia.substring(0, start + 1)
                        + getCorrectTitles(linie).get(index) + ".mp3" + linia.substring(end));
                index++;
            } else zamiana.add(linia);
        }
        System.out.println("\n Wprowadziłem następujące zmiany ");
        zamiana.forEach(s -> System.out.println(s));
        return zamiana;
    }

    private static List<String> getCorrectTitles(List<String> linie) {
        List<String> tytuły = new ArrayList<>();
        Pattern titlesPattern = Pattern.compile(patTITLE);
        for (String linia : linie
                ) {
            Matcher matchTitle = titlesPattern.matcher(linia);
            while (matchTitle.find()) {
                tytuły.add(matchTitle.group(2));
            }
        }
        System.out.println("\n Znalazłem następujące tytuły rozdziałów: ");
        tytuły.forEach(s -> System.out.println(s));
        return tytuły;
    }
}

