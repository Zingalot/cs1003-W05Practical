import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class bigram {



    public static String[] createBigram(String input){

        String[] bigrams = new String[input.length()];
        for(int i = 0; i < input.length()-1; i++){
            bigrams[i] = input.substring(i,i+2);
            //System.out.println(input.substring(i, i+2));
        }
        Set<String> uniqueForce = new LinkedHashSet<>(Arrays.asList(bigrams));
        String[] forReturn = uniqueForce.toArray(new String[0]);

        return forReturn;
    }

    public static double createScore(String[] inputBigram, String[] queryBigram){

        double intersection = 0;
        for(int i = 0; i < inputBigram.length-1; i++ ){
            for(int j = 0; j < queryBigram.length-1; j++){
                if(inputBigram[i].equals(queryBigram[j])){
                    intersection++;
                }
            }
        }
        double union = (inputBigram.length-1) + (queryBigram.length-1) - intersection;
        double jaccard = intersection/union;
        //System.out.println(intersection);
        return jaccard;
    }


}


