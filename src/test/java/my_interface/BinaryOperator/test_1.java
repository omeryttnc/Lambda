package my_interface.BinaryOperator;

import org.testng.annotations.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public class test_1 {

    @Test
    public void testName() {

        // iki argument olmasi lazim
        BinaryOperator<Integer> func = (x1, x2) -> x1 + x2;

        System.out.println(func.apply(24, 23));

        // iki argument ve return types
        BiFunction<String, String, Integer> func1 = (x1, x2) -> (x1 + x2).length();

        System.out.println(func1.apply("omer", "ali"));
    }

    @Test
    public void test2() {
        BinaryOperator<Integer> B_toplama = (x, y) -> x + y;
        System.out.println(B_toplama.apply(2, 5));

        BiFunction<String, Integer, Integer> b_isim_uzunlugunu_dort_ile_carp = (x, y) -> x.length() * y;
        System.out.println(b_isim_uzunlugunu_dort_ile_carp.apply("erkanabi", 5));

        //String uzunlugu belir bir degerden buyuk mu?
        BiPredicate<String, Integer> b_uzunluk_buyuk_mu = (x, y) -> x.length() > y;
        System.out.println(b_uzunluk_buyuk_mu.test("abdullahabi", 3));

        BiConsumer<String,String> b_concat = (x,y)-> System.out.println(x+y);
        b_concat.accept("omer ","ali");


        conc("omer ","ali");
        }
         void conc (String str1 , String str2){
            System.out.println(str1+ str2);
    }
}
