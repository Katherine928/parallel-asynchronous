package net.katherine.parallelasynchronous.parallelstreams;

import java.util.List;
import java.util.stream.Collectors;

import static net.katherine.parallelasynchronous.util.LoggerUtil.log;

public class ParallelstreamResultOrder {

    public static List<Integer> listOrder(List<Integer> inputList) {
        return inputList.parallelStream()
                .map(integer -> integer*2)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {

        List<Integer> inputList = List.of(1,2,3,4,5,6,7,8);
        log("inputList: " + inputList);
        List<Integer> result = listOrder(inputList);
        log("result: " + result);

    }

}
