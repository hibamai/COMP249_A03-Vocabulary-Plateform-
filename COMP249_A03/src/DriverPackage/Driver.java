package DriverPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        System.out.println("hello");
        Scanner input = new Scanner (System.in);
        boolean quit = false;
        DoublyLinkedList topicList = new DoublyLinkedList();
        ArrayList<String> topicArr = new ArrayList<String>();
        do{
        //Printing the menu 
        boolean validChoice = true;
        int choiceInt = 0;
        
            do {
                try{
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

                    if(choiceInt>9 || choiceInt<0){
                        System.out.println("You have entered an invalid choice");
                        validChoice = false;
                    }
                    else{
                        validChoice = true;
                    }

                }catch(NumberFormatException e){
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

                    if (topicList!=null && topicList.getHead()!=null){
                    int i1 = topicList.displayTopics()-1;
                    System.out.println("-----------------------------------");
                    System.out.print("Enter your choice: ");
                        input1 = input.nextInt(); 

                        if(input1>i1 || input1<0){
                            System.out.println("You have entered an invalid choice");
                        }
                        else if (input1==0){
                            System.out.println("You will immediately exit this section");
                        }
                        else{
                            String topicName1 = topicArr.get(input1-1);
                            System.out.println(topicName1);
                            topicList.display(topicName1);
                        }

                    }
                    else{
                        System.out.println("There are no topics registered yet");
                    }
                    


                    break;
                case 2: //insert a topic before another one
                int input2 = 0;
                System.out.println("-----------------------------------");
                System.out.println("           Pick a topic            ");
                System.out.println("-----------------------------------");
                
                if (topicList!=null && topicList.getHead()!=null){
                int i2 = topicList.displayTopics()-1;
                System.out.println(i2);
                System.out.println("-----------------------------------");
                System.out.print("Enter your choice: ");

                
                    input2 = input.nextInt(); 
                    if(input2>i2 || input2<0){
                        System.out.println("You have entered an invalid choice");
                    }
                    else if (input2==0){
                        System.out.println("You will immediately exit this section");
                    }
                    else{
                        String topicName2 = topicArr.get(input2-1);
                        System.out.print("Enter the name of the topic: ");
                        String topicAdd2 = input.next();
                        
                        if (topicList.insertBefore(topicName2, topicAdd2)){
                            topicArr.add(input2-1, topicAdd2);
                            System.out.println("Enter a word - to quit, press Enter: ");
                            input.nextLine();
                            String newWords = input.nextLine();
                            String[] newWordsArr = newWords.split(" ");
                            Vocab vocab2 = topicList.getVocab(topicAdd2);;
                            for(int i = 0; i<newWordsArr.length; i++){
                                vocab2.addWord(newWordsArr[i]);
                            }
                        }
                        else{
                            System.out.println("Your topic could not be added");
                         }
                        }
                }   
                else{
                    System.out.println("Since there are no topics, you will enter the first one");
                    System.out.print("Enter the name of the topic: ");
                    String topicAdd2 = input.next();
                    topicArr.add(topicAdd2);
                    if (topicList.addTopic(topicAdd2)){
                        System.out.println("Enter a word - to quit, press Enter: ");
                        input.nextLine();
                        String newWords = input.nextLine();
                        String[] newWordsArr = newWords.split(" ");
                        Vocab vocab2 = topicList.getVocab(topicAdd2);
                            for (int i = 0; i<newWordsArr.length; i++){
                                vocab2.addWord(newWordsArr[i]);
                            }
                    }
                    else{
                        System.out.println("Your topic could not be added");
                    }
                }
                    break;
                case 3: //insert a topic after another one
                input2 = 0;
                System.out.println("-----------------------------------");
                System.out.println("           Pick a topic            ");
                System.out.println("-----------------------------------");
                
                if (topicList!=null && topicList.getHead()!=null){
                int i2 = topicList.displayTopics()-1;
                System.out.println("-----------------------------------");
                System.out.print("Enter your choice: ");

                
                    input2 = input.nextInt(); 
                    if(input2>i2 || input2<0){
                        System.out.println("You have entered an invalid choice");
                    }
                    else if (input2==0){
                        System.out.println("You will immediately exit this section");
                    }
                    else{
                        String topicName2 = topicArr.get(input2-1);
                        System.out.print("Enter the name of the topic: ");
                        String topicAdd2 = input.next();
                        if (topicList.insertAfter(topicName2, topicAdd2)){
                            topicArr.add(input2, topicAdd2);
                            System.out.println("Enter a word - to quit, press Enter: ");
                            input.nextLine();
                            String newWords = input.nextLine();
                            String[] newWordsArr = newWords.split(" ");
                            Vocab vocab2 = topicList.getVocab(topicAdd2);;
                            for(int i = 0; i<newWordsArr.length; i++){
                                vocab2.addWord(newWordsArr[i]);
                            }
                        }
                        else{
                            System.out.println("Your topic could not be added");
                         }
                        }
                }   
                else{
                    System.out.println("Since there are no topics, you will enter the first one");
                    System.out.print("Enter the name of the topic: ");
                    String topicAdd2 = input.next();
                    topicArr.add(topicAdd2);
                    if (topicList.addTopic(topicAdd2)){
                        System.out.println("Enter a word - to quit, press Enter: ");
                        input.nextLine();
                        String newWords = input.nextLine();
                        String[] newWordsArr = newWords.split(" ");
                        Vocab vocab2 = topicList.getVocab(topicAdd2);
                            for (int i = 0; i<newWordsArr.length; i++){
                                vocab2.addWord(newWordsArr[i]);
                            }
                    }
                    else{
                        System.out.println("Your topic could not be added");
                    }
                }

                    break;
                case 4: //remove a topic
                input2 = 0;
                System.out.println("-----------------------------------");
                System.out.println("           Pick a topic            ");
                System.out.println("-----------------------------------");
                
                if (topicList!=null && topicList.getHead()!=null){
                int i2 = topicList.displayTopics()-1;
                System.out.println("-----------------------------------");
                System.out.print("Enter your choice: ");

                
                    input2 = input.nextInt(); 
                    if(input2>i2 || input2<0){
                        System.out.println("You have entered an invalid choice");
                    }
                    else if (input2==0){
                        System.out.println("You will immediately exit this section");
                    }
                    else{
                        String topicName2 = topicArr.get(input2-1);
                        if(topicList.removeTopic(topicName2)){
                            topicArr.remove(input2-1);
                            System.out.println("The topic "+topicName2+" has been removed");
                        }
                        else{
                            System.out.println("The topic could not be removed");
                        }
                    }
                }
                    break;
                case 5: //modify a topic

                    break;
                case 6: //search topics for a word

                    break;
                case 7: //load from a file

                    break;
                case 8: //show all words starting with a given letter

                    break;
                case 9: //save to file

                    break;
                case 0: //quit
                    System.out.println("Thank you for using this program!");
                    quit = true;
                    System.exit(0);
                    break;
                
            }
        }while(!quit);

        input.close();
    }
    
}