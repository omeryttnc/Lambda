package my_interface.consumer;

import org.testng.annotations.Test;

import java.util.function.Consumer;

public class test_1 implements Consumer<Integer> {
    //no return -> void
    // onyl accept



    @Override
    public void accept(Integer integer) {

    }
}
