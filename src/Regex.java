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

    private static FilenameFilter findCUE() {
        FilenameFilter filenameFilter = (dir, name) -> {
            return name.endsWith(".cue");
        };
        return filenameFilter;
    }

    public static void main(String[] args) throws IOException {
        File folder = new File("./");
        File[] cues = folder.listFiles(findCUE());
        List<String> plikiCUE = new ArrayList<>();

        for (File plik : cues
                ) {
            plikiCUE.add(plik.getName());
            System.out.println("Znalazłem takie pliki z rodziałami " + plik.getName());
        }

        for (int i = 0; i < plikiCUE.size(); i++) {
            Path path = Paths.get(plikiCUE.get(i));
            List<String> linie = Files.readAllLines(path);
            List<String> tytuły = new ArrayList<>();
            for (String linia : linie
                    ) {
                Matcher matcher = Pattern.compile(patTITLE).matcher(linia);
                Matcher matcher2 = Pattern.compile(patREM).matcher(linia);
                while (matcher.find()) {
                    tytuły.add(matcher.group(2));
                }
                while (matcher2.find()) {
                    tytuły.add(matcher2.group(2));
                }
            }
//            tytuły.remove(0);

            for (String tytuł : tytuły
                    ) {
                if (tytuł == tytuły.get(tytuły.size() - 1)) {
                    System.out.println("*********************");
                } else
                    System.out.println(tytuł);
//                for (tytuły.get(tytuły.size() - 1);  {
            }
        }
//            tytuły.forEach(System.out::println);
    }
}


   /* String lineOfText = "if(getip(document.referrer)==\"www.eg.com\" || getip(document.referrer)==\"192.57.42.11\"";

    String[] valuesInQuotes = StringUtils.substringsBetween(lineOfText, "\"", "\"");

    assertThat(valuesInQuotes[0], is("www.eg.com"));
    assertThat(valuesInQuotes[1],is("192.57.42.11"));*/