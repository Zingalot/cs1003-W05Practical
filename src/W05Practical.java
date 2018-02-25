import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class W05Practical {
    public static void main(String[] args) throws IOException {
        SentenceReader reader = new SentenceReader();
        List<String> sentences = new ArrayList<>();
        List<ScoredResult> results = new ArrayList<>();
        List<String[]> bigramTest = new ArrayList<>();
        DecimalFormat outputFormat = new DecimalFormat("#.####");

        try {

            //Giving arguments variable names
            String filepath = args[0];
            String query = reader.sanitiseSentence(args[1]);
            String[] queryBigram = bigram.createBigram(query);


            sentences = reader.readAllSentences(filepath);
            for(int i = 0; i<sentences.size(); i++){
                bigramTest.add(bigram.createBigram(sentences.get(i)));
            }

            for(int j = 0; j < bigramTest.size(); j++){
                //System.out.println(sentences.get(j));
                //System.out.println(bigram.createScore(bigramTest.get(j), queryBigram));
                results.add(new ScoredResult(sentences.get(j),bigram.createScore(bigramTest.get(j),queryBigram)));
            }
            Collections.sort(results);
            for(int k = 0; k < results.size(); k++){
                System.out.println(outputFormat.format(results.get(k).getScore()) + " " + results.get(k).getResult());
            }



            //System.out.println(bigramTest.get(0)[0]);
            //calculateScores();




        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java W05Practical <input_file> <query>");
        }

    }
}
