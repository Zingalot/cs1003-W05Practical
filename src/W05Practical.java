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
        DecimalFormat outputFormat = new DecimalFormat("0.0000");

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
            for(int k = 0; k < 50; k++){
                System.out.println(outputFormat.format(results.get(k).getScore()) + " " + results.get(k).getResult());
            }



            //System.out.println(bigramTest.get(0)[0]);
            //calculateScores();




        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java W05Practical <input_file> <query>");
        }
        /*//Test for bigram creator
        String[] testBigram = bigram.createBigram(String.valueOf(results.get(1).getResult()));
        for(int p = 0; p < testBigram.length-1; p++){
            System.out.print(testBigram[p] + "||");
        }
        System.out.print(" " + (testBigram.length-1));
        System.out.println();
        String[] testBigram2 = bigram.createBigram("happy alice");
        for(int p = 0; p < testBigram2.length-1; p++){
            System.out.print(testBigram2[p] + "||");
        }
        System.out.print(" " + (testBigram2.length-1));
        System.out.println();
        int intersection = 0;
        for(int i = 0; i < testBigram.length-1; i++ ){
            for(int j = 0; j < testBigram2.length-1; j++){
                if(testBigram[i].equals(testBigram2[j])){
                    intersection++;
                }
            }
        }
        System.out.println(intersection);*/


    }
}
