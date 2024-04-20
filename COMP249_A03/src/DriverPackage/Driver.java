package DriverPackage;

import java.io.*;
/**
 * @author Lan Thi Duong (ID: 40276821) and Hiba Maifi (ID: 40289223)
 * COMP249 - Assignment #3
 * Monday, April 15th
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

  // Method reads the file and returns an array of topics
  public static ArrayList<String> topicFromFile(String file) {
    Scanner inputFile = null;
    String line = "";
    int j = 0;
    String[] lineArr = new String[399];
    ArrayList<String> topicArr = new ArrayList<String>();

    try {
      inputFile =
        new Scanner(
          new FileInputStream("COMP249_A03/src/DriverPackage/" + file)
        ); //open the file
      for (int i = 0; inputFile.hasNextLine(); i++) {
        line = inputFile.nextLine();
        lineArr[i] = line;
      }
      for (int i = 0; i < lineArr.length; i++) {
        if (lineArr[i].length() != 0 && lineArr[i].charAt(0) == '#') {
          topicArr.add(lineArr[i].substring(1));
        }
      }
      System.out.println("File loaded successfully");
      inputFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      System.exit(0);
    }
    return topicArr;
  }

  public static DoublyLinkedList createDoublyLinkedList(
    ArrayList<String> topicArr
  ) {
    DoublyLinkedList topicList = new DoublyLinkedList();
    for (int i = 0; i < topicArr.size(); i++) {
      topicList.addTopic1(topicArr.get(i));
    }
    return topicList;
  }

  public static ArrayList<String> wordsFromFile(String file) {
    Scanner inputFile = null;
    String line = "";
    ArrayList<String> wordArr = new ArrayList<String>();
    try {
      inputFile =
        new Scanner(
          new FileInputStream("COMP249_A03/src/DriverPackage/" + file)
        );
      while (inputFile.hasNextLine()) {
        line = inputFile.nextLine();
        if (line.length() != 0 && line.charAt(0) != '#') {
          wordArr.add(line);
        } else if (line.length() == 0) { //separates list of words from different topics by a space
          wordArr.add(" ");
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      System.exit(0);
    }
    return wordArr;
  }

  // Method adds the words to the topics
  public static void addWordsToTopics(
    DoublyLinkedList topicList,
    ArrayList<String> topicArr,
    ArrayList<String> wordArr
  ) {
    Vocab[] vocArr = new Vocab[topicArr.size()];
    int count = 0;
    for (int i = 0; i < vocArr.length; i++) {
      vocArr[i] = topicList.getVocab(topicArr.get(i));
    }

    for (int i = 0; i < vocArr.length; i++) {
      for (int j = 0; j < wordArr.size() && count < wordArr.size(); j++) {
        if (wordArr.get(count).equals(" ")) { //if the word is a space, it means the list of words for the topic has ended
          count++;
          break;
        } else { //add the word list to the vocab object
          if (vocArr[i].addWord(wordArr.get(count))) {
            count++;
          } else {
            System.out.println(wordArr.get(count));
            count++;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean quit = false;

    DoublyLinkedList topicList = new DoublyLinkedList();
    ArrayList<String> topicArr = new ArrayList<String>();

    do {
      //Printing the menu
      boolean validChoice = true;
      int choiceInt = 0;

      do {
        try {
          System.out.println("-----------------------------------");
          System.out.println("Vocabulary Control Center");
          System.out.println("-----------------------------------");
          System.out.println("1 browse a topic");
          System.out.println("2 insert a new topic before another one");
          System.out.println("3 insert a new topic after another one");
          System.out.println("4 remove a topic");
          System.out.println("5 modify a topic");
          System.out.println("6 search topics for a word");
          System.out.println("7 load from a file");
          System.out.println("8 show all words starting with a given letter");
          System.out.println("9 save to file");
          System.out.println("0 exit");
          System.out.println("-----------------------------------");
          System.out.print("Enter your choice: ");
          String choice = input.next();
          choiceInt = Integer.parseInt(choice);

          if (choiceInt > 9 || choiceInt < 0) {
            System.out.println("You have entered an invalid choice");
            validChoice = false;
          } else {
            validChoice = true;
          }
        } catch (NumberFormatException e) {
          System.out.println("You have entered an invalid choice");
          validChoice = false;
        }
      } while (!validChoice);

      switch (choiceInt) {
        case 1: //browse a topic
          int input1 = 0;
          System.out.println("-----------------------------------");
          System.out.println("           Pick a topic            ");
          System.out.println("-----------------------------------");

          if (topicList != null && topicList.getHead() != null) {
            int i1 = topicList.displayTopics() - 1; //display the topics
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");
            input1 = input.nextInt();

            if (input1 > i1 || input1 < 0) {
              System.out.println("You have entered an invalid choice");
            } else if (input1 == 0) {
              System.out.println("You will immediately exit this section");
            } else {
              String topicName1 = topicArr.get(input1 - 1);
              System.out.println(topicName1); //display the topic
              topicList.display(topicName1); //display the words
            }
          } else {
            System.out.println("There are no topics registered yet");
          }

          break;
        case 2: //insert a topic before another one
          int input2 = 0;
          System.out.println("-----------------------------------");
          System.out.println("           Pick a topic            ");
          System.out.println("-----------------------------------");

          if (topicList != null && topicList.getHead() != null) { //if there are topics
            int i2 = topicList.displayTopics() - 1;
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            input2 = input.nextInt();
            if (input2 > i2 || input2 < 0) {
              System.out.println("You have entered an invalid choice");
            } else if (input2 == 0) {
              System.out.println("You will immediately exit this section");
            } else {
              String topicName2 = topicArr.get(input2 - 1);
              System.out.print("Enter the name of the topic: ");
              String topicAdd2 = input.next();

              if (topicList.insertBefore(topicName2, topicAdd2)) {
                topicArr.add(input2 - 1, topicAdd2);
                System.out.println("Enter a word - to quit, press Enter: ");
                input.nextLine();
                String newWords = input.nextLine();
                String[] newWordsArr = newWords.split(" ");
                Vocab vocab2 = topicList.getVocab(topicAdd2);
                for (int i = 0; i < newWordsArr.length; i++) {
                  vocab2.addWord(newWordsArr[i]);
                }
              } else {
                System.out.println("Your topic could not be added");
              }
            }
          } else {
            System.out.println(
              "Since there are no topics, you will enter the first one"
            );
            System.out.print("Enter the name of the topic: ");
            String topicAdd2 = input.next();
            topicArr.add(topicAdd2);
            if (topicList.addTopic(topicAdd2)) {
              System.out.println("Enter a word - to quit, press Enter: ");
              input.nextLine();
              String newWords = input.nextLine();
              String[] newWordsArr = newWords.split(" ");
              Vocab vocab2 = topicList.getVocab(topicAdd2);
              for (int i = 0; i < newWordsArr.length; i++) {
                vocab2.addWord(newWordsArr[i]);
              }
            } else {
              System.out.println("Your topic could not be added");
            }
          }
          break;
        case 3: //insert a topic after another one
          input2 = 0;
          System.out.println("-----------------------------------");
          System.out.println("           Pick a topic            ");
          System.out.println("-----------------------------------");

          if (topicList != null && topicList.getHead() != null) {
            int i2 = topicList.displayTopics() - 1;
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            input2 = input.nextInt();
            if (input2 > i2 || input2 < 0) {
              System.out.println("You have entered an invalid choice");
            } else if (input2 == 0) {
              System.out.println("You will immediately exit this section");
            } else {
              String topicName2 = topicArr.get(input2 - 1);
              System.out.print("Enter the name of the topic: ");
              String topicAdd2 = input.next();
              if (topicList.insertAfter(topicName2, topicAdd2)) {
                topicArr.add(input2, topicAdd2);
                System.out.println("Enter a word - to quit, press Enter: ");
                input.nextLine();
                String newWords = input.nextLine();
                String[] newWordsArr = newWords.split(" ");
                Vocab vocab2 = topicList.getVocab(topicAdd2);
                for (int i = 0; i < newWordsArr.length; i++) {
                  vocab2.addWord(newWordsArr[i]);
                }
              } else {
                System.out.println("Your topic could not be added");
              }
            }
          } else {
            System.out.println(
              "Since there are no topics, you will enter the first one"
            );
            System.out.print("Enter the name of the topic: ");
            String topicAdd2 = input.next();
            topicArr.add(topicAdd2);
            if (topicList.addTopic(topicAdd2)) {
              System.out.println("Enter a word - to quit, press Enter: ");
              input.nextLine();
              String newWords = input.nextLine();
              String[] newWordsArr = newWords.split(" ");
              Vocab vocab2 = topicList.getVocab(topicAdd2);
              for (int i = 0; i < newWordsArr.length; i++) {
                vocab2.addWord(newWordsArr[i]);
              }
            } else {
              System.out.println("Your topic could not be added");
            }
          }

          break;
        case 4: //remove a topic
          input2 = 0;
          System.out.println("-----------------------------------");
          System.out.println("           Pick a topic            ");
          System.out.println("-----------------------------------");

          if (topicList != null && topicList.getHead() != null) {
            int i2 = topicList.displayTopics() - 1;
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            input2 = input.nextInt();
            if (input2 > i2 || input2 < 0) {
              System.out.println("You have entered an invalid choice");
            } else if (input2 == 0) {
              System.out.println("You will immediately exit this section");
            } else {
              String topicName2 = topicArr.get(input2 - 1);
              if (topicList.removeTopic(topicName2)) {
                topicArr.remove(input2 - 1);
                System.out.println(
                  "The topic " + topicName2 + " has been removed"
                );
              } else {
                System.out.println("The topic could not be removed");
              }
            }
          }
          break;
        case 5: //modify a topic
          input2 = 0;
          System.out.println("-----------------------------------");
          System.out.println("           Pick a topic            ");
          System.out.println("-----------------------------------");

          if (topicList != null && topicList.getHead() != null) {
            int i2 = topicList.displayTopics() - 1;
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            input2 = input.nextInt();
            if (input2 > i2 || input2 < 0) {
              System.out.println("You have entered an invalid choice");
            } else if (input2 == 0) {
              System.out.println("You will immediately exit this section");
            } else {
              String topicName = topicArr.get(input2 - 1);
              System.out.println("-----------------------------------");
              System.out.println("       Modify topics menu");
              System.out.println("-----------------------------------");
              System.out.println("a add a word");
              System.out.println("r remove a word");
              System.out.println("c change a word");
              System.out.println("0 exit");
              char choice5 = input.next().charAt(0);

              switch (choice5) {
                case 'a':
                  System.out.println("Enter the word you want to add: ");
                  String word = input.next();
                  Vocab vocab2 = topicList.getVocab(topicName);
                  vocab2.addWord(word);
                  break;
                case 'r':
                  System.out.println("Enter the word you want to remove: ");
                  word = input.next();
                  vocab2 = topicList.getVocab(topicName);
                  vocab2.removeWord(word);
                  break;
                case 'c':
                  System.out.println("Enter the word you want to modify: ");
                  word = input.next();
                  System.out.println("Enter the new word: ");
                  String newWord = input.next();
                  vocab2 = topicList.getVocab(topicName);
                  vocab2.replaceWord(word, newWord);
                  break;
                case '0':
                  break;
              }
            }
          }
          break;
        case 6: //search topics for a word
          System.out.print("Enter the word you want to search for: ");
          String word = input.next();
          for (int i = 0; i < topicArr.size(); i++) {
            Vocab vocab = topicList.getVocab(topicArr.get(i));
            if (vocab.findWord(word)) {
              System.out.println(topicArr.get(i));
              break;
            } else {
              System.out.println("The word was not found in any topic");
            }
          }

          break;
        case 7: //load from a file
          System.out.println("Enter the name of the file: ");
          String file = input.next();
          topicArr = topicFromFile(file); //array list of topics from the file
          topicList = createDoublyLinkedList(topicArr); //doubly linked list of topics
          ArrayList<String> wordArr = wordsFromFile(file); //array list of words from the file
          addWordsToTopics(topicList, topicArr, wordArr); //add the words to the topics

          break;
        case 8: //show all words starting with a given letter
          System.out.print(
            "Enter the first letter of the words you want to display: "
          );
          char letter = input.next().charAt(0);
          for (int i = 0; i < topicArr.size(); i++) {
            Vocab vocab = topicList.getVocab(topicArr.get(i));
            System.out.println(vocab.allWordsThatStart(letter));
          }
          break;
        case 9: //save to file
          System.out.println("Enter the name of the file: ");
          String fileSave = input.next();
          try {
            PrintWriter outPutFile = new PrintWriter(
              new FileOutputStream("COMP249_A03/src/DriverPackage/" + fileSave)
            );
            for (int i = 0; i < topicArr.size(); i++) {
              Vocab vocab = topicList.getVocab(topicArr.get(i));
              outPutFile.println(vocab.toString());
            }
            outPutFile.close();
          } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
          }
          break;
        case 0: //quit
          System.out.println("Thank you for using this program!");
          quit = true;
          System.exit(0);
          break;
      }
    } while (!quit);

    input.close();
  }
}
