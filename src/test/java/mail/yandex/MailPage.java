package mail.yandex;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {

    public WebDriver driver;
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //Определение поля фильтрации сообщений
    @FindBy(xpath = "//input[@class='textinput__control']")
    private WebElement filterMail;

    //Определение кнопки фильтра "Папки"
    @FindBy(xpath = "//span[contains (text(),'Папки')]//ancestor::button")
    private WebElement foldersMail;

    //Определение кнопки "Входящие" в фильтрации поиска
    @FindBy(xpath = "//span[@class = 'menu__text' and text()='Входящие']")
    private WebElement inputMail;

    //Определение поля, в котором содержится инфомация по количеству сообщений
    @FindBy(xpath = "//span[@class = 'mail-MessagesSearchInfo-Title']//span")
    private WebElement countMale;

    //Определение кнопки "Написать" для создания нового сообщения
    @FindBy(xpath = "//span[contains (text(), 'Написать')]//ancestor::a")
    private WebElement newMail;

    //Определение поля для ввода адресата
    @FindBy(xpath = "//div[contains(@class, 'tst-field-to')]//div[@is= 'x-bubbles']")
    private WebElement addressMail;

    //Определение поля для ввода темы сообщения
    @FindBy(xpath = "//input[@name= 'subject']")
    private WebElement themeMail;

    //Определение поля ввода сообщения
    @FindBy(xpath = "//div[@role = 'textbox']")
    private WebElement textMail;

    //Определение поля написания письма
    @FindBy(xpath = "//div//[@class = 'composeReact__scrollable-content']")
    private WebElement writeMail;

    //Определение кнопки "Отправить"
    @FindBy(xpath = "//span[contains (text(), 'Отправить')]//ancestor::button")
    private WebElement sendMail;

    //Определение кнопки "Вернуться во входящие"
    @FindBy(xpath = "//a[@class = 'control link link_theme_normal ComposeDoneScreen-Link']")
    private WebElement returnBack;

    //Метод для получения количества сообщений
    public String getSizeMail(){
        return countMale.getText();
    }

    //Получение числа из количества сообщений
    public Integer getNumSize(){
        return Integer.parseInt(getSizeMail().split(" ")[0]);
    }

    @Step
    //Ввод значения для фильтрации
    public void fiterByTheme(String inputTheme) {
        filterMail.sendKeys(inputTheme);
        filterMail.submit();
    }

    @Step
    //Сортировка писем по "Входящим"
    public void sortMail(){
        foldersMail.click();
        inputMail.click();
    }

    @Step
    //Нажатие кнопки "Написать
    public void clickNew() {
        newMail.click();
    }

    @Step
    public void sendAddress() {
        addressMail.sendKeys(PropertyManager.getInstance().getAddressmail());
    }

    //Ввод темы сообщения
    @Step
    public void sendTheme(String inputTheme){
        themeMail.sendKeys(inputTheme);
    }

    //ввод текста сообщения
    @Step
    public void sendText(String inputText){
        textMail.sendKeys(inputText, getSizeMail());
    }

    @Step
    //Нажатие кнопки отправить
    public void sendMessage(){
        sendMail.click();
    }

    @Step
    //Вернуться во входящие
    public void setReturnBack() {
        returnBack.click();
    }




}

