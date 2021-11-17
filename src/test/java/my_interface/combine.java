package my_interface;

import Utilities.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class combine {
    Predicate<Integer> predicate = t -> t % 2 == 0;                                            //boolean
    Consumer<Integer> consumer = (t) -> System.out.println("data:  " + t);                     //void
    Supplier<List<Integer>> supplierList = () -> Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //return type

    BiPredicate<Integer, Integer> biPredicate = (integer, integer2) -> integer + integer2 > 0;


    Function<String, Integer> getLength = (x) -> x.length();


    Supplier<List<String>> names = () -> Arrays.asList("omer", "abdulllah", "hasan", "pinar", "erkan", "hatice", "erdal", "selman");
    Supplier<String> warning = () -> "istenilen ozellik bulunamadi";
    Predicate<String> ikiHarf = t -> t.length() == 2;


    Random random = new Random();
    String[] str_trade = {"TRADE", "NONTRADE"};
    String[] str_organic = {"TRADE", "NONTRADE"};
    int number = random.nextInt(2);  //0 1
    Supplier<String> S_Random_isTrade = () -> str_trade[number];
    Supplier<String> S_Random_isOrganic = () -> str_organic[number];


    Predicate<String> is_false = (t) -> t.equals("false");

    @Test
    public void testName() {
        System.out.println(biPredicate.test(32, 32));
        System.out.println(names.get().stream().filter(ikiHarf).findAny().orElseGet(warning));
        System.out.println(names.get().stream().filter(t -> t.length() == 2).findAny().orElseGet(() -> "istenilen ozellik bulunamadi"));

    }

    @Test
    public void test1() {
        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Supplier<String> CurrentDateAndTime = () -> formatter.format(date);

        System.out.println(CurrentDateAndTime.get());
    }

    @Test
    public void test2() {

        List<String> allOptions = Arrays.asList("true", "true", "false", "false", "true", "true", "false", "false", "true", "true", "false");

        // check if all true or false
        Assert.assertTrue(allOptions.stream().anyMatch(is_false));
        Assert.assertTrue(allOptions.stream().filter(is_false).findAny().isPresent());


        System.out.println(Random_isTrade());
        System.out.println(S_Random_isTrade.get());

    }

    public static String Random_isTrade() {
        Random random = new Random();
        String[] str = {"trade", "nontrade"};
        int number = random.nextInt(2);  //0 1
        return str[number];
    }

    public static String Random_isOrganic() {
        Random random = new Random();
        String[] str = {"organic", "nonOrrganic"};
        int number = random.nextInt(2);  //0 1
        return str[number];
    }

    BiConsumer<WebElement, Integer> Bi_selectByIndex = (webElement, integer) -> {
        Select city = new Select(webElement);
        city.selectByIndex(integer);
    };

    public static void selectByIndex(WebElement webElement, int index) {
        Select city = new Select(webElement);
        city.selectByIndex(index);
    }


    @Test
    public void test4() {


        List<WebElement> elements = BaseDriver.getDriver().findElements(By.xpath("//div[@class='row m-0 p-2 ']/a/span"));

        Assert.assertTrue(elements.stream().noneMatch(Pre_isVisible));

        for (int i = 0; i < elements.size(); i++) {
            Assert.assertTrue(isElementVisible(elements.get(i)));
        }
    }

    Predicate<WebElement> Pre_isVisible = (t) -> {
        try {
            t.isDisplayed();
            return true;
        } catch (NoSuchElementException var2) {
            return false;
        }
    };

    public static boolean isElementVisible(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException var2) {
            System.out.println(webElement + " is not found");
            return false;
        }
    }
}


