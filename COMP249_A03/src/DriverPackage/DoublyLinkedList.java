package DriverPackage;

public class DoublyLinkedList {
    private class Node {
        private Vocab vocab;
        private Node next;
        private Node previous;

        public Node(){
            this.vocab = null;
            this.next = null;
            this.previous = null;
        }
        public Node (Vocab vocab, Node previous, Node next){
            this.vocab = vocab;
            this.next  = next;
            this.previous = previous;
        }
        //End of the inner class Node
    }

    private Node head;

    public DoublyLinkedList(){
        this.head = null;
    }
    public DoublyLinkedList(Node head){
        this.head = head;
    }
    public void display(){ //to print out all the nodes in this linked list
        Node position = head; 

        while(position!=null){
            System.out.println(position.vocab + "\n");
            position = position.next;
        }
    }
    public Node findTopic(String topic){
        Node position = head;
        boolean found = false;
        Node theOne = new Node();
        while (position !=null && !found){
            String name = position.vocab.getName();
            if (name.equals(topic)){
                found = true;
                theOne = position;
            }
            position = position.next;
        }
        if (found){
            return theOne;
        }
        else{
            return null;
        }
    }
    public boolean insertBefore(String topic, String addThis){
        //we should find if the topic exists
        if (this.findTopic(topic)!=null){
            Node position = this.findTopic(topic);
            Node previous = position.previous;
            Node theOne = new Node(new Vocab(topic), previous, position);
            return true;
        }
        else{
            System.out.println("The topic "+topic+" does not exist");
            return false;
        }
    }
    public boolean insertAfter(String topic, String addThis){
        //find out if the topic exists
        if (this.findTopic(topic)!=null){
            Node position = this.findTopic(topic);
            Node next = position.next;
            Node theOne = new Node(new Vocab(topic), position, next);
            return true;
        }
        else{
            System.out.println("The topic "+topic+" does not exist");
            return false;
        }
    }
    public boolean removeTopic(String topic){
        //find out if the topic exists
        if (this.findTopic(topic)!=null){
            Node position = this.findTopic(topic);
            Node next = position.next;
            Node previous = position.previous;
            next.previous = previous;
            previous.next = next;
            return true;
        }
        else{
            System.out.println("The topic "+topic+" does not exist");
            return false;
        }
    }
    
}

