package DriverPackage;

/**
 * @author Lan Thi Duong (ID: 40276821) and Hiba Maifi (ID: 40289223)
 * COMP249 - Assignment #3
 * Monday, April 15th
 */
public class DoublyLinkedList {

  private class Node {

    private Vocab vocab;
    private Node next;
    private Node previous;

    public Node() {
      this.vocab = new Vocab();
      this.next = null;
      this.previous = null;
    }

    public Node(Vocab vocab, Node previous, Node next) {
      this.vocab = vocab;
      this.next = next;
      this.previous = previous;
    }

    public String getName() {
      return this.vocab.getName();
    }

    public Vocab getVocab() {
      return this.vocab;
    }
    //End of the inner class Node
  }

  private Node head;

  public DoublyLinkedList() {
    this.head = null;
  }

  public DoublyLinkedList(Node head) {
    this.head = head;
  }

  public Vocab getVocab(String topicName) {
    if (head != null) {
      Node position = findTopic(topicName);
      return position.getVocab();
    } else {
      return null;
    }
  }

  //method to add a new topic in the linked list
  public void addTopic1(String topic) {
    Node newNode = new Node(new Vocab(topic), null, null);
    Node current = head;
    //if the list is empty
    if (head == null) {
      head = newNode;
      return;
    }
    //if the list has at least 1 element
    else {
      while (current != null) {
        if (current.next == null) {
          current.next = newNode; //add the new node at the end of the list
          newNode.previous = current; //set the previous of the new node to the current node
          return;
        } else {
          current = current.next; //move the next node
        }
      }
    }
  }

  public boolean addTopic(String topic) {
    if (head == null) {
      head = new Node(new Vocab(topic), null, null);
      return true;
    } else {
      return false;
    }
  }

  public void display(String topic) { //to print out all the nodes in this linked list
    if (head != null) {
      Node position = findTopic(topic);
      System.out.println(position.vocab.display());
    } else {
      System.out.println("This section is empty");
    }
  }

  public int displayTopics() {
    int i = 0;
    if (head != null) {
      Node position = head;
      i++;
      while (position != null) {
        System.out.println(i + " " + position.getName() + "\n");
        position = position.next;
        i++;
      }
      System.out.println("0 Exit");
    } else {
      System.out.println("This section is empty");
    }
    return i;
  }

  public Node findTopic(String topic) {
    Node position = head;
    boolean found = false;
    Node theOne = new Node();
    while (position != null && !found) {
      String name = position.vocab.getName();
      if (name.equals(topic)) {
        found = true;
        theOne = position;
      }
      position = position.next;
    }
    if (found) {
      return theOne;
    } else {
      return null;
    }
  }

  public boolean insertBefore(String topic, String addThis) {
    //we should find if the topic exists
    Node position = findTopic(topic);
    if (position != null) {
      Node previous = position.previous;
      if (previous != null) {
        Node adding = new Node(new Vocab(addThis), previous, position);
        position.previous = adding;
        previous.next = adding;
      } else {
        Node adding = new Node(new Vocab(addThis), null, position);
        position.previous = adding;
        head = adding;
      }
      return true;
    } else {
      System.out.println("This topic does not exist");
      return false;
    }
  }

  public boolean insertAfter(String topic, String addThis) {
    //find out if the topic exists
    Node position = this.findTopic(topic);
    if (position != null) {
      Node next = position.next;
      if (next != null) {
        Node theOne = new Node(new Vocab(addThis), position, next);
        position.next = theOne;
        next.previous = theOne;
      } else {
        Node theOne = new Node(new Vocab(addThis), position, null);
        position.next = theOne;
      }

      return true;
    } else {
      System.out.println("The topic " + topic + " does not exist");
      return false;
    }
  }

  public boolean removeTopic(String topic) {
    //find out if the topic exists
    if (this.findTopic(topic) != null) {
      Node position = findTopic(topic);
      Node next = position.next;
      Node previous = position.previous;

      //our 4 cases
      if (next == null && previous == null) { //we only have the head
        head = null;
        System.out.println("1");
      } else if (previous == null && next != null) { //we are trying to remove the head
        head = next;
        System.out.println("2");
      } else if (previous != null && next == null) { //we are trying to remove the tail
        previous.next = null;
        System.out.println("3");
      } else { //we are a removing a node from the middle
        previous.next = next;
        next.previous = previous;
        System.out.println("4");
      }
      return true;
    } else {
      System.out.println("The topic " + topic + " does not exist");
      return false;
    }
  }

  public Node getHead() {
    return this.head;
  }

  public String getHeadString() {
    return this.head.getName();
  }
}
