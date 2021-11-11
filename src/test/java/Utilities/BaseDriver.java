package Utilities;


//import Pages.Parent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
Interview soruusu: singleton driver nedir?
Bir projede tek bir driver'ın kullanılmasıdır.
 */

    public class BaseDriver {

        //Her thrad'e özel olacak ve static olduğu için o thread deki bütün classlar aynı driver'ı kullanmış olacak.
        private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>(); // WebDriver1,   WebDriver2
        public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();//chrome,        firefox

        public static WebDriver getDriver() {

            //XML'den gelmeyen tarayıcı adı ile çalışan senaryolar için default setleme yapıldı.
            //default browser olarak chrome atandı.
            if (threadBrowserName.get() == null) {
                threadBrowserName.set("chrome");
            }

            if (threadDriver.get() == null) // bu hatta driver var mı? Bu pipe-line (paralel hatta) driver var mı?
            {
                switch (threadBrowserName.get()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        if (!runningFromIntelliJ()) { //jenkins testi için bunu ekledik, ordan çalışırsa buraya düşecek
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1400,2400"); //width, height
                            threadDriver.set(new ChromeDriver(options));
                        } else {
                            threadDriver.set(new ChromeDriver()); //intellijden çalıştırılan testlerde bunu kullanacak
                        }
                        break;

                    //threadDriver.set(new ChromeDriver());  yorumdan çıkarıp
                    //yukardaki 3 satırı yoruma alıyoruz.

                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        threadDriver.set(new FirefoxDriver());
                        break;
                }
            }
            return threadDriver.get();
        }

        public static void DriverQuit() {
//            Parent.delay(5);

            if (threadDriver.get() != null) {
                threadDriver.get().quit();
                WebDriver driver = threadDriver.get();
                driver = null;
                threadDriver.set(driver);
            }
        }

        public static boolean runningFromIntelliJ() { //intellijden çalıştırılan testlerde bunu kullanacak
            String classPath = System.getProperty("java.class.path");
            return classPath.contains("idea_rt.jar");
        }
    }

