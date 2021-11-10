package my_interface.unaryOperator;

import jdk.dynalink.linker.LinkerServices;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class test_1 {
    //one argument return same typeof argument
    //function a cok benziyor burda sadece same argument olma zorunlulugu var

    @Test
    public void testName() {
        UnaryOperator<Integer> func = x->x*7;

        System.out.println(func.apply(10));

        Function<Integer,Integer> func2 = x->x*7;
        System.out.println(func2.apply(10));

        //replace all
        UnaryOperator<String> javaEkle = t-> t+" java" ;


        List<String> list = Arrays.asList("omer","ali");
        list.replaceAll(javaEkle);
        System.out.println(list);

    }
}
