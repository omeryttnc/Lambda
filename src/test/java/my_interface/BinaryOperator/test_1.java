package my_interface.BinaryOperator;

import org.testng.annotations.Test;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class test_1 {

    @Test
    public void testName() {

    // iki argument olmasi lazim
        BinaryOperator<Integer> func = (x1,x2)-> x1+x2;

        System.out.println(func.apply(24, 23));

        // iki argument ve return types
        BiFunction<String,String,Integer> func1 = (x1,x2)->(x1+x2).length();

        System.out.println(func1.apply("omer", "ali"));
    }
}
