import java.util.ArrayList;
import java.util.HashMap;

public class Markov {
    private static final String BEGINS_SENTENCE = "__$";
    private static final String PUNCTUATION_MARKS = ".!?$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    public Markov(){
        words = new HashMap<>();
        words.put(BEGINS_SENTENCE, new ArrayList<>());
        prevWord = BEGINS_SENTENCE;
    }

    public HashMap<String, ArrayList<String>> getWords(){
        return null;
    }

    public String getSentence(){
        return null;
    }

    public void addFromFile(String filename){

    }

    public void addLine(String line){

    }

    public void addWord(String aWord){

    }

    public String randomWord(String aWord){
        return null;
    }

    public static boolean endsWithPunctuation(String aWord){
        return false;
    }

    public String toString(){
        return null;
    }
}
