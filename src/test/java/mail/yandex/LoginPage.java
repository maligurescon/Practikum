package mail.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Определение кнопки входа в почту
    @FindBy(xpath = "//div[@class = 'HeadBanner-ButtonsWrapper']//span[contains (text(), 'Войти')]//ancestor::a")
    private WebElement mail;

    //Определение поля ввода логина электронной почты
    @FindBy(xpath = "//input[@type = 'text']")
    public WebElement loginMail;

    //Определение кнопки подтверждения введенных данных
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement buttonSubmit;

    //Определение поля ввода пароля электронной почты
    @FindBy(xpath = "//input[@class = 'Textinput-Control' and @name = 'passwd']")
    public WebElement passwordMail;

    @Step
    //Вход в окно авторизации
    public void cliclMail() {
        mail.click();
    }

    @Step
    public void inputLogin() {
        this.loginMail.sendKeys(PropertyManager.getInstance().getAddressmail());
    }

    @Step
    public void inputPassword() {
        this.passwordMail.sendKeys(PropertyManager.getInstance().getPassword());
    }

    @Step
    //Нажатие кнопки подтверждения данных
    public void clickSubmit(){
        buttonSubmit.click();
    }
}


