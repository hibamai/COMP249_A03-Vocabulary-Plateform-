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
            int i = 1;

            while (position!=null){
                output+= i+". "+position.word+"\t";
                i++;
                position = position.next;
            }
        }
        return output;
     }
     public boolean findWordBoolean(String word){
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
       else{
        return false;
       }
     }
     public boolean removeWord(String word){
        if (this.head == null){
            return false;
        }
        else if (!findWordBoolean(word)){
            return false;
        }
        else{
            Node position = findWordNode(word);
            Node previous = findPreviousNode(position);
            previous.next = position.next;
            return true;
        }
     }
     public Node findPreviousNode(Node node){
        if (head == null){
            return null;
        }
        else{
            if(node.word.equals(head.word)){
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
     }
     public String displayAllWordsThatBegin(char letter){
        if (head!=null){
            Node position = this.head;
            String output = "All the words that begin with the letter "+letter +"\n";
            int i = 1;
            while (position != null){
                char firstChar = position.word.charAt(0);
                if (firstChar == letter){
                    output+= i+". "+position.word+"\t";
                }
            }
            return output;
        }
        return "";
     }

}
