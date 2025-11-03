package linkedlist;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @brief Implementation of a generic doubly linked list
 * @tparam T Type of elements stored in the list
 *
 * @details
 * This class provides a complete implementation of a doubly linked list
 * with insertion, removal, search, and iteration operations. The list maintains
 * references to both head and tail for O(1) operations at both ends.
 *
 * @author Renê Garmatter
 * @version 1.0
 * @date 2024
 */
public class LinkedList<T> implements Iterable<T>{
    private Node<T> head;    /**< Pointer to the first node in the list */
    private Node<T> tail;    /**< Pointer to the last node in the list */
    private int size;        /**< Number of elements in the list */

    /**
     * @brief Default constructor that initializes an empty list
     */
    public LinkedList(){
        head = null ;
        tail = null;
        size = 0;
    }

    /**
     * @brief Adds an element to the end of the list
     * @param data Element to be added
     * @complexity O(1)
     */
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

    /**
     * @brief Adds an element to the beginning of the list
     * @param data Element to be added
     * @complexity O(1)
     */
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

    /**
     * @brief Removes the last element from the list
     * @complexity O(1)
     * @throws NoSuchElementException if the list is empty
     */
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

    /**
     * @brief Removes the first element from the list
     * @complexity O(1)
     * @throws NoSuchElementException if the list is empty
     */
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

    /**
     * @brief Returns the element at the specified position
     * @param index Position of the element to return
     * @return The element at the specified position
     * @complexity O(n)
     * @throws IndexOutOfBoundsException if index is out of range
     */
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

    /**
     * @brief Replaces the element at the specified position
     * @param index Index of the element to replace
     * @param value Element to be stored at the specified position
     * @complexity O(n)
     * @throws IndexOutOfBoundsException if index is out of range
     */
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

    /**
     * @brief Inserts the specified element at the specified position
     * @param index Index at which the specified element is to be inserted
     * @param value Element to be inserted
     * @complexity O(n)
     * @throws IndexOutOfBoundsException if index is out of range
     */
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

    /**
     * @brief Removes the element at the specified position
     * @param index Index of the element to be removed
     * @complexity O(n)
     * @throws IndexOutOfBoundsException if index is out of range
     */
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

    /**
     * @brief Removes the first occurrence of the specified element
     * @param object Element to be removed from the list
     * @complexity O(n)
     */
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

    /**
     * @brief Removes the last occurrence of the specified element
     * @param object Element to be removed from the list
     * @complexity O(n)
     */
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

    /**
     * @brief Returns the first element in the list
     * @return The first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst(){
        if(head == null) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    /**
     * @brief Returns the last element in the list
     * @return The last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast(){
        if(tail == null) throw new NoSuchElementException("List is empty");
        return tail.data;
    }

    /**
     * @brief Returns the index of the first occurrence of the specified element
     * @param object Element to search for
     * @return The index of the first occurrence, or -1 if not found
     * @complexity O(n)
     */
    public int findFirstOcurrence(T object){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(object)){
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * @brief Returns the index of the last occurrence of the specified element
     * @param object Element to search for
     * @return The index of the last occurrence, or -1 if not found
     * @complexity O(n)
     */
    public int findLastOcurrence(T object){
        Node<T> current = tail;
        int index = size-1;
        while(current != null){
            if(current.data.equals(object)){
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    /**
     * @brief Returns true if the list contains the specified element
     * @param object Element whose presence in the list is to be tested
     * @return true if the list contains the specified element
     * @complexity O(n)
     */
    public boolean contains(T object){
        Node<T> current = head;
        while(current != null){
            if(current.data.equals(object)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * @brief Returns true if the list contains no elements
     * @return true if the list contains no elements
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @brief Removes all elements from the list
     */
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @brief Returns the number of elements in the list
     * @return The number of elements in the list
     */
    public int size(){
        return size;
    }

    /**
     * @brief Returns an array containing all elements in the list
     * @param a The array into which the elements of the list are to be stored
     * @return An array containing all elements in the list
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in this list
     */
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

    /**
     * @brief Reverses the order of elements in the list
     * @complexity O(n)
     */
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

    /**
     * @brief Returns an iterator over elements in the list
     * @return An iterator over elements in the list
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * @brief Private iterator implementation for the linked list
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current; /**< Current node in the iteration */

        /**
         * @brief Constructs an iterator starting at the head of the list
         */
        public LinkedListIterator() {
            current = head;
        }

        /**
         * @brief Returns true if the iteration has more elements
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * @brief Returns the next element in the iteration
         * @return The next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

        /**
         * @brief Removes from the underlying collection the last element returned by this iterator
         * @throws UnsupportedOperationException always, as remove is not supported
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported");
        }
    }
}