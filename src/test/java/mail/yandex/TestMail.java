package mail.yandex;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestMail {
    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;
    static String theme = "Simbirsoft theme";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://mail.yandex.ru/");
    }

    public void testSetUp() throws MalformedURLException {
        String Node = "http://localhost:4444";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(Node), caps);
    }

    @Test
    public void sendingMessage() throws InterruptedException {
        loginPage.cliclMail();
        loginPage.inputLogin();
        loginPage.clickSubmit();
        loginPage.inputPassword();
        loginPage.clickSubmit();
        mailPage.fiterByTheme(theme);
        mailPage.sortMail();
        var beforeSize = mailPage.getNumSize();
        mailPage.clickNew();
        mailPage.sendAddress();
        mailPage.sendTheme(theme);
        mailPage.sendText("Найдено ");
        mailPage.sendMessage();

        //Проверка изменения числа входящих писем на единицу
        mailPage.setReturnBack();
        mailPage.fiterByTheme("Simbirsoft theme");
        mailPage.sortMail();
        var afterSize = mailPage.getNumSize();
        beforeSize ++;
        Assert.assertEquals("Счетчик не совпадает", afterSize, beforeSize);
    }

    @After
    public void after() {
        driver.quit();

    }
}
