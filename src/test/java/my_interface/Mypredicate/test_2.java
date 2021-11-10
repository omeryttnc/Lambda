package my_interface.Mypredicate;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class test_2 {
    Predicate<Integer> predicate = t -> t % 4 == 0;
    Predicate<Integer> predicate2 = t -> t % 3 == 0;
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //test negate arastir
    @Test
    public void testName() {
        list.stream().filter(predicate).forEach(t -> System.out.println(t));
        System.out.println("************");
        list.stream().filter(predicate).forEach(t -> System.out.println(t));
        System.out.println("************");
        list.stream().filter(predicate.negate()).forEach(t -> System.out.println(t));
        System.out.println("************");

        Predicate<Integer> greater_than = x -> (x > 10);

        // calling test method of the predicate
        System.out.println(greater_than.test(11));

    }

    public static boolean getEven(int number) {
        return number % 2 == 0;
    }

    @Test
    public void test3() {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                System.out.println(list.get(i));
            }
        }
        System.out.println();
        Consumer<Integer> getPrint = (t) -> System.out.print(t + " ");
        Predicate<Integer> preEven = t -> t % 2 == 0;
        Predicate<Integer> preThird = t -> t % 3 == 0;
        Supplier<String> warning = ()-> "hata mesaji aldiniz";
        list.stream().filter(preEven).forEach(getPrint);

        System.out.println();

        list.stream().filter(preEven.and(preThird)).forEach(getPrint);
        System.out.println();
        list.stream().filter(preEven.or(preThird)).forEach(getPrint);
        System.out.println();
        list.stream().filter(preEven.negate()).forEach(getPrint);
        System.out.println();

        Predicate<String> Pre_email = email-> email.contains("yahoo");
        List<String> allmail = Arrays.asList("asdasd@gmail","asdsad.hotmail","asdsadasd@gmail");

        System.out.println(allmail.stream().filter(Pre_email).findAny());
        System.out.println();
        System.out.println(allmail.stream().filter(Pre_email).findAny().orElseGet(warning));
        Predicate<Integer> greater_than = x -> (x > 10);
        System.out.println(greater_than.test(12));
        System.out.println();
        System.out.println(greater_than.test(8));
        System.out.println();
        System.out.println(preEven.test(57));


    }
}
