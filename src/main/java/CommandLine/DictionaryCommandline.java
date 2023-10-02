package CommandLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        HashMap<String, String> words = this.dictionaryManagement.getDictionary().getMapWord();
        List<String> sortedKeys=new ArrayList<String>(words.keySet());
        Collections.sort(sortedKeys);
        for (String wordTarget : sortedKeys) {
            String wordExplain = words.get(wordTarget);
            System.out.println(wordTarget + ": " + wordExplain);
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();

        DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);
        
        dictionaryManagement.insertFromFile("E:\\Dictionary-App\\Vocabulary.db");
        dictionaryManagement.dictionaryLookup();
        
    }
}