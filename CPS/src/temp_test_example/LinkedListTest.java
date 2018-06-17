package temp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    LinkedList<Integer> ll;


    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }

    @BeforeEach
    public void initEach() {
//        System.out.println("Before Each initEach() method called");
        ll = new LinkedList<Integer>(1);
    }

    @Test
    void appendToLL() {
        assertEquals(ll.size, 1);
        ll.append(2);
        assertTrue(ll.size == 2);
    }

    @Test
    void checkIndex() {
        ll.append(2);
        assertTrue(ll.checkIndex(2));
    }

}