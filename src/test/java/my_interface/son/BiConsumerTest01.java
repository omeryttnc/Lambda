package my_interface.son;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerTest01 {
    //@FunctionalInterface
    //public interface BiConsumer<T, U>
    // void accept(T t, U u);
    // V put(K key, V value);
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        //close case
        BiConsumer<String, Integer> biConsumer = map::put;

        //open case
        //BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        putMap("java", 8, biConsumer);
        putMap("biConsumer", 2, biConsumer);
        biConsumer.accept("java..", 8);
        biConsumer.accept("biConsumer..", 2);
        System.out.println(map);

    }

    static void putMap(String key,Integer value, BiConsumer<String, Integer> biConsumer){
        biConsumer.accept(key,value);
    }
}
/**
 {java=8, biConsumer..=2, biConsumer=2, java..=8}
 */