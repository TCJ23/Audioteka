import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // comment out while loop for single execution with ===>
        while (true) {
            System.out.printf("%nEnter your regex: ");
            Pattern pattern =
                    Pattern.compile(scanner.nextLine());
            System.out.printf("Enter input string to search: ");
            Matcher matcher =
                    pattern.matcher(scanner.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.printf("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                found = true;
            }
            if (!found) {
                System.out.println(("No match found.%n"));
            }
            // <=== this line
//            scanner.close();
        }
    }
}
/*      Greedy 	Reluctant 	Possessive 	Meaning
        X? 	X?? 	X?+ 	X, once or not at all
        X* 	X*? 	X*+ 	X, zero or more times
        X+ 	X+? 	X++ 	X, one or more times
        X{n} 	X{n}? 	X{n}+ 	X, exactly n times
        X{n,} 	X{n,}? 	X{n,}+ 	X, at least n times
        X{n,m} 	X{n,m}? 	X{n,m}+ 	X, at least n but not more than m times*/
