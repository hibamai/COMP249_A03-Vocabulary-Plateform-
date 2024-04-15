package DriverPackage;

public class DoublyLinkedList {
    private class Node {
        private Vocab vocab;
        private Node next;
        private Node previous;

        public Node(){
            this.vocab = new Vocab();
            this.next = null;
            this.previous = null;
        }
        public Node (Vocab vocab, Node previous, Node next){
            this.vocab = vocab;
            this.next  = next;
            this.previous = previous;
        }
        public String getName(){
            return this.vocab.getName();
        }
        public Vocab getVocab(){
            return this.vocab;
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
    public Vocab getVocab(String topicName){
        if(head!=null){
            Node position = findTopic(topicName);
            return position.getVocab();
        }
        else{
            return null;
        }
    }
    public boolean addTopic(String topic){
        if(head==null){
            head = new Node(new Vocab(topic), null, null);
            return true;
        }
        else{
            return false;
        }
    }
    public void display(String topic){ //to print out all the nodes in this linked list
        if (head!=null){
        Node position = findTopic(topic); 
        System.out.println(position.vocab);
        }

        else{
            System.out.println("This section is empty");
        }
    }
    public int displayTopics(){
        int i = 0;
       if(head!=null){
        Node position = head;
        i++;
        while (position!=null) {
            System.out.println(i + " " + position.getName()+"\n");
            position = position.next;
            i++;
        }
        System.out.println("0 Exit");
       }
       else{
        System.out.println("This section is empty");
       }
       return i;
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
        Node position = findTopic(topic);
        if(position!=null){
            Node previous = position.previous;
            if(previous!=null){
                Node adding = new Node(new Vocab(addThis), previous, position);
                position.previous = adding;
                previous.next = adding;
            }
            else{
                Node adding = new Node (new Vocab(addThis), null, position);
                position.previous = adding;
                head = adding;
            }
            return true;

        }
        else{
            System.out.println("This topic does not exist");
            return false;
        }
    }
    public boolean insertAfter(String topic, String addThis){
        //find out if the topic exists
        Node position = this.findTopic(topic);
        if (position!=null){
            Node next = position.next;
            if(next!=null){
                Node theOne = new Node(new Vocab(addThis), position, next);
                position.next = theOne;
                next.previous = theOne;
            }
            else{
                Node theOne = new Node (new Vocab(addThis), position, null);
                position.next = theOne;
            }
            
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
            Node position = findTopic(topic);
            Node next = position.next;
            Node previous = position.previous;

            //our 4 cases
            if(next==null && previous==null){ //we only have the head
                head = null;
            }
            else if(previous == null&&next!=null){ //we are trying to remove the head
                head = next;
            }
            else if (previous!=null && next==null){ //we are trying to remove the tail
                previous.next = null;
            }
            else{//we are a removing a node from the middle
                position.next = next;
                next.previous = previous;
            }
            return true;
        }
        else{
            System.out.println("The topic "+topic+" does not exist");
            return false;
        }
    }   
    public Node getHead(){
        return this.head;
    }
    public String getHeadString(){
        return this.head.getName();
    }
    
}

