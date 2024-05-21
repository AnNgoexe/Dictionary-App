package commandline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import trie.Trie;

public class DictionaryManagement {
    private Dictionary dictionary;
    private String FileName;
    private Trie trie = new Trie();

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary dc) {
        dictionary = dc;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    /**
     * Load data from a file.
     */
    public void insertFromFile(String s) {
        FileName = s;
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String w = s0.trim();
                String pr = reader.readLine().trim();
                List<String> mean = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    line = line.trim();
                    if (line.startsWith("- ")) {
                        line = line.substring(2); 
                    }
                    mean.add(line);
                }

                dictionary.addWordForDictionary(w, pr, mean);
                trie.insert(w);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Showing all words.
     */
    public void showAll()
    {
       TreeMap<String, Word> test=dictionary.getDictionary();
       for (String key : test.keySet())
       {
            System.out.println(dictionary.getDictionary().get(key).toString());
       }
    }

    /**
     * Searching a word.
     */
    public void dictionarySearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the word you want to look up: ");
        String word = scanner.nextLine().toLowerCase();
        if (dictionary.searchWord(word)) {
            System.out.println("Explanation of the word you just entered: ");
            String a = dictionary.getDictionary().get(word).toString();
            System.out.println(a);
        } else {
            System.out.println("Sorry! The word you are looking for is not in the dictionary.");
        }
    }

    /**
     * Removing a word.
     */
    public void removeWordFromDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the word you want to remove: ");
        String word = scanner.nextLine().toLowerCase();
        dictionary.removeWord(word);
        trie.delete(word);
    }

    /**
     * Add a new word from the command line.
     */
    public void insertFromCommandLine() {
        System.out.println("Please add the word you want!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the English word: ");
        String word = scanner.nextLine();
        System.out.print("Please enter the pronunciation: ");
        String pronunciation = scanner.nextLine();
        System.out.print("Please enter the meaning of the word: ");
        String wordExp = scanner.nextLine();
        if (dictionary.searchWord(word)) {
            System.out.println("The word you want to add already exists in the dictionary! Cannot add this word.");
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.addWordForUser(word, pronunciation, wordExplain);
        trie.insert(word);
    }

    /**
     * Editing the word target.
     */
    public void editWordTargetForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to change: ");
        String oldWordTarget = scanner.nextLine().toLowerCase();
        System.out.print("You want to change it to: ");
        String newWordTarget = scanner.nextLine().toLowerCase();
        if (!dictionary.searchWord(oldWordTarget)) {
            System.out.println("The word " + oldWordTarget + " is not in the dictionary.");
            return;
        }
        if (dictionary.searchWord(newWordTarget)) {
            System.out.println("The word " + newWordTarget + " already exists in the dictionary! Cannot edit it into the dictionary.");
            return;
        }
        dictionary.getDictionary().get(oldWordTarget).setWordTarget(newWordTarget);
        trie.edit(oldWordTarget, newWordTarget);
        System.out.println("The word " + oldWordTarget + " has been changed to: " + newWordTarget + ".");
    }

    /**
     * Edit the word pronunciation.
     */
    public void editWordPronunciation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to edit the pronunciation: ");
        String wordTarget = scanner.nextLine().toLowerCase();
        System.out.print("You want to change it to: ");
        String newPronunciation = scanner.nextLine();
        if (dictionary.searchWord(wordTarget)) {
            dictionary.getDictionary().get(wordTarget).setWordPronunciation(newPronunciation);
            System.out.println("The pronunciation of the word " + wordTarget + " has been changed.");
        } else {
            System.out.println("The word " + wordTarget + " is not in the dictionary.");
        }
    }

    /**
     * Edit the word explanation.
     */
    public void editWordExplainForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to change the explanation: ");
        String word = scanner.nextLine().toLowerCase();
        System.out.print("Enter the explanation you want to change to: ");
        String wordExp = scanner.nextLine();
        if (!dictionary.searchWord(word)) {
            System.out.println("The word " + word + " is not in the dictionary.");
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.getDictionary().get(word).setWordExplain(wordExplain);
        System.out.println("The explanation of the word " + word + " has been changed!");
    }

    /**
     * Overwrite to the old file
     */
    public void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileName))) {
            for (Map.Entry<String, Word> a : dictionary.getDictionary().entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error when exporting to the file. ");
        }
    }

    /**
     * Exporting to a file.
     */
    public void dictionaryExportToFile() {
        System.out.print("Enter the path to the .txt file you want to export data to: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {
            for (Map.Entry<String, Word> a : dictionary.getDictionary().entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
            System.out.println("Successfully exported data to the file. " + s);
        } catch (IOException e) {
            System.err.println("Error when exporting to the file. " + s);
        }
    }

    /**
     * Finding words starting with a string.
     */
    public void dictionaryLookup(){
        System.out.print("Enter the string you want to look up: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toLowerCase();
        System.out.println("List of words starting with this string: ");
        List<String> result = trie.getWordsStartingWith(s);
        if (result.isEmpty()) {
            System.out.println("There are no words starting with this string.");
            return;
        }
        for (String str : result) {
            System.out.println(str);
        }
    }
}