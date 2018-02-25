import java.text.DecimalFormat;

public class bigram {



    public static String[] createBigram(String input){
        String[] bigrams = new String[input.length()];
        for(int i = 0; i < input.length()-1; i++){
            bigrams[i] = input.substring(i,i+2);
            //System.out.println(input.substring(i, i+2));
        }
        return bigrams;
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
        double union = inputBigram.length + queryBigram.length - intersection;
        double jaccard = intersection/union;
        //System.out.println(intersection);
        return jaccard;
    }


}


