package net.katherine.parallelasynchronous.parallelstreams;

import net.katherine.parallelasynchronous.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;
import static net.katherine.parallelasynchronous.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public List<String> StringTransform(List<String> namesList) {
       return namesList
               //.stream()
               .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> StringTransform_1(List<String> namesList, boolean isParallel) {
        Stream<String> namesStream = namesList.stream();
        if(isParallel) {
            namesStream.parallel();
        }
        return namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }
    public static void main(String[] args){
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = parallelStreamsExample.StringTransform(namesList);
        log("resultList: " + resultList);
        timeTaken();
    }


    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

}
