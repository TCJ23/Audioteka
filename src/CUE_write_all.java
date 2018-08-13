import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class CUE_write_all {

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

        List<String> cueLines = Files.readAllLines(path);
        cueLines.forEach(System.out::println);
        Path good = Paths.get("good cue.cue");
        Files.write(good, cueLines);
        /*========= by bytes*/
//        String cuebytes = new String(Files.readAllBytes(path));
        Files.write(Paths.get("bytesCue.cue"), Files.readAllBytes(path));

    }
}
/*Files.write(Paths.get("file1.bin"), data);
        Files.write(Paths.get("file2.bin"), data,
        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        Files.write(Paths.get("file3.txt"), "content".getBytes());
        Files.write(Paths.get("file4.txt"), "content".getBytes(utf8));
        Files.write(Paths.get("file5.txt"), lines, utf8);
        Files.write(Paths.get("file6.txt"), lines, utf8,
        StandardOpenOption.CREATE, StandardOpenOption.APPEND);*/
