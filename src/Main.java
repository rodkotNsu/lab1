import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            String file_name = args[0];
            ParserFile parserFile = new ParserFile(file_name);
            parserFile.parserFile();
            parserFile.statisticsInCsvFile("statistic.csv");
        }
    }
}
