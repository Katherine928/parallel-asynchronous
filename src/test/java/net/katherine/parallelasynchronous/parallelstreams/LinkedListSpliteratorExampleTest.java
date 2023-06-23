package net.katherine.parallelasynchronous.parallelstreams;

import net.katherine.parallelasynchronous.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {


    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();
    @RepeatedTest(5)
    void multiplyEachValue_parallel() {

        int size = 1000000;

        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, true);
        assertEquals(size, resultList.size());

    }

    @RepeatedTest(5)
    void multiplyEachValue() {

        int size = 1000000;

        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, false);
        assertEquals(size, resultList.size());

    }
}