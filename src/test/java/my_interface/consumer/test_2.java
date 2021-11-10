package my_interface.consumer;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class test_2 {
    Consumer<Integer> consumer = (t) -> System.out.println("data:  " + t);
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test

    public void name() {
        consumer.accept(10);
        list.stream().forEach(consumer);
    }
}
