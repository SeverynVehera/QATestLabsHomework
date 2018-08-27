package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import myprojects.automation.assignment2.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckMainMenuTest extends BaseScript{

    private  static WebDriver driver = getDriver();
    private static  List<WebElement> menuItems;
    public static void main(String[] args)  {

        String login = System.getenv("PRESTA_LOGIN");
        String password = System.getenv("PRESTA_PASSWD");

        driver.manage().window().maximize();
        driver.navigate().to(Properties.getBaseAdminUrl());

        initializeWebElements();

        setLoginFieldText(login);

        setPasswordFieldText(password);

        getButtonLogin().click();

        try {
            setThreadWait(1000);
        }
        catch (InterruptedException e){
            e.getMessage();
        }

        initMainMenuItems();

        for (int i = 0; i < 13; i++){
            getMainMenuItems(i).click();
            try {
                setThreadWait(1000);
            }
            catch (InterruptedException e){
                e.getMessage();
            }
            System.out.println(driver.getTitle());
            String expected = driver.getTitle();
            driver.navigate().refresh();
            try {
                setThreadWait(1000);
            }
            catch (InterruptedException e){
                e.getMessage();
            }
            String actual = driver.getTitle();
            if(equalsObjects(actual,expected)) {
                System.out.println("The user stays at the same page after refresh");
            } else {
                System.out.println("The opened page is not as expected, after refresh");            }
            initMainMenuItems();
        }

        driver.quit();
    }

    private static void initializeWebElements(){
        getLoginField();
        getPasswordField();
        getButtonLogin();
    }

    private static void initMainMenuItems(){
        menuItems = new ArrayList<>();
        //The "Каталог" and "Modules" sections are located by the "link-levelone" class
        // while other by the "maintab" class
        menuItems.addAll(driver.findElements(By.className("link-levelone")));
        menuItems.addAll(driver.findElements(By.className("maintab")));
    }
    // ***Login Field***
    private static WebElement getLoginField(){
        return driver.findElement(By.id("email"));
    }
    private static void setLoginFieldText(String text){
        getLoginField().click();
        getLoginField().clear();
        getLoginField().sendKeys(text);
    }

    // ***Password Field***
    private static WebElement getPasswordField(){
        return driver.findElement(By.id("passwd"));
    }

    private static void setPasswordFieldText(String text){
        getPasswordField().click();
        getPasswordField().clear();
        getPasswordField().sendKeys(text);
    }

    // ***Login Button***
    private static WebElement getButtonLogin(){
        return driver.findElement(By.name("submitLogin"));
    }

    // ***Main Menu Items***
    private static List<WebElement> getMainMenuItems(){
        return menuItems;
    }

    private static WebElement getMainMenuItems(int i){
        return getMainMenuItems().get(i);
    }

}
