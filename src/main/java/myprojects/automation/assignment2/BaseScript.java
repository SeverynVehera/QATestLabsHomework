package myprojects.automation.assignment2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    protected BaseScript(){

    }
    /**
     *
     * @return New instance of {@link WebDriver} object.
     */
    protected static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource("chromedriver.exe").getPath();
        if (driverPath == null) {
            System.out.println("Path to driver does not specified");
        } else {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
        return new ChromeDriver();
    }

    protected static void setThreadWait(int miliseconds) throws InterruptedException {
        Thread.sleep(miliseconds);
    }

    protected static boolean equalsObjects(Object a, Object b) {
        return  a.equals(b);
    }

}
