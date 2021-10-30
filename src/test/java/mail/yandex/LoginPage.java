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
    @FindBy(xpath = "//a[@class = 'control button2 button2_view_classic button2_size_mail-big button2_theme_mail-white button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")
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


