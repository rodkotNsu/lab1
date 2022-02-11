import com.sun.jdi.event.ExceptionEvent;

import java.io.*;
import java.util.*;

public class ParserFile {

    static public final Set<Character> STANDARD_ALLOWED_CHARACTERS = Set.of('-', '`');

    private final String fileName;
    private boolean isOpen = false;
    private Reader reader = null;
    private final SetWordCounter table = new SetWordCounter();

    public ParserFile(String file_name) {
        this.fileName = file_name;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public SetWordCounter getTable() {
        return table;
    }

    public void openFile() {
        if (!isOpen) {
            try {
                reader = new InputStreamReader(new FileInputStream(fileName));
                isOpen = true;
            } catch (FileNotFoundException e) {
                isOpen = false;
                e.printStackTrace();
            }
        }
    }

    public void parserFile(Set<Character> allowedCharacters) {
        openFile();
        if (isOpen) {
            char symbol = 0;
            char flagAllowedChar = 0;

            StringBuilder buffer = new StringBuilder();
            try {
                while (reader.ready()) {
                    symbol = (char) reader.read();

                    if (Character.isLetterOrDigit(symbol) || allowedCharacters.contains(symbol)) {
                        if (!allowedCharacters.contains(symbol)) {
                            if (flagAllowedChar > 0)
                                buffer.append(flagAllowedChar);
                            buffer.append(symbol);
                            flagAllowedChar = 0;
                        } else if (!buffer.isEmpty()) {
                            flagAllowedChar = symbol;
                        }

                    } else if (!buffer.isEmpty()) {
                        table.insert(buffer.toString());
                        buffer = new StringBuilder();
                    }
                }
                if (!buffer.isEmpty()) {
                    table.insert(buffer.toString());
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can not parser file");
        }
    }

    public void parserFile() {
        parserFile(STANDARD_ALLOWED_CHARACTERS);
    }

    public void statisticsInCsvFile(String path_file) {
        try {
            FileWriter fileWriter = new FileWriter(path_file, false);
            List<WordCounter> counterList = table.getSortedList();
            for (WordCounter wordCounter : counterList) {
                fileWriter.write(table.wordCounterInCsv(wordCounter) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
