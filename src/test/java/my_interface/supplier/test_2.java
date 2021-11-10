package my_interface.supplier;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class test_2 {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //Supplier<List<Integer>> list2 = ()-> Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Supplier<List<Integer>> listSupplier = ()->Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    public void testName() {
        System.out.println(listSupplier.get());
        System.out.println("***************");
        System.out.println(list);
    }
}
