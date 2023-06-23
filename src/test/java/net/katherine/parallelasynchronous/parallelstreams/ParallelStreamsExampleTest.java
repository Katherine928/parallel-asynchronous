package net.katherine.parallelasynchronous.parallelstreams;

import net.katherine.parallelasynchronous.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void stringTransform() {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamsExample.StringTransform(inputList);
        timeTaken();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(
                name -> assertTrue(name.contains("-"))
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransform_1(boolean isParallel) {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamsExample.StringTransform_1(inputList, isParallel);
        timeTaken();
        stopWatchReset();

        //then
        assertEquals(4, resultList.size());
        resultList.forEach(
                name -> assertTrue(name.contains("-"))
        );
    }
}