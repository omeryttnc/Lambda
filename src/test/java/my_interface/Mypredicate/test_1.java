package my_interface.Mypredicate;

import org.testng.annotations.Test;

import java.util.function.Predicate;

public class test_1 implements Predicate<Integer> {

    // return boolean
    @Test
    public void testName() {
    }

    @Override
    public boolean test(Integer integer) {
        return false;
    }

    @Override
    public Predicate<Integer> and(Predicate<? super Integer> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<Integer> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<Integer> or(Predicate<? super Integer> other) {
        return Predicate.super.or(other);
    }
}
