package my_interface;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class combine {
    Predicate<Integer> predicate = t -> t % 2 == 0;                                            //boolean
    Consumer<Integer> consumer = (t) -> System.out.println("data:  " + t);                     //void
    Supplier<List<Integer>> supplierList = () -> Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //return type


    Supplier<List<String>> names =()-> Arrays.asList("omer", "abdulllah", "hasan", "pinar", "erkan", "hatice", "erdal", "selman");
    Supplier<String> warning = ()-> "istenilen ozellik bulunamadi";
    Predicate <String> ikiHarf = t->t.length()==2;
    @Test

    public void testName() {
        System.out.println(names.get().stream().filter(ikiHarf).findAny().orElseGet(warning));
        System.out.println(names.get().stream().filter(t->t.length()==2).findAny().orElseGet(()-> "istenilen ozellik bulunamadi"));

    }
}
