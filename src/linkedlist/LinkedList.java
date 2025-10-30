package linkedlist;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList(){
        head = null ;
        tail = null;
        size = 0;
    }

    public void pushBack(T data){
        if(size == 0){
            head = new Node<T>(data);
            tail = head;
        }

        tail.next = new Node<T>(data);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
    }
    public void pushFront(T data){
        Node<T> newHead = new Node<T>(data);
        newHead.next = head;
        if(head != null) {
            head.prev = newHead;
        }
        head = newHead;

        if(size == 0){
            tail = newHead;
        }
        size++;
    }

    public void popBack(){
        if(size > 0){
            tail = tail.prev;
            if(tail != null){
                tail.next = null;
            }
            size--;
        }
    }
    public void popFront(){
        if(size > 0){
            head = head.next;
            if(head != null){
                head.prev = null;
            }
            size--;
        }
    }


}
