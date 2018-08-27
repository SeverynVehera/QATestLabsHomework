package myprojects.automation.assignment2.tests;

import myprojects.automation.assignment2.BaseScript;
import myprojects.automation.assignment2.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseScript {

    private  static  WebDriver driver = getDriver();
    public static void main(String[] args) {

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

        getProfileImage().click();

        getHeaderLogout().click();

        driver.quit();
    }

    private static void initializeWebElements(){
        getLoginField();
        getPasswordField();
        getButtonLogin();
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

    // ***Profile Image***
    private static WebElement getProfileImage(){
        return driver.findElement(By.className("img-thumbnail"));
    }

    // ***Header Logout***
    private static WebElement getHeaderLogout(){
        return driver.findElement(By.id("header_logout"));
    }

}
