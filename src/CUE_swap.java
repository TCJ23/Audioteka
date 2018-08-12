import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CUE_swap {

    private static final String pattern = "*.cue";

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
        Path path = Paths.get(source.get(0));
        boolean readable = Files.isReadable(path);
        boolean writable = Files.isWritable(path);
        System.out.println("readable " + readable + " writeable " + writable);
        List<String> cueLines = Files.readAllLines(path);
//        cueLines.forEach(System.out::println);
//        cueLines.forEach(s -> s.matches("REM"));

        String patREM = "^\\s.(REM).+\\d$";
        String patTITLE = "^\\s.(TITLE).*(\")$";

        for (String cueLine : cueLines) {
            cueLine.
//            if (!cueLine.matches("^\\s.(REM)")) ;
            if (!cueLine.matches("^\\S")) ;
            {
//                int i = cueLines
                System.out.println(cueLine);
            }
        }
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
