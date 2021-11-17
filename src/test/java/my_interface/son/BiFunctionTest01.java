package my_interface.son;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionTest01 {
        // public interface BiFunction<T, U, R> {
        // R apply(T t, U u);
        public static void main(String[] args) {

            //open case
            BiFunction<String, String, String> biFunction1 = (String x, String y) -> x.concat(y);

            System.out.println("-------------------Example 01--------------------------");
            //short case
            BiFunction<String, String, String> biFunction2 = String::concat;

            System.out.println(biFunction2.apply("java8 ", "biFunction"));

            System.out.println("-------------------Example 02--------------------------");

            BiFunction<Double, Double, Integer> compareDoubles = Double::compare;

            System.out.println(compareDoubles.apply(25.0, 25.0));

            System.out.println("-------------------Example 03--------------------------");

            String stringResult = powToString(2,4,
                    (int1, int2)->Math.pow(int1, int2),
                    x->x.toString());
            System.out.println(stringResult);
        }

        public static String powToString(Integer integer1, Integer integer2,
                                         BiFunction<Integer, Integer, Double> biFunction,
                                         Function<Double, String> function) {

            return biFunction.andThen(function).apply(integer1, integer2);

        }
    }
/**
 -------------------Example 01--------------------------
 java8 biFunction
 -------------------Example 02--------------------------
 0
 -------------------Example 03--------------------------
 16.0
 */

