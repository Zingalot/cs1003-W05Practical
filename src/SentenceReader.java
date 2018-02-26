
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;

public class SentenceReader {

    /**
     * Given a file path, this method will read the entire contents of the file,
     * split the text into sentences, and return a list of sentences.
     *
     * The sentence splitting is very basic: we just split on the full-stop character.
     *
     * The contents of each sentence are sanitised as well.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param filepath The file path for the input file
     * @return A list of all sentences in the file
     * @throws IOException May throw an IOException while reading the file
     */
    public List<String> readAllSentences(String filepath) throws IOException {

        int numberOfLines = 0;
        List<String> contents = new ArrayList<>();
        try {
            //Creating the scanner to read data from the text file
            Scanner scanner = new Scanner(new File(filepath));

            //Delimits data from the scanner with empty lines and a full stop to ensure that whole sentences are read
            scanner.useDelimiter("\\.");
            //scanner.useDelimiter("\\n\\s|\\.\\s");
            //While the scanner still has data stored
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (line.isEmpty() || line.equals("\r") || line.equals("\n")) {//Ensuring that an empty line is not included
                    continue;
                }
                    //Add a sanitised sentence to the arraylist
                    contents.add(sanitiseSentence(line));




                //System.out.println(line);
            }

        } catch (IOException e) { //Catches the case where the input file cannot be accessed
            System.out.println("Input file not found, check file name and location");

        } catch (NumberFormatException f) { //Catches some of the cases where the file does not fit the expected format
            System.out.println("Incorrect Input Format");
        }
        return contents;
    }

    /**
     * Given a string, this method will sanitise it.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param sentence The input string
     * @return The output string
     */
    public String sanitiseSentence(String sentence) {
        List<String> words = new ArrayList<>();
        for (String word : sentence.split("\\s+")) {
            String sanitised = word.toLowerCase().replaceAll("[^a-z]+", "");
            if (!sanitised.isEmpty()) {
                words.add(sanitised);
            }
        }

        return joinWords(words);
    }

    /**
     * This is a private method to join a list of words into a sentence.
     *
     * @param words The list of words
     * @return A string which contains the words separated by spaces
     */
    private String joinWords(List<String> words) {
        String joined = "";
        if (words.size() > 0) {
            joined = words.get(0);
        }
        for (int i = 1; i < words.size(); i++) {
            joined += " " + words.get(i);
        }
        return joined;
    }

}
