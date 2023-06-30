package net.katherine.parallelasynchronous.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;

public class ArrayListSpliteratorExample {

    public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue, boolean isParallel) {

        startTimer();
//        Stream<Integer> integerStream = inputList.stream(); // sequential
//                if(isParallel) {
//            integerStream.parallel();
//        }

        Stream<Integer> integerStream = isParallel? inputList.stream().parallel() : inputList.stream();

        List<Integer> resultList = integerStream
                .map(integer -> integer*multiplyValue)
                .toList();
        timeTaken();
        stopWatchReset();
        return resultList;
    }

}
