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

public class CUE_swap {

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
        List<String> source = new ArrayList<>(100);

        for (File file : cue) {
            System.out.println(file.getName());
            source.add(file.getName());
        }
        Path path = Paths.get(source.get(0));
        boolean readable = Files.isReadable(path);
        boolean writable = Files.isWritable(path);
        System.out.println("readable " + readable + " writeable " + writable);
        List<String> cueLines = Files.readAllLines(path);
//        cueLines.forEach(System.out::println);
//        cueLines.forEach(s -> s.matches("REM"));

        String patREM = "^\\s.(REM).+\\d$";
        String patTITLE = "^\\s.(TITLE).*(\")$";

        List<String> allREM = new ArrayList<String>(100);
        for (String cueLine : cueLines) {
            {
                Matcher m = Pattern.compile(patREM)
                        .matcher(cueLine);
                while (m.find()) {
                    allREM.add(m.group());
                }
                m.reset(patTITLE);
            }
        }
        System.out.println("Size REM " + allREM.size());
        allREM.forEach(System.out::println);

        cueLines.forEach(System.out::println);

        List<String> allTITLES = new ArrayList<String>(100);
        for (String cueline : cueLines) {
            Matcher matcher = Pattern.compile(patTITLE).matcher(cueline);
//            matcher.reset(patTITLE);
            while (matcher.find()) {
//                matcher.appendReplacement(matcher.group(), )
                allTITLES.add(matcher.group());
            }
        }
        System.out.println("Size TITLE is" + allTITLES.size());
        allREM.forEach(System.out::println);


        /*      ============== JAVA 9 ================
        String[] matches = Pattern.compile("your regex here")
                .matcher("string to search from here")
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
        // or .collect(Collectors.toList())
/*

=============================
        Find.Finder finder = new Find.Finder(pattern);
        Path path = Files.walkFileTree(Paths.get("./"), finder);//Files.walkFileTree(startingDir, finder);
        finder.done();
*/


//        Path cue = Files.createFile(path);
//        System.out.println(" ZNALAZłem" + path.getFileName());

        /*=============
        List<String> nazwy = new ArrayList<>();
        try {
//            nazwy = Files.readAllLines(Paths.get("./rzecz-o-ptakach.cue"));
            nazwy = Files.readAllLines(Paths.get(source.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nazwy.forEach(System.out::println);
*/
       /* PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        Path filename = Paths.get(String.valueOf(matcher));

        if (matcher.matches(filename)) {
            System.out.println(filename);
        }*/
        /* https://www.javacodegeeks.com/2014/05/playing-with-java-8-lambdas-paths-and-files.html */
    }
}
