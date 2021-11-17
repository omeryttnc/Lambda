package my_interface.function;

import Utilities.BaseDriver;
import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test_1 {
    @Test
    public void testName() {
        Function<String, Integer> length = x -> x.length();


        System.out.println(length.apply("omer ali"));

        //chain
        Function<Integer, Integer> mult = x -> x * 5;

        System.out.println(length.andThen(mult).apply("omer ali"));


    }

    @Test
    public void test2() {

        Function<String, Integer> funLength = x -> x.length();
        System.out.println(funLength.apply("omer"));

        Function<Integer, Integer> lengthTimesFour = t -> t * 4;

        WebElement webElement;
        String string;
        Function<WebElement, String> getTextFu = t -> t.getAttribute("value");
        System.out.println();
        System.out.println(funLength.andThen(lengthTimesFour).apply("omer"));

//getTextFu.apply();
    }


    @Test
    public void test3() {
        List<WebElement> elements = BaseDriver.getDriver().findElements(By.xpath("//div[@class='row m-0 p-2 ']/a/span"));

        String str = "tomatoes";
        Function<WebElement, String> F_getAttribute = (t) -> t.getAttribute("value");
        Predicate<String> P_str_var_mi = (t)->t.equals(str);

        for (int i = 0; i < elements.size(); i++) {
            Assert.assertTrue(elements.get(i).getAttribute("value").equals(str));
        }

        /// webelementler icerisinde domates var mi ?

        Assert.assertTrue(elements.stream().anyMatch(t -> t.getAttribute("value").equals(str)));

        long collect = elements.stream().map(F_getAttribute).filter(P_str_var_mi).count();
        Predicate<WebElement> Pre_isVisible = (t) -> {
            try {
                t.isDisplayed();
                return true;
            } catch (NoSuchElementException var2) {
                return false;
            }
        };


    }
}
