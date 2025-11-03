package linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<Integer> list;
    private LinkedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
        stringList = new LinkedList<>();
    }

    @Test
    void testPushBack() {
        list.pushBack(1);
        assertEquals(1, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());

        list.pushBack(2);
        assertEquals(2, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(2, list.getLast());

        list.pushBack(3);
        assertEquals(3, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void testPushFront() {
        list.pushFront(1);
        assertEquals(1, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());

        list.pushFront(2);
        assertEquals(2, list.size());
        assertEquals(2, list.getFirst());
        assertEquals(1, list.getLast());

        list.pushFront(3);
        assertEquals(3, list.size());
        assertEquals(3, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void testPopBack() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        list.popBack();
        assertEquals(2, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(2, list.getLast());

        list.popBack();
        assertEquals(1, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());

        list.popBack();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testPopFront() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        list.popFront();
        assertEquals(2, list.size());
        assertEquals(2, list.getFirst());
        assertEquals(3, list.getLast());

        list.popFront();
        assertEquals(1, list.size());
        assertEquals(3, list.getFirst());
        assertEquals(3, list.getLast());

        list.popFront();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testGet() {
        list.pushBack(10);
        list.pushBack(20);
        list.pushBack(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testSet() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        list.set(1, 99);
        assertEquals(99, list.get(1));
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, 100));
    }

    @Test
    void testInsert() {
        list.pushBack(1);
        list.pushBack(3);

        list.insert(1, 2);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        list.insert(0, 0);
        assertEquals(4, list.size());
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
    }

    @Test
    void testRemove() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);

        list.remove(1);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));

        list.remove(0);
        assertEquals(2, list.size());
        assertEquals(3, list.get(0));
        assertEquals(4, list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    void testRemoveFirstOccurrence() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(4);

        list.removeFirstOcurrence(2);
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));

        list.removeFirstOcurrence(1);
        assertEquals(3, list.size());
        assertEquals(3, list.get(0));
    }

    @Test
    void testRemoveLastOccurrence() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(4);

        list.removeLastOcurrence(2);
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    void testGetFirstAndLast() {
        list.pushBack(10);
        assertEquals(10, list.getFirst());
        assertEquals(10, list.getLast());

        list.pushBack(20);
        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());

        list.pushFront(5);
        assertEquals(5, list.getFirst());
        assertEquals(20, list.getLast());
    }

    @Test
    void testFindFirstOccurrence() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(4);

        assertEquals(1, list.findFirstOcurrence(2));
        assertEquals(0, list.findFirstOcurrence(1));
        assertEquals(4, list.findFirstOcurrence(4));
        assertEquals(-1, list.findFirstOcurrence(99));
    }

    @Test
    void testFindLastOccurrence() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(4);

        assertEquals(3, list.findLastOcurrence(2));
        assertEquals(0, list.findLastOcurrence(1));
        assertEquals(4, list.findLastOcurrence(4));
        assertEquals(-1, list.findLastOcurrence(99));
    }

    @Test
    void testContains() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertFalse(list.contains(4));
        assertFalse(list.contains(null));
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.pushBack(1);
        assertFalse(list.isEmpty());
        list.popFront();
        assertTrue(list.isEmpty());
    }

    @Test
    void testClear() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.pushBack(1);
        assertEquals(1, list.size());
        list.pushBack(2);
        assertEquals(2, list.size());
        list.popFront();
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testToArray() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        Integer[] array = new Integer[3];
        Integer[] result = list.toArray(array);

        assertEquals(3, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);

        // Test with smaller array
        Integer[] smallArray = new Integer[1];
        Integer[] enlargedArray = list.toArray(smallArray);
        assertEquals(3, enlargedArray.length);
    }

    @Test
    void testReverse() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);

        list.reverse();

        assertEquals(4, list.size());
        assertEquals(4, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(1, list.get(3));
        assertEquals(4, list.getFirst());
        assertEquals(1, list.getLast());

        // Test reverse on empty list
        LinkedList<Integer> emptyList = new LinkedList<>();
        emptyList.reverse(); // Should not throw exception
        assertTrue(emptyList.isEmpty());

        // Test reverse on single element
        LinkedList<Integer> singleList = new LinkedList<>();
        singleList.pushBack(42);
        singleList.reverse();
        assertEquals(1, singleList.size());
        assertEquals(42, singleList.getFirst());
        assertEquals(42, singleList.getLast());
    }

    @Test
    void testIterator() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorRemoveNotSupported() {
        list.pushBack(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();

        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    void testStringList() {
        stringList.pushBack("hello");
        stringList.pushBack("world");
        stringList.pushBack("test");

        assertEquals(3, stringList.size());
        assertEquals("hello", stringList.getFirst());
        assertEquals("test", stringList.getLast());
        assertTrue(stringList.contains("world"));
    }

    @Test
    void testMixedOperations() {
        // Comprehensive test with mixed operations
        assertTrue(list.isEmpty());

        list.pushBack(10);
        list.pushFront(5);
        list.pushBack(15);

        assertEquals(3, list.size());
        assertEquals(5, list.getFirst());
        assertEquals(15, list.getLast());

        list.insert(1, 7);
        assertEquals(4, list.size());
        assertEquals(7, list.get(1));

        list.set(2, 12);
        assertEquals(12, list.get(2));

        list.remove(1);
        assertEquals(3, list.size());
        assertEquals(12, list.get(1));

        list.reverse();
        assertEquals(15, list.getFirst());
        assertEquals(5, list.getLast());
    }
}