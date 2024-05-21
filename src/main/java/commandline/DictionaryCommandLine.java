package commandline;

import java.util.Scanner;

public class DictionaryCommandLine {
    public void dictionaryAdvanced() {
        DictionaryManagement test = new DictionaryManagement();
        boolean tt = true;
        test.insertFromFile("src/main/resources/data/Vocabulary.txt");
        while (tt) {
            System.out.println("Welcome to my application!");
            System.out.println("[0]: Exit");
            System.out.println("[1]: Add a word to the dictionary");
            System.out.println("[2]: Remove a word from the dictionary");
            System.out.println("[3]: Update for a word in the dictionary");
            System.out.println("[4]: Display all words in the dictionary");
            System.out.println("[5]: Search for a word in the dictionary");
            System.out.println("[6]: Lookup words in the dictionary");
            System.out.println("[7]: Game");
            System.out.println("[8]: Export dictionary to file");
            System.out.println("[9]: Save dictionary to file");
            System.out.print("Your choice: ");
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 1;
            }
            switch (choice) {
                case (0):
                    sc.close();
                    tt = false;
                    break;
                case (1):
                    test.insertFromCommandLine();
                    break;
                case (2):
                    test.removeWordFromDictionary();
                    break;
                case (3):
                    System.out.println("Do you want to edit:\n" +
                            "1. The Word Target\n" +
                            "2.The Word Pronunciation\n"
                            + "3. The Word Explain\n");
                    System.out.print("Your choice: ");
                    int edit = sc.nextInt();
                    switch (edit) {
                        case (1):
                            test.editWordTargetForUser();
                            break;
                        case (2):
                            test.editWordPronunciation();
                            break;
                        case (3):
                            test.editWordExplainForUser();
                            break;
                        default:
                            System.out.println("Wrong choice!");
                            break;
                    }
                    break;
                case (4):
                    test.showAll();
                    break;
                case (5):
                    test.dictionarySearch();
                    break;
                case (6):
                    test.dictionaryLookup();
                    break;
                case (7):
                    Hangman gg = new Hangman();
                    gg.game();
                    break;
                case (8):
                    test.dictionaryExportToFile();
                    break;
                case (9):
                    test.saveDictionary();
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }
        }
    }
}
