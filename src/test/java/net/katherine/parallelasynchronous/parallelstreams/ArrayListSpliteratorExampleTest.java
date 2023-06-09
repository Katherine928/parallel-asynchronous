package net.katherine.parallelasynchronous.parallelstreams;

import net.katherine.parallelasynchronous.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    @RepeatedTest(5)
    void multiplyEachValue_parallel() {

        int size = 1000000;

        ArrayList<Integer> inputList = DataSet.generateArrayList(size);
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, true);
        assertEquals(size, resultList.size());

    }

    @RepeatedTest(5)
    void multiplyEachValue() {

        int size = 1000000;

        ArrayList<Integer> inputList = DataSet.generateArrayList(size);
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, false);
        assertEquals(size, resultList.size());

    }
}