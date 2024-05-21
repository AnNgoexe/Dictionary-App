package commandline;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class Hangman {
    private String[] hangmanImage = {
            "  -----\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=======",

            "  -----\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "======="
    };

    public void game() {
        List<String> words = new ArrayList<>();
        DictionaryManagement d = new DictionaryManagement();
        d.insertFromFile("src/main/resources/cmdline/Vocabulary.txt");
        Dictionary dictionary = d.getDictionary();
        TreeMap<String, Word> td = dictionary.getDictionary();
        for (String word : td.keySet()) {
            words.add(word);
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        char[] guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '-');
        int attempts = 5;

        Scanner scanner = new Scanner(System.in);
        System.out.println(hangmanImage[0]); 

        while (attempts > 0 && new String(guessedWord).contains("-")) {
            System.out.println("Guessed word: " + new String(guessedWord));
            System.out.println("Attempts left: " + attempts);
            System.out.print("Guess a letter: ");
            char guess = scanner.nextLine().charAt(0);

            if (word.contains(String.valueOf(guess))) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        guessedWord[i] = guess;
                        System.out.println(hangmanImage[5 - attempts + 1]);
                    }
                }
            } else {
                attempts--;
                System.out.println(hangmanImage[5 - attempts + 1]); 
            }
        }

        if (attempts > 0) {
            System.out.println("Congratulations! You've guessed the word: " + word);
        } else {
            System.out.println("Sorry, you've run out of attempts. The word was: " + word);
        }

    }
}
