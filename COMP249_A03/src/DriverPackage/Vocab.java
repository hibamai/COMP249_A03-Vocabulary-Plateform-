package DriverPackage;

public class Vocab {
    private String name;
    private SinglyLinkedList words;

    public Vocab(){
        this.name = "";
        this.words = new SinglyLinkedList();
    }
    public Vocab (String name){
        this.name = name;
        this.words = new SinglyLinkedList();
    }

    public String getName(){
        return this.name;
    }

    public SinglyLinkedList getWords(){
        return this.words;
    }

    public String toString(){ //* */
        String output = "#"+this.name +"\n";
        output+=this.words.toString();
        return output;
    }
    public String display(){
        String output = "Topic : "+this.name +"\n";
        output+=this.words.display();
        return output;
    }
    public boolean findWord(String word){ //* */
        return this.words.findWordBoolean(word);
    }
    public boolean addWord(String word){ //* */
        return this.words.addWord(word);
    }
    public boolean removeWord(String word){ //* */
       return this.words.removeWordL(word);
    }
    public String allWordsThatStart(char letter){ //* */
        return "All the words that begin with the letter "+letter +" in the topic: "+name+"\n" + this.words.displayAllWordsThatBegin(letter);
    }
    public boolean replaceWord(String word, String newWord){
        return this.words.replaceWordL(word, newWord);
    }

}
