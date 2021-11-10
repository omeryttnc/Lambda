package my_interface.supplier;

import org.testng.annotations.Test;

import java.util.function.Supplier;

public class test_1 implements Supplier {

    // always return type
    @Test
    public void testName() {
    }

    @Override
    public Object get() {
        return null;
    }
}
