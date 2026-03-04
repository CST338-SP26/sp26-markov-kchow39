import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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

    /**
     * getter
     * @return words
     */
    public HashMap<String, ArrayList<String>> getWords(){
        return words;
    }

    /**
     * iterates through a sentence, r
     * @return a string containing a full sentence
     */
    public String getSentence(){
        StringBuilder sb = new StringBuilder();
        String currentWord = randomWord(BEGINS_SENTENCE);
        while(true){
            if(endsWithPunctuation(currentWord)){
                sb.append(currentWord);
                break;
            } else {
                sb.append(currentWord).append(" ");
                currentWord = randomWord(currentWord);
            }
        }
        return sb.toString();
    }

    /**
     * add in the data from a file - calls addLine
     * @param filename as a string
     */
    public void addFromFile(String filename){
        try{
            File f = new File(filename);
            Scanner fs = new Scanner(f);
            while(fs.hasNextLine()){
                addLine(fs.nextLine());
            }
        }catch (IOException e){
            System.out.println("IOException");
        }
    }

    /**
     * separates the words in the line and passes them through addWords if the line isn't empty
     * @param line as a string
     */
    public void addLine(String line){
        if(line.isEmpty()){
            System.out.println("Empty line");
            return;
        }
        String[] separated = line.split(" ");
        for (String s : separated) {
            addWord(s);
        }
    }

    /**
     * adds a word to the hashmap
     * @param aWord as a String this is the current word
     */
    public void addWord(String aWord){
        if(endsWithPunctuation(prevWord)){
            words.get(BEGINS_SENTENCE).add(aWord);
        } else {
            if(words.containsKey(prevWord)){
                words.get(prevWord).add(aWord);
            } else {
                words.put(prevWord, new ArrayList<>());
            }
        }
        prevWord = aWord;
    }

    /**
     * gets a random index and uses it to return a word
     * @param aWord as a string that is a key used to retrieve a list
     * @return the random word
     */
    public String randomWord(String aWord){
        Random r = new Random();
        return words.get(aWord).get(r.nextInt(words.get(aWord).size()-1));
    }

    /**
     * loops through punctuation marks and checks if the passed in word has one of them
     * @param aWord as a string. An entire word - noly care about the last char
     * @return true if punctuation found
     */
    public static boolean endsWithPunctuation(String aWord){
        for(int i = 0; i < PUNCTUATION_MARKS.length(); i++){
            if(PUNCTUATION_MARKS.charAt(i) == aWord.charAt(aWord.length()-1)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return words.toString();
    }
}
