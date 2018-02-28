import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class W05PracticalExt {

    public static void main(String[] args) throws IOException {
        SentenceReader reader = new SentenceReader();
        List<String> sentencesInFile = new ArrayList<>();
        List<List<String>> sentenceList = new ArrayList<>();
        List<String[]> bigramsInFile = new ArrayList<>();
        List<List<String[]>> bigramsList = new ArrayList<>();
        List<ScoredResult> results = new ArrayList<>();
        DecimalFormat outputFormat = new DecimalFormat("0.0000");
        outputFormat.setRoundingMode(RoundingMode.HALF_UP);
        List<String> filepathStorage = new ArrayList<>();
        final int numberOfResults = 50;


        try {

            int numberOfDocuments = args.length - 1;


            //Adding all sentences into a list of sentences from each file
            for(int argsLoop = 0; argsLoop < numberOfDocuments; argsLoop++){
                sentencesInFile = reader.readAllSentences(args[argsLoop]);
                bigramsInFile = bigram.createBigramList(sentencesInFile);
                bigramsList.add(bigramsInFile);
                sentenceList.add(sentencesInFile);
            }


            String query = reader.sanitiseSentence(args[numberOfDocuments]);
            String[] queryBigram = bigram.createBigram(query);

            //Creating a list of sentences and its corresponding list of bigram arrays



            //Creates ScoredResults objects and adds them to the results list
            for(int i = 0; i < sentenceList.size(); i++){
                for(int j = 0; j < sentenceList.get(i).size(); j++){
                    results.add(new ScoredResult(sentenceList.get(i).get(j),bigram.createScore(bigramsList.get(i).get(j),queryBigram)));
                }
            }


            //Sorts and prints the results
            Collections.sort(results);
            try {
                for (int k = 0; k < numberOfResults; k++) {
                    System.out.println(outputFormat.format(results.get(k).getScore()) + " " + results.get(k).getResult());
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("No results found");
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java W05Practical <input_file> <query>");
            System.out.println("Multiple input files can be used in separate quote marks, ensure the query is the last term");
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


