package net.katherine.parallelasynchronous.forkjoin;

import net.katherine.parallelasynchronous.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static net.katherine.parallelasynchronous.util.CommonUtil.delay;
import static net.katherine.parallelasynchronous.util.CommonUtil.stopWatch;
import static net.katherine.parallelasynchronous.util.LoggerUtil.log;


public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        resultList = forkJoinPool.invoke(forkJoinUsingRecursion);
        log("names : "+ names);

//        names.forEach((name)->{
//            String newValue = addNameLengthTransform(name);
//            resultList.add(newValue);
//        });
        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {
        if(this.inputList.size() <= 1) {
            List<String> resultList = new ArrayList<String>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
            return resultList;
        }
        int midPoint = inputList.size()/2;
        ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, midPoint)).fork();
        inputList = inputList.subList(midPoint, inputList.size());
        List<String> rightResult = compute();// recursion happens
        List<String> leftResult = leftInputList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }
}
