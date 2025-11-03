package linkedlist;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>{
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
        } else {
            tail.next = new Node<T>(data);
            tail.next.prev = tail;
            tail = tail.next;
        }
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
            } else {
                head = null;
            }
            size--;
        }
    }

    public void popFront(){
        if(size > 0){
            head = head.next;
            if(head != null){
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    public T get(int index){
        if(index < 0 || index >= size){
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
        if(index < 0 || index >= size){
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
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        if(index == 0) {
            pushFront(value);
            return;
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        Node<T> newNode = new Node<T>(value);
        Node<T> prev = current.prev;

        newNode.prev = prev;
        newNode.next = current;
        prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException
                    ("Index " + index + " is out of bounds for a list of size " + size);
        }

        if(index == 0) {
            popFront();
            return;
        }

        if(index == size - 1) {
            popBack();
            return;
        }

        Node<T> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }

        Node<T> prev = current.prev;
        Node<T> next = current.next;

        prev.next = next;
        next.prev = prev;

        size--;
    }

    public void removeFirstOcurrence(T object){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(object)){
                remove(index);
                return;
            }
            current = current.next;
            index++;
        }
    }

    public void removeLastOcurrence(T object){
        Node<T> current = tail;
        int index = size-1;
        while(current != null){
            if(current.data.equals(object)){
                remove(index);
                return;
            }
            current = current.prev;
            index--;
        }
    }

    public T getFirst(){
        if(head == null) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    public T getLast(){
        if(tail == null) throw new NoSuchElementException("List is empty");
        return tail.data;
    }

    public int findFirstOcurrence(T object){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(object)){ // CORREÇÃO: usar equals
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public int findLastOcurrence(T object){
        Node<T> current = tail;
        int index = size-1;
        while(current != null){
            if(current.data.equals(object)){ // CORREÇÃO: usar equals
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    public boolean contains(T object){
        Node<T> current = head;
        while(current != null){
            if(current.data.equals(object)){ // CORREÇÃO: usar equals
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0; // CORREÇÃO: Zerar o size
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a){
        if(a.length < size){
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        int i=0;
        Node<T> current = head;
        while (current != null){
            a[i++] = current.data;
            current = current.next;
        }

        while(i < a.length){
            a[i++] = null;
        }

        return a;
    }

    public void reverse(){
        if(size <= 1) return;

        Node<T> current = head;
        Node<T> temp = null;

        head = tail;
        tail = current;

        while(current != null){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported");
        }
    }
}