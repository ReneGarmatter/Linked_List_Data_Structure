package linkedlist;

/**
 * @brief Node class for doubly linked list implementation
 * @tparam T Type of data stored in the node
 *
 * @details
 * This class represents a node in a doubly linked list, containing
 * references to the previous and next nodes, and storing the actual data.
 */
public class Node<T> {
    /**
     * @brief Constructs a new node with the specified data
     * @param data The data to be stored in the node
     */
    public Node (T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    };

    public T data;          /**< The data stored in this node */
    public Node<T> next;    /**< Reference to the next node in the list */
    public Node<T> prev;    /**< Reference to the previous node in the list */
}