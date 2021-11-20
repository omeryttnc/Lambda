import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class erdal_bey {

    @Test
    public void testName() {
        List<Integer> l_Integer = new ArrayList<>();
        l_Integer.add(12);
        l_Integer.add(9);
        l_Integer.add(13);
        l_Integer.add(4);
        l_Integer.add(6);
        l_Integer.add(2);
        l_Integer.add(4);
        l_Integer.add(12);
        l_Integer.add(15);
        List<String> l_String = new ArrayList<>();
        l_String.add("Alia");  // -> 3
        l_String.add("Mark");  // ->4
        l_String.add("Jackson"); //->7
        l_String.add("Amanda");
        l_String.add("Chrisa");
        l_String.add("Tuckera");


        //structure programming

        // 2 ile bolunebilen
        List<Integer> new_list = new ArrayList<>();
        for (int i = 0; i < l_Integer.size(); i++) {
            if (l_Integer.get(i) % 2 == 0) {
//             new_list.add(l_Integer.get(i));
                System.out.println(l_Integer.get(i));
            }
        }
        System.out.println();
        // functional programing
        // if -> filter
        // for -> foreach
        l_Integer.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.print(t + " : "));

        //uzunlugu 4den kucuk olanlari yazdir
        System.out.println();
        l_String.stream().filter(t -> t.length() < 4).forEach(t -> System.out.print(t + " : "));

        // class method iliskisi
        System.out.println();
        System.out.println();
        l_String.stream().filter(t -> t.length() < 4).forEach(erdal_bey::getPrint);
        System.out.println();
        //2 way
        l_String.stream().filter(t -> t.length() < 4).forEach(System.out::print);


        // structure
        // isimlerin uzunluklarini
        for (int i = 0; i < l_String.size(); i++) {
            System.out.println(l_String.get(i).length());
        }

        //functional
        // update -> map
        System.out.println();
        l_String.stream().map(t -> t.length()).forEach(erdal_bey::getPrint);

        //2 yol
        l_String.stream().map(String::length).forEach(erdal_bey::getPrint);

        // isimlerin sonuna bey ekleyelim

        l_String.stream().map(t -> t.concat(" bey")).forEach(erdal_bey::getPrint);

        // 2 way

        l_String.stream().forEach(t -> System.out.println(t + " bey"));


        // veri toplamak istersek -> collect
        // isim uzunluklari 4 den buyuk olanlari listeye ekle
        // ctrl alt V

        // to list
        List<String> collect = l_String.stream().filter(t -> t.length() > 4).collect(Collectors.toList());

        //to Set
        Set<String> collect_set = l_String.stream().filter(t -> t.length() > 4).collect(Collectors.toSet());

        // to Map
        // isimler ve onlari uzunluklari
        Map<String, Integer> collect1 = l_String.stream().collect(Collectors.toMap(x -> x, String::length));

        System.out.println();
        System.out.println(collect1);

        // siralamak icin -> sorted
        System.out.println();
        l_Integer.stream().sorted(Comparator.naturalOrder()).forEach(erdal_bey::getPrint);

        //
        System.out.println();
        l_Integer.stream().sorted(Comparator.reverseOrder()).forEach(erdal_bey::getPrint);

        // son harfine gore sirala
        System.out.println();
        l_String.stream().sorted(Comparator.comparing(t -> t.charAt(t.length() - 1))).forEach(erdal_bey::getPrint);

        //duplicate leri kaldir -> distinct
        System.out.println();
        l_Integer.stream().distinct().sorted(Comparator.reverseOrder()).forEach(erdal_bey::getPrint);

        //sayi belirleme
        long count = l_String.stream().filter(t -> t.length() > 4).count();

        //skip
        l_String.stream().filter(t -> t.length() > 4).skip(2).forEach(erdal_bey::getPrint);

        //limit
        l_String.stream().filter(t -> t.length() > 4).limit(4).forEach(erdal_bey::getPrint);

        //Match -> return boolean

        //anyMatch  -> herhangi
        //allMatch  -> hepsi
        //noneMatch -> hicbiri

        //erdal ismi var mi
        // Assert.assertTrue(l_String.stream().noneMatch(t -> t.equals("erdal")));

        // uzunlugu 4 den kucuk olan var mi?
        // Assert.assertTrue(l_String.stream().anyMatch(t -> t.length() < 4));

        //hepsinde a harfi var mi?
        //Assert.assertTrue(l_String.stream().allMatch(t -> t.contains("a")));

        //reduce -> toplama islemi

        // butun sayilari topla
        Integer integer = l_Integer.stream().reduce((x, y) -> x + y).get();

        //findAny
        // Assert.assertTrue(l_String.stream().filter(t -> t.length() > 1).findAny().isPresent());

        // 2 way
        System.out.println();
        Optional<String> a = l_String.stream().filter(t -> t.length() > 1).findAny();
        System.out.println(a);

        //dropwhile takewhile
        System.out.println();
        System.out.println(l_String);
        System.out.println("dropwhile");
        System.out.println();
        l_String.stream().dropWhile(t -> t.length() < 4).forEach(erdal_bey::getPrint);
        System.out.println("takewhile");
        l_String.stream().takeWhile(t -> t.length() < 4).forEach(erdal_bey::getPrint);



        //interface
        //Function -> map
        Function<String, Integer> f_getLength = (x) -> x.length();
        Function<Integer, Integer> f_times_four = (x) -> x * 4;

        //Consumer -> void data type -> forEach
        Consumer<Object> c_getPrint = (x) -> System.out.print(x + " ; ");

        //Predicate -> boolean -> filter
        Predicate<String> p_length_dortden_kucuk = (t) -> t.length() < 4;
        Predicate<String> p_length_altidan_buyuk = (t) -> t.length() > 6;
        l_String.stream().filter(p_length_dortden_kucuk).forEach(c_getPrint);
        l_String.stream().filter(p_length_dortden_kucuk.negate()).forEach(c_getPrint); //olumsuzunu
        l_String.stream().filter(p_length_dortden_kucuk.and(p_length_altidan_buyuk)).forEach(c_getPrint); //ve
        l_String.stream().filter(p_length_dortden_kucuk.or(p_length_altidan_buyuk)).forEach(c_getPrint); // veya
        Assert.assertFalse(p_length_dortden_kucuk.test("omer")); //test

        //Supplier
        Supplier<String> s_name = () -> "omer";
        Supplier<String> s_warning = () -> "hata yaptin";
        System.out.println(l_String.stream().filter(p_length_altidan_buyuk).findAny().orElseGet(s_warning));
        System.out.println(l_String.stream().filter(p_length_altidan_buyuk).findAny().orElse("bir yerde hata var"));
        s_name.get();

        //single function
        l_String.stream().map(f_getLength).forEach(erdal_bey::getPrint);

        //multiple function
        l_String.stream().map(f_getLength.andThen(f_times_four)).forEach(c_getPrint);


    }

    static void getPrint(Object erdal) {
        System.out.print(erdal + " : ");
    }
}
