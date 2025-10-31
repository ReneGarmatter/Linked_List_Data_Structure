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

    public T get(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        return current.data;
    }
    public void set(int index,T value){
        if(index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        current.data = value;
    }

    public void insert(int index, T value){
        if(index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        Node<T> prev = current.prev;
        Node<T> newNode = new Node<T>(value);
        current.prev = newNode;
        if(prev != null) {
            prev.next = newNode;
        }
        if(index == 0){
            head = newNode;
        }
    }

    public void remove(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        Node<T> prev = current.prev;
        Node<T> next = current.next;
        if(prev != null) {
            prev.next = next;
        }
        if(next != null){
            next.prev = prev;
        }
        if(index == 0){
            head = next;
        }
    }

    public void removeFirstOcurrence(T object){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if(current.data == object){
                remove(index);
                return;
            }
            else{
                current = current.next;
                index++;
            }
        }
    }
    public void removeLastOcurrence(T object){
        Node<T> current = tail;
        int index = size-1;
        while(current != null){
            if(current.data == object){
                remove(index);
                return;
            }
            else{
                current = current.prev;
                index--;
            }
        }
    }
}
