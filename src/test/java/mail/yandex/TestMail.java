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
import java.util.concurrent.TimeUnit;

public class TestMail {
    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;

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
        /* The execution happens on the Selenium Grid with the address mentioned earlier */
        driver = new RemoteWebDriver(new URL(Node), caps);
    }

    @Test
    public void sendingMessage() throws InterruptedException {
        loginPage.cliclMail();
        loginPage.inputLogin();
        loginPage.clickSubmit();
        loginPage.inputPassword();
        loginPage.clickSubmit();
        mailPage.fiterByTheme();
        mailPage.sortMail();
        //mailPage.getSizeMail();
        Thread.sleep(1000);
        String beforeSize = mailPage.getSizeMail().split(" ")[0];
        mailPage.clickNew();
        mailPage.inputAddress();
        mailPage.inputeTheme();
        mailPage.message();
        mailPage.sendMessage();

        //Проверка изменения числа входящих писем на единицу
        mailPage.setReturnBack();
        mailPage.fiterByTheme();
        mailPage.sortMail();
        Thread.sleep(1000);
        String afterSize = mailPage.getSizeMail().split(" ")[0];
        Integer countBefore = Integer.valueOf(beforeSize);
        countBefore += 1;
        String actualCount = String.valueOf(countBefore);
        Assert.assertEquals(afterSize, actualCount);
    }

        @After
        public void after() {
        driver.quit();

    }
}
