import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CUE_swap {

    private static final String pattern = "*.cue";

    public static void main(String[] args) throws IOException {

        Find.Finder finder = new Find.Finder(pattern);
        Path path = Files.walkFileTree(Paths.get("./"), finder);//Files.walkFileTree(startingDir, finder);
        finder.done();
        Path fileName = Paths.get(path.getFileName().toString());


        List<String> nazwy = new ArrayList<>();
        try {
            nazwy = Files.readAllLines(Paths.get("./rzecz-o-ptakach.cue"));
//            nazwy = Files.readAllLines(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nazwy.forEach(System.out::println);
       /* PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        Path filename = Paths.get(String.valueOf(matcher));

        if (matcher.matches(filename)) {
            System.out.println(filename);
        }*/
/* https://www.javacodegeeks.com/2014/05/playing-with-java-8-lambdas-paths-and-files.html */
    }
}
