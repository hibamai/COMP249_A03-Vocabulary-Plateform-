package DriverPackage;

public class Vocab {
    private String name;
    private SinglyLinkedList words;

    public Vocab(){
        this.name = "";
        this.words = null;
    }
    public Vocab (String name){
        this.name = name;
        this.words = null;
    }

    public String getName(){
        return this.name;
    }
    public String toString(){ //* */
        String output = "Topic: "+this.name +"\n";
        output+=this.words.toString();
        return output;
    }
    public boolean findWord(String word){ //* */
        return this.words.findWordBoolean(word);
    }
    public boolean addWord(String word){ //* */
        return this.words.addWord(word);
    }
    public boolean removeWord(String word){ //* */
       return this.removeWord(word);
    }
    public String allWordsThatStart(char letter){ //* */
        return this.words.displayAllWordsThatBegin(letter);
    }

}
