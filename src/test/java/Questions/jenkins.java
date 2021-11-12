package Questions;

import Utilities.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class jenkins {
    @Test(groups = "smoke")
    public void tst(){
        BaseDriver.getDriver().get("https://www.w3schools.com/java/java_modifiers.asp");
        Assert.assertEquals("Java Modifiers",BaseDriver.getDriver().getTitle());
    }
}
