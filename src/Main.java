import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String file_name = "C:\\Users\\rodion\\Desktop\\JAVA\\untitled\\src\\input.txt";

        ParserFile parserFile = new ParserFile(file_name);
        parserFile.parserFile();
        parserFile.statisticsInCsvFile("C:\\Users\\rodion\\Desktop\\JAVA\\untitled\\src\\out.txt");

    }
}
