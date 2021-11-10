package my_interface.function;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.function.Function;

public class test_1 {
    @Test
    public void testName() {
        Function<String,Integer> length = x-> x.length();


        System.out.println(length.apply("omer ali"));

        //chain
        Function<Integer,Integer> mult = x-> x*5;

        System.out.println(length.andThen(mult).apply("omer ali"));


    }

    @Test
    public void test2() {

        Function<String,Integer> funLength = x->x.length();
        System.out.println(funLength.apply("omer"));

        Function<Integer,Integer> lengthTimesFour = t->t*4;

        WebElement webElement;
        String string;
        Function<WebElement,String > getTextFu= t->t.getAttribute("value");
        System.out.println();
        System.out.println(funLength.andThen(lengthTimesFour).apply("omer"));

getTextFu.apply()
    }
}
