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
    private WebElement loginMail;

    //Определение кнопки подтверждения введенных данных
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement buttonSubmit;

    //Определение поля ввода пароля электронной почты
    @FindBy(xpath = "//input[@class = 'Textinput-Control' and @name = 'passwd']")
    private WebElement passwordMail;

    @Step
    //Вход в окно авторизации
    public LoginPage cliclMail() {
        mail.click();
        return this;
    }

    @Step
    public LoginPage inputLogin(String getInputAddress){
        loginMail.sendKeys(getInputAddress);
        return this;
    }
    @Step
    public LoginPage inputPassword(String getInputPassord) {
        this.passwordMail.sendKeys(getInputPassord);
        return this;
    }

    @Step
    //Нажатие кнопки подтверждения данных
    public LoginPage clickSubmit(){
        buttonSubmit.click();
        return this;
    }
}


