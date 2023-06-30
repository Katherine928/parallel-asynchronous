package net.katherine.parallelasynchronous.parallelstreams;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;

public class LinkedListSpliteratorExample {


    public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParallel) {

        startTimer();
//        Stream<Integer> integerStream = inputList.stream(); // sequential
        Stream<Integer> integerStream = isParallel? inputList.stream().parallel() : inputList.stream();
        List<Integer> resultList = integerStream
                .map(integer -> integer*multiplyValue)
                .toList();
        timeTaken();
        stopWatchReset();
        return resultList;
    }

}
