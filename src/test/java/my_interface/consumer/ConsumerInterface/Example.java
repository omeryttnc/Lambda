package my_interface.consumer.ConsumerInterface;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Example {
    public static void main(String[] args) {

        //andThen
        List<String> list = Arrays.asList("Selam", "Merhaba", "Nasilsin");

        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer1 = s -> System.out.println(s.toUpperCase());
        Consumer<String> consumer2 = s -> System.out.println(s + "*");
        list.forEach(consumer.andThen(consumer1).andThen(consumer2));
    }

    @Test
    public void TEST() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        List<WebElement> elements = driver.findElements(By.xpath("//a"));

        Consumer<WebElement> consumer = e -> {
            if (!e.getText().isBlank()) {
                System.out.println(e.getText());
            }
        };
        //elements.forEach(consumer);
        Consumer<WebElement> consumer1 = e -> {
            if (!e.getText().isBlank()) {
                System.out.print(e.getTagName() + " ");
            }
        };
        elements.forEach(consumer1.andThen(consumer));
       // driver.quit();

    }

    @Test
    public void DEMOQA() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");

        WebElement element = driver.findElement(By.id("oldSelectMenu"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        element.click();
        //selectValueFromDropDown(e -> e.selectByIndex(3), element);
        selectValueFromDropDown(e->e.selectByIndex(3),element);


        // selectValueFromDropDown(element,"3","index");
        List<WebElement> options=new Select(driver.findElement(By.id("cars"))).getOptions();
        //options.forEach(e->e.click());
        options.stream().skip(2).forEach(e->e.click());

       // driver.quit();
    }

    private static void selectValueFromDropDown(Consumer<Select> consumer, WebElement element) {
        Select select = new Select(element);
        consumer.accept(select);
    }


//private static void selectValueFromDropDown(WebElement element, String textorValueorIndex, String strategy){
//    Select select=new Select(element);
//    //element.click();
//    if(strategy.equalsIgnoreCase("text")){
//        select.selectByVisibleText(textorValueorIndex);
//    }else if(strategy.equalsIgnoreCase("value")){
//        select.selectByValue(textorValueorIndex);
//    }else if(strategy.equalsIgnoreCase("index")){
//        select.selectByIndex(Integer.parseInt(textorValueorIndex));
//    }else{
//        System.out.println("Invalid strategy");
//    }

//}
}


