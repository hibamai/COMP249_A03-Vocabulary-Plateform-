package DriverPackage;

public class SinglyLinkedList {
    private class Node{
        private String word;
        private Node next;

        public Node(){
            word = "";
            next = null;
        }
        public Node (String word, Node next){
            this.word = word;
            this.next = next;
        }
        //End of inner class Node
    }
    /*
     * To do:
     * -display 1
     * -find word 1
     * -add word (always from head) 1
     * -remove word 1
     * -display all words that begin w/ a letter 1
     */

     private Node head;

     public SinglyLinkedList(){
        head = null;
     }
     public SinglyLinkedList(Node head){
        this.head = head;
     }
     public String toString(){
        String output = "";
        if (head!=null){
        Node position = head;
        while(position.next!=null){
            position = position.next;
        }
        
            while (position!=null){
                output+= position.word+"\n";
                position = findPreviousNode(position);
            }
        }
        return output;
     }
     public String display(){
        String output = "";
        if (head!=null){
        Node position = head;
        while(position.next!=null){
            position = position.next; //position is now the last node
        }
        
            int i=1;
            while (position!=null){
                output+= i+". "+ position.word+"\t";
                position = findPreviousNode(position);
                i++;
            }
        }
        return output;
     }


     
     public boolean findWordBoolean(String word){ //returns true if the word exists in the list and false otherwise
        boolean found = false;
        if(head!=null){
            Node position = head;

            while (!found && position!=null){
                if (position.word.equals(word)){
                    found = true;
                }
                position = position.next;
            }
        }
        return found;
     }
     public Node findWordNode(String word){
        boolean found = false;
        Node foundNode = new Node();
        if(head!=null){
            Node position = head;
            while (!found && position!=null){
                if (position.word.equals(word)){
                    found = true;
                    foundNode = position;
                }
                position = position.next;
            }
        }
        return foundNode;
     }
     public boolean addWord(String word){
        if (this.head == null){
            this.head = new Node(word, null);
            return true;
        }
       else if (!findWordBoolean(word)){
                Node newHead = new Node (word, head);
                this.head = newHead;
            return true;
       }
       else if (findWordBoolean(word)){
            System.out.println("This word is already in the list");
            return false;
       }
       else{
        return false;
       }
     }
     public boolean removeWordL(String word){
        if (this.head == null){
            return false;
        }
        else if (!findWordBoolean(word)){
            System.out.println("This word is not on the list");
            return false;
        }
        else if(word.equals(head.word)){
            head = head.next;
            System.out.println("Your word has been removed successfully");
            return true;
        }
        else{
            Node position = findWordNode(word);
            Node previous = findPreviousNode(position);
            System.out.println(previous.word);
            previous.next = position.next;
            System.out.println("Your word has been removed successfully");
            
            return true;
        }
     }
     public Node findPreviousNode(Node node){
        String value = node.word;
        String headValue = head.word;
        if (head == null){
            return null;
        }
        else if(value.equals(headValue)){ //node is the first node (the head)
                return null;
            }
        else{
                Node position = head;
                Node previousNode = new Node();
                boolean found = false;
                while (!found && position.next!=null){
                    if(position.next.word.equals(node.word)){
                        previousNode = position;
                        found = true;
                    }
                    position = position.next;
                }
                return previousNode;
            }
        
     }
     public String displayAllWordsThatBegin(char letter){
        if (head!=null){
            Node position = this.head;
            String output ="";
            int i = 1;
            while (position != null){
                char firstChar = position.word.charAt(0);
                if (firstChar == letter){
                    output+= i+". "+position.word+"\t";
                    i++;
                }
                position = position.next;
            }
            return output;
        }
        return "";
     }
     public boolean replaceWordL(String word, String newWord){
        if(findWordBoolean(word)){
            Node position = findWordNode(word);
            position.word = newWord;
            return true;
        }
        else{
            System.out.println("This word does not exist");
            return false;
        }
     }

}
