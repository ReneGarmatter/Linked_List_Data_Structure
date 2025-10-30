package linkedlist;

public class Node<T> {
    public Node (T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    };

    public T data;
    public Node<T> next;
    public Node<T> prev;
}