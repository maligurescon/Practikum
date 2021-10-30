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
        loginPage.loginMail.sendKeys(PropertyManager.getInstance().getAddressmail());
        loginPage.clickSubmit();
        loginPage.passwordMail.sendKeys(PropertyManager.getInstance().getPassword());
        loginPage.clickSubmit();
        mailPage.filterMail.sendKeys("Simbirsoft theme");
        mailPage.filterMail.submit();
        int beforeSize = Integer.parseInt(mailPage.getSizeMail().split(" ")[0]);
        Boolean runCycle = true;
        var startTime = new Date().getTime();
        while(runCycle) {
            mailPage.filterMail.clear();
            mailPage.filterMail.submit();
            mailPage.filterMail.sendKeys("Simbirsoft theme");
            mailPage.filterMail.submit();
            mailPage.sortMail();
            int currentSize = Integer.parseInt(mailPage.getSizeMail().split(" ")[0]);
            if((new Date().getTime() - startTime) < 30 || currentSize != beforeSize ) {
                runCycle = false;
            }
            beforeSize = currentSize;
        }
        mailPage.clickNew();
        mailPage.addressMail.sendKeys(PropertyManager.getInstance().getAddressmail());
        mailPage.themeMail.sendKeys("Simbirsoft theme");
        mailPage.textMail.sendKeys("Найдено ", mailPage.getSizeMail());
        mailPage.sendMessage();

        //Проверка изменения числа входящих писем на единицу
        mailPage.setReturnBack();
        mailPage.filterMail.sendKeys("Simbirsoft theme");
        mailPage.filterMail.submit();
        int afterSize = Integer.parseInt(mailPage.getSizeMail().split(" ")[0]);
        runCycle = true;
        while(runCycle) {
            mailPage.filterMail.clear();
            mailPage.filterMail.submit();
            mailPage.filterMail.sendKeys("Simbirsoft theme");
            mailPage.filterMail.submit();
            mailPage.sortMail();
            int currentSize = Integer.parseInt(mailPage.getSizeMail().split(" ")[0]);
            if((new Date().getTime() - startTime) < 30 || currentSize != afterSize ) {
                runCycle = false;
            }
            afterSize = currentSize;
        }
        beforeSize ++;
        Assert.assertEquals("Счетчик не совпадает", afterSize, beforeSize);
    }

        @After
        public void after() {
        driver.quit();

    }
}
